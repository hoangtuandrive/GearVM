package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product p) {
        return productService.createProduct(p);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> readProducts() {
        return productService.getProducts();
    }

    // api/products/2 GET
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product findProduct(@PathVariable(value = "productId") Long id) {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable(value = "productId") Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PATCH)
    public Product updateImageUri(@PathVariable(value = "productId") Long id, @RequestBody String description) {
        return productService.updateDescription(id, description);
    }

    @RequestMapping(value = "/image/{productId}", method = RequestMethod.PATCH)
    public Product updateDescription(@PathVariable(value = "productId") Long id, @RequestBody String uri) {
        return productService.updateImageUri(id, uri);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(value = "productId") Long id) {
        productService.deleteProduct(id);
    }


}
