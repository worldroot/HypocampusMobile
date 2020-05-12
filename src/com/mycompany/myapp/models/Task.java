/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mehdibehira
 */
public class Task implements Serializable {
    private int id;
    private int backlog_id;
    private String backlog_name;
    private String title;
    private String description_fonctionnel;
    private String description_technique;
    private int story_points;
    private Date created_date;
    private Date finished_date;
    private String state;
    private int priority;
    private int archive;
    private int sprint_id;
    private String sprint_name;
    private int user_id;
    private String user_name;

    private static final long serialVersionUID = 1L;
    public Task(int id, int backlog_id, String title, String description_fonctionnel, String description_technique, int story_points, Date created_date, Date finished_date, String state, int priority, int archive, int sprint_id) {
        this.id = id;
        this.backlog_id = backlog_id;
        this.title = title;
        this.description_fonctionnel = description_fonctionnel;
        this.description_technique = description_technique;
        this.story_points = story_points;
        this.created_date = created_date;
        this.finished_date = finished_date;
        this.state = state;
        this.priority = priority;
        this.archive = archive;
        this.sprint_id = sprint_id;
    }
    
    public Task(int id, int backlog_id, String title, String description_fonctionnel, String description_technique, int story_points, String state, int priority, int archive, int sprint_id, String sprint_name) {
        this.id = id;
        this.backlog_id = backlog_id;
        this.title = title;
        this.description_fonctionnel = description_fonctionnel;
        this.description_technique = description_technique;
        this.story_points = story_points;
        this.state = state;
        this.priority = priority;
        this.archive = archive;
        this.sprint_id = sprint_id;
        this.sprint_name = sprint_name;
    }

    public Task(int backlog_id, String title, String description_fonctionnel, String description_technique, int story_points, Date created_date, Date finished_date, String state, int priority, int archive, int sprint_id) {
        this.backlog_id = backlog_id;
        this.title = title;
        this.description_fonctionnel = description_fonctionnel;
        this.description_technique = description_technique;
        this.story_points = story_points;
        this.created_date = created_date;
        this.finished_date = finished_date;
        this.state = state;
        this.priority = priority;
        this.archive = archive;
        this.sprint_id = sprint_id;
    }

    public Task() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBacklog_name() {
        return backlog_name;
    }

    public void setBacklog_name(String backlog_name) {
        this.backlog_name = backlog_name;
    }

    public String getSprint_name() {
        return sprint_name;
    }

    public void setSprint_name(String sprint_name) {
        this.sprint_name = sprint_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    
    
    public int getBacklog_id() {
        return backlog_id;
    }

    public void setBacklog_id(int backlog_id) {
        this.backlog_id = backlog_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription_fonctionnel() {
        return description_fonctionnel;
    }

    public void setDescription_fonctionnel(String description_fonctionnel) {
        this.description_fonctionnel = description_fonctionnel;
    }

    public String getDescription_technique() {
        return description_technique;
    }

    public void setDescription_technique(String description_technique) {
        this.description_technique = description_technique;
    }

    public int getStory_points() {
        return story_points;
    }

    public void setStory_points(int story_points) {
        this.story_points = story_points;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getFinished_date() {
        return finished_date;
    }

    public void setFinished_date(Date finished_date) {
        this.finished_date = finished_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public int getSprint_id() {
        return sprint_id;
    }

    public void setSprint_id(int sprint_id) {
        this.sprint_id = sprint_id;
    }

    @Override
    public String toString() {
        return  "Titre:"+title+"\n"
                + "Description: "+description_fonctionnel+"\n"
                + "created_date: "+created_date+"\n"
                + "Date Estimer: "+finished_date+"\n\n"
                + "                      Story Points: "+story_points+"\n"
                + "*******************************";
    }
    
    
    
   
    
}
