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

        String input = "ilovejavaprogramming";
        System.out.println("====***  STRING Characters Stream Play cases :: ***  ===== SAMPLE ::: "+ input);

        System.out.println("=> CASE : 1 Occurance count of character in String :: ");
        System.out.println(""+
                Arrays.stream(input.split(""))
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        System.out.println("=> CASE : 2 Duplicate count of character in String :: ");
        System.out.println(""+
                Arrays.stream(input.split(""))
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .filter(x->x.getValue()>1)
                        .map(Map.Entry::getKey)
                        .toList());

        System.out.println("=> CASE : 3 Unique character in String :: ");
        System.out.println(""+
                Arrays.stream(input.split(""))
                        .collect(Collectors
                                .groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .filter(x->x.getValue()==1)
                        .map(Map.Entry::getKey)
                        .toList());

        System.out.println("=> CASE : 4 First non repeated character in String :: ");
        System.out.println(""+
                Arrays.stream(input.split(""))
                        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                        .entrySet().stream()
                        .filter(x -> x.getValue() == 1)
                        .findFirst().get().getKey());

        System.out.println("=> CASE : 5 First repeated character in String :: ");
        System.out.println(""+
                Arrays.stream(input.split(""))
                        .collect(Collectors
                                .groupingBy(Function.identity(),LinkedHashMap::new ,Collectors.counting()))
                        .entrySet().stream().filter(x->x.getValue()>1)
                        .map(Map.Entry::getKey)
                        .toList());


        String[] stringArray={"java","latest","spring","microservices","amazonwebservices"};
        System.out.println("====***  STRING Stream Play cases :: ***  ===== SAMPLE ::: "+ Arrays.toString(stringArray));

        System.out.println("=> CASE : 1 Longest word in String :: ");
        System.out.println(""+
                Arrays.stream(stringArray)
                        .reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2)
                        .get());


        List<String> listJoinDelimeter = Arrays.asList("1", "2", "3", "4");
        System.out.println("=> CASE : 2 word delimiter join in String :: SAMPLE ::: "+listJoinDelimeter);
        System.out.println(String.join("->",listJoinDelimeter));






        int[] numbers={1,2,3,4,11,3,10,5,6,7,8,9,10,12,15,51,61};
        System.out.println("====***  Integer Array Stream Play cases :: ***  ===== SAMPLE ::: "+ Arrays.toString(numbers));


        System.out.println("=> CASE : 1 ASC Order Sorting in Integer Array :: ");
        System.out.println(""+
                Arrays.stream(numbers).boxed()
                        .sorted()
                        .toList());

        System.out.println("=> CASE : 2 DESC Order Sorting in Integer Array :: ");
        System.out.println(""+
                Arrays.stream(numbers).boxed()
                        .sorted(
                                Comparator.reverseOrder() // put this line if you want to revrese the natural order
                        )
                        .toList());

        System.out.println("=> CASE : 3 Highest in Integer Array :: ");
        System.out.println(""+
                Arrays.stream(numbers).boxed()
                        .sorted(Comparator.reverseOrder())
//                .skip(1)
                        .findFirst().get());

        System.out.println("=> CASE : 4 Second highest in Integer Array :: ");
        System.out.println(""+
                Arrays.stream(numbers).boxed()
                        .sorted(Comparator.reverseOrder())
                        .skip(1)
                        .findFirst().get());

        System.out.println("=> CASE : 5 Lowest in Integer Array :: ");
        System.out.println(""+
                Arrays.stream(numbers).boxed()
                        .sorted()
                        .findFirst().get());

        System.out.println("=> CASE : 6 Second lowest in Integer Array :: ");
        System.out.println(""+
                Arrays.stream(numbers).boxed()
                        .sorted()
                        .skip(1)
                        .findFirst().get());

        System.out.println("=> CASE : 7 Starts with in Integer Array :: ");
        System.out.println(""+
                Arrays.stream(numbers).boxed()
                        .map(s -> s + "")
                        .filter(s-> s.startsWith("1"))
                        .toList());

        System.out.println("=> CASE : 8 Stream range in Integer Array :: ");
        IntStream.rangeClosed(1,10)
                .skip(1)
                .limit(8)
                .forEach(System.out::println);




        IntStream.rangeClosed(1, 10)
                .parallel()
                .forEachOrdered(System.out::print);




        /*

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

    */

        /*

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

        String logngestWord = ;

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
                .forEach(System.out::println);*/





    }

}
