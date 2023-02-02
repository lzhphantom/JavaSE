package com.lzhphantom.se.dto;

import lombok.Data;

import java.util.List;

@Data
public class SchoolClass {
    private int classNo;
    private List<Student> students;
}
