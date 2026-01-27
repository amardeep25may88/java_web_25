package com.javaweb.stream;

import java.util.List;

public class EmployeeStreamTest {

    public static void main(String[] args) {
        List<Employee> employees = EmployeeDataBase.getAllEmployees();

//        normal collection iteration
//        employees.forEach(System.out::println);

        //stream collection iteration
//        employees.stream().forEach(System.out::println);

        List<Employee> employeeSalFilter = employees.stream()
                .filter(employee -> employee.getDept().equals("Development") && employee.getSalary()>8000)
                .toList();





        System.out.println("employeeSalFilter :: "+employeeSalFilter);
    }
}
