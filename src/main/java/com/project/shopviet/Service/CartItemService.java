package com.project.shopviet.Service;

import com.project.shopviet.DTO.request.ItemCartRequest;
import com.project.shopviet.DTO.response.ResponseObject;

public interface CartItemService {
    ResponseObject addToCart(ItemCartRequest request);

    ResponseObject updateCart(ItemCartRequest request);

    ResponseObject deleteCartItem(int productId, int productTypeId);

    ResponseObject getCart();
}
