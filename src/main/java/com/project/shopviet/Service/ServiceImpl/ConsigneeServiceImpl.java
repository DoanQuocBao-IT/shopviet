package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.request.ConsigneeRequest;
import com.project.shopviet.DTO.response.ConsigneeResponse;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.Area;
import com.project.shopviet.Model.Consignee;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.AreaRepository;
import com.project.shopviet.Repository.ConsigneeRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.ConsigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsigneeServiceImpl implements ConsigneeService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConsigneeRepository consigneeRepository;
    @Autowired
    AreaRepository areaRepository;

    @Override
    public ResponseObject createConsignee(ConsigneeRequest consigneeDto) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findUserByName(authentication.getName());
            Consignee consignee = new Consignee();
            consignee.setAddress(consigneeDto.getAddress());
            consignee.setPhone(consigneeDto.getPhone());
            consignee.setName(consigneeDto.getName());
            consignee.setEmail(consigneeDto.getEmail());
            consignee.setBuyer(user);
            consignee.setArea(!Objects.equals(consigneeDto.getProvince(), "")
                    && !Objects.equals(consigneeDto.getDistrict(), "")
                    && !Objects.equals(consigneeDto.getPrecinct(), "")
                    ? areaRepository.findByProvinceAndDistrictAndPrecinctAndStatus(consigneeDto.getProvince(), consigneeDto.getDistrict(), consigneeDto.getPrecinct(), "1")
                    : null
            );
            consigneeRepository.save(consignee);
            return ResponseObject.builder()
                    .code(200)
                    .message("Create consignee success")
                    .build();
        } catch (Exception e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Create consignee fail")
                    .build();
        }
    }

    @Override
    public ResponseObject updateConsignee(ConsigneeRequest consigneeDto, int id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findUserByName(authentication.getName());
            Optional<Consignee> consigneeOptional = consigneeRepository.findById(id);
            if (consigneeOptional.isEmpty())
                return ResponseObject.builder()
                        .code(400)
                        .message("Consignee not found")
                        .build();
            Consignee consignee = consigneeOptional.get();
            consignee.setAddress(!Objects.equals(consigneeDto.getAddress(), "") ? consigneeDto.getAddress() : consignee.getAddress());
            consignee.setPhone(!Objects.equals(consigneeDto.getPhone(), "") ? consigneeDto.getPhone() : consignee.getPhone());
            consignee.setName(!Objects.equals(consigneeDto.getName(), "") ? consigneeDto.getName() : consignee.getName());
            consignee.setEmail(!Objects.equals(consigneeDto.getEmail(), "") ? consigneeDto.getEmail() : consignee.getEmail());
            consignee.setBuyer(user);
            consignee.setArea(!Objects.equals(consigneeDto.getProvince(), "")
                    && !Objects.equals(consigneeDto.getDistrict(), "")
                    && !Objects.equals(consigneeDto.getPrecinct(), "")
                    ? areaRepository.findByProvinceAndDistrictAndPrecinctAndStatus(consigneeDto.getProvince(), consigneeDto.getDistrict(), consigneeDto.getPrecinct(), "1")
                    : null
            );
            consigneeRepository.save(consignee);
            return ResponseObject.builder()
                    .code(200)
                    .message("Update consignee success")
                    .build();
        } catch (Exception e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Update consignee fail")
                    .build();
        }
    }

    @Override
    public ResponseObject deleteConsignee(int id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findUserByName(authentication.getName());
            Optional<Consignee> consigneeOptional = consigneeRepository.findById(id);
            if (consigneeOptional.isEmpty())
                return ResponseObject.builder()
                        .code(400)
                        .message("Consignee not found")
                        .build();
            Consignee consignee = consigneeOptional.get();
            if (consignee.getBuyer().getId() != user.getId())
                return ResponseObject.builder()
                        .code(400)
                        .message("Consignee not found")
                        .build();
            consigneeRepository.deleteById(id);
            return ResponseObject.builder()
                    .code(200)
                    .message("Delete consignee success")
                    .build();
        } catch (Exception e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Delete consignee fail")
                    .build();
        }
    }

    @Override
    public ResponseObject getAllConsignee() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findUserByName(authentication.getName());
            List<Consignee> consigneeList = consigneeRepository.findAllByBuyer_Id(user.getId());

            return ResponseObject.builder()
                    .code(200)
                    .message("Get all consignee success")
                    .data(consigneeList.stream().map(consignee -> ConsigneeResponse.builder()
                            .id(consignee.getId())
                            .name(consignee.getName())
                            .phone(consignee.getPhone())
                            .email(consignee.getEmail())
                            .address(consignee.getAddress())
                            .province(consignee.getArea() != null ? consignee.getArea().getProvince() : "")
                            .district(consignee.getArea() != null ? consignee.getArea().getDistrict() : "")
                            .precinct(consignee.getArea() != null ? consignee.getArea().getPrecinct() : "")
                            .build()).toList())
                    .build();
        } catch (Exception e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Get all consignee fail")
                    .build();
        }
    }
}
