
package com.test.demo.list;

import com.alibaba.fastjson.JSON;
import com.test.demo.domain.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TestList {

    public static void main(String[] args) {

        List<Student> list1 = new ArrayList<>();
        List<Student> list2 = new ArrayList<>();

        Student student1 = new Student();
        student1.setName("111");
        student1.setClassName("aaa");

        Student student2 = new Student();
        student2.setName("222");
        student2.setClassName("bbb");

        Student student3 = new Student();
        student3.setName("333");

        Student student4 = new Student();
        student4.setName("444");


        list1.add(student1);
        list1.add(student2);

        list2.add(student3);
        list2.add(student4);

        boolean b = list1.removeAll(list2);

        log.info(JSON.toJSONString(list1));

    }
}
