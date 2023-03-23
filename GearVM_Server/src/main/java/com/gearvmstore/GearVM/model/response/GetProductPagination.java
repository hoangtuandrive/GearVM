package com.gearvmstore.GearVM.model.response;

import com.gearvmstore.GearVM.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class GetProductPagination {
    private int totalPage;
    private List<Product> productList;
}
