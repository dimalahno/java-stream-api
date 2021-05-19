package stream_api;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamUtil {

    /**
     *
     * @param size - numbers of elements
     * @param startElement - start element
     * @param endElement - end element
     * @return collection of random Integer elements the length of size
     */
    public static List<Integer> getRandomIntegerList(int size, int startElement, int endElement) {
        return Arrays.stream(new Random().ints(size, startElement, endElement).toArray())
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<String> getRandomStrNumList(int size, int startElement, int endElement) {
         return Arrays.stream(new Random().ints(size, startElement, endElement).toArray())
                 .boxed()
                 .map(x -> x + "")
                 .collect(Collectors.toList());
    }
}
