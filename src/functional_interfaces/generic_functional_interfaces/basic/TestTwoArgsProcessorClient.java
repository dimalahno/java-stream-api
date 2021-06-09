package functional_interfaces.generic_functional_interfaces.basic;

public class TestTwoArgsProcessorClient {
    public static void main(String[] args) {
        TwoArgsProcessor<Integer> multiplyInts = (arg1, arg2) -> arg1 * arg2;

        TwoArgsProcessor<Double> addDoubles = (arg1, arg2) -> arg1 + arg2;

        TwoArgsProcessor<String> compareStrings = (arg1, arg2) -> arg1.compareTo(arg2) > 0 ? arg1 : arg2;

        System.out.println(multiplyInts.process(2,3));
        System.out.println(addDoubles.process(4.1,5.2));
        System.out.println(compareStrings.process("ace","age"));
        System.out.println(compareStrings.process("access","plane"));
    }
}
