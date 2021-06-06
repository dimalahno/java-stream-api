package functional_interfaces.define.processor;

import java.util.Locale;

public class StringProcessorTest {
    public static void main(String[] args) {
        NamedStringProcessor nms = new NamedStringProcessor();
        System.out.println(nms.process("NMS"));

        StringProcessor sp = msg -> msg.toUpperCase(Locale.ROOT);
        System.out.println(sp.process("sp"));
    }
}
