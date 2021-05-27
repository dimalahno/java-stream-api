package stream_api;

import java.util.List;
import java.util.stream.DoubleStream;

public class Example07DiffOperator {

    private static final List<Integer> srcList = StreamUtil.getRandomIntegerList(50, 1, 100);

    public static void main(String[] args) {
        limitStream();
        skipStream();
        sortedStream();
        distinctStream();
        pickStream();
        takeWhileStream();
        dropWhileStream();
        boxedStream();
    }

    private static void limitStream() {

        System.out.println("---limit---");

        srcList.stream()
                .limit(5)
                .forEach(num -> System.out.printf("%s, ", num));
    }

    private static void skipStream() {

        System.out.println("\n---skip---");

        srcList.stream()
                .skip(40)
                .forEach(num -> System.out.printf("%s, ", num));
    }

    private static void sortedStream() {
        // sorted принимает компоратор(можно делать свойю сортировку)
        System.out.println("\n---sorted---");

        srcList.stream()
                .sorted()
                .forEach(num -> System.out.printf("%s, ", num));
    }

    private static void distinctStream() {
        // Убирает повторяющиеся элементы и возвращаем стрим с уникальными элементами.
        System.out.println("\n---distinct---");

        srcList.stream()
                .sorted()
                .distinct()
                .forEach(num -> System.out.printf("%s, ", num));


    }

    private static void pickStream() {

        // Выполняет действие над каждым элементом стрима и при этом возвращает стрим с элементами исходного стрима.
        // Служит для того, чтобы передать элемент куда-нибудь, не разрывая при этом цепочку операторов
        // (вы же помните, что forEach — терминальный оператор и после него стрим завершается?), либо для отладки.

        System.out.println("\n---pick---");

        srcList.stream().peek(x -> System.out.format("before distinct: %d%n", x)) // Можем залоггировать
                .distinct()
                .peek(x -> System.out.format("after distinct: %d%n", x))
                .map(x -> x * x)
                .forEach(x -> System.out.format("after map: %d%n", x));


    }

    private static void takeWhileStream() {
        // takeWhile(Predicate predicate)
        // Возвращает элементы до тех пор, пока они удовлетворяют условию, то есть функция-предикат возвращает true.
        // Это как limit, только не с числом, а с условием.

        System.out.println("\n---takeWhile---");
        srcList.stream()
                .takeWhile(x -> x > 30)
                .forEach(x -> System.out.printf("%s, ", x));
    }

    private static void dropWhileStream() {
        // Пропускает элементы до тех пор, пока они удовлетворяют условию, затем возвращает оставшуюся часть стрима.
        // Если предикат вернул для первого элемента false, то ни единого элемента не будет пропущено.
        // Оператор подобен skip, только работает по условию.

        System.out.println("\n---dropWhile---");

        srcList.stream()
                .dropWhile(x -> x >= 27)
                .forEach(x -> System.out.printf("%s, ", x));
    }

    private static void boxedStream() {
        // Преобразует примитивный стрим в объектный.

        System.out.println("\n---boxed---");

        DoubleStream.of(0.1, Math.PI)
                .boxed()
                .map(Object::getClass)
                .forEach(System.out::println);
    }
}
