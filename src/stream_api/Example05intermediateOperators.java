package stream_api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example05intermediateOperators {

    public static void main(String[] args) {
        filter();
        mapOperators();
        flatMapOperators();
    }

    private static void filter() {
        // filter(Predicate predicate)
        // Фильтрует стрим, принимая только те элементы, которые удовлетворяют заданному условию.
        Stream.of(1, 2, 3)
                .filter(x -> x == 10)
                .forEach(System.out::print);
        // Вывода нет, так как после фильтрации стрим станет пустым

        Stream.of(120, 410, 85, 32, 314, 12)
                .filter(x -> x > 100)
                .forEach(System.out::println);

        System.out.println("-----");

        List<Integer> srcList = StreamUtil.getRandomIntegerList(20, 1, 100);
        srcList.stream()
                .filter(x -> x > 50)
                .forEach(System.out::println);

        System.out.println("-----");

        srcList.stream()
                .filter(x -> x % 3 == 0)
                .forEach(System.out::println);

    }

    private static void mapOperators() {
        List<String> srcStrList= StreamUtil.getRandomStrNumList(100, 1, 100);

        srcStrList.stream()
                .map(Integer::parseInt)
                .map(x -> x + 10)
                .filter(x -> x > 90)
                .forEach(System.out::println);

        System.out.println("----");

        Stream.of("10", "11", "32")
                .map(x -> Integer.parseInt(x, 16))
                .forEach(System.out::println);
    }

    private static void flatMapOperators() {
        Stream.of(2, 3, 0, 1, 3)
                .flatMapToInt(x -> IntStream.range(0, x))
                .forEach(System.out::println);

        System.out.println("-----");

        Stream.of(1, 2, 3, 4, 5, 6)
                .flatMap(x -> {
                    switch (x % 3) {
                        case 0:
                            return Stream.of(x, x*x*2);
                        case 1:
                            return Stream.of(x);
                        case 2:
                        default:
                            return Stream.empty();
                    }
                })
                .forEach(System.out::println);

        System.out.println("-----");

        int[][] data = {{1,2},{3,4},{5,6}};
        IntStream is1 = Arrays.stream(data).flatMapToInt(Arrays::stream);
        is1.forEach(System.out::println);
    }
}
