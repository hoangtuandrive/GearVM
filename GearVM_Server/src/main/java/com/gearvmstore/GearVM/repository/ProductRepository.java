package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByPriceBetweenOrderByPriceAsc(Pageable pageable, double min, double max);

    Page<Product> findDistinctByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrTypeContainingIgnoreCaseOrderByPriceAsc(Pageable pageable, String name, String brand, String type);

    Page<Product> findDistinctByNameContainingIgnoreCaseAndBrandContainingIgnoreCaseAndTypeContainingIgnoreCaseAndPriceBetweenOrderByPriceAsc(Pageable pageable, String name, String brand, String type, double min, double max);

    List<Product> findDistinctByIdEqualsOrNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrTypeContainingIgnoreCaseOrderByIdAsc(Long id, String name, String brand, String type);

    Page<Product> findByTypeAndIdNot(Pageable pageable,String type,Long productId);
}
