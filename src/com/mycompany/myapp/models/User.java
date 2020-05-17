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
public class User {
	private int id;
	private String username;
	private String password;
	private String roles;
	private String email;

	public User() {
	}

	public User(String username, String password, String roles, String email) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.email = email;
	}

	public User(int id, String username, String password, String roles, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.email = email;
	}

	public User(int id, String username, String roles, String email) {
		this.id = id;
		this.username = username;
		this.roles = roles;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}