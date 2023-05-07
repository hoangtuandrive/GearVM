package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.model.response.GetProductPagination;
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

    @GetMapping
    public GetProductPagination readProducts(@RequestParam(defaultValue = "0") Integer pageNumber,
                                             @RequestParam(defaultValue = "24") Integer pageSize,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return productService.getProducts(pageNumber, pageSize, sortBy);
    }

    @GetMapping(value = "price-range")
    public GetProductPagination readProductsByPrice(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                    @RequestParam(defaultValue = "24") Integer pageSize,
                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                    @RequestParam(defaultValue = "0") Integer min,
                                                    @RequestParam(defaultValue = "1000000000") Integer max) {
        return productService.findAllByPriceBetween(pageNumber, pageSize, sortBy, min, max);
    }

    @GetMapping(value = "search-bar")
    public GetProductPagination searchBar(@RequestParam(defaultValue = "0") Integer pageNumber,
                                          @RequestParam(defaultValue = "24") Integer pageSize,
                                          @RequestParam(defaultValue = "id") String sortBy,
                                          @RequestParam(required = false) String filter) {
        return productService.searchBar(pageNumber, pageSize, sortBy, filter);
    }

    @GetMapping(value = "filter-search")
    public GetProductPagination filterProducts(@RequestParam(defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "24") Integer pageSize,
                                               @RequestParam(defaultValue = "id") String sortBy,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) String brand,
                                               @RequestParam(required = false) String type) {
        return productService.filterSearchNameBrandType(pageNumber, pageSize, sortBy, name, brand, type);
    }

    @GetMapping(value = "filter-search-price")
    public GetProductPagination filterProducts(@RequestParam(defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "24") Integer pageSize,
                                               @RequestParam(defaultValue = "id") String sortBy,
                                               @RequestParam(required = false, defaultValue = "") String name,
                                               @RequestParam(required = false, defaultValue = "") String brand,
                                               @RequestParam(required = false, defaultValue = "") String type,
                                               @RequestParam(required = false, defaultValue = "0") Integer min,
                                               @RequestParam(required = false, defaultValue = "1000000000") Integer max) {

        return productService.filterSearchNameBrandTypePriceBetween(pageNumber, pageSize, sortBy, name, brand, type, min, max);
    }

    @GetMapping(value = "get-all")
    public List<Product> readAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "get-all-filter")
    public List<Product> readAllProductsByFilter(@RequestParam(defaultValue = "") String filter) {
        return productService.getAllProductsByFilter(filter, filter, filter, filter);
    }

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
