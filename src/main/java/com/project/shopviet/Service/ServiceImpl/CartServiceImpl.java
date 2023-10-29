package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.UserSellerDto;
import com.project.shopviet.DTO.response.CartResponse;
import com.project.shopviet.DTO.response.ProductCart;
import com.project.shopviet.DTO.response.SellerProductResponse;
import com.project.shopviet.Model.Cart;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.CartRepository;
import com.project.shopviet.Repository.CartSellerItemRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public CartResponse getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User buyer = userRepository.findUserByName(authentication.getName());
        Cart cartItem=cartRepository.getCartByBuyer_Id(buyer.getId());
        return CartResponse.builder()
                .sellerProduct(cartItem.getCartSellerItems().stream().map(cartSellerItem -> SellerProductResponse.builder()
                        .seller(UserSellerDto.builder()
                                .id(cartSellerItem.getSeller().getId())
                                .fname(cartSellerItem.getSeller().getFname())
                                .image(cartSellerItem.getSeller().getImage())
                                .build())
                        .products(cartSellerItem.getCartItem().stream().map(item -> ProductCart.builder()
                                .id(item.getProduct().getId())
                                .name(item.getProduct().getName())
                                .price(item.getProduct().getPrice())
                                .discount(item.getProduct().getDiscount())
                                .image(item.getProduct().getImage())
                                .inventory(item.getProduct().getInventory())
                                .quantity(item.getQuantity())
                                .totalPrice(item.getProduct().getPrice()*item.getQuantity())
                                .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList()))
                .totalProduct(cartItem.getCartSellerItems().stream().map(cartSellerItem -> cartSellerItem.getCartItem().size()).reduce(0,Integer::sum))
                .totalSeller(cartItem.getCartSellerItems().size())
                .build();
    }
}
