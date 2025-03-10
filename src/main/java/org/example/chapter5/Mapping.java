package org.example.chapter5;


import org.example.chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.chapter4.Dish.menu;

public class Mapping {

    public static void main(String[] args) {

        //map
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(dishNames);

        System.out.println("Returning a list of the number of characters in each word:");
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        System.out.println("The words are: " + words);
        List<Integer> wordsLenghts = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("The words' lengths are: " + wordsLenghts);

        System.out.println("Extracting the length of the name of each dish:");
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNameLengths);

        //Flattering streams
        System.out.println("Flattering streams using map and Arrays.stream()");
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        System.out.println();

        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(words);

        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);

        System.out.println("Quiz5.2");
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        System.out.println("The numbers are: " + numbers);
        List<Integer> squared = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("The squared numbers are: " + squared);

        System.out.println("Given two lists of numbers, return all pairs of numbers? " +
                "For example, given a list [1, 2, 3] and a list [3, 4] " +
                "you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]");
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i,j}))
                .collect(Collectors.toList());
        pairs.forEach(pair -> System.out.printf("(%d,%d)", pair[0], pair[1]));
        System.out.println();

        System.out.println("Return only pairs of numbers whose sum is devided by 3");
        List<int[]> devidedBy3 = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i+j) % 3 == 0)
                        .map(j -> new int[]{i,j}))
                .collect(Collectors.toList());
        devidedBy3.forEach(pair -> System.out.printf("(%d,%d)", pair[0], pair[1]));



    }
}
