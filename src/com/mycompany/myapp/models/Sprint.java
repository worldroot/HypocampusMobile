/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.models;

import java.util.Date;

/**
 *
 * @author mehdibehira
 */
public class Sprint {
    private int id;
    private int project_id;
    private String name;
    private String Completed_Task;
    private Date start_date_sprint;
    private Date end_date_sprint;
    private int etat;

    public Sprint() {
    }

    public Sprint(int id, int project_id, String name, Date start_date_sprint, Date end_date_sprint, int etat) {
        this.id = id;
        this.project_id = project_id;
        this.name = name;
        this.start_date_sprint = start_date_sprint;
        this.end_date_sprint = end_date_sprint;
        this.etat = etat;
    }

    public Sprint(int id, String name, Date start_date_sprint, Date end_date_sprint, int project_id) {
        this.id = id;
        this.project_id = project_id;
        this.name = name;
        this.start_date_sprint = start_date_sprint;
        this.end_date_sprint = end_date_sprint;
   
    }    
        public Sprint(String name, Date start_date_sprint, Date end_date_sprint) {
        this.name = name;
        this.start_date_sprint = start_date_sprint;
        this.end_date_sprint = end_date_sprint;
   
    }   
    public Sprint(int id, String name, Date start_date_sprint, Date end_date_sprint, int project_id,String Completed_Task) {
        this.id = id;
        this.project_id = project_id;
        this.name = name;
        this.start_date_sprint = start_date_sprint;
        this.end_date_sprint = end_date_sprint;
        this.Completed_Task = Completed_Task;
   
    }
     public Sprint(int id,String name, Date start_date_sprint, Date end_date_sprint, int project_id, int etat) {
        this.id = id;
        this.project_id = project_id;
        this.name = name;
        this.start_date_sprint = start_date_sprint;
        this.end_date_sprint = end_date_sprint;
        this.etat = etat;
   
    }
    public Sprint(String name, Date start_date_sprint, Date end_date_sprint, int project_id, int etat) {
        this.project_id = project_id;
        this.name = name;
        this.start_date_sprint = start_date_sprint;
        this.end_date_sprint = end_date_sprint;
        this.etat = etat;
   
    }
        public Sprint(String name, Date start_date_sprint, Date end_date_sprint, int project_id) {
        this.project_id = project_id;
        this.name = name;
        this.start_date_sprint = start_date_sprint;
        this.end_date_sprint = end_date_sprint;
        
   
    }


    public Sprint(int id) {
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompleted_Task() {
        return Completed_Task;
    }

    public void setCompleted_Task(String Completed_Task) {
        this.Completed_Task = Completed_Task;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_date_sprint() {
        return start_date_sprint;
    }

    public void setStart_date_sprint(Date start_date_sprint) {
        this.start_date_sprint = start_date_sprint;
    }

    public Date getEnd_date_sprint() {
        return end_date_sprint;
    }

    public void setEnd_date_sprint(Date end_date_sprint) {
        this.end_date_sprint = end_date_sprint;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Sprint{" + "id=" + id  + ", name=" + name + ", start_date_sprint=" + start_date_sprint + ", end_date_sprint=" + end_date_sprint + ", project_id=" + project_id+ '}';
    }


    
}
