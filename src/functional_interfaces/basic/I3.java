package functional_interfaces.basic;

import java.util.Random;

public interface I3 {
    private int getNumber() {
        return (new Random()).nextInt(100);
    }

    default String M1 (String s) {
        return "M1 : " + s + getNumber();
    }
}
