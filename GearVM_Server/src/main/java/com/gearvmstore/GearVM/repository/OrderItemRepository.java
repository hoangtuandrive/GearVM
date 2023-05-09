package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.OrderItem;
import com.gearvmstore.GearVM.model.response.MostSoldProductResponseModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT NEW com.gearvmstore.GearVM.model.response.MostSoldProductResponseModel(item.product.id, " +
            "item.product.name, item.product.imageUri, item.product.price, SUM(item.quantity)) " +
            "FROM order_item item WHERE item.order.orderStatus = com.gearvmstore.GearVM.model.OrderStatus.SHIP_SUCCESS " +
            "GROUP BY item.product.id, item.product.name, item.product.imageUri, item.product.price " +
            "ORDER BY SUM(item.quantity) DESC")
    List<MostSoldProductResponseModel> findMostSoldItems(Pageable pageable);
}
