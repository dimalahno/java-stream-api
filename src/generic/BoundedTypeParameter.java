package generic;

public class BoundedTypeParameter {
    public static void main(String[] args) {
        Integer [] numbers = {1, 2, 3};
        Double [] numbers2 = {1.0, 2.2, 3.8};
        int countGreaterThan = countGreaterThan(numbers, 0);
        int countGreaterThan1 = countGreaterThan(numbers2, 3.1);
        System.out.println(countGreaterThan);
        System.out.println(countGreaterThan1);
    }
    static <T extends Comparable<T>> int countGreaterThan(T[] numbers, T number) {
        int count = 0;
        for(T n: numbers) {
            if(n.compareTo(number) > 0) {
                count++;
            }
        }
        return count;
    }
}
