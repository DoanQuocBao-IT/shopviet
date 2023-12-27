package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.ConsigneeDto;
import com.project.shopviet.Model.Consignee;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.ConsigneeRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.ConsigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ConsigneeServiceImpl implements ConsigneeService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConsigneeRepository consigneeRepository;
    @Override
    public ConsigneeDto createConsignee(ConsigneeDto consigneeDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Consignee consignee = new Consignee();
        consignee.setName(consigneeDto.getName());
        consignee.setPhone(consigneeDto.getPhone());
        consignee.setEmail(consigneeDto.getEmail());
        consignee.setProvince(consigneeDto.getProvince());
        consignee.setDistrict(consigneeDto.getDistrict());
        consignee.setWard(consigneeDto.getWard());
        consignee.setAddress(consigneeDto.getAddress());
        consignee.setBuyer(user);
        consigneeRepository.save(consignee);
        return ConsigneeDto.builder()
                .name(consignee.getName())
                .phone(consignee.getPhone())
                .email(consignee.getEmail())
                .province(consignee.getProvince())
                .district(consignee.getDistrict())
                .ward(consignee.getWard())
                .address(consignee.getAddress())
                .build();
    }
}
