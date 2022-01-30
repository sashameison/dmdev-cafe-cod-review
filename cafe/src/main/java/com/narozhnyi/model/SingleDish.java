package com.narozhnyi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleDish {
    private Integer count;
    private Dish dish;
}
