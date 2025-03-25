package org.example.chapter6;

import org.example.chapter4.Dish;
import org.example.chapter4.Type;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.example.chapter4.Dish.menu;

public class Grouping {

    public static void main(String[] args) {

        Map<Type, List<Dish>> dishesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println("Dishes by type: " + dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy( dish ->
                {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        System.out.println("Dishes by caloric level: " + dishesByCaloricLevel);

        Map<Type, List<Dish>> caloricDishesByType = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println("Caloric dishes by type: " + caloricDishesByType);

        Map<Type, List<Dish>> caloricDishesByType2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));
        System.out.println("Caloric dishes by type: " + caloricDishesByType2);

        Map<Type, List<String>> dishNamesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(Dish::getName, Collectors.toList())));
        System.out.println("Dish names by type: " + dishNamesByType);


        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        Map<Type, Set<String>> dishNamesByType3 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.flatMapping(dish -> dishTags.get(dish.getName()).stream(), Collectors.toSet())));
        System.out.println("Dish names by type: " + dishNamesByType3);

        //multilevel grouping
        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(
                        Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        } )
                        )
                );
        System.out.println("Dishes by type and caloric level: " + dishesByTypeCaloricLevel);

        //collecting data in subgroups
        Map<Type, Long> typesCount = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println("Types count: " + typesCount);

        Map<Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("Most caloric by type: " + mostCaloricByType);

        //finding the highest-calorie dish in each subgroup
        Map<Type, Dish> mostCaloricByType2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("Most caloric by type: " + mostCaloricByType2);

        Map<Type, Integer> totalCaloriesByType2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.summingInt(Dish::getCalories)));
        System.out.println("Total calories by type: " + totalCaloriesByType2);

        Map<Type, Set<CaloricLevel>> caloricLevelByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT; },
                        Collectors.toSet() )));
        System.out.println("Caloric level by type: " + caloricLevelByType);

        Map<Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
                .collect(
                        Collectors.groupingBy(Dish::getType, Collectors.mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <- 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                },
                                Collectors.toCollection(HashSet::new)))
                        );
        System.out.println("Caloric level by type: " + caloricLevelsByType);

    }

    public enum CaloricLevel {
        DIET,
        NORMAL,
        FAT
    }
}
