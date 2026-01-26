package com.javaweb.stream;

import java.util.List;

public class Student {

    private String name;
    private int age;
    private String gender;
    private String dept;
    private String city;
    private int rank;
    private List<String> mobileNumbers;

    public Student(String name, int age, String gender, String dept,
                   String city, int rank, List<String> mobileNumbers) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.dept = dept;
        this.city = city;
        this.rank = rank;
        this.mobileNumbers = mobileNumbers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", dept='" + dept + '\'' +
                ", city='" + city + '\'' +
                ", rank=" + rank +
                ", mobileNumbers=" + mobileNumbers +
                '}';
    }

    // Getters (optional)
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDept() { return dept; }
    public String getCity() { return city; }
    public int getRank() { return rank; }
    public List<String> getMobileNumbers() { return mobileNumbers; }

}
