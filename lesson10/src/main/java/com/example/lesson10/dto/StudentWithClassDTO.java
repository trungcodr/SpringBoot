package com.example.lesson10.dto;

public class StudentWithClassDTO {
    private long id;
    private String name;
    private int age;
    private String gender;

    private ClassDTO classInfo;

    public static class ClassDTO {
        private long id;
        private String name;
        private String teacherName;
        private String address;

        public long getId() {
            return id;
        }

        public void setId(long id) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
