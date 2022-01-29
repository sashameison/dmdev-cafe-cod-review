package com.narozhnyi.generator;

import com.narozhnyi.model.Dish;
import com.narozhnyi.model.SingleDish;
import lombok.experimental.UtilityClass;
import com.narozhnyi.util.DishesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@UtilityClass
public class OrderGenerator {

    public static List<SingleDish> generateRandomOrder() {
        var random = new Random();
        List<Dish> dishes = new ArrayList<>(DishesContext.getDishesList().values());
        List<SingleDish> result = new ArrayList<>();

        for (int i = 0; i < random.nextInt(1, 5); i++) {
            result.add(buildRandomDish(dishes, random));
        }
        return result;
    }

    private SingleDish buildRandomDish(List<Dish> dishes, Random random) {
        return new SingleDish(random.nextInt(1, 5), dishes.get(random.nextInt(dishes.size())));
    }
}
