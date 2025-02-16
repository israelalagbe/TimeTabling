package com.israelalagbe.timetable;

import cat.quickdb.db.AdminBase;
import com.israelalagbe.timetable.models.Course;
import com.israelalagbe.timetable.models.Department;
import com.israelalagbe.timetable.models.DepartmentalCourses;
import com.israelalagbe.timetable.models.Example;
import com.israelalagbe.timetable.models.Lecturer;
import com.israelalagbe.timetable.models.Level;
import com.israelalagbe.timetable.models.TimeTable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Israel Alagbe
 */
public class DataService {
    private AdminBase admin;
    public DataService() {
       admin = AdminBase.initialize(AdminBase.DATABASE.SQLite,
                "timetable.db");
       
    }
    
    public void addCourse(Course course){
        admin.save(course);
    }
    public void saveModel(Object model){
        admin.save(model);
    }
    public List  getModels(Object model){
        return admin.obtain(model).findAll();
    }
    
    public List<Course> getCourses(){
        return admin.obtain(new Course()).findAll();
    }
     public List<DepartmentalCourses> getDepartmentCoursesByDepartment(Department department){
         List<DepartmentalCourses> values=admin.obtain(new DepartmentalCourses()).findAll();
         List<DepartmentalCourses> filtered=new ArrayList<>();
         for(DepartmentalCourses dc:values){
             if(dc.getDepartment().getId()==department.getId()){
                 filtered.add(dc);
             }
         }
         return filtered;
    }
    public List<TimeTable> getTimetables(Department d, Level l){
      return admin.obtainAll(new TimeTable(),  String.format("department=%s AND level=%s", d.getId(),l.getId()));
    }
    public List<TimeTable> getTimetables(Course c){
      return admin.obtainAll(new TimeTable(),  String.format("course=%s", c.getId()));
    }
    public List<DepartmentalCourses> getDepartmentalCourses(Course c){
      return admin.obtainAll(new DepartmentalCourses(),  String.format("course=%s", c.getId()));
    }
    public void  deleteTimetables(Course c){
        for (TimeTable t: getTimetables(c)) {
            admin.delete(t);
        }
    }
    public void  deleteDepartmentalCourses(Course c){
        for (DepartmentalCourses dc: getDepartmentalCourses(c)) {
            admin.delete(dc);
        }
    }
    public void deleteModels(Object model,String query){
      List items= admin.obtainAll(model,  query);
        for (Object item : items) {
            deleteModel(item);
        }
    }
    public void deleteModel(Object model){
        admin.delete(model);
    }
    public List<TimeTable> getAllTimetables(){
       return admin.obtain(new TimeTable()).findAll();
    }
    public List<Lecturer> getLecturers(){
        return admin.obtain(new Lecturer()).findAll();
    }
    public void addDepartment(Department department){
        admin.save(department);
    }
    public List<Department> getDepartments(){
        return admin.obtain(new Department()).findAll();
    }
}
