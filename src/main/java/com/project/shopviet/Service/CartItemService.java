package com.project.shopviet.Service;

import com.project.shopviet.DTO.CartDto;
import com.project.shopviet.Model.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem addToCart(CartItem cartItem);
    List<CartDto> getAllCartItem();
    CartItem updateCart(int id, CartItem cartItem);
    boolean isExistById(int id);

}
