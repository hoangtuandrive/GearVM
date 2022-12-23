package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(value="/products", method= RequestMethod.POST)
    public Product createProduct(@RequestBody Product p) {
        return productService.createProduct(p);
    }

    @RequestMapping(value="/products", method=RequestMethod.GET)
    public List<Product> readProducts(){
        return productService.getProducts();
    }

    @RequestMapping(value="/products/{productId}", method=RequestMethod.GET)
    public Product findProduct(@PathVariable(value = "productId") Long id){
        return productService.getProduct(id);
    }

    @RequestMapping(value="/products/{productId}", method=RequestMethod.PUT)
    public Product updateProduct(@PathVariable(value = "productId") Long id, @RequestBody Product productDetails){
        return productService.updateProduct(id, productDetails);
    }

    @RequestMapping(value="products/{productId}", method=RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(value = "productId") Long id){
        productService.deleteProduct(id);
    }
}
