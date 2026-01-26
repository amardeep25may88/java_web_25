package com.javaweb.controllers;

import com.javaweb.employeestream.Employee;
import com.javaweb.employeestream.EmployeeDataBase;
import com.javaweb.stream.Student;
import com.javaweb.stream.StudentDummyData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/students")
    public List<Student> getTestResults(){

        List<Student> students = StudentDummyData.getStudents();
        return students.stream().toList();
    }

    @GetMapping("/employee")
    List<Employee> getEmployee(){

        List<Employee> employees = EmployeeDataBase.getAllEmployees();


        return employees.stream()
                .filter(employee -> employee.getDept().equals("Development") && employee.getSalary()>5000)
                .toList();

    }

}
