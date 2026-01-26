package com.javaweb.stream;

import java.util.ArrayList;
import java.util.List;

public class StudentDummyData {

    public static List<Student> getStudents() {

        List<Student> students = new ArrayList<>();

        students.add(new Student("Student1", 18, "Male", "CSE", "Delhi", 1, List.of("900000001")));
        students.add(new Student("Student2", 19, "Female", "ECE", "Mumbai", 2, List.of("900000002", "900000102")));
        students.add(new Student("Student3", 20, "Male", "ME", "Pune", 3, List.of("900000003")));
        students.add(new Student("Student4", 21, "Female", "CSE", "Bangalore", 4, List.of("900000004")));
        students.add(new Student("Student5", 22, "Male", "EEE", "Chennai", 5, List.of("900000005", "900000105")));

        // ---- pattern continues ----
        for (int i = 6; i <= 1000; i++) {
            students.add(
                    new Student(
                            "Student" + i,
                            18 + (i % 5),
                            (i % 2 == 0) ? "Female" : "Male",
                            switch (i % 5) {
                                case 0 -> "CSE";
                                case 1 -> "ECE";
                                case 2 -> "ME";
                                case 3 -> "EEE";
                                default -> "CIVIL";
                            },
                            switch (i % 6) {
                                case 0 -> "Delhi";
                                case 1 -> "Mumbai";
                                case 2 -> "Bangalore";
                                case 3 -> "Pune";
                                case 4 -> "Hyderabad";
                                default -> "Chennai";
                            },
                            i,
                            List.of(
                                    "900000" + String.format("%03d", i),
                                    "900001" + String.format("%03d", i)
                            )
                    )
            );
        }

        return students;
    }

}
