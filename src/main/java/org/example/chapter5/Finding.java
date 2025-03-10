package org.example.chapter5;

import org.example.chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.example.chapter4.Dish.menu;

public class Finding {

    public static void main(String[] args) {

        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        boolean isHealthy = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(isHealthy);

        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        dish.ifPresent(System.out::println);

        //Finding the first element
        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst();
        firstSquareDivisibleByThree.ifPresent(System.out::println);

    }
}
