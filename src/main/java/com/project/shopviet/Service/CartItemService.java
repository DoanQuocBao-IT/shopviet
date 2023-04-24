package com.project.shopviet.Service;

import com.project.shopviet.DTO.CartDto;
import com.project.shopviet.Model.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem addToCart(CartItem cartItem);
    CartDto updateCart(int id, CartItem cartItem);
    String deleteCartItem(int id);
    List<CartDto> getAllCartItem();

    boolean isExistById(int id);

}
