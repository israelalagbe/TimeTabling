/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.israelalagbe.timetable;

import cat.quickdb.db.AdminBase;
import com.israelalagbe.timetable.models.Course;
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
        Course course = new Course();
//        return admin.obtainAll(course,"1=1");
        
        //System.out.println("Size is: "+admin.obtain(new Example()).findAll().size());
        return admin.obtain(new Course()).findAll();
    }
}
