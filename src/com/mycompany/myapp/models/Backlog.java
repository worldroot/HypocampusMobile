/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.models;

/**
 *
 * @author mehdibehira
 */
public class Backlog {
        private int id;
    private int points_done;
    private int points_in_progress;
    private int points_to_do;
    private int project_id;

    public Backlog(int id, int points_to_do, int points_in_progress, int points_done, int project_id) {
        this.id = id;
        this.points_done = points_done;
        this.points_in_progress = points_in_progress;
        this.points_to_do = points_to_do;
        this.project_id = project_id;
    }

    public Backlog(int points_to_do, int points_in_progress, int points_done, int project_id) {
        this.points_done = points_done;
        this.points_in_progress = points_in_progress;
        this.points_to_do = points_to_do;
        this.project_id = project_id;
    }

    public Backlog(int aInt, int aInt0, String string) {
    }

    public Backlog() {
    }

    public Backlog(int id, int project_id) {
         this.id = id;
        this.project_id = project_id;
        
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints_done() {
        return points_done;
    }

    public void setPoints_done(int points_done) {
        this.points_done = points_done;
    }

    public int getPoints_in_progress() {
        return points_in_progress;
    }

    public void setPoints_in_progress(int points_in_progress) {
        this.points_in_progress = points_in_progress;
    }

    public int getPoints_to_do() {
        return points_to_do;
    }

    public void setPoints_to_do(int points_to_do) {
        this.points_to_do = points_to_do;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
    
    @Override
    public String toString() {
        return "Backlog{" + "id=" + id + ", points_done=" + points_done + ", points_in_progress=" + points_in_progress + ", points_to_do=" + points_to_do + ", project_id=" + project_id + '}';
    }
    
}
