import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ContainerNotSafeDemo{
    public static void main(String[] args){
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::println);
    }

}