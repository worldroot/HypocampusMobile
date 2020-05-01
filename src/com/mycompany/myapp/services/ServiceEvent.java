/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.mycompany.myapp.models.Event;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
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
    
    
        public ArrayList<Event> getAllEvents() {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Event/Index";

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

    
}
