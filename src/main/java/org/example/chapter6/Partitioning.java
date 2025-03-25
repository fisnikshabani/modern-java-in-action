package org.example.chapter6;

import org.example.chapter4.Dish;
import org.example.chapter4.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.chapter4.Dish.menu;

public class Partitioning {

    public static void main(String[] args) {

        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println("Is the menu vegetarian friendly? " + partitionedMenu);

        //achieve same result by filtering the stream created from menu List
        List<Dish> vegetarianDishes = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println("Is the menu vegetarian friendly? " + vegetarianDishes);

        Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType = menu.stream()
                .collect(
                        Collectors.partitioningBy(Dish::isVegetarian,
                                Collectors.groupingBy(Dish::getType))
                );
        System.out.println("vegetarianDishesByType: " + vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(
                        Collectors.partitioningBy(Dish::isVegetarian,
                                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))
                );
        System.out.println("mostCaloricPartitionedByVegetarian: " + mostCaloricPartitionedByVegetarian);
        }

    //partitioning numbers into prime and nonprime
    public boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    };

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2,n).boxed()
                .collect(Collectors.partitioningBy(this::isPrime));
    }
}

