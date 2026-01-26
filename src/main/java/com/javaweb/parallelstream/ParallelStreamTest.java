package com.javaweb.parallelstream;

import com.javaweb.stream.Student;
import com.javaweb.stream.StudentDummyData;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamTest {

    public static void main(String[] args) {

        Long start;
        Long end;
//
//        start=System.currentTimeMillis();
//            IntStream.range(1,100).forEach(System.out::println);
//        end=System.currentTimeMillis();
//
//        System.out.println("Plain stream took time :: "+(end-start));
//
//        System.out.println("===================== ***** ====================");
//
//        start=System.currentTimeMillis();
//        IntStream.range(1,100).parallel().forEach(System.out::println);
//        end=System.currentTimeMillis();
//
//        System.out.println("Parallel stream took time :: "+(end-start));


//        IntStream.range(1,10).forEach(x->{
//            System.out.println("Thread :: " + Thread.currentThread().getName()+" : "+x);
//        });
//
//        System.out.println("===================== ***** ====================");
//
//        IntStream.range(1,10).parallel().forEach(x->{
//            System.out.println("Thread :: " + Thread.currentThread().getName()+" : "+x);
//        });

        List<Student> listOfStudents= StudentDummyData.getStudents();

        start=System.currentTimeMillis();

            Map<String, Double> normalStreamList=listOfStudents.stream()
                .collect(Collectors.groupingBy(Student::getGender,Collectors.averagingInt(Student::getAge)));

        end=System.currentTimeMillis();

        System.out.println("Plain stream took time :: "+(end-start));

        System.out.println("===================== ***** ====================");

        start=System.currentTimeMillis();

            Map<String, Double> parallelStreamList=listOfStudents.parallelStream()
                .collect(Collectors.groupingBy(Student::getGender,Collectors.averagingInt(Student::getAge)));

        end=System.currentTimeMillis();

        System.out.println("Parallel stream took time :: "+(end-start));
//        System.out.println((long) listOfStudents.size());

    }
}
