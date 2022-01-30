package com.narozhnyi.util;

import com.narozhnyi.model.Dish;
import lombok.experimental.UtilityClass;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static com.narozhnyi.util.Constants.DEBUG_COLA;
import static com.narozhnyi.util.Constants.IT_STEAK;
import static com.narozhnyi.util.Constants.LEGACY_SALAD;
import static com.narozhnyi.util.Constants.SCRIPT_ICE_CREAM;
import static com.narozhnyi.util.Constants.SWITCH_POTATO;

@UtilityClass
public class DishesContext {

    private final Map<String, Dish> dishesList = new HashMap<>();

    static {
        loadMenu();
    }

    public Map<String, Dish> getDishesList() {
        return dishesList;
    }

    private static void loadMenu() {
        dishesList.put(IT_STEAK, new Dish(500, IT_STEAK, BigDecimal.valueOf(500)));
        dishesList.put(LEGACY_SALAD, new Dish(50, LEGACY_SALAD, BigDecimal.valueOf(5)));
        dishesList.put(SWITCH_POTATO, new Dish(300, SWITCH_POTATO, BigDecimal.valueOf(3)));
        dishesList.put(DEBUG_COLA, new Dish(25, DEBUG_COLA, BigDecimal.valueOf(2)));
        dishesList.put(SCRIPT_ICE_CREAM, new Dish(150, SCRIPT_ICE_CREAM, BigDecimal.valueOf(4)));
    }
}
