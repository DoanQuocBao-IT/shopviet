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
    @Query("SELECT c FROM Category c WHERE c.parentId = 0")
    List<Category> findAllParentCategory();
    @Query("SELECT c FROM Category c WHERE c.parentId > 0")
    List<Category> findAllChildCategory();

    @Query("SELECT c FROM Category c WHERE c.parentId = ?1")
    List<Category> findAllChildCategory(int parentId);

    default List<Category> findAllChildCategoryByParentId(int parentId){
        List<Category> categories = findAllChildCategory(parentId);
        return categories.stream().map(category -> {
            ModelMapper modelMapper = new ModelMapper();
            Category categoryDto = modelMapper.map(category, Category.class);
            categoryDto.setParentId(category.getParentId());
            return categoryDto;
        }).collect(Collectors.toList());
    }
}
