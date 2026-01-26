package com.javaweb.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StudentStreamTest {

    public static void main(String[] args) {


        List<Student> students = StudentDummyData.getStudents();
//        students.forEach(s -> System.out.println(s.getName() + " - " + s.getDept()));

        List<Student> rankeFilteredlistRang_50_100 = students.stream()
                .filter(student -> student.getRank() > 50 && student.getRank() < 100)
                .toList();

        List<Student>  cityFilterList= students.stream()
                        .filter(student -> student.getCity().equals("Pune"))
                        .sorted(Comparator.comparing(Student::getName
                                ,Comparator.reverseOrder()  // add this input if want order of sorting
                        )).toList();

        Set<String> deptFilterList= students.stream()
                .map(Student::getDept)
                .distinct()
//                .toList()
                .collect(Collectors.toSet());

        List<List<String>> contactList= students.stream()
                .map(Student::getMobileNumbers)// .map() method used for one to one object mappings
                .toList();

        List<String> contactFlatList= students.stream()
                .flatMap(student -> student.getMobileNumbers().stream())// .flatMap() is used to one to many object mapping
                .toList();

        Map<String, List<Student>> groutStudentByDeptNameMap=students.stream()
                .collect(Collectors.groupingBy(Student::getDept));

        Map<String, Long> groutStudentByDeptNameMapWithCount=students.stream()
                .collect(Collectors.groupingBy(Student::getDept,Collectors.counting()));

        Map.Entry<String, Long> groutStudentByDeptNameMapWithCountMax=students.stream()
                .collect(Collectors.groupingBy(Student::getDept,Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();

        Map<String, Double> groutStudentByGenderAgeAvg=students.stream()
                .collect(Collectors.groupingBy(Student::getGender,Collectors.averagingInt(Student::getAge)));

        Map<String, Optional<Student>> groutStudentByDeptNameMinRank=students.stream()
                .collect(Collectors.groupingBy(Student::getDept,
                        Collectors.minBy(Comparator.comparing(Student::getRank))));

        Student minRankStudent = students.stream()
                .sorted(Comparator.comparing(Student::getRank))
                .skip(1)
                .findFirst().get();


//        System.out.println(rankeFilteredlistRang_50_100);
//        System.out.println(cityFilterList);
//        System.out.println("deptFilterList  :: "+deptFilterList);
//        System.out.println("groutStudentByDeptNameMap  :: "+groutStudentByDeptNameMap);
        System.out.println("groutStudentByDeptNameMapWithCount  :: "+groutStudentByDeptNameMapWithCount);
        System.out.println("groutStudentByDeptNameMapWithCountMax  :: "+groutStudentByDeptNameMapWithCountMax);
        System.out.println("groutStudentByGenderAgeAvg  :: "+groutStudentByGenderAgeAvg);
        System.out.println("groutStudentByDeptNameMinRank  :: "+groutStudentByDeptNameMinRank);
        System.out.println("minRankStudent  :: "+minRankStudent);


    }
}
