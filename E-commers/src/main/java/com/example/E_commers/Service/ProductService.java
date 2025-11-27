package com.example.E_commers.Service;


import com.example.E_commers.Repository.ProductRepository;
import com.example.E_commers.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct( Long id, Product p) {
        Product old = productRepository.findById(id).orElseThrow();
        old.setName(p.getName());
        old.setDescription(p.getDescription());
        old.setPrice(p.getPrice());
        old.setImageUrl(p.getImageUrl());
        return productRepository.save(old);
    }

//    public void DeleteProduct(Long id){
//         productRepository.deleteById(id);
//    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
