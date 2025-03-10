package org.example.chapter5;

import org.example.chapter4.Dish;
import org.example.chapter4.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.example.chapter4.Dish.menu;


public class Filtering {

    public static void main(String[] args) {

        //Filtering with Predicate
        System.out.println("Filtering with predicate:");

        List<Dish> vegetarianMenu  = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        vegetarianMenu.forEach(System.out::println);

        //Filtering unique elements
        System.out.println("Filtering unique elements:");
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        //Slicing a stream
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER));

        System.out.println("Filtered sorted menu:");

        List<Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        filteredMenu.forEach(System.out::println);

        System.out.println("Sorted menu sliced with takeWhile():");
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        slicedMenu1.forEach(System.out::println);

        System.out.println("Sorted menu sliced with dropWhile():");
        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        slicedMenu2.forEach(System.out::println);

        //Truncating a stream
        List<Dish> dishesLimit3 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Truncating a stream:");
        dishesLimit3.forEach(System.out::println);

        //Skiping elements
        List<Dish> dishesSkip2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println("Skiping elements:");
        dishesSkip2.forEach(System.out::println);

        //Filtering the first two meat dishes
        List<Dish> meatDishes =
                menu.stream()
                        .filter(dish -> dish.getType() == Type.MEAT)
                        .limit(2)
                .collect(Collectors.toList());
        System.out.println("Filtering the first two meat dishes:");
        System.out.println(meatDishes);
    }
}
