package com.project.shopviet.Controller;

import com.project.shopviet.Model.*;
import com.project.shopviet.Service.BrandService;
import com.project.shopviet.Service.CategoryService;
import com.project.shopviet.Service.ProductService;
import com.project.shopviet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@RestController
@RequestMapping("/api/buyer")
@CrossOrigin(origins = {"*"})
public class BuyerController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

}
