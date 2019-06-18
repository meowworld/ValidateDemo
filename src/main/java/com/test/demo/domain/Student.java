package com.test.demo.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.demo.group.AddValid;
import lombok.Data;

import javax.validation.constraints.NotNull;

public class Student {

    private String name;

    private Integer age;

    private String className;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
