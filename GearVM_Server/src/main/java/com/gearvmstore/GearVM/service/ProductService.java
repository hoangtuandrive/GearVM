package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.model.dto.cart.CheckCartQuantityDto;
import com.gearvmstore.GearVM.model.response.GetProductPagination;
import com.gearvmstore.GearVM.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public Product createProduct(Product p) {
        p.setImageUri("empty-product.jpg");
        p.setQuantity(0);
        return productRepository.save(p);
    }

    public GetProductPagination getProductPagination(Page<Product> pagedResult) {
        if (pagedResult.hasContent()) {
            GetProductPagination getProductPagination = new GetProductPagination();
            getProductPagination.setTotalPage(pagedResult.getTotalPages());
            getProductPagination.setProductList(pagedResult.getContent());
            return getProductPagination;
        } else {
            return new GetProductPagination();
        }
    }

    public GetProductPagination getProducts(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult = productRepository.findAll(paging);
        return getProductPagination(pagedResult);
    }

    public GetProductPagination searchBar(Integer pageNo, Integer pageSize, String sortBy, String filter) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult =
                productRepository.findDistinctByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrTypeContainingIgnoreCaseOrderByPriceAsc
                        (paging, filter, filter, filter);
        return getProductPagination(pagedResult);
    }

    public GetProductPagination filterSearchNameBrandType(Integer pageNo, Integer pageSize, String sortBy,
                                                          String name, String brand, String type) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult =
                productRepository.findDistinctByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrTypeContainingIgnoreCaseOrderByPriceAsc
                        (paging, name, brand, type);
        return getProductPagination(pagedResult);
    }

    public GetProductPagination filterSearchNameBrandTypePriceBetween(Integer pageNo, Integer pageSize, String sortBy,
                                                                      String name, String brand, String type, int min, int max) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult =
//                productRepository.findDistinctByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrTypeContainingIgnoreCaseAndPriceBetweenOrderByPriceAsc
//                        (paging, name, brand, type, min, max);
                productRepository.findDistinctByNameContainingIgnoreCaseAndBrandContainingIgnoreCaseAndTypeContainingIgnoreCaseAndPriceBetweenOrderByPriceAsc
                        (paging, name, brand, type, min, max);


        return getProductPagination(pagedResult);
    }

    public GetProductPagination findAllByPriceBetween(Integer pageNo, Integer pageSize, String sortBy, int min, int max) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pagedResult = productRepository.findAllByPriceBetweenOrderByPriceAsc(paging, min, max);
        return getProductPagination(pagedResult);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsByFilter(String id, String name, String brand, String type) {
        try {
            return productRepository.findDistinctByIdEqualsOrNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrTypeContainingIgnoreCaseOrderByIdAsc
                    (Long.parseLong(id), name, brand, type);
        } catch (NumberFormatException e) {
            return productRepository.findDistinctByIdEqualsOrNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrTypeContainingIgnoreCaseOrderByIdAsc
                    (null, name, brand, type);
        }
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
        p.setQuantity(p.getQuantity() - quantityToReduce);
        return productRepository.save(p);
    }

    public List<Product> getProductType(String type, Long id) {
        return productRepository.findByTypeAndIdNot(type, id);
    }

    public List<Long> checkCartQuantity(List<CheckCartQuantityDto> checkCartQuantityDtos) {
        // List of products out of inventory
        List<Long> productList = new ArrayList<>();

        for (CheckCartQuantityDto cartProduct : checkCartQuantityDtos) {
            Product p = productRepository.findById(cartProduct.getId()).get();
            if (cartProduct.getCartQuantity() > p.getQuantity() || p.getQuantity() <= 0) {
                productList.add(cartProduct.getId());
            }
        }

        return productList;
    }
}
