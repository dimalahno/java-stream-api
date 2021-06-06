package functional_interfaces.define.processor;

public class NamedStringProcessor implements StringProcessor{
    @Override
    public String process(String msg) {
        return msg;
    }
}
