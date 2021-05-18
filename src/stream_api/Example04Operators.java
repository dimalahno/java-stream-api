package stream_api;

import java.util.Arrays;
import java.util.stream.Stream;

public class Example04Operators {

    public static void main(String[] args) {
//        createStream();
//        createIterateStream();
        concatStream();
    }

    private static void createStream() {
        // Пустой стрим
        Stream.empty().forEach(System.out::println);

        // Для нескольких элементов
        Arrays.asList(1, 2, 3).stream().forEach(System.out::println);

        // Так лучше
        Stream.of(1, 2, 3).forEach(System.out::println);

        // Возвращает пустой стрим, если в качестве аргумента передан null,
        // в противном случае, возвращает стрим из одного элемента.
        String str = Math.random() > 0.5 ? "I'm feeling lucky" : null;
        Stream.ofNullable(str)
                .forEach(System.out::println);

        // Возвращает стрим с бесконечной последовательностью элементов, генерируемых функцией Supplier s.
        Stream.generate(() -> 6)
                .limit(6)
                .forEach(System.out::println);
    }

    private static void createIterateStream() {
        // Возвращает бесконечный стрим с элементами, которые образуются в результате
        // последовательного применения функции f к итерируемому значению.
        // Первым элементом будет seed, затем f(seed), затем f(f(seed)) и так далее.
        Stream.iterate(2, x -> x + 2)
                .limit(6)
                .forEach(System.out::println);

        System.out.println("-----");

        Stream.iterate(1, x -> x * 2)
                .limit(6)
                .forEach(System.out::println);

        System.out.println("-----");
        // iterate(T seed, Predicate hasNext, UnaryOperator f)
        // Всё то же самое, только добавляется ещё один аргумент hasNext:
        // если он возвращает false, то стрим завершается.
        Stream.iterate(2, x -> x < 25, x -> x + 6)
                .forEach(System.out::println);
    }

    private static void concatStream() {
        Stream.concat(
                Stream.of(1, 2, 3),
                Stream.of(4, 5, 6)
        ).forEach(System.out::println);
    }
}
