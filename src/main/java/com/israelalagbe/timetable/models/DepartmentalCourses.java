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
@Table("departmental_courses")
public class DepartmentalCourses {
    @Column(type = TYPES.PRIMARYKEY)
    @ColumnDefinition(autoIncrement = true, length = 11,
            primary = true, type = DATATYPE.INT)
    private int id;
    private Department department;
    private Course course;
    private String courseType;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    @Override
    public String toString() {
        return this.getCourse().getName()+" - "+getDepartment().getName(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
