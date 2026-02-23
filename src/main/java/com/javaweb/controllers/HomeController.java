package com.javaweb.controllers;

import com.javaweb.service.KafkaProducer;
import com.javaweb.stream.Employee;
import com.javaweb.stream.EmployeeDataBase;
import com.javaweb.stream.Student;
import com.javaweb.stream.StudentDummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/app/v1/java25")
public class HomeController {

    @Autowired
    private KafkaProducer kafkaProducer;

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

    @GetMapping("/kafka/producer/{msg}")
    public ResponseEntity<?> produceMsg(@PathVariable String msg){
        try {
            kafkaProducer.sendMsg(msg);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("msg published successfully ");
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
