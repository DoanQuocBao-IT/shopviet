package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.DTO.Status;
import com.project.shopviet.Model.*;
import com.project.shopviet.Repository.*;
import com.project.shopviet.Service.EmailSenderService;
import com.project.shopviet.Service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
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
    OrderRepository orderRepository;
    @Autowired
    private EmailSenderService emailSenderService;


    @Override
    public OrderItem addToOrderItem(int cart_id, int order_id) {
        CartItem cartItem=cartItemRepository.findById(cart_id).get();
        OrderDetail orderDetail=orderRepository.findById(order_id).get();
        OrderItem orderItem=new OrderItem();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());

        orderItem.setUser(user);
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setOrderDetail(orderDetail);
        orderItem.setStatus(Status.Pending.getOrderStatus());
        orderItem.setCreateDateTime(new Date());
        Product product=productRepository.findById(orderItem.getProduct().getId()).get();
        int inventory=product.getInventory()-orderItem.getQuantity();
        int sold=product.getSold()+orderItem.getQuantity();
        if (inventory >= 0) {
            product.setInventory(inventory);
            product.setSold(sold);
            productRepository.save(product);
            cartItemRepository.deleteById(cart_id);
            return orderItemRepository.save(orderItem);
        }else return null;


    }

    @Override
    public List<OrderItemDto> getAllOrderItemByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userBuyer = userRepository.findUserByName(authentication.getName());
        List<OrderItem> orderItemDtos=orderItemRepository.findByOrderItemContaining(userBuyer.getId());
        ModelMapper modelMapper=new ModelMapper();

        return orderItemDtos.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
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
        List<OrderItem> orderItems=orderItemRepository.findByOrderItemOfSeller(userSeller.getId());
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
            orderItem.setUserShipper(userShipper);
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
        List<OrderItem> orderItems= orderItemRepository.findByStatus(Status.Approved.getOrderStatus());
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getAllOrderItemShipped() {
        List<OrderItem> orderItems= orderItemRepository.findByStatus(Status.Shipped.getOrderStatus());
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getAllOrderItemOnTheWay() {
        List<OrderItem> orderItems= orderItemRepository.findByStatus(Status.OnTheWay.getOrderStatus());
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getAllOrderItemDelivered() {
        List<OrderItem> orderItems= orderItemRepository.findByStatus(Status.Delivered.getOrderStatus());
        ModelMapper modelMapper=new ModelMapper();
        return orderItems.stream().map(orderItem -> modelMapper.map(orderItem,OrderItemDto.class)).collect(Collectors.toList());
    }
}
