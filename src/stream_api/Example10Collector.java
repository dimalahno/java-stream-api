package stream_api;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example10Collector {
    public static void main(String[] args) {
        collecting();
        collectingMap();
    }

    private static void collecting () {
        // собрать лишь уникальные элементы в список
        List<Integer> list = Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(LinkedHashSet::new),
                        ArrayList::new));
        System.out.println(list);
    }

    private static void collectingMap() {
        Map<Boolean, List<Integer>> map;
        map = Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
                .collect(partitioningByUniqueness());
        System.out.println(map);
    }

    public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningByUniqueness() {
        return Collector.<T, Map.Entry<List<T>, Set<T>>, Map<Boolean, List<T>>>of(
                () -> new AbstractMap.SimpleImmutableEntry<>(
                        new ArrayList<T>(), new LinkedHashSet<>()),
                (c, e) -> {
                    if (!c.getValue().add(e)) {
                        c.getKey().add(e);
                    }
                },
                (c1, c2) -> {
                    c1.getKey().addAll(c2.getKey());
                    for (T e : c2.getValue()) {
                        if (!c1.getValue().add(e)) {
                            c1.getKey().add(e);
                        }
                    }
                    return c1;
                },
                c -> {
                    Map<Boolean, List<T>> result = new HashMap<>(2);
                    result.put(Boolean.FALSE, c.getKey());
                    result.put(Boolean.TRUE, new ArrayList<>(c.getValue()));
                    return result;
                });
    }
}
