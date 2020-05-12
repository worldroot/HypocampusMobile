/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.models;

/**
 *
 * @author ASUS
 */
public class Participant {
    
    private String nomp;
    private String prenomp;    
    private String email;
    private String passwordp;
    private int choix;
    private int review;
    private int status;

    

    public Participant(String nomp, String prenomp, String email, String passwordp, int choix, int review) {
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.email = email;
        this.passwordp = passwordp;
        this.choix = choix;
        this.review = review;
    }
    
    public Participant(String nomp, String prenomp, String email, String passwordp, int choix, int review, int status) {
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.email = email;
        this.passwordp = passwordp;
        this.choix = choix;
        this.review = review;
        this.status = status;
    }
    
    public Participant(String nomp) {
        this.nomp = nomp;
    }
          
    public Participant() {
    }
    
    @Override
    public String toString() {
        return "Participant{" + "nomp=" + nomp + ", prenomp=" + prenomp + ", email=" + email + ", passwordp=" + passwordp + ", choix=" + choix + ", review=" + review + '}';
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getPrenomp() {
        return prenomp;
    }

    public void setPrenomp(String prenomp) {
        this.prenomp = prenomp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordp() {
        return passwordp;
    }

    public void setPasswordp(String passwordp) {
        this.passwordp = passwordp;
    }

    public int getChoix() {
        return choix;
    }

    public void setChoix(int choix) {
        this.choix = choix;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
    
}
