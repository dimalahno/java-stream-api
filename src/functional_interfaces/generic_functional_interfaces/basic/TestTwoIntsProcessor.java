package functional_interfaces.generic_functional_interfaces.basic;

public class TestTwoIntsProcessor {
    public static void main(String[] args) {
        TwoIntsProcessor multiplyInts = (arg1, arg2) -> arg1 * arg2;
        TwoIntsProcessor subtractInts = (arg1, arg2) -> arg1 - arg2;
        TwoIntsProcessor divideInts = (arg1, arg2) -> arg1 / arg2;

        TwoIntsProcessorAbstract divide = new TwoIntsProcessorAbstract() {
            @Override
            public Integer process(Integer arg1, Integer arg2) {
                return arg1 / arg2;
            }
        };

        System.out.println(multiplyInts.process(2,3));
        System.out.println(subtractInts.process(5,2));
        System.out.println(divideInts.process(10,2));
        System.out.println(divide.process(10,2));
    }
}
