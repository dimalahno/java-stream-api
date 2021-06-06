package functional_interfaces.define.processor;

// annotation instructs the compiler to verify that the interface has only one abstract method
@FunctionalInterface
public interface StringProcessor {
    // A functional interface is an interface with a single abstract method, called its functional method
    String process(String msg);
}
