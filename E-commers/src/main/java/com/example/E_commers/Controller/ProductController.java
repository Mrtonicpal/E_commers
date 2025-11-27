package com.example.E_commers.Controller;


import com.example.E_commers.Service.ProductService;
import com.example.E_commers.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
}
