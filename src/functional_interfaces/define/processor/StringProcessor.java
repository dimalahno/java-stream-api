package functional_interfaces.define.processor;

// annotation instructs the compiler to verify that the interface has only one abstract method
@FunctionalInterface
public interface StringProcessor {
    // A functional interface is an interface with a single abstract method, called its functional method
    String process(String msg);

    static boolean isLowerCase(String s) {
        boolean result = true;
        for (int i = 0; i < s.length() && result; ++i)
            result &= Character.isLowerCase(s.charAt(i));
        return result;
    }

    static boolean isUpperCase(String s)
    {
        boolean result = true;
        for (int i = 0; i < s.length() && result; ++i)
            result &= Character.isUpperCase(s.charAt(i));
        return result;
    }
}
