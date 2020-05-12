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
public class Project {
    private int id;
    private String name;
    private String owner;
    private Date start_date;
    private Date end_date;
    private String description;
    private int history;

    public Project() {
    }

    public Project(int id, String name, String owner, Date start_date, Date end_date, String description, int history) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.history = history;
    }

    public Project(int id, String name, String owner, Date start_date, Date end_date, String description) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
    }
        public Project( int id,String name, String owner, String description) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
    }


    public Project(String name, String owner, Date start_date, Date end_date, String description, int history) {
        this.name = name;
        this.owner = owner;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.history = history;
    }

    public Project(int id) {
        this.id = id;
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Project(int id, int history) {
        this.id = id;
        this.history = history;
    }






 
    
    

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", owner=" + owner + ", start_date=" + start_date + ", end_date=" + end_date + ", description=" + description + '}';
    }




    
 
    
}
