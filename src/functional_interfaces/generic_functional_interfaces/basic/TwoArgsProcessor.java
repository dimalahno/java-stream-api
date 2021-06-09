package functional_interfaces.generic_functional_interfaces.basic;

@FunctionalInterface
public interface TwoArgsProcessor<X> {
    X process(X arg1, X arg2);
}
