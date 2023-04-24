package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.CartDto;
import com.project.shopviet.Model.CartItem;
import com.project.shopviet.Model.Product;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.CartItemRepository;
import com.project.shopviet.Repository.ProductRepository;
import com.project.shopviet.Repository.UserRepository;
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
    @Override
    public CartItem addToCart(CartItem cartItem) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userBuyer = userRepository.findUserByName(authentication.getName());
        Product  product=productRepository.findById(cartItem.getProduct().getId()).get();
        cartItem.setProduct(product);
        cartItem.setUser(userBuyer);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartDto> getAllCartItem() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userBuyer = userRepository.findUserByName(authentication.getName());
        List<CartItem> cartItems=cartItemRepository.getCartByBuyerId(userBuyer.getId());
        ModelMapper modelMapper=new ModelMapper();
        return cartItems.stream().map(cartItem -> modelMapper.map(cartItem, CartDto.class)).collect(Collectors.toList());
    }

    @Override
    public CartDto updateCart(int id, CartItem cartItem) {
        try {
            if (isExistById(id)) {
                CartItem currentCart = cartItemRepository.findById(id).get();
                currentCart.setQuantity(cartItem.getQuantity());
                if (currentCart.getQuantity()==0){
                    cartItemRepository.deleteById(currentCart.getId());
                }
                else {
                    cartItemRepository.save(currentCart);
                }
                ModelMapper modelMapper=new ModelMapper();
                return modelMapper.map(currentCart,CartDto.class);
            } else return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Update Cart Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteCartItem(int id) {
        try {
            if (isExistById(id)){
                cartItemRepository.deleteById(id);
                return "Delete items in the cart success";
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Delete Cart Error: " + e.getMessage());
        }return null;
    }

    @Override
    public boolean isExistById(int id) {
        return cartItemRepository.existsById(id);
    }
}
