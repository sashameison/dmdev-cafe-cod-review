package com.narozhnyi.util;

import com.narozhnyi.model.RandomOrder;
import com.narozhnyi.model.SingleDish;
import lombok.experimental.UtilityClass;
import java.math.BigDecimal;

@UtilityClass
public class OrderCounter {

    public static Integer countTimeToMake(RandomOrder randomOrder) {
        var randomOrdersList = randomOrder.getRandomOrdersList();

        return randomOrdersList.stream()
                .map(OrderCounter::getFullTimeToMake)
                .reduce(0, Integer::sum);
    }

    public static BigDecimal countFullPrice(RandomOrder randomOrder) {
        var randomOrdersList = randomOrder.getRandomOrdersList();

        return randomOrdersList.stream()
                .map(OrderCounter::getFullPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Double countAverageCalories(RandomOrder randomOrder) {
        var randomOrdersList = randomOrder.getRandomOrdersList();

        return randomOrdersList.stream()
                .mapToDouble(OrderCounter::getCalories)
                .average()
                .orElse(0);
    }

    public static BigDecimal countAveragePrice(RandomOrder randomOrder) {
        var randomOrdersList = randomOrder.getRandomOrdersList();

        return BigDecimal.valueOf(randomOrdersList.stream()
                .map(OrderCounter::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0));
    }

    private Integer getFullTimeToMake(SingleDish singleDish) {
        return singleDish.getCount();
    }

    private BigDecimal getFullPrice(SingleDish singleDish) {
        return singleDish.getDish().getPrice().multiply(BigDecimal.valueOf(singleDish.getCount()));
    }

    private Double getCalories(SingleDish singleDish) {
        return singleDish.getDish().getCalories().doubleValue();
    }

    private BigDecimal getPrice(SingleDish singleDish) {
        return singleDish.getDish().getPrice();
    }


}
