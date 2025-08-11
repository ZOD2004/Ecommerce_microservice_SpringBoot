package com.getgoods.cartservice.Model;

import com.getgoods.cartservice.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long productId;
    private String name;
    private Category category;
    private Date expDate;
}
