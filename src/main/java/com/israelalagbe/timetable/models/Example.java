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
 * @author User
 */

@Table("Hello")
public class Example{
    @Column(type = TYPES.PRIMARYKEY)
    @ColumnDefinition(autoIncrement = true, length = 11,
            primary = true, type = DATATYPE.INT)
    private int id;
   
   @Column
   private String me;

    public Example() {
        me="jdjdjdj";
    }
   
    public int getId(){
        return id;
    }

    public String getMe() {
        return me;
    }
    
}
