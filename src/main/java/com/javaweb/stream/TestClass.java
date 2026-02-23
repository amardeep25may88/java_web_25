package com.javaweb.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestClass {
    public static void main(String[] args) {

//        String inputString="I love java programing with microservices";
//        String inputString="I love java programing with microservices";
//        String inputString="Amardeep Singh";



//        String string = Arrays.stream(inputString.split(" "))
//                .reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2)
//                .get();
//
//        List<Map.Entry<Character, Long>> list = inputString.toLowerCase()
//                .chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet().stream()
//                .filter(c -> c.getValue()>1)
//                .toList();
//
//        Map<Character, Long> collect = inputString.toLowerCase()
//                .chars()
//                .mapToObj(c -> (char) c)
//                .filter(c -> "aeiou".indexOf(c) != -1)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//
//        String collect1 = IntStream.range(0,inputString.length())
////                .chars()
//                .mapToObj(c -> inputString.charAt(inputString.length() - 1 - c))
//                .map(String::valueOf)
//                .collect(Collectors.joining());
//
//        //for reverse string
//        String collect2=IntStream.range(0,inputString.length())
//                        .mapToObj(c-> inputString.charAt(inputString.length()-1-c))
//                        .map(String::valueOf)
//                        .collect(Collectors.joining());
//
//        //string reverse
//        boolean b = IntStream.range(0, inputString.length() / 2)
//                .allMatch(c -> inputString.charAt(c) == inputString.charAt(inputString.length() - 1 - c));
//
//        //decond non repet
//        Map.Entry<Character, Long> characterLongEntry = inputString.toLowerCase()
//                .chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet().stream()
//                .filter(c -> c.getValue() == 1)
//                .skip(1)
//                .findFirst().get();
//
//        int[] prices ={7,1,5,3,6,4};
//        int min = Integer.MAX_VALUE;
//        int maxProfit = 0;
//
//        for (int price : prices) {
//            min = Math.min(min, price);
//            maxProfit = Math.max(maxProfit, price - min);
//        }


//        List<EmployeeNew> employeeList=new ArrayList<>();
        List<EmployeeNew> employeesList = Arrays.asList(

                new EmployeeNew(1,  "Amar",     "IT",        75000, 30),
                new EmployeeNew(2,  "Ravi",     "HR",        55000, 28),
                new EmployeeNew(3,  "Simran",   "Finance",   82000, 35),
                new EmployeeNew(4,  "Neha",     "IT",        92000, 40),
                new EmployeeNew(5,  "Karan",    "IT",        75000, 30),  // duplicate salary
                new EmployeeNew(6,  "Priya",    "HR",        60000, 32),
                new EmployeeNew(7,  "Arjun",    "Finance",   82000, 29),  // duplicate salary
                new EmployeeNew(8,  "Megha",    "Admin",     45000, 26),
                new EmployeeNew(9,  "Ravi",     "IT",        88000, 34),  // duplicate name
                new EmployeeNew(10, "Sonia",    "Admin",     47000, 29),
                new EmployeeNew(11, "Deepak",   "Finance",   99000, 45),
                new EmployeeNew(12, "Anjali",   "HR",        61000, 31),
                new EmployeeNew(13, "Rohit",    "IT",        68000, 27),
                new EmployeeNew(14, "Sneha",    "Finance",   73000, 38),
                new EmployeeNew(15, "Vikas",    "Admin",     52000, 33),
                new EmployeeNew(16, "Pooja",    "IT",        92000, 41),  // duplicate high salary
                new EmployeeNew(17, "Aman",     "HR",        58000, 36),
                new EmployeeNew(18, "Kriti",    "Finance",   77000, 30),
                new EmployeeNew(19, "Ravi",     "Admin",     47000, 25),  // duplicate name + salary
                new EmployeeNew(20, "Nisha",    "IT",        88000, 29)   // duplicate salary
        );

        //highest paid in each dept
        Map<String, Optional<EmployeeNew>> collect = employeesList.stream()
                .collect(Collectors.groupingBy(EmployeeNew::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(EmployeeNew::getSalary)
                        )));
        //second highest salery
        Optional<Double> secondHighestSal = employeesList.stream()
                .map(EmployeeNew::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        //partition by field
        Map<Boolean, List<EmployeeNew>> collect1 = employeesList.stream()
                .collect(Collectors.partitioningBy(employeeNew -> employeeNew.getSalary() > 5000));

        //duplicate names
        Set<String> collect2 = employeesList.stream()
                .collect(Collectors.groupingBy(EmployeeNew::getName, Collectors.counting()))
                .entrySet().stream()
                .filter(e->e.getValue()>1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        List<EmployeeNew> top3sal = employeesList.stream()
                .sorted(Comparator.comparingDouble(EmployeeNew::getSalary).reversed())
                .limit(3)
                .toList();
//        top 2nd sal
        Optional<EmployeeNew> first = employeesList.stream()
                .sorted(Comparator.comparingDouble(EmployeeNew::getSalary).reversed())
                .limit(3)
                .skip(1)
                .findFirst();

        //Group By Department → Then Sort by Salary Desc
        Map<String, List<EmployeeNew>> collect3 = employeesList.stream()
                .collect(Collectors.groupingBy(EmployeeNew::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(EmployeeNew::getSalary).reversed())
                                        .toList())));

        System.out.println("*** CODDING TEST 👍 ***");
//        System.out.println(" 📥 INPUT : "+ inputString+"\n");
        System.out.println(" 📥 INPUT : "+ "LIST of EmployeeNews" +"\n");
        System.out.println(" 📤 OUTPUT : "+ collect3);
    }


}
