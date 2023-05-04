package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findDistinctByIdEqualsOrEmployee_IdEqualsOrEmployee_NameContainingIgnoreCaseOrProduct_IdEqualsOrProduct_NameContainingIgnoreCaseOrderByIdAsc
            (Long id, Long employeeId, String employeeName, Long productId, String productName);
}
