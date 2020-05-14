/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;

import com.mycompany.myapp.models.Event;
import com.mycompany.myapp.models.Project;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceEvent {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Event> events;

    public ServiceEvent() {
        request = DataSource.getInstance().getRequest();
    }
    
    
    public ArrayList<Event> getAllEvents(int idev) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Event/Index/"+idev;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvent(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return events;
    }
        
        
        
        
    public ArrayList<Event> parseEvent(String jsonText) {
        try {
            events = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> eventListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
            for (Map<String, Object> obj : list) {
                Event e = new Event();
                int id = (int)Float.parseFloat(obj.get("idev").toString());
                e.setIdev(id);
                String titre = obj.get("titreEvent").toString();
                e.setTitreEvent(titre);
                int cap = (int)Float.parseFloat(obj.get("numeroEvent").toString());
                e.setNumeroEvent(cap);
                String type = obj.get("typeEvent").toString();
                e.setTypeEvent(type);
                events.add(e);
                //Map<String, Object> project = (Map<String, Object>) obj.get("project");
                //int project_id= (int)Float.parseFloat(project.get("id").toString());
                //int status = (int)Float.parseFloat(obj.get("status").toString());
            }

        } catch (IOException ex) {
        }

        return events;
    }
    
    
    public boolean resultOK;
    public boolean addEvent(Event ev) {
        
        String url = Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/Event/Add" + "/" + ev.getIdev()+ "/" + ev.getTitreEvent() + "/" 
                + ev.getTypeEvent()+ "/" + ev.getNumeroEvent() + "/" + ev.getDateEvent() + "/" + ev.getEnddateEvent() +"/"+  ev.getImage_name() ;
        ConnectionRequest req = new ConnectionRequest(url);
        req.addResponseListener((NetworkEvent evt) -> {
            resultOK = req.getResponseCode()==200; //Code Http 200 OK 
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    public boolean deleteEvent(int idev) {
        String url = Statics.BASE_URL + "/ProjetPi/Hypocampus/web/app_dev.php/api/Event/Delete/"+idev ;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;

        }
    
    
    public void updateEvent(Event p,int idev) {
        try {
             
                MultipartRequest cr = new MultipartRequest();
                cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/Event/Update/"+idev);
                cr.setPost(true);
                cr.addArgument("Titre", p.getTitreEvent());
                cr.addArgument("Type", p.getTypeEvent());
                //cr.addArgument("Cap", Integer.parseInt(p.getNumeroEvent() ) );
                cr.addArgument("Img", p.getImage_name());

                String start_date = parseDate(p.getDateEvent().toString() ,"", "MM-dd-yyyy");
                String end_date = parseDate(p.getEnddateEvent().toString() ,"", "MM-dd-yyyy");

                cr.addArgument("start_date",start_date);
                cr.addArgument("end_date",end_date);

                cr.addResponseListener(e -> {

                    if(cr.getResponseCode() == 200)
                        Dialog.show("Modifier","Event Modifié " +  p.getTitreEvent(), "OK", null);
                    });

                    NetworkManager.getInstance().addToQueueAndWait(cr);
            }
        
        catch (ParseException e1) {}        
    }
    
    
    
    public String parseDate(String dateTime,String inputPattern, String outputPattern) throws ParseException {

       SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
       SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

       Date date = inputFormat.parse(dateTime);
       String str = outputFormat.format(date);

       return str;
}

    
}
