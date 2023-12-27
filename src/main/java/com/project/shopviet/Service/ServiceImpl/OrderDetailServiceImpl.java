package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.OrderUserDto;
import com.project.shopviet.Model.OrderDetail;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.OrderDetailRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public OrderDetail addToOrder(OrderDetail orderDetail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        orderDetail.setUserBuyer(user);
        return orderDetailRepository.save(orderDetail);
    }


    @Override
    public List<OrderUserDto> getAllOrder() {
        List<OrderDetail> orderDetails= orderDetailRepository.findAll();
        ModelMapper modelMapper=new ModelMapper();
        return orderDetails.stream().map(orderDetail -> modelMapper.map(orderDetail,OrderUserDto.class)).collect(Collectors.toList());
    }



    @Override
    public List<OrderDetail> getAllOrderBySeller(int seller_id) {
        return null; //orderRepository.getAllOrderBySeller(seller_id);
    }

    @Override
    public List<OrderUserDto> getAllOrderByBuyer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        List<OrderDetail> orderDetails= orderDetailRepository.getOrderByBuyerId(user.getId());
        ModelMapper modelMapper=new ModelMapper();

        return orderDetails.stream().map(orderDetail -> modelMapper.map(orderDetail, OrderUserDto.class)).collect(Collectors.toList());
    }
}
