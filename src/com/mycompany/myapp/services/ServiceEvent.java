/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
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
import static com.mycompany.myapp.utils.Statics.BASE_URL;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
                //String image = obj.get("image_name").toString();
                //e.setImage_name(image);
/*                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                
                String start_date_S=obj.get("DateEvent").toString();
                Date start_date = formatter.parse(start_date_S); 
                e.setDateEvent(start_date);

                String endDate_S=obj.get("enddateEvent ").toString();
                Date endDate = formatter.parse(endDate_S);
                e.setEnddateEvent(endDate);
 */               
               
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
    
    
    
    public boolean deleteEvent(Event e) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Event/Delete/"+e.getIdev() ;

        System.out.println(url);               
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
        }
    
    

    
    public boolean updateEvent(Event ev)
     {
        
        String url =  Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/Event/Update" + "/" + ev.getIdev()+ "/" + ev.getTitreEvent() + "/" 
                + ev.getNumeroEvent() + "/" + ev.getTypeEvent()+  "/" + ev.getDateEvent() + "/" + ev.getEnddateEvent() + "/" +  ev.getImage_name() ;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
     
     }
    
    
    
    public String parseDate(String dateTime,String inputPattern, String outputPattern) throws ParseException {

       SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
       SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

       Date date = inputFormat.parse(dateTime);
       String str = outputFormat.format(date);

       return str;
}
      public List<Integer> Stati() {
        List<Integer> li = new ArrayList<Integer>();
        try {
            ConnectionRequest r = new ConnectionRequest();

            r.setUrl(BASE_URL+"/Hypocampus/web/app_dev.php/api/Event/Stat");
            r.setPost(false);
            r.setHttpMethod("GET");

            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            r.setDisposeOnCompletion(dlg);
            NetworkManager.getInstance().addToQueueAndWait(r);

            Map<String, Object> response = (Map<String, Object>) new JSONParser().parseJSON(
                    new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            List<String> content = (List<String>) response.get("root");
            System.out.println("content ====> " + content);
            for (String obj : content) {
                li.add(Integer.parseInt(obj));
                System.out.println("li ====> " + li);
            }
            
        } catch (IOException err) {
           
        }
        return li;
    }

    
}
