package functional_interfaces.basic;

public interface I4 {

    private static String getPrefix(String prefix){
        return "male".equals(prefix) ? "Mr. " : "Ms. ";
    }

    static String getName(String name, String prefix){
        return "I4 : " + getPrefix(prefix) + name;
    }
}
