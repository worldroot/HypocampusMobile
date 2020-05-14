/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.models;

/**
 *
 * @author Houcem
 */
public class meeting {
	
	private int id; 
    private int team_id; 
    private String description; 
    private String duration; 
    private int nbrmeeting;    
 
    public meeting(int id, int team_id, String description, String duration, int nbrmeeting) { 
        this.id = id; 
        this.team_id = team_id; 
        this.description = description; 
        this.duration = duration; 
        this.nbrmeeting = nbrmeeting; 
    } 
 
    public meeting(int team_id, String description, String duration, int nbrmeeting) { 
        this.team_id = team_id; 
        this.description = description; 
        this.duration = duration; 
        this.nbrmeeting = nbrmeeting; 
    } 
 
    public meeting(int id) { 
        this.id = id; 
    } 

	public meeting() {
	}
	
	
 
    public int getId() { 
        return id; 
    } 
 
    public void setId(int id) { 
        this.id = id; 
    } 
 
    public int getTeam_id() { 
        return team_id; 
    } 
 
    public void setTeam_id(int team_id) { 
        this.team_id = team_id; 
    } 
 
    public String getDescription() { 
        return description; 
    } 
 
    public void setDescription(String description) { 
        this.description = description; 
    } 
 
    public String getDuration() { 
        return duration; 
    } 
 
    public void setDuration(String duration) { 
        this.duration = duration; 
    } 
 
    public int getNbrmeeting() { 
        return nbrmeeting; 
    } 
 
    public void setNbrmeeting(int nbrmeeting) { 
        this.nbrmeeting = nbrmeeting; 
    } 
 
    @Override 
    public String toString() { 
        return "meeting{" + "id=" + id + ", team_id=" + team_id + ", description=" + description + ", duration=" + duration + ", nbrmeeting=" + nbrmeeting + '}'; 
    } 
	
}
