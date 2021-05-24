package optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class ExampleOptional {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
        Optional<Integer> min = numbers.stream().min(Integer::compare);

        // Метод orElse() позволяет определить альтернативное значение,
        // которое будет возвращаться, если Optional не получит из потока какого-нибудь значения
        System.out.println(min.orElse(-1));
        min.ifPresent(System.out::println);

        Random rnd = new Random();
        System.out.println(min.orElseGet(()->rnd.nextInt(100)));

        // orElseThrow позволяет сгенерировать исключение, если Optional не содержит значения
        System.out.println(min.orElseThrow(IllegalStateException::new));

        // Метод ifPresentOrElse() позволяет определить альтернативную логику на случай, если значение в Optional отсутствует
        min.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Value not found")
        );
    }
}
