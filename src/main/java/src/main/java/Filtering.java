package src.main.java;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Filtering {

    public static void filterList(List<String> list, Predicate<String> condition) {
        list.stream().filter(condition::test).forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Porsche", "Mustang", "Ferrari", "Lamborghini", "BMW");
        list.forEach((str) -> System.out.println(str));
        System.out.println("now using method reference:");
        list.forEach( System.out::println );
        System.out.println("names with 'a' :");
        filterList(list, (s) -> s.contains("a"));
    }
}
