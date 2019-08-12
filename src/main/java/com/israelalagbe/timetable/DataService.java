/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable;

import cat.quickdb.db.AdminBase;
import com.israelalagbe.timetable.models.Course;
import com.israelalagbe.timetable.models.Department;
import com.israelalagbe.timetable.models.Example;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author User
 */
public class DataService {
    private AdminBase admin;
    public DataService() {
        admin = AdminBase.initialize(AdminBase.DATABASE.MYSQL,
                "localhost", "3306", "timetable", "root", "");
       
    }
    
    public void addCourse(Course course){
        admin.save(course);
    }
    
    public List<Course> getCourses(){
        return admin.obtain(new Course()).findAll();
    }
    public void addDepartment(Department department){
        admin.save(department);
    }
    public List<Department> getDepartments(){
        return admin.obtain(new Department()).findAll();
    }
}
