package org.example.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.chapter2.FilteringApples.Color.GREEN;

public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, GREEN),
                new Apple(155, GREEN),
                new Apple(120, Color.RED)
        );

        List<Apple> greenApples = filterGreenApples(inventory);
        System.out.println(greenApples);

        List<Apple> applesByColor = filterApplesByColor(inventory, GREEN);
        System.out.println(applesByColor);

        List<Apple> applesByWeight = filterApplesByWeight(inventory, 150);
        System.out.println(applesByWeight);

        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
        System.out.println(redApples);

        //this solution is bad!!!
        List<Apple> greenApples2 = filterApples(inventory, GREEN, 0, true);
        System.out.println(greenApples2);
        List<Apple> heavyApples = filterApples(inventory, null, 150, false);
        System.out.println(heavyApples);

        List<Apple> heavyApplesWithPredicate = filterAppleswithPredicate(inventory, new AppleHeavyWeightPredicate());
        System.out.println("Heavy apples with predicate: " + heavyApplesWithPredicate.size());

        List<Apple> greenApplesWithPredicate = filterAppleswithPredicate(inventory, new AppleGreenColorPredicate());
        System.out.println("Green apples with predicate: " + greenApplesWithPredicate.size());

        AppleFormatter simpleFormatter = new AppleSimpleFormatter();
        prettyPrintApple(inventory, simpleFormatter);

        AppleFormatter fancyFormatter = new AppleFancyFormatter();
        prettyPrintApple(inventory, fancyFormatter);

    }

    enum Color {
        RED,
        GREEN
    }

    public static class Apple {
        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (GREEN.equals(apple.getColor()) ) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (apple.getColor().equals(color) ) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            // un ugly way to select color or weight
            if ( (flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight) ) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterAppleswithPredicate(List<Apple> inventory, ApplePredicate applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (applePredicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


    public interface ApplePredicate {
        boolean test (Apple apple);
    }

    public interface AppleFormatter {
        String accept(Apple apple);
    }

    public static class AppleHeavyWeightPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    public static class AppleGreenColorPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return GREEN.equals(apple.getColor());
        }
    }

    public static class AppleFancyFormatter implements AppleFormatter {

        @Override
        public String accept(Apple apple) {
            String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
            return "A " + characteristic + " " + apple.getColor() + " apple";
        }
    }

    public static class AppleSimpleFormatter implements AppleFormatter {
        @Override
        public String accept(Apple apple) {
            return "An apple of " + apple.getWeight() + "g";
        }
    }

    public static void prettyPrintApple (List<Apple> inventory, AppleFormatter appleFormatter) {
        for (Apple apple: inventory) {
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }


}
