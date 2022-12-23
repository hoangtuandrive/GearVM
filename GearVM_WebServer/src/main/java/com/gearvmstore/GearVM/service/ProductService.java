package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product p){
        return productRepository.save(p);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {return productRepository.findById(id).get();}
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Long productId, Product productDetails){
        Product p = productRepository.findById(productId).get();
        p.setName(productDetails.getName());
        p.setPrice(productDetails.getPrice());

        return productRepository.save(p);
    }
}
