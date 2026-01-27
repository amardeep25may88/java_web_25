package com.javaweb.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StudentStreamTest {

    public static void main(String[] args) {


        List<Student> students = StudentDummyData.getStudents();
//        students.forEach(s -> System.out.println(s.getName() + " - " + s.getDept()));

        /*
          ====== ***  1. Rank filtered list with range 50 to 100 */
        System.out.println("=> CASE 1 : Rank filtered list with range 50 to 100 :: " +
                students.stream()
                    .filter(student -> student.getRank() > 50 && student.getRank() < 100)
                    .toList());

        /*
        ========= *** 2. city wise filtered list */
        System.out.println("=> CASE 2 : City wise filtered list :: " +
                students.stream()
                    .filter(student -> student.getCity().equals("Pune"))
                    .sorted(Comparator.comparing(Student::getName
                            ,Comparator.reverseOrder()  // add this input if want order of sorting
                    )).toList());
         /*
        ========= *** 3. department wise filtered list */
        System.out.println("=> CASE 3 : Department wise filtered list :: " +
                students.stream()
                    .map(Student::getDept)
                    .distinct()
//                      .toList()
                    .collect(Collectors.toSet()));
        /*
        ========= *** 4. Mobile number wise filtered list */
        System.out.println("=> CASE 4 : Mobile number wise filtered list ::: " +
                students.stream()
                        .map(Student::getMobileNumbers)// .map() method used for one to one object mappings
                        .toList());

        /*
        ========= *** 5. Mobile number wise flat filtered list */
        System.out.println("=> CASE 5 : Mobile number wise flat filtered list :: " +
                students.stream()
                        .flatMap(student -> student.getMobileNumbers().stream())// .flatMap() is used to one to many object mapping
                        .toList());

         /*
        ========= *** 6. Department wise name in map */
        System.out.println("=> CASE 6 : Department wise name in map :: " +
                students.stream()
                        .collect(Collectors.groupingBy(Student::getDept)));

        /*
        ========= *** 7. Department wise name in map with count */
        System.out.println("=> CASE 7 : Department wise name in map with count :: " +
                students.stream()
                        .collect(Collectors.groupingBy(Student::getDept,Collectors.counting())));

        /*
        ========= *** 8. Department wise name in map with count max */
        System.out.println("=> CASE 8 : Department wise name in map with count max :: " +
                students.stream()
                        .collect(Collectors.groupingBy(Student::getDept,Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get());

        /*
        ========= *** 9. Gender wise average age map  */
        System.out.println("=> CASE 9 : Gender wise average age map  :: " +
                students.stream()
                        .collect(Collectors.groupingBy(Student::getGender,Collectors.averagingInt(Student::getAge))));


        /*
        ========= *** 10. Department wise min rank map   */
        System.out.println("=> CASE 10 : Department wise min rank map :: " +
                students.stream()
                        .collect(Collectors.groupingBy(Student::getDept,
                                Collectors.minBy(Comparator.comparing(Student::getRank)))));

        /*
        ========= *** 11. min rank student   */
        System.out.println("=> CASE 11 : min rank student :: " +
                students.stream()
                        .sorted(Comparator.comparing(Student::getRank))
                        .skip(1)
                        .findFirst().get());

    }
}
