package org.example.chapter6;

import org.example.chapter4.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class Summarizing {

    public static void main(String[] args) {

        long howManyDishes = Dish.menu.stream().count();
        System.out.println("Number of dishes: " + howManyDishes);

        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);
        System.out.println(dishCaloriesComparator);

        Optional<Dish> mostCalorieDish = Dish.menu.stream()
                .collect(maxBy(dishCaloriesComparator));
        mostCalorieDish.ifPresentOrElse(
                value -> System.out.println("Most calorie dish: " + value),
                () -> System.out.println("No dishes")
        );

        int totalCalories = Dish.menu.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println("Total calories: " + totalCalories);

        double avgCalories = Dish.menu.stream()
                .collect(averagingInt(Dish::getCalories));
        System.out.println("Average calories: " + avgCalories);

        IntSummaryStatistics menuStatistics = Dish.menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println("Menu statistics: " + menuStatistics);

        String shortMenu = Dish.menu.stream()
                .map(Dish::getName)
                .collect(joining(", "));
        System.out.println("Short menu: " + shortMenu);

        int totalCalories2 = Dish.menu.stream()
                .collect(reducing(0, Dish::getCalories, (i,j) -> i + j));
        System.out.println("Total calories2: " + totalCalories2);

        int totalCalories3 = Dish.menu.stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println("Total calories3: " + totalCalories3);




    }
}
