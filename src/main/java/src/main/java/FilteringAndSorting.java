package src.main.java;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringAndSorting {

	public static final String LINE = "----------------------------------------";
			
    public static void filterAndSort(List<String> list, Predicate<String> condition) {
        list.stream().filter(condition::test).sorted().forEach(System.out::println);
    }

    private static void printLine() {
    	System.out.println(LINE);
    }
    
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Porsche", "Mustang", "Ferrari", "Lamborghini", "BMW");
        printLine();
        System.out.println("Unordered:");
        list.forEach((str) -> System.out.println(str));
        printLine();
        System.out.println("Sorted:");
        list.stream().sorted().forEach(System.out::println);
        printLine();
        System.out.println("sorted names with 'a' :");
        filterAndSort(list, (s) -> s.contains("a"));
        printLine();
    }
}
