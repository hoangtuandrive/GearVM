package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByPriceBetweenOrderByPriceAsc(Pageable pageable, double min, double max);
}
