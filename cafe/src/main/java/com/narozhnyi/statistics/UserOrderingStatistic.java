package com.narozhnyi.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderingStatistic {
    private Integer userId;
    private Integer ordersNumbers;
    private Double averageCalories;
    private BigDecimal averageOrderPrice;
}
