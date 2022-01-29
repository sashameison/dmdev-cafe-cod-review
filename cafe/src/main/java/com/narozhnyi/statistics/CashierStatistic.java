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
public class CashierStatistic {
    private Long cashierId;
    private Integer ordersOperated;
    private BigDecimal moneyEarned;
}
