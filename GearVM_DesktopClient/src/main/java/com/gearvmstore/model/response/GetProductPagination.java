package com.gearvmstore.model.response;


import com.gearvmstore.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class GetProductPagination {
    private int totalPage;
    private List<Product> productList;
}
