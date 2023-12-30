package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Model.Area;
import com.project.shopviet.Repository.AreaRepository;
import com.project.shopviet.Service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaRepository areaRepository;
    @Override
    public List<Map<String, Object>> getAllProvince() {
        List<Area> areas = areaRepository.findByDistrictAndPrecinctAndStatus("", "", "1");
        List<Map<String, Object>> result = areas.stream()
                .map(area -> {
                    Map<String, Object> areaJson = new java.util.HashMap<>();
                    areaJson.put("province_id", area.getProvince());
                    areaJson.put("province_name", area.getName());
                    return areaJson;
                })
                .toList();
        return result;
    }

    @Override
    public List<Map<String, Object>> getDistrictByProvince(String province_id) {
        List<Area> areas = areaRepository.findByProvinceAndStatus(province_id, "1");
        List<Map<String, Object>> result = areas.stream()
                .map(area -> {
                    Map<String, Object> areaJson = new java.util.HashMap<>();
                    areaJson.put("district_id", area.getDistrict());
                    areaJson.put("district_name", area.getName());
                    return areaJson;
                })
                .toList();
        return result;
    }

    @Override
    public List<Map<String, Object>> getPrecinctByDistrictAndProvince(String district_id, String province_id) {
        List<Area> areas = areaRepository.findByProvinceAndDistrictAndStatus(province_id, district_id, "1");
        List<Map<String, Object>> result = areas.stream()
                .map(area -> {
                    Map<String, Object> areaJson = new java.util.HashMap<>();
                    areaJson.put("precinct_id", area.getPrecinct());
                    areaJson.put("precinct_name", area.getName());
                    return areaJson;
                })
                .toList();
        return result;
    }
}
