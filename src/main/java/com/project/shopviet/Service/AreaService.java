package com.project.shopviet.Service;

import java.util.List;
import java.util.Map;

public interface AreaService {
    List<Map<String, Object>> getAllProvince();
    List<Map<String, Object>> getDistrictByProvince(String province_id);
    List<Map<String, Object>> getPrecinctByDistrictAndProvince(String district_id, String province_id);
}
