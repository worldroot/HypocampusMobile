/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.mycompany.myapp.models.Project;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.util.DateUtil;






/**
 *
 * @author 21694
 */
public class ServiceProject {
    private ConnectionRequest request;

    private boolean responseResult;
    public boolean resultOK;
    public ArrayList<Project> Projects;

    public ServiceProject() {
        request = DataSource.getInstance().getRequest();
    }
    
    
        public ArrayList<Project> getAllProjects() {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Project/afficher";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Projects = parseProjects(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Projects;
    }
        
        
    public ArrayList<Project> FindProjects(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Project/More/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(request.getResponseData()));
                Projects = parseProjects(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Projects;
    }    
    public boolean deleteProject(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Project/delete/"+id;
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
    
  
    
    public ArrayList<Project> parseProjects(String jsonText)  {
        try {
            Projects = new ArrayList<>();
            Project p = new Project();
            JSONParser jp = new JSONParser();
            
           //String s = "["+jsonText+"]";
            Map<String, Object> ProjectListJson = jp.parseJSON(new CharArrayReader(find_(jsonText).toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProjectListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
               String name=obj.get("projet_name").toString();
         
                String owner=obj.get("owner").toString();
             
              
                // string to date
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                
                String start_date_S=obj.get("start_date").toString();
                Date start_date = formatter.parse(start_date_S);  

                String endDate_S=obj.get("end_date").toString();
                Date endDate = formatter.parse(endDate_S);  
              
                String description=(obj.get("description").toString());

                Projects.add(new Project(id,name,owner,start_date,endDate,description));
            }


        } catch (IOException ex) {
        }
        catch (ParseException e1) {
         e1.printStackTrace();
         }

        return Projects;
    }
    //hhhhh
    
       public String  find_(String a)
       {
           if(!a.substring(0,1).equals("[")){
                a = "["+a+"]";
           }
           
       return a;
       }
    
    public void ajouterProject(Project p) {
        try {

            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/Project/ajouter/");
            cr.setPost(true);
            cr.addArgument("projet_name", p.getName());
            cr.addArgument("owner", p.getOwner());

            String start_date = parseDate(p.getStart_date().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");
            String end_date = parseDate(p.getEnd_date().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");

            cr.addArgument("start_date",start_date);
            cr.addArgument("end_date",end_date);
            cr.addArgument("description", p.getDescription());
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Ajouter","Projet ajouté " +  p.getName(), "OK",null);

         
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);

            }
        catch (ParseException e1) {
         e1.printStackTrace();
         }
    }
    
    
    
    
    
    
    
        public void updateProject(Project p,int id) {
        try {
             
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/Project/update/"+id);
            cr.setPost(true);
            cr.addArgument("projet_name", p.getName());
            cr.addArgument("owner", p.getOwner());
            //->substring(0,10)
            String start_date = parseDate(p.getStart_date().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");
            String end_date = parseDate(p.getEnd_date().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");

            cr.addArgument("start_date",start_date);
            cr.addArgument("end_date",end_date);
            cr.addArgument("description", p.getDescription());
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Modifier","Projet Modifié " +  p.getName(), "OK",null);

         
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            }
        catch (ParseException e1) {
         e1.printStackTrace();
         }
    }
    
    
        
        /*
         public int IProjects(String jsonText)  {
        try {
            Projects = new ArrayList<>();
            Project p = new Project();
            JSONParser jp = new JSONParser();
            
           //String s = "["+jsonText+"]";
            Map<String, Object> ProjectListJson = jp.parseJSON(new CharArrayReader(find_(jsonText).toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProjectListJson.get("root");
            for (Map<String, Object> obj : list) {
                obj.get(obj);
            }


        } catch (IOException ex) {
        }

        return Projects;
    }
        */
        
        
        
        
        
        
    public boolean archiverProject(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Project/archiver/"+id;
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
    public boolean NOTarchiverProject(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Project/NOTarchiver/"+id;
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
    
     public ArrayList<Project> getAllProjects_archiver() {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Project/affiche_archiver";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Projects = parseProjects(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Projects;
    }
        
         public String ProgressProjects(int id) {
          String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Project/Progress/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                //a=new String(request.getResponseData());
               // Projects = parseProjects(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return new String(request.getResponseData());
    } 
       
        public String CompletedProjects(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Project/Completed/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                //a=new String(request.getResponseData());
               // Projects = parseProjects(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return new String(request.getResponseData());
    } 

    
    public String parseDate(String dateTime,String inputPattern, String outputPattern) throws ParseException {

       SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
       SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

       Date date = inputFormat.parse(dateTime);
       String str = outputFormat.format(date);

       return str;
}
      

}
