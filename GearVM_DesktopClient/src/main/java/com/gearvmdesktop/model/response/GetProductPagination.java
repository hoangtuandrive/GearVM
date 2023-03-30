package com.gearvmdesktop.model.response;


import com.gearvmdesktop.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class GetProductPagination {
    private int totalPage;
    private List<Product> productList;
}
