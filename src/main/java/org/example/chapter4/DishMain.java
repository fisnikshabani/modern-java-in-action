package org.example.chapter4;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.example.chapter4.Dish.menu;

public class DishMain {

    public static void main(String[] args) {

        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        System.out.println(threeHighCaloricDishNames);
    }
}
