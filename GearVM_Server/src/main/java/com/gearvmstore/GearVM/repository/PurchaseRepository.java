package com.gearvmstore.GearVM.repository;

import com.gearvmstore.GearVM.model.Purchase;
import com.gearvmstore.GearVM.model.response.MonthlyFinanceReportResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findDistinctByIdEqualsOrEmployee_IdEqualsOrEmployee_NameContainingIgnoreCaseOrProduct_IdEqualsOrProduct_NameContainingIgnoreCaseOrderByIdAsc
            (Long id, Long employeeId, String employeeName, Long productId, String productName);

    List<Purchase> findAllByOrderByIdDesc();

    @Query("SELECT NEW com.gearvmstore.GearVM.model.response.MonthlyFinanceReportResponseModel(MONTH(p.createdDate), " +
            "0.0, SUM(p.price * p.quantity), 0.0, 0.0) " +
            "FROM purchase p " +
            "WHERE YEAR(p.createdDate) = :year " +
            "GROUP BY MONTH(p.createdDate)")
    List<MonthlyFinanceReportResponseModel> calculateMonthlyCost(@Param("year") int year);
}
