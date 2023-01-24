package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product p) {
        return productRepository.save(p);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Long productId, Product productDetails) {
        Product p = productRepository.findById(productId).get();
        p.setName(productDetails.getName());
        p.setBrand(productDetails.getBrand());
        p.setType(productDetails.getType());
        p.setPrice(productDetails.getPrice());
        p.setQuantity(productDetails.getQuantity());
        return productRepository.save(p);
    }

    public Product updateImageUri(Long productId, String uri) {
        Product p = productRepository.findById(productId).get();
        p.setImageUri(uri);
        return productRepository.save(p);
    }

    public Product updateDescription(Long productId, String description) {
        Product p = productRepository.findById(productId).get();
        p.setDescription(description);
        return productRepository.save(p);
    }
}
