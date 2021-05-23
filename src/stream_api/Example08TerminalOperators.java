package stream_api;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Example08TerminalOperators {

    public static void main(String[] args) {
//        forEachStream();
//        forEachOrderedStream();
//        countStream();
//        collectStream();
//        streamToArray();
//        streamToList();
//        reduceStream();
//        matchPredicate();
        averageStream();
    }

    private static void forEachStream() {
        Stream.of(120, 410, 85, 32, 314, 12)
                .forEach(x -> System.out.format("%s, ", x));
    }

    private static void forEachOrderedStream() {
        System.out.println("\n---forEach---");

        IntStream.range(0, 100000)
                .parallel()
                .filter(x -> x % 10000 == 0)
                .map(x -> x / 10000)
                .forEach(x -> System.out.format("%s, ", x));

        System.out.println("\n---forEachOrdered---");
        // Тоже выполняет указанное действие для каждого элемента стрима, но перед этим добивается
        // правильного порядка вхождения элементов.
        // Используется для параллельных стримов, когда нужно получить правильную последовательность элементов.

        IntStream.range(0, 100000)
                .parallel()
                .filter(x -> x % 10000 == 0)
                .map(x -> x / 10000)
                .forEachOrdered(x -> System.out.format("%s, ", x));
    }

    private static void countStream() {
        // Возвращает количество элементов стрима.
        System.out.println("\n---count---");

        long count = IntStream.range(0, 10)
                .flatMap(x -> IntStream.range(0, x))
                .count();
        System.out.println(count);

        System.out.println(IntStream.rangeClosed(-3, 6).count());

        System.out.println(
                Stream.of(0, 2, 9, 13, 5, 11)
                        .map(x -> x * 2)
                        .filter(x -> x % 2 == 1)
                        .count()
        );
    }

    private static void collectStream() {
        // Один из самых мощных операторов Stream API.
        // С его помощью можно собрать все элементы в список, множество или другую коллекцию,
        // сгруппировать элементы по какому-нибудь критерию, объединить всё в строку и т.д.

        // При желании можно написать свой коллектор, реализовав интерфейс Collector.
        System.out.println("\n---collect---");

        List<Integer> list = Stream.of(1, 2, 3)
                .collect(Collectors.toList());
        System.out.println(list);

        String s = Stream.of(1, 2, 3)
                .map(String::valueOf)
                .collect(Collectors.joining("-", "<", ">"));
        System.out.println(s);

        System.out.println("---R collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)---");
        // То же, что и collect(collector), только параметры разбиты для удобства.
        // Если нужно быстро сделать какую-то операцию, нет нужды реализовывать интерфейс Collector,
        // достаточно передать три лямбда-выражения.
        // supplier должен поставлять новые объекты (контейнеры), например new ArrayList(),
        // accumulator добавляет элемент в контейнер,
        // combiner необходим для параллельных стримов и объединяет части стрима воедино.
        List<String> listSrc = Stream.of("a", "b", "c", "d")
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(listSrc);
    }

    private static void streamToArray() {
        // Возвращает нетипизированный массив с элементами стрима.
        System.out.println("---toArray---");
        String[] elements = Stream.of("a", "b", "c", "d", "e")
                .toArray(String[]::new);
        System.out.println(elements);
    }

    private static void streamToList() {
        System.out.println("---toList---");
        List<String> stringList = Stream.of("a", "b", "c", "d", "e")
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(stringList);
    }

    private static void reduceStream() {
        System.out.println("---reduce---");
        // T reduce(T identity, BinaryOperator accumulator)
        // U reduce(U identity, BiFunction accumulator, BinaryOperator combiner)
        // Позволяет преобразовать все элементы стрима в один объект.
        // Например, посчитать сумму всех элементов, либо найти минимальный элемент.
        // Сперва берётся объект identity и первый элемент стрима, применяется функция accumulator
        // и identity становится её результатом. Затем всё продолжается для остальных элементов.
        int sum = Stream.of(1, 2, 3, 4, 5)
                .reduce(10, (acc, x) -> acc + x);
        System.out.println(sum);

        System.out.println("---Optional reduce(BinaryOperator accumulator)---");
        // Этот метод отличается тем, что у него нет начального объекта identity.
        // В качестве него служит первый элемент стрима.
        // Поскольку стрим может быть пустой и тогда identity объект не присвоится,
        // то результатом функции служит Optional, позволяющий обработать и эту ситуацию, вернув Optional.empty().
        Optional<Integer> result = Stream.<Integer>empty()
                .reduce((acc, x) -> acc + x);
        System.out.println(result.isPresent());

        Optional<Integer> sumInt = Stream.of(1, 2, 3 ,4, 5)
                .reduce((acc, x) -> acc + x);
        System.out.println(sumInt.get());

        int product = IntStream.range(0, 10)
                .filter(x -> x++ % 4 == 0)
                .reduce((acc, x) -> acc * x)
                .getAsInt();
        System.out.println(product);

        // Optional min(Comparator comparator)
        // Optional max(Comparator comparator)
        System.out.println("---min & max");

        List<Integer> srcList = StreamUtil.getRandomIntegerList(20, 1, 100);
        Optional<Integer> min = srcList.stream()
                .reduce((a, b) -> a <= b ? a : b);
        System.out.println("min " + min.get());

        Optional<Integer> max = srcList.stream()
                .reduce((a, b) -> a >= b ? a : b);
        System.out.println("max " + max.get());

        int minInt = Stream.of(20, 11, 45, 78, 13)
                .min(Integer::compare).get();
        System.out.println("minInt " + minInt);

        int maxInt = Stream.of(20, 11, 45, 78, 13)
                .max(Integer::compare).get();
        System.out.println("maxInt " + maxInt);

        System.out.println("---Optional findAny()---");
        // Возвращает первый попавшийся элемент стрима.
        // В параллельных стримах это может быть действительно любой элемент,
        // который лежал в разбитой части последовательности.

        Optional<Integer> anyInt = srcList.stream().findAny();
        System.out.println("anyInt " + anyInt);

        System.out.println("---Optional findFirst()---");
        // Гарантированно возвращает первый элемент стрима, даже если стрим параллельный.
        int anySeq = IntStream.range(4, 65536)
                .findAny()
                .getAsInt();
        // anySeq: 4
        System.out.println("anySeq " + anySeq);

        int firstSeq = IntStream.range(4, 65536)
                .findFirst()
                .getAsInt();
        // firstSeq: 4
        System.out.println("firstSeq " + firstSeq);

        int anyParallel = IntStream.range(4, 65536)
                .parallel()
                .findAny()
                .getAsInt();
        // anyParallel: any number
        System.out.println("anyParallel " + anyParallel);

        int firstParallel = IntStream.range(4, 65536)
                .parallel()
                .findFirst()
                .getAsInt();
        // firstParallel: 4
        System.out.println("firstParallel " + firstParallel);
    }

    private static void matchPredicate() {
        System.out.println("---boolean allMatch(Predicate predicate)---");
        // Возвращает true, если все элементы стрима удовлетворяют условию predicate.
        // Если встречается какой-либо элемент, для которого результат вызова функции-предиката будет false,
        // то оператор перестаёт просматривать элементы и возвращает false.
        boolean resultTrue = Stream.of(1, 2, 3, 4, 5)
                .allMatch(x -> x <= 7);
        System.out.println(resultTrue);

        boolean resultFalse = Stream.of(1, 2, 3, 4, 5)
                .allMatch(x -> x < 3);
        System.out.println(resultFalse);

        System.out.println("---boolean anyMatch(Predicate predicate)---");
        // Возвращает true, если хотя бы один элемент стрима удовлетворяет условию predicate.
        // Если такой элемент встретился, нет смысла продолжать перебор элементов, поэтому сразу возвращается результат.
        boolean resultT = Stream.of(1, 2, 3, 4, 5)
                .anyMatch(x -> x == 3);
        System.out.println(resultT);

        boolean resultF = Stream.of(1, 2, 3, 4, 5)
                .anyMatch(x -> x == 8);
        System.out.println(resultF);

        System.out.println("---boolean noneMatch(Predicate predicate)---");
        // Возвращает true, если, пройдя все элементы стрима, ни один не удовлетворил условию predicate.
        boolean resultT1 = Stream.of(1, 2, 3, 4, 5)
                .noneMatch(x -> x == 9);
        System.out.println(resultT1);

        boolean resultF1 = Stream.of(1, 2, 3, 4, 5)
                .noneMatch(x -> x == 3);
        System.out.println(resultF1);
    }

    private static void averageStream() {
        System.out.println("---OptionalDouble average()---");
        // Только для примитивных стримов!
        // Возвращает среднее арифметическое всех элементов.
        // Либо Optional.empty, если стрим пуст.
        double result = IntStream.range(2, 16)
                .average()
                .getAsDouble();
        System.out.println(result);

        System.out.println("---sum()---");
        // Возвращает сумму элементов примитивного стрима.
        // Для IntStream результат будет типа int, для LongStream — long, для DoubleStream — double.
        long sum = LongStream.range(2, 16)
                .sum();
        System.out.println(sum);

        System.out.println("---IntSummaryStatistics summaryStatistics()---");
        // Полезный метод примитивных стримов. Позволяет собрать статистику о числовой последовательности стрима,
        // а именно: количество элементов, их сумму, среднее арифметическое, минимальный и максимальный элемент.

        LongSummaryStatistics statistics = LongStream.range(2, 16)
                .summaryStatistics();

        System.out.format("  count: %d%n", statistics.getCount());
        System.out.format("    sum: %d%n", statistics.getSum());
        System.out.format("average: %.1f%n", statistics.getAverage());
        System.out.format("    min: %d%n", statistics.getMin());
        System.out.format("    max: %d%n", statistics.getMax());
    }

}
