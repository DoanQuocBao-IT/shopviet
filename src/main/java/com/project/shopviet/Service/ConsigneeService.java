package com.project.shopviet.Service;

import com.project.shopviet.DTO.request.ConsigneeRequest;
import com.project.shopviet.DTO.response.ResponseObject;

public interface ConsigneeService {
    ResponseObject createConsignee(ConsigneeRequest consigneeDto);

    ResponseObject updateConsignee(ConsigneeRequest consigneeDto, int id);

    ResponseObject deleteConsignee(int id);

    ResponseObject getAllConsignee();
}
