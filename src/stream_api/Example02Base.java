package stream_api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example02Base {
//    Особенности
//    Во-первых, обработка не начнётся до тех пор, пока не будет вызван терминальный оператор.
//    Во-вторых, стрим после обработки нельзя переиспользовать.
    public static void main(String[] args) {
        List<String> list = Arrays.stream(args)
                .filter(s -> s.length() <= 2)
                .collect(Collectors.toList());

//        обработка происходит от терминального оператора к источнику
//        pull iteration - элементы запрашиваются у источника по мере надобности
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println); // вызван терминальный оператор
        stream.filter(s -> s.contains("Stream API")); // недостежим IllegalStateException
        stream.forEach(System.out::println);
    }
}
