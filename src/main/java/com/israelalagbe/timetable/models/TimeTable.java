/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable.models;

import cat.quickdb.annotation.Column;
import cat.quickdb.annotation.ColumnDefinition;
import cat.quickdb.annotation.Definition;
import cat.quickdb.annotation.Definition.DATATYPE;
import cat.quickdb.annotation.Properties.TYPES;
import cat.quickdb.annotation.Table;

/**
 *
 * @author Israel Alagbe
 */
@Table("TimeTables")
public class TimeTable {

    @Column(type = TYPES.PRIMARYKEY)
    @ColumnDefinition(autoIncrement = true, length = 11,
            primary = true, type = DATATYPE.INT)
    private int id;
    private Course course;
    private Level level;
    private Department department;
    private String day;
    private String time;
    private Lecturer lecturer;

    public int getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

}
