package com.project.shopviet.Repository;

import com.project.shopviet.Model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
    @Query("SELECT c FROM Category c")
    List<Category> findAll();
}
