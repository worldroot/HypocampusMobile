/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.models;

import java.util.Date;

/**
 *
 * @author Houcem
 */
public class team {
	
	private int id; 
     
    private String teamname; 
    private Date dateofcreation; 

	public team() {
	}

	public team(int id) {
		this.id = id;
	}

	public team(String teamname, Date dateofcreation) {
		this.teamname = teamname;
		this.dateofcreation = dateofcreation;
	}

	public team(int id, String teamname, Date dateofcreation) {
		this.id = id;
		this.teamname = teamname;
		this.dateofcreation = dateofcreation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public Date getDateofcreation() {
		return dateofcreation;
	}

	public void setDateofcreation(Date dateofcreation) {
		this.dateofcreation = dateofcreation;
	}

	@Override
	public String toString() {
		return  teamname;
	}
	
}
