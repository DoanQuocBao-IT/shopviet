package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.CartDto;
import com.project.shopviet.DTO.UserSellerDto;
import com.project.shopviet.DTO.request.ItemCartRequest;
import com.project.shopviet.DTO.response.CartResponse;
import com.project.shopviet.DTO.response.Response;
import com.project.shopviet.DTO.response.SellerProductResponse;
import com.project.shopviet.Model.*;
import com.project.shopviet.Repository.*;
import com.project.shopviet.Service.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartSellerItemRepository cartSellerItemRepository;
    @Autowired
    CartRepository cartRepository;
    @Override
    public Response addToCart(ItemCartRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userBuyer = userRepository.findUserByName(authentication.getName());
        Product product=productRepository.findById(request.getProduct_id()).get();
        if (request.getQuantity()<=0 || request.getQuantity()>product.getInventory())
            return Response.builder()
                .message("Quantity must be less than inventory and greater than 0")
                .status("fail")
                .build();
        if (isExist(userBuyer.getId(),request.getProduct_id())){
            CartItem cartItem=cartItemRepository.getCartItemByUserIdAndProductId(userBuyer.getId(),request.getProduct_id());
            cartItem.setQuantity(cartItem.getQuantity()+request.getQuantity());
            cartItemRepository.save(cartItem);
            return Response.builder()
                    .message("Add to cart success")
                    .status("success")
                    .build();
        }
        CartItem cartItem =new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(request.getQuantity());
        cartItem.setUser(userBuyer);
        cartItemRepository.save(cartItem);

        List<CartItem> cartItems=cartItemRepository.getCartItemsByProduct_UserSeller_Id(product.getUserSeller().getId());
        boolean existCartSellerItem=cartSellerItemRepository.existsBySellerIdAndBuyerId(product.getUserSeller().getId(),userBuyer.getId());
        if (existCartSellerItem) {
            CartSellerItem cartSellerItem=cartSellerItemRepository.getCartSellerItemBySellerIdAndBuyerId(product.getUserSeller().getId(),userBuyer.getId());
            cartSellerItem.setCartItem(cartItems);
            cartSellerItemRepository.save(cartSellerItem);
            List<CartSellerItem> cartSellerItems=cartSellerItemRepository.getCartSellerItemsByBuyerId(userBuyer.getId());
            Cart cart=cartRepository.getCartByBuyer_Id(userBuyer.getId());
            cart.setCartSellerItems(cartSellerItems);
            cartRepository.save(cart);
            return Response.builder()
                    .message("Add to cart success")
                    .status("success")
                    .build();
        }
        CartSellerItem cartSellerItem=new CartSellerItem();
        cartSellerItem.setSeller(product.getUserSeller());
        cartSellerItem.setCartItem(cartItems);
        cartSellerItem.setBuyer(userBuyer);
        cartSellerItemRepository.save(cartSellerItem);
        List<CartSellerItem> cartSellerItems=cartSellerItemRepository.getCartSellerItemsByBuyerId(userBuyer.getId());
        boolean existCart=cartRepository.existsByBuyer_Id(userBuyer.getId());
        if (existCart){
            Cart cart=cartRepository.getCartByBuyer_Id(userBuyer.getId());
            cart.setCartSellerItems(cartSellerItems);
            cartRepository.save(cart);
            return Response.builder()
                    .message("Add to cart success")
                    .status("success")
                    .build();
        }
        Cart cart=new Cart();
        cart.setBuyer(userBuyer);
        cart.setCartSellerItems(cartSellerItems);
        cartRepository.save(cart);
        return Response.builder()
                .message("Add to cart success")
                .status("success")
                .build();
    }

    @Override
    public Response updateCart(ItemCartRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userBuyer = userRepository.findUserByName(authentication.getName());
            if (isExist(userBuyer.getId(), request.getProduct_id())) {
                CartItem currentCart = cartItemRepository.getCartItemByUserIdAndProductId(userBuyer.getId(), request.getProduct_id());
                if (request.getQuantity()>0 && request.getQuantity()<=currentCart.getProduct().getInventory()){
                    currentCart.setQuantity(request.getQuantity());
                    cartItemRepository.save(currentCart);
                    return Response.builder()
                            .message("Update cart success")
                            .status("success")
                            .build();
                } else if (request.getQuantity()==0){
                    cartItemRepository.deleteById(currentCart.getId());
                    return Response.builder()
                            .message("Delete cart success")
                            .status("success")
                            .build();
                } else {
                    return Response.builder()
                            .message("Quantity must be less than inventory")
                            .status("fail")
                            .build();
                }
            } else return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Update Cart Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Response deleteCartItem(int productId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userBuyer = userRepository.findUserByName(authentication.getName());
            if (isExist(userBuyer.getId(), productId)) {
                CartItem currentCart = cartItemRepository.getCartItemByUserIdAndProductId(userBuyer.getId(), productId);
                CartSellerItem cartSellerItem = cartSellerItemRepository.findBySellerIdAndCartItems(currentCart.getProduct().getUserSeller().getId(), List.of(currentCart));
                cartItemRepository.deleteById(currentCart.getId());
                if (cartSellerItem.getCartItem().isEmpty()){
                    cartSellerItemRepository.deleteById(cartSellerItem.getId());
                }
                return Response.builder()
                        .message("Delete cart success")
                        .status("success")
                        .build();
            }
            return Response.builder()
                    .message("Delete cart fail")
                    .status("fail")
                    .build();
        }catch (IllegalArgumentException e) {
            System.out.println("Delete Cart Error: " + e.getMessage());
        }return null;
    }
    @Override
    public boolean isExist(int user_id, int product_id) {
        return cartItemRepository.existsByUser_IdAndProduct_Id(user_id,product_id);
    }
}
