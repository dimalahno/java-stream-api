package stream_api;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Example03ParallelStream {
    /*
    * Параллельные стримы
    * Стримы бывают последовательными (sequential) и параллельными (parallel).
    * Последовательные выполняются только в текущем потоке,
    * а вот параллельные используют общий пул ForkJoinPool.commonPool().
    * элементы разбиваются (если это возможно) на несколько групп и обрабатываются в каждом потоке отдельно.
    * Затем на нужном этапе группы объединяются в одну для предоставления конечного результата.
     * */
    public static void main(String[] args) {
        List<Integer> list = StreamUtil.getRandomIntegerList(10000, 0, 100);

        // 1. Вызываем метод parallelStream() вместо stream()
        List<Integer> resultList = list.parallelStream()
                .filter(x -> x > 10)
                .collect(Collectors.toList());

        System.out.println("resultList: " + resultList);
        System.out.println("resultList.size: " + resultList.size());

        // 2. Превращаем обычный стрим в параллельный, вызвав промежуточный оператор parallel.
        int sum = IntStream.range(0, 10)
                .parallel()
                .map(x -> x * 10)
                .sum();

        System.out.println("sum: " + sum);

        final List<Integer> ints = new ArrayList<>();
        IntStream.range(0, 1000000)
                .parallel()
                .forEach(ints::add); // так делать нельзя! Нельзя вмешиваться в работу стрима.
        System.out.println(ints.size());
    }
}
