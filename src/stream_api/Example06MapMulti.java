package stream_api;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Example06MapMulti {

    public static void main(String[] args) {
        Example06MapMulti example06 = new Example06MapMulti();
        example06.example();
    }

    private static void mapMulti() {
        // Появился в Java 16. Этот оператор похож на flatMap,
        // но использует императивный подход при работе.
        // Теперь вместе с элементом стрима приходит ещё и Consumer,
        // в который можно передать одно или несколько значений, либо не передавать вовсе.

        Stream.of(1, 2, 3, 4, 5, 6)
                .mapMulti((x, consumer) -> {
                    if (x % 2 == 0) {
                        consumer.accept(-x);
                        consumer.accept(x);
                    }
                })
                .forEach(System.out::println);


    }

    private void example() {
        Stream.of(arr("A", "B"), "C", "D", arr(arr("E"), "F"), "G")
                .mapMulti(this::processSerializable)
                .forEach(System.out::println);
    }

    private void processSerializable(Serializable ser, Consumer<String> consumer) {
        if (ser instanceof String str) {
            consumer.accept(str);
        } else if (ser instanceof List) {
            for (Serializable s : (List<Serializable>) ser) {
                processSerializable(s, consumer);
            }
        }
    }

    private Serializable arr(Serializable... elements) {
        return (Serializable) Arrays.asList(elements);
    }


}
