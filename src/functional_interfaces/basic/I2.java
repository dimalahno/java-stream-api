package functional_interfaces.basic;

public interface I2 {
    String s = "I2";

    static void method1() {
        System.out.println("static method1 " + s);
    }

    default String method2(String x) {
        return "default method2 " + s + x;
    }
}
