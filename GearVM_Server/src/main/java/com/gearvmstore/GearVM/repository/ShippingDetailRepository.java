package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.ShippingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingDetailRepository extends JpaRepository<ShippingDetail, Long> {
}
