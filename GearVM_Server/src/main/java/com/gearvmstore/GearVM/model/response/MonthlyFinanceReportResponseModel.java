package com.gearvmstore.GearVM.model.response;

import lombok.Data;

@Data
public class MonthlyFinanceReportResponseModel {
    private int month;
    private double revenue;
    private double cost;
    private double profit;
    private double loss;

    public MonthlyFinanceReportResponseModel() {
    }

    public MonthlyFinanceReportResponseModel(int month, double revenue, double cost, double profit, double loss) {
        this.month = month;
        this.revenue = revenue;
        this.cost = cost;
        this.profit = profit;
        this.loss = loss;
    }
}
