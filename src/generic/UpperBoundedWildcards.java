package generic;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UpperBoundedWildcards {
    public static void main(String[] args) {
        // ? Wildcards
        List<Object> list1 = Arrays.asList("11", 2, new Date());
        List<Integer> list2 = Arrays.asList(1, 2);
        List<String> list3 = Arrays.asList("1", "2");
        List<Double> list4 = Arrays.asList(0.3, 1.9);
//        print(list1);
        print(list2);
//        print(list3);
//        print(list4);
    }

    static void print(List<? super Integer> list) {
        list.forEach(System.out::println);
    }
}
