package stream_api;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example09Collectors {
    public static void main(String[] args) {
        streamToList();
        streamToSet();
        streamToMap();
        streamToMap2();
        streamCollectingAndThen();
        streamJoining();
        streamSumAndAvg();
        countStreamElements();
        diffMethodCollectors();
        findMinMaxBy();
        groupByStream();
        partitioningBy();
    }

    private static void streamToList() {
        System.out.println("---toList()---");

        List<String> phones = new ArrayList<>();

        Collections.addAll(phones, "iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        List<String> filteredList = phones.stream()
                .filter(s -> s.length() >= 10)
                .sorted()
                .collect(Collectors.toList());

        filteredList.forEach(System.out::println);
    }

    private static void streamToSet() {
        System.out.println("---toSet()---");
        List<String> phones = new ArrayList<>();

        Collections.addAll(phones, "iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        Set<String> sortedAndFilteredSet = phones.stream()
                .filter(s -> s.length() <=10)
                .collect(Collectors.toSet());

        sortedAndFilteredSet.forEach(System.out::println);
    }

    private static void streamToMap() {
        System.out.println("---toMap()---");

        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 8", 54000),
                new Phone("Nokia 9", 45000),
                new Phone("Samsung Galaxy S9", 40000),
                new Phone("LG G6", 32000));

        Map<String, Integer> phones = phoneStream.
                collect(Collectors.toMap(Phone::getName, Phone::getPrice));

        phones.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    private static void streamToMap2() {
        System.out.println("---toMap(Function keyMapper, Function valueMapper)---");
        // Собирает элементы в Map. Каждый элемент преобразовывается в ключ и в значение,
        // основываясь на результате функций keyMapper и valueMapper соответственно.
        // Если нужно вернуть тот же элемент, что и пришел, то можно передать Function.identity().

        Map<Integer, Integer> map1 = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toMap(
                        Function.identity(),
                        Function.identity()
                ));
        map1.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("---");

        Map<Integer, String> map2 = Stream.of(1, 2, 3)
                .collect(Collectors.toMap(
                        Function.identity(),
                        i -> String.format("%d * 2 = %d", i, i * 2)
                ));

        map2.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("---");

        Map<Character, String> map3 = Stream.of(50, 54, 55)
                .collect(Collectors.toMap(
                        i -> (char) i.intValue(),
                        i -> String.format("<%d>", i)
                ));

        map3.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("---toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)---");
        // Аналогичен первой версии метода, только в случае, когда встречается два одинаковых ключа,
        // позволяет объединить значения.

        Map<Integer, String> map4 = Stream.of(50, 55, 64, 20, 19, 52)
                .collect(Collectors.toMap(
                        i -> i % 5,
                        i -> String.format("<%d>", i),
                        (a, b) -> String.join(", ", a, b)
                ));
        map4.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("---toMap(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapFactory)---");
        // Можем указать какой класс Map использовать
        Map<Integer, String> map5 = Stream.of(50, 55, 64, 20, 19, 52)
                .collect(Collectors.toMap(
                        i -> i % 5,
                        i -> String.format("<%d>", i),
                        (a, b) -> String.join(", ", a, b),
                        LinkedHashMap::new
                ));
        map5.forEach((k, v) -> System.out.println(k + ":" + v));

    }

    private static void streamCollectingAndThen() {
        System.out.println("---collectingAndThen(Collector downstream, Function finisher)---");
        // Собирает элементы с помощью указанного коллектора, а потом применяет к полученному результату функцию.
        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList));
        System.out.println(list.getClass());

        List<String> list2 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Function.identity(), s -> s + s),
                        map -> map.entrySet().stream()))
                .map(e -> e.toString())
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList));
        list2.forEach(System.out::println);
    }

    private static void streamJoining() {
        // Собирает элементы, реализующие интерфейс CharSequence, в единую строку.
        // Дополнительно можно указать разделитель, а также префикс и суффикс для всей последовательности.

        System.out.println("---joining()---");

        String s1 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining());
        System.out.println(s1);

        System.out.println("---joining(CharSequence delimiter)---");

        String s2 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining("-"));
        System.out.println(s2);

        System.out.println("---joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)---");

        String s3 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining(" -> ", "[ ", " ]"));
        System.out.println(s3);
    }

    /*
     summingInt(ToIntFunction mapper)
    summingLong(ToLongFunction mapper)
    summingDouble(ToDoubleFunction mapper)
    Коллектор, который преобразовывает объекты в int/long/double и подсчитывает сумму.

    averagingInt(ToIntFunction mapper)
    averagingLong(ToLongFunction mapper)
    averagingDouble(ToDoubleFunction mapper)
    Аналогично, но со средним значением.

    summarizingInt(ToIntFunction mapper)
    summarizingLong(ToLongFunction mapper)
    summarizingDouble(ToDoubleFunction mapper)
    Аналогично, но с полной статистикой.*/
    private static void streamSumAndAvg() {
        Integer sum = Stream.of("1", "2", "3", "4")
                .collect(Collectors.summingInt(Integer::parseInt));
        System.out.println("sum:" + sum);

        Double average = Stream.of("1", "2", "3", "4")
                .collect(Collectors.averagingInt(Integer::parseInt));
        System.out.println("average:" + average);

        DoubleSummaryStatistics stats = Stream.of("1.1", "2.34", "3.14", "4.04")
                .collect(Collectors.summarizingDouble(Double::parseDouble));
        System.out.println("stats:" + stats);
    }

    private static void countStreamElements() {
        System.out.println("---counting()---");
        // Подсчитывает количество элементов

        Long count = Stream.of("1", "2", "3", "4")
                .collect(Collectors.counting());
        System.out.println(count);
    }

    /*
    filtering(Predicate predicate, Collector downstream)
    mapping(Function mapper, Collector downstream)
    flatMapping(Function downstream)
    reducing(BinaryOperator op)
    reducing(T identity, BinaryOperator op)
    reducing(U identity, Function mapper, BinaryOperator op)
    Специальная группа коллекторов, которая применяет операции filter, map, flatMap и reduce.
    */
    private static void diffMethodCollectors() {
        List<Integer> ints = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.filtering(
                        x -> x % 2 == 0,
                        Collectors.toList()));
        System.out.println(ints);

        String s1 = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.filtering(
                        x -> x % 2 == 0,
                        Collectors.mapping(
                                x -> Integer.toString(x),
                                Collectors.joining("-")
                        )
                ));
        System.out.println(s1);

        String s2 = Stream.of(2, 0, 1, 3, 2)
                .collect(Collectors.flatMapping(
                        x -> IntStream.range(0, x).mapToObj(Integer::toString),
                        Collectors.joining(", ")
                ));
        System.out.println(s2);

        int value = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.reducing(
                        0, (a, b) -> a + b
                ));
        System.out.println(value);

        String s3 = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.reducing(
                        "", x -> Integer.toString(x), (a, b) -> a + b
                ));
        System.out.println(s3);
    }

    private static void findMinMaxBy() {
        System.out.println("---minBy(Comparator comparator)---");
        System.out.println("---maxBy(Comparator comparator)---");

        Optional<String> min = Stream.of("ab", "c", "defgh", "ijk", "l")
                .collect(Collectors.minBy(Comparator.comparing(String::length)));
        min.ifPresent(System.out::println);


        Optional<String> max = Stream.of("ab", "c", "defgh", "ijk", "l")
                .collect(Collectors.maxBy(Comparator.comparing(String::length)));
        max.ifPresent(System.out::println);
    }

    private static void groupByStream() {
        // Группирует элементы по критерию, сохраняя результат в Map.
        // Вместе с представленными выше агрегирующими коллекторами, позволяет гибко собирать данные.

        System.out.println("---groupingByConcurrent(Function classifier)---");

        Map<Integer, List<String>> map1 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.groupingBy(String::length));
        map1.entrySet().forEach(System.out::println);

        System.out.println("---groupingByConcurrent(Function classifier, Collector downstream)---");

        Map<Integer, String> map2 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                String::toUpperCase,
                                Collectors.joining())
                ));
        map2.entrySet().forEach(System.out::println);

        System.out.println("---groupingByConcurrent(Function classifier, Supplier mapFactory, Collector downstream)---");

        Map<Integer, List<String>> map3 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.groupingBy(
                        String::length,
                        LinkedHashMap::new,
                        Collectors.mapping(
                                String::toUpperCase,
                                Collectors.toList())
                ));
        map3.entrySet().forEach(System.out::println);
    }

    private static void partitioningBy() {
        // Разбивает последовательность элементов по какому-либо критерию.
        // В одну часть попадают все элементы, которые удовлетворяют переданному условию,
        // во вторую — все, которые не удовлетворяют.

        System.out.println("---partitioningBy(Predicate predicate)---");

        Map<Boolean, List<String>> map1 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.partitioningBy(s -> s.length() <= 2));
        map1.entrySet().forEach(System.out::println);

        System.out.println("---partitioningBy(Predicate predicate, Collector downstream)---");
        Map<Boolean, String> map2 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.partitioningBy(
                        s -> s.length() <= 2,
                        Collectors.mapping(
                                String::toUpperCase,
                                Collectors.joining())
                ));
        map2.entrySet().forEach(System.out::println);
    }

    static class Phone{

        private String name;
        private int price;

        public Phone(String name, int price){
            this.name=name;
            this.price=price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }
    }
}
