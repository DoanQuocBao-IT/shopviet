package com.project.shopviet.Service;

import com.project.shopviet.DTO.CartDto;
import com.project.shopviet.DTO.request.ItemCartRequest;
import com.project.shopviet.DTO.response.CartResponse;
import com.project.shopviet.DTO.response.Response;
import com.project.shopviet.Model.CartItem;

import java.util.List;

public interface CartItemService {
    Response addToCart(ItemCartRequest request);
    Response updateCart(ItemCartRequest request);
    Response deleteCartItem(int productId);
    boolean isExist(int user_id,int product_id);
}
