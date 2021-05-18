package stream_api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Example01Base {
//    взять лишь некоторые элементы (filter),
//    преобразовать каждый элемент (map),
//    посчитать сумму элементов или объединить всё в один объект (reduce).

//    Операторы:
//     - Промежуточные (intermediate) — обрабатывают поступающие элементы и возвращают стрим. Может быть много.
//     - Терминальные (terminal) — обрабатывают элементы и завершают работу стрима. Может быть только один.

    public static void main(String[] args) {
        List<String> list = Arrays.stream(args)
                .filter(s -> s.length() <= 2)
                .collect(Collectors.toList());
        System.out.println(list);

        IntStream.of(120, 410, 85, 32, 314, 12)
                .filter(x -> x < 300)
                .map(x -> x + 11)
                .limit(3)
                .forEach(System.out::println);
    }
}
