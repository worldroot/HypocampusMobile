/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.models;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Event {
    
    private int idev;
    private String titreEvent;
    private int numeroEvent;
    private String typeEvent;
    private Date dateEvent;
    private Date enddateEvent;
    private String image_name;
    private Date updated_at;

    public Event(int idev, String titreEvent, int numeroEvent, String typeEvent, Date dateEvent, Date enddateEvent, String image_name) {
        this.idev = idev;
        this.titreEvent = titreEvent;
        this.numeroEvent = numeroEvent;
        this.typeEvent = typeEvent;
        this.dateEvent = dateEvent;
        this.enddateEvent = enddateEvent;
        this.image_name = image_name;
    
    }

    public Event(String titreEvent, String typeEvent, int numeroEvent, Date dateEvent, Date enddateEvent, String image_name) {
        this.titreEvent = titreEvent;
        this.numeroEvent = numeroEvent;
        this.typeEvent = typeEvent;
        this.dateEvent = dateEvent;
        this.enddateEvent = enddateEvent;
        this.image_name = image_name;
        
    }
    public Event(String titreEvent, int numeroEvent, String typeEvent, Date dateEvent, Date enddateEvent) {
        this.titreEvent = titreEvent;
        this.numeroEvent = numeroEvent;
        this.typeEvent = typeEvent;
        this.dateEvent = dateEvent;
        this.enddateEvent = enddateEvent;
    }

    public Event(String titreEvent, String typeEvent, int numeroEvent) {
        this.titreEvent = titreEvent;
        this.numeroEvent = numeroEvent;
        this.typeEvent = typeEvent;
    }

    public Event(int idev) {
        this.idev = idev;
    }

    public Event(String titreEvent,  String typeEvent,int numeroEvent, Date dateEvent, Date enddateEvent) {
        
        this.titreEvent = titreEvent;
        this.numeroEvent = numeroEvent;
        this.typeEvent = typeEvent;
        this.dateEvent = dateEvent;
        this.enddateEvent = enddateEvent;
    }
    
    
    
    
    
    public Event() {
    }

   
    
    
    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
      
    public int getIdev() {
        return idev;
    }

    public void setIdev(int idev) {
        this.idev = idev;
    }

    public String getTitreEvent() {
        return titreEvent;
    }

    public void setTitreEvent(String titreEvent) {
        this.titreEvent = titreEvent;
    }

    public int getNumeroEvent() {
        return numeroEvent;
    }

    public void setNumeroEvent(int numeroEvent) {
        this.numeroEvent = numeroEvent;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Date getEnddateEvent() {
        return enddateEvent;
    }

    public void setEnddateEvent(Date enddateEvent) {
        this.enddateEvent = enddateEvent;
    }

    @Override
    public String toString() {
        return "Event{" + "idev=" + idev + ", titreEvent=" + titreEvent + ", numeroEvent=" + numeroEvent + ", typeEvent=" + typeEvent + ", dateEvent=" + dateEvent + ", enddateEvent=" + enddateEvent + ", image_name=" + image_name + ", updated_at=" + updated_at + '}';
    }
    
    

    

    
           
}
