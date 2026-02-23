package com.javaweb.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoreStream {


    public static void main(String[] args) {

        String inputString = "Amardeep Singh";
//        String inputString = "I love Java programming";
//        String inputString="Madam";
//        String inputString = "I love Java programming with microservices";

        //string palindrome
        boolean b = IntStream.range(0, inputString.length() / 2)
                .allMatch(c -> inputString.charAt(c) == inputString.charAt(inputString.length() - 1 - c));


        //first non repeat
        Map.Entry<Character, Long> characterLongEntry = inputString.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(c -> c.getValue() == 1)
                .findFirst()
                .get();

        //duplicate count
        List<Map.Entry<Character, Long>> list = inputString.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .toList();

        //reverse string
        String collect = IntStream.range(0,inputString.length())
//                .chars()
                .mapToObj(c -> inputString.charAt(inputString.length() - 1 - c))
                .map(String::valueOf)
                .collect(Collectors.joining());

        //remove duplicates
        String collect1 = inputString.toLowerCase()
                .chars()
                .distinct()
                .mapToObj(c->String.valueOf((char) c))
                .collect(Collectors.joining());

        //longest word
        String string = Arrays.stream(inputString.split(" "))
                .max(Comparator.comparingInt(String::length))
                .orElse("");

        //longest word
        String string1 = Arrays.stream(inputString.split(" "))
                .reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2)
                .get();

        //vowels filter
        Map<String, Long> collect2 = inputString.toLowerCase()
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .filter(c -> "aeiou".indexOf(c) != -1)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //first non repeat
        Set<Character> seen = new HashSet<>();
        Character c1 = inputString.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !seen.add(c))
                .findFirst().orElse(null);
        //group by length
        Map<Integer, List<String>> collect3 = Arrays.stream(inputString.split(" "))
                .collect(Collectors.groupingBy(String::length));

        //reverse words in string
        String collect4 = Arrays.stream(inputString.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));

        String collect5 = inputString.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining());

        //Stream consumed once
        //streams are lazy if they have no-terminal operation then no output/effect
//        Stream<String> stream = Stream.of("java", "stream");
//
//        stream.forEach(System.out::println);
//            try {
//                Thread.sleep(2000);
//            }catch (InterruptedException e){
//                e.getMessage();
//            }
//        stream.forEach(System.out::println);

        int[] inputArray = {10, 20, 5, 30, 25};

        //second highest without sorting
        int max=Arrays.stream(inputArray).max().orElse(Integer.MAX_VALUE);
        int max2 = Arrays.stream(inputArray)
                .filter(n -> n < max)
                .max()
                .orElse(Integer.MIN_VALUE);

        int minVal=0;
        int maxVal=0;
        for(int i :inputArray){
            if(i>maxVal){
                maxVal=i;


            }

        }

//      Partition Array Into Even and Odd
        Map<Boolean, List<Integer>> collect6 = Arrays.stream(inputArray)
                .boxed().collect(Collectors.partitioningBy(n -> n % 2 == 0));



        System.out.println(" ⬇️ INPUT : "+ Arrays.toString(inputArray) +"\n");
        System.out.println(" ⬆️ OUTPUT : "+collect6);



    }

}
