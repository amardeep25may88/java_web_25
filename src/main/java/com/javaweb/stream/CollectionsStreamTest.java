package com.javaweb.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@FunctionalInterface
interface AdditionInterface {
    int add(int a,int b);
}

public class CollectionsStreamTest {
    private static int fib(int n) { return (n <= 1) ? n : fib(n - 1) + fib(n - 2); }
    public static void main(String[] args) {


        //    1. *** char occurrences count in string
        String input = "ilovejavaprogramming";
        String[] stringArray={"java","latest","spring","microservices","amazonwebservices"};

        Map<String, Long> occuranceCount  = Arrays.stream(input.split(""))
                .collect(Collectors
                .groupingBy(Function.identity(), Collectors.counting()));

        List<String> duplicateCount = Arrays.stream(input.split(""))
                .collect(Collectors
                .groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x->x.getValue()>1)
                .map(Map.Entry::getKey)
                .toList();

        List<String> uniqueElements = Arrays.stream(input.split(""))
                .collect(Collectors
                .groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x->x.getValue()==1)
                .map(Map.Entry::getKey)
                .toList();

        String fistNonRepeat = Arrays.stream(input.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .findFirst().get().getKey();

        List<String> firstRepeted = Arrays.stream(input.split(""))
                .collect(Collectors
                .groupingBy(Function.identity(),LinkedHashMap::new ,Collectors.counting()))
                .entrySet().stream().filter(x->x.getValue()>1)
                .map(Map.Entry::getKey)
                .toList();

        int[] numbers={1,2,3,4,11,3,10,5,6,7,8,9,10,12,15,51,61};

        List<Integer> collectIntegersAsc = Arrays.stream(numbers).boxed()
                .sorted()
                .toList();

        List<Integer> collectIntegersDesc = Arrays.stream(numbers).boxed()
                .sorted(
                        Comparator.reverseOrder() // put this line if you want to revrese the natural order
                )
                .toList();

        Integer highest = Arrays.stream(numbers).boxed()
                .sorted(Comparator.reverseOrder())
//                .skip(1)
                .findFirst().get();

        Integer secondHighest = Arrays.stream(numbers).boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().get();

        Integer lowast = Arrays.stream(numbers).boxed()
                .sorted()
                .findFirst().get();

        Integer secondLowestInt = Arrays.stream(numbers).boxed()
                .sorted()
                .skip(1)
                .findFirst().get();

        String logngestWord = Arrays.stream(stringArray)
                .reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2)
                .get();

        List<String> listString = Arrays.stream(numbers).boxed()
                .map(s -> s + "")
                .filter(s-> s.startsWith("1"))
                .toList();

        //String.join method
        List<String> listJoinDelimeter = Arrays.asList("1", "2", "3", "4");

        //skip and limit based codes
        IntStream.rangeClosed(1,10)
                .skip(1)
                .limit(8)
                .forEach(System.out::println);

        //sort list and map in stream

        //map and flat map in stream

        //





        System.out.println("Occurance count :: "+occuranceCount);
        System.out.println("duplicateCount :: "+duplicateCount);
        System.out.println("uniqueElements :: "+uniqueElements);
        System.out.println("fistNonRepeat :: "+fistNonRepeat);
        System.out.println("firstRepeted :: "+firstRepeted);

        System.out.println("sorted asc :: "+collectIntegersAsc);
        System.out.println("sorted desc :: "+collectIntegersDesc);
        System.out.println("highest :: "+highest);
        System.out.println("secondHighest :: "+secondHighest);
        System.out.println("lowast :: "+lowast);
        System.out.println("secondLowestInt :: "+secondLowestInt);

        System.out.println("logngestWord :: "+logngestWord);
        System.out.println("listString :: "+listString);

        System.out.println(String.join("->",listJoinDelimeter));




    }

}
