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
import com.mycompany.myapp.models.Project;
import com.mycompany.myapp.models.Sprint;
import com.mycompany.myapp.models.Task;
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
 * @author 21694
 */
public class Servicesprint {
    
     public ArrayList<Sprint> Sprints;
     public ArrayList<Task> tasks;
     private ConnectionRequest request;
     public boolean resultOK;
         public Servicesprint() {
        request = DataSource.getInstance().getRequest();
    }
     public ArrayList<Sprint> parseSprints(String jsonText)  {
        try {
            Sprints = new ArrayList<>();
            Sprint s = new Sprint();
            JSONParser jp = new JSONParser();
            
           //String s = "["+jsonText+"]";
            Map<String, Object> SprintListJson = jp.parseJSON(new CharArrayReader(find_(jsonText).toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) SprintListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
               String name=obj.get("sprint_name").toString();
               int etat = (int)Float.parseFloat(obj.get("etat").toString());
                Map<String, Object> projets = (Map<String, Object>) obj.get("projets");
                int project_id= (int)Float.parseFloat(projets.get("id").toString());
             
               
             
              
                // string to date
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                
                String start_date_S=obj.get("start_datesprint").toString();
                Date start_date = formatter.parse(start_date_S);  

                String endDate_S=obj.get("end_datesprint").toString();
                Date endDate = formatter.parse(endDate_S);  


                Sprints.add(new Sprint(id,name,start_date,endDate,project_id,etat));
            }


        } catch (IOException ex) {
        }
        catch (ParseException e1) {
         e1.printStackTrace();
         }

        return Sprints;
    }
     
     public ArrayList<Sprint> FindSprints(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/sprint/afficher/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(request.getResponseData()));
                Sprints = parseSprints(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Sprints;
    }
        public void ajouterSprint(Sprint s) {
        try {
          
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/sprint/ajouter/");
            cr.setPost(true);
            cr.addArgument("sprintname", s.getName());
            cr.addArgument("projets_id", s.getProject_id()+"");
            //->substring(0,10)
            String start_date = parseDate(s.getStart_date_sprint().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");
            String end_date = parseDate(s.getEnd_date_sprint().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");

            cr.addArgument("startDatesprint",start_date);
            cr.addArgument("endDatesprint",end_date);
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Ajouter","Sprint ajouté " +  s.getName(), "OK",null);

         
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            }
        catch (ParseException e1) {
         e1.printStackTrace();
         }
    }
       public boolean deleteSprint(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/sprint/delete/"+id;
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
       
       public void updateSprint(Sprint s,int id) {
        try {
             
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/sprint/update/"+id);
            cr.setPost(true);
            cr.addArgument("sprintname", s.getName());
            cr.addArgument("projets_id", s.getProject_id()+"");
            //->substring(0,10)
            String start_date = parseDate(s.getStart_date_sprint().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");
            String end_date = parseDate(s.getEnd_date_sprint().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");

            cr.addArgument("startDatesprint",start_date);
            cr.addArgument("endDatesprint",end_date);
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Modifier","Sprint Modifié " +  s.getName(), "OK",null);

         
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            }
        catch (ParseException e1) {
         e1.printStackTrace();
         }
    } 
       
       
    public String parseDate(String dateTime,String inputPattern, String outputPattern) throws ParseException {

       SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
       SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

       Date date = inputFormat.parse(dateTime);
       String str = outputFormat.format(date);

       return str;
}
   
    public String  find_(String a)
       {
           if(!a.substring(0,1).equals("[")){
                a = "["+a+"]";
           }
           
       return a;
       }
    
    
    
    //Drag and drop 
    
        public ArrayList<Task> parse_sprint_Tasks(String jsonText) {
        try {
            tasks = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(find_(jsonText).toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String title= (String)(obj.get("title").toString());
                String description_fonctio= (String)(obj.get("description_fonctionnel").toString());
                String description_tech= (String)(obj.get("description_technique").toString());
                int story_points= (int)Float.parseFloat(obj.get("story_points").toString());
                String state= (String)(obj.get("state").toString());
                int priorite= (int)Float.parseFloat(obj.get("priority").toString());
                int archived= (int)Float.parseFloat(obj.get("archive").toString());
                

               
                int backlog_id= (int)Float.parseFloat(obj.get("backlog_id").toString());
                int sprint_id= (int)Float.parseFloat(obj.get("sprint_id").toString());
              
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                
                String start_date_S=obj.get("created_date").toString();
                Date start_date = formatter.parse(start_date_S);  

                String endDate_S=obj.get("finished_date").toString();
                Date endDate = formatter.parse(endDate_S);  

                //String name = obj.get("name").toString();
                //Task(int backlog_id, String title, String description_fonctionnel, String description_technique, int story_points, Date created_date, Date finished_date, String state, int priority, int archive, int sprint_id)
                Task T = new Task(id,backlog_id,title, description_fonctio,description_tech,story_points,start_date,endDate,state,priorite, archived, sprint_id);

                tasks.add(T);
            }

        } catch (IOException ex) {
        }
         catch (ParseException e1) {
         e1.printStackTrace();
         }

        return tasks;
    }
        
      public ArrayList<Task> Find_sprint_Task(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/sprint/DRAG_DROP/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(request.getResponseData()));
                tasks = parse_sprint_Tasks(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return tasks;
    }

    public boolean update_drag_drop(int id,int idd,String etat) {
       
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/sprint/update_DRAG_DROP/"+ id + "/" + idd + "/" +etat;
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
}
