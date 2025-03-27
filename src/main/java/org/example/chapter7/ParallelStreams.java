package org.example.chapter7;

import java.util.stream.Stream;

public class ParallelStreams {

    public static void main(String[] args) {


        ParallelStreams ps = new ParallelStreams();
        long sum = ps.parallelSum(100L);
        System.out.println(sum);

        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());


    }

    public long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
