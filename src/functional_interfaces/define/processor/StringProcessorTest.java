package functional_interfaces.define.processor;

public class StringProcessorTest {
    public static void main(String[] args) {
        NamedStringProcessor nms = new NamedStringProcessor();
        System.out.println(nms.process("NMS"));

        StringProcessor sp = msg -> msg.toUpperCase();
        System.out.println(sp.process("sp"));

        System.out.println("---");

        StringProcessor toLowerCase = msg -> msg.toLowerCase();

        StringProcessor toUpperCase = msg -> msg.toUpperCase();

        String s = toLowerCase.process("FUNCTIONALINTERFACES");
        System.out.println(s);
        System.out.println("Lower case: " + StringProcessor.isLowerCase(s));
        System.out.println("Upper case: " + StringProcessor.isUpperCase(s));
        String t = toUpperCase.process(s);
        System.out.println("\n" + t);
        System.out.println("Lower case: " + StringProcessor.isLowerCase(t));
        System.out.println("Upper case: " + StringProcessor.isUpperCase(t));
    }
}
