package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.model.response.GetProductPagination;
import com.gearvmstore.GearVM.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product p) {
        p.setImageUri("empty-product.jpg");
        p.setQuantity(0);
        return productRepository.save(p);
    }

    public GetProductPagination getProducts(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Product> pagedResult = productRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            GetProductPagination getProductPagination = new GetProductPagination();
            getProductPagination.setTotalPage(pagedResult.getTotalPages());
            getProductPagination.setProductList(pagedResult.getContent());
            return getProductPagination;
        } else {
            return new GetProductPagination();
        }
    }

    public List<Product> getAllProducts() {
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

    public Product addQuantity(Product p, int quantityToAdd) {
        p.setQuantity(p.getQuantity() + quantityToAdd);
        return productRepository.save(p);
    }

    public Product reduceQuantity(Product p, int quantityToReduce) {
        System.out.println("here");
        p.setQuantity(p.getQuantity() - quantityToReduce);
        return productRepository.save(p);
    }
}
