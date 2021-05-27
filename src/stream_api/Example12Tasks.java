package stream_api;

import java.util.stream.IntStream;

public class Example12Tasks {
    public static void main(String[] args) {
        System.out.println("---1---");
        IntStream.concat(
                IntStream.range(2, 6),
                IntStream.rangeClosed(-1, 2))
                .forEach(System.out::println);

        System.out.println("---2---");
        IntStream.range(5, 30)
                .limit(12)
                .skip(3)
                .limit(6)
                .skip(2)
                .forEach(System.out::println);

        System.out.println("---3---");
        IntStream.range(0, 10)
                .skip(2)
                .dropWhile(x -> x < 5)
                .limit(3)
                .forEach(System.out::println);

        System.out.println("---4---");
        IntStream.range(0, 10)
                .skip(3)
                .takeWhile(x -> x < 5)
                .limit(3)
                .forEach(System.out::println);

        System.out.println("---5---");
        IntStream.range(1, 5)
                .flatMap(i -> IntStream.generate(() -> i).limit(i))
        .forEach(System.out::println);

        System.out.println("---6---");
        int x = IntStream.range(-2, 2)
                .map(i -> i * 5)
                .reduce(10, Integer::sum);
        System.out.println("x = " + x);
    }
}
