package org.example.chapter5;

import java.util.*;
import java.util.stream.Collectors;

public class PuttingIntoPractise {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //Finds all transactions in 2011 and sort by value (small to high)
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(tr2011);

        //What are all the unique cities where the traders work?
        List<String> traderCities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(traderCities);

        //another way using Set
        Set<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println(cities);

        //Finds all traders from Cambridge and sort them by name
        List<Trader> tradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(tradersFromCambridge);

        //Returns a string of all traders’ names sorted alphabetically
        String traderNames = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1,n2) -> n1 + n2);
        System.out.println(traderNames);

        String traderNames2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(traderNames2);

        //are any traders based in Milan?
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        //print all transaction's values from the traders living in Cambridge
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        //what's the highest value of all transactions
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println("The highest value of all transactions is: " + highestValue.get());

        //find the transaction with the smallest value
        Optional<Transaction> smallestTransaction = transactions.stream()
                .reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println("The smallest transaction is: " + smallestTransaction.get());

        //other way
        Optional<Transaction> smallestTransaction2 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println("The smallest transaction is: " + smallestTransaction2.get());
    }
}
