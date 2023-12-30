package com.project.shopviet.Repository;

import com.project.shopviet.Model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area,Integer> {
    List<Area> findByDistrictAndPrecinctAndStatus(String district, String precinct, String status);
    List<Area> findByDistrictAndStatus(String district, String status);
    List<Area> findByProvinceAndStatus(String province, String status);
    List<Area> findByProvinceAndDistrictAndStatus(String province, String district, String status);
    Area findByProvinceAndDistrictAndPrecinctAndStatus(String province, String district, String precinct, String status);

}
