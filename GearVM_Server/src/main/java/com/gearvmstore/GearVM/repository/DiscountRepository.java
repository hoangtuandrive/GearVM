package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    Discount findByCode(String code);

}
