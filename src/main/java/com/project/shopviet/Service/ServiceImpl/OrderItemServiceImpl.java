package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.DTO.Status;
import com.project.shopviet.DTO.request.OrderRequest;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.*;
import com.project.shopviet.Repository.*;
import com.project.shopviet.Service.EmailSenderService;
import com.project.shopviet.Service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private ConsigneeRepository consigneeRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;


    @Override
    public ResponseObject addToOrderItem(OrderRequest orderRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userBuyer = userRepository.findUserByName(authentication.getName());
            Consignee consignee = consigneeRepository.findById(orderRequest.getConsigneeId()).get();
            orderRequest.getProducts().stream().forEach(productDto -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(productRepository.findById(productDto.getProduct_id()).get());
                orderItem.setUser(userBuyer);
                orderItem.setConsignee(consignee);
                orderItem.setProductType(productTypeRepository.findById(productDto.getProduct_type_id()).get());
                orderItem.setCreatedAt(new Date());
                orderItem.setStatus(Status.Pending.getOrderStatus());
                orderItemRepository.save(orderItem);
            });
            emailSenderService.sendEmailActive(userBuyer.getEmail(), "Thông báo xác thực đơn hàng #" + " ShopViet thành công!!", "Đơn hàng của bạn đang được xử lý");

            return ResponseObject.builder()
                    .code(200)
                    .message("Success")
                    .build();
        } catch (Exception e) {
            return ResponseObject.builder()
                    .code(500)
                    .message("Fail")
                    .build();
        }
    }

    @Override
    public ResponseObject getAllOrderItemByUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userBuyer = userRepository.findUserByName(authentication.getName());
            List<OrderItem> orderItems = orderItemRepository.findAllByUserIdOrderByCreatedAtDesc(userBuyer.getId());
            ModelMapper modelMapper = new ModelMapper();
            return ResponseObject.builder()
                    .code(200)
                    .message("Success")
                    .data(orderItems.stream().map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class)).collect(Collectors.toList()))
                    .build();
        } catch (Exception e) {
            return ResponseObject.builder()
                    .code(500)
                    .message("Fail")
                    .build();
        }
    }

    @Override
    public List<OrderItemDto> getAllOrderItem() {
        List<OrderItem> orderItems=orderItemRepository.findAll();
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getAllOrderItemOfSeller() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userSeller = userRepository.findUserByName(authentication.getName());
        List<OrderItem> orderItems=orderItemRepository.findAllByProduct_Seller_Id(userSeller.getId());
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public OrderItemDto updateStatusApproved(int orderItem_id) {
        OrderItem orderItem=orderItemRepository.findById(orderItem_id).get();
        if (orderItem.getStatus().equals(Status.Pending.getOrderStatus()) ){
            orderItem.setStatus(Status.Approved.getOrderStatus());
            orderItemRepository.save(orderItem);
            emailSenderService.sendEmailActive(orderItem.getUser().getEmail(),"Thông báo xác thực đơn hàng #"+orderItem.getId()+ " ShopViet thành công!!",orderItem.toString());
        }
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(orderItem,OrderItemDto.class);
    }

    @Override
    public OrderItemDto updateStatusShipped(int orderItem_id) {
        OrderItem orderItem=orderItemRepository.findById(orderItem_id).get();
        if (orderItem.getStatus().equals(Status.Approved.getOrderStatus()) ){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userShipper = userRepository.findUserByName(authentication.getName());
            orderItem.setShipper(userShipper);
            orderItem.setStatus(Status.Shipped.getOrderStatus());
            orderItemRepository.save(orderItem);
        }
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(orderItem,OrderItemDto.class);
    }

    @Override
    public OrderItemDto updateStatusCancelled(int orderItem_id) {
        OrderItem orderItem=orderItemRepository.findById(orderItem_id).get();
        if (orderItem.getStatus().equals(Status.Pending.getOrderStatus()) ){
            orderItem.setStatus(Status.Cancelled.getOrderStatus());
            orderItemRepository.save(orderItem);
            ModelMapper modelMapper=new ModelMapper();
            return modelMapper.map(orderItem,OrderItemDto.class);
        }else {
            ModelMapper modelMapper=new ModelMapper();
            return modelMapper.map(orderItem,OrderItemDto.class);
        }
    }

    @Override
    public OrderItemDto updateStatusDelivered(int orderItem_id) {
        OrderItem orderItem=orderItemRepository.findById(orderItem_id).get();
        orderItem.setStatus(Status.Delivered.getOrderStatus());
        orderItemRepository.save(orderItem);
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(orderItem,OrderItemDto.class);
    }

    @Override
    public OrderItemDto updateStatusOnTheWay(int orderItem_id) {
        OrderItem orderItem=orderItemRepository.findById(orderItem_id).get();
        orderItem.setStatus(Status.OnTheWay.getOrderStatus());
        orderItemRepository.save(orderItem);
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(orderItem,OrderItemDto.class);
    }

    @Override
    public OrderItemDto updateStatusSuccess(int orderItem_id) {
        OrderItem orderItem=orderItemRepository.findById(orderItem_id).get();
        if (orderItem.getStatus().equals(Status.Delivered.getOrderStatus()) ){
            orderItem.setStatus(Status.Success.getOrderStatus());
            orderItemRepository.save(orderItem);
            ModelMapper modelMapper=new ModelMapper();
            return modelMapper.map(orderItem,OrderItemDto.class);
        }else {
            ModelMapper modelMapper=new ModelMapper();
            return modelMapper.map(orderItem,OrderItemDto.class);
        }
    }

    @Override
    public List<OrderItemDto> getAllOrderItemApproved() {
        List<OrderItem> orderItems= orderItemRepository.findAllByStatus(Status.Approved.getOrderStatus());
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getAllOrderItemShipped() {
        List<OrderItem> orderItems= orderItemRepository.findAllByStatus(Status.Shipped.getOrderStatus());
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getAllOrderItemOnTheWay() {
        List<OrderItem> orderItems= orderItemRepository.findAllByStatus(Status.OnTheWay.getOrderStatus());
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getAllOrderItemDelivered() {
        List<OrderItem> orderItems= orderItemRepository.findAllByStatus(Status.Delivered.getOrderStatus());
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }
}
