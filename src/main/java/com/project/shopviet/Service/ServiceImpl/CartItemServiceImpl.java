package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.request.ItemCartRequest;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.*;
import com.project.shopviet.Repository.*;
import com.project.shopviet.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public ResponseObject addToCart(ItemCartRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userBuyer = userRepository.findUserByName(authentication.getName());
        Product product = productRepository.findById(request.getProduct_id()).get();
        Optional<ProductType> productType = productTypeRepository.findById(request.getProduct_type_id());
        if (productType.isEmpty())
            return ResponseObject.builder()
                    .code(400)
                    .message("Product type not found")
                    .build();
        if (request.getQuantity() <= 0 || request.getQuantity() > productType.get().getQuantity())
            return ResponseObject.builder()
                    .code(400)
                    .message("Quantity must be greater than 0 and less than inventory")
                    .build();
        Optional<CartItem> cartItemOptional = cartItemRepository.findByProductIdAndUserIdAndProductTypeId(request.getProduct_id(), userBuyer.getId(), request.getProduct_type_id());
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItemRepository.save(cartItem);
            return ResponseObject.builder()
                    .code(200)
                    .message("Add to cart success")
                    .build();
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(request.getQuantity());
        cartItem.setUser(userBuyer);
        cartItem.setProductType(productType.get());
        cartItem.setCreatedAt(new Date());
        cartItemRepository.save(cartItem);

        return ResponseObject.builder()
                .code(200)
                .message("Add to cart success")
                .build();
    }

    @Override
    public ResponseObject updateCart(ItemCartRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userBuyer = userRepository.findUserByName(authentication.getName());
            Optional<ProductType> productType = productTypeRepository.findById(request.getProduct_type_id());
            if (productType.isEmpty())
                return ResponseObject.builder()
                        .code(400)
                        .message("Product type not found")
                        .build();
            Optional<CartItem> currentCart = cartItemRepository.findByProductIdAndUserIdAndProductTypeId(request.getProduct_id(), userBuyer.getId(), request.getProduct_type_id());
            if (currentCart.isEmpty()) {
                return addToCart(request);
            }
            if (request.getQuantity() == 0) {
                cartItemRepository.deleteById(currentCart.get().getId());
                return ResponseObject.builder()
                        .code(200)
                        .message("Delete cart success")
                        .build();
            }
            if (request.getQuantity() < 0 || request.getQuantity() > currentCart.get().getProductType().getQuantity()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Quantity must be greater than 0 and less than inventory")
                        .build();
            }
            currentCart.get().setQuantity(request.getQuantity());
            cartItemRepository.save(currentCart.get());
            return ResponseObject.builder()
                    .code(200)
                    .message("Update cart success")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Update cart error")
                    .build();
        }
    }

    @Override
    public ResponseObject deleteCartItem(int productId, int productTypeId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userBuyer = userRepository.findUserByName(authentication.getName());
            Optional<CartItem> currentCart = cartItemRepository.findByProductIdAndUserIdAndProductTypeId(productId, userBuyer.getId(), productTypeId);
            if (currentCart.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Cart item not found")
                        .build();
            }
            cartItemRepository.deleteById(currentCart.get().getId());
            return ResponseObject.builder()
                    .code(200)
                    .message("Delete cart success")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Delete cart error")
                    .build();
        }
    }

    @Override
    public ResponseObject getCart() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userBuyer = userRepository.findUserByName(authentication.getName());
            List<CartItem> cartItems = cartItemRepository.findByUserIdOrderByCreatedAtDesc(userBuyer.getId());
            return ResponseObject.builder()
                    .code(200)
                    .message("Get cart success")
                    .data(cartItems)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Get Cart Error: " + e.getMessage());
            return null;
        }
    }
}
