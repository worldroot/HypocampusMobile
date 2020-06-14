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
public class Entreprise {
	private int id;
	private String name;
	private String email;
    private Date createdate;
	
	public Entreprise() {
	}

	public Entreprise(int id, String name, String email, Date createdate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.createdate = createdate;
	}

	public Entreprise(String name, String email, Date createdate) {
		this.name = name;
		this.email = email;
		this.createdate = createdate;
	}

	public Entreprise(int id) {
		this.id = id;
	}
	
	

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Date getCreatedate() {
		return createdate;
	}
	
	
	
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
}
