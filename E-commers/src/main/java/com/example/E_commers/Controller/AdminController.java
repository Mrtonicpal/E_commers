package com.example.E_commers.Controller;

import com.example.E_commers.Service.ProductService;
import com.example.E_commers.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final ProductService productService;

    @PostMapping("/products")
    public Product add(@RequestBody Product p){
        return productService.saveProduct(p);
    }

    @GetMapping("/products")
    public List<Product> all(){
        return productService.getAllProducts();
    }

    @PutMapping("/product/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p){
        return productService.updateProduct( id , p);
    }

    @DeleteMapping("/product/{id}")
    public String delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return "Deleted";
    }
}
