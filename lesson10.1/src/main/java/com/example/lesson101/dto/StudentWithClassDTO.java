package com.example.lesson101.dto;

public class StudentWithClassDTO {
    private Long id;
    private String name;
    private int age;
    private String gender;

    private ClassDTO classInfo;

    public static class ClassDTO {
        private Long id;
        private String name;
        private String teacherName;
        private String address;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ClassDTO getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassDTO classInfo) {
        this.classInfo = classInfo;
    }
}
