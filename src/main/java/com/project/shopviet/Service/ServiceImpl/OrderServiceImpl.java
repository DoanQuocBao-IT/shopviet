package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.ConsigneeDto;
import com.project.shopviet.DTO.OrderDto;
import com.project.shopviet.DTO.Status;
import com.project.shopviet.DTO.UserSellerDto;
import com.project.shopviet.DTO.request.OrderRequest;
import com.project.shopviet.DTO.response.ProductCart;
import com.project.shopviet.DTO.response.SellerProductResponse;
import com.project.shopviet.Model.*;
import com.project.shopviet.Repository.*;
import com.project.shopviet.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ConsigneeRepository consigneeRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartSellerItemRepository cartSellerItemRepository;
    @Override
    public List<OrderDto> createOrder(OrderRequest orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Consignee consignee = consigneeRepository.findById(orderRequest.getConsignee_id()).get();
        List<OrderDto> orderDtos = new ArrayList<>();
        orderRequest.getSellersProduct().forEach(itemCartRequest -> {
            List<CartItem> cartItems = new ArrayList<>();
            itemCartRequest.getProducts().forEach(productCart -> {
                if (cartItemRepository.existsByUser_IdAndProduct_Id(user.getId(),productCart.getProduct_id())){
                    CartItem cartItem = cartItemRepository.getCartItemByUserIdAndProductId(user.getId(),productCart.getProduct_id());
                    cartItems.add(cartItem);
                }
            });
            if (cartItems.isEmpty())
                return;
            CartSellerItem cartSellerItem = cartSellerItemRepository.findBySellerIdAndCartItems(itemCartRequest.getSeller_id(),cartItems);
            Order order = new Order();
            order.setBuyer(cartSellerItem.getBuyer());
            order.setConsignee(consignee);
            order.setCartItems(cartItems);
            order.setSeller(cartSellerItem.getSeller());
            order.setTotalPrice(cartSellerItem.getCartItem().stream().map(cartItem -> cartItem.getProduct().getPrice()*cartItem.getQuantity()).reduce(0,Integer::sum));
            order.setStatus(Status.Pending.getOrderStatus());
            order.setCreateDateTime(new Date());
            orderRepository.save(order);
            orderDtos.add(OrderDto.builder()
                    .consignee(ConsigneeDto.builder()
                            .name(order.getConsignee().getName())
                            .phone(order.getConsignee().getPhone())
                            .email(order.getConsignee().getEmail())
                            .province(order.getConsignee().getProvince())
                            .district(order.getConsignee().getDistrict())
                            .ward(order.getConsignee().getWard())
                            .address(order.getConsignee().getAddress())
                            .build())
                    .products(SellerProductResponse.builder()
                            .products(order.getCartItems().stream().map(cartItem -> ProductCart.builder()
                                    .id(cartItem.getProduct().getId())
                                    .name(cartItem.getProduct().getName())
                                    .price(cartItem.getProduct().getPrice())
                                    .discount(cartItem.getProduct().getDiscount())
                                    .image(cartItem.getProduct().getImage())
                                    .inventory(cartItem.getProduct().getInventory())
                                    .quantity(cartItem.getQuantity())
                                    .totalPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity())
                                    .build()).collect(java.util.stream.Collectors.toList()))
                            .seller(UserSellerDto.builder()
                                    .id(order.getSeller().getId())
                                    .fname(order.getSeller().getFname())
                                    .image(order.getSeller().getImage())
                                    .build())
                            .build())
                    .totalPrice(order.getTotalPrice())
                    .status(order.getStatus())
                    .createDateTime(order.getCreateDateTime().toString())
                    .shipDateTime(null)
                    .paymentDateTime(null)
                    .receiveDateTime(null)
                    .build());
        });
        orderRequest.getSellersProduct().forEach(itemCartRequest -> {
            itemCartRequest.getProducts().forEach(productCart -> {
                CartItem cartItem = cartItemRepository.getCartItemByUserIdAndProductId(user.getId(),productCart.getProduct_id());
                CartSellerItem cartSellerItem = cartSellerItemRepository.findBySellerIdAndCartItems(itemCartRequest.getSeller_id(),List.of(cartItem));
                cartItemRepository.delete(cartItem);
                if (cartSellerItem.getCartItem().isEmpty()){
                    cartSellerItemRepository.deleteById(cartSellerItem.getId());
                }
            });

        });
        return orderDtos;
    }
}
