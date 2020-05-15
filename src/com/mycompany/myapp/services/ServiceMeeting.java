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
import com.mycompany.myapp.models.team;
import com.mycompany.myapp.models.meeting;
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
 * @author Houcem
 */
public class ServiceMeeting {
	private ConnectionRequest request;
	public boolean resultOK;

    private boolean responseResult;
    public ArrayList<meeting> meetings;

	public ServiceMeeting() {
		request = DataSource.getInstance().getRequest();
	}

	public ArrayList<meeting> getAllMeeting() {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Meeting/afficher";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                meetings = parseProjects(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return meetings;
    }
	
	public ArrayList<meeting> parseProjects(String jsonText)  {
        try {
            meetings = new ArrayList<>();
            meeting p = new meeting();
            JSONParser jp = new JSONParser();
            
           //String s = "["+jsonText+"]";
            Map<String, Object> ProjectListJson = jp.parseJSON(new CharArrayReader(find_(jsonText).toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProjectListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
				
				
				Map<String, Object> team = (Map<String, Object>) obj.get("team");
                int team_id= (int)Float.parseFloat(team.get("id").toString());

				
				
				
				
				
//				int team_id = (int)Float.parseFloat(obj.get("team").toString());
				
				
				System.out.println(obj.get("team").toString());
				
				
		
				
                String description=obj.get("description").toString();
				String duration=obj.get("duration").toString();
				int nbrmeeting = (int)Float.parseFloat(obj.get("nbrmeeting").toString());
   

                meetings.add(new meeting(id, team_id,description,duration, nbrmeeting));
            }


        } catch (IOException ex) {
			
        }
        

        return meetings;
    }
	
	public String  find_(String a){
			if(!a.substring(0,1).equals("[")){
                a = "["+a+"]";
			}
        return a;
    }
	
/*	public ArrayList<team> FindMeeting(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Team/More/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(request.getResponseData()));
                teams = parseProjects(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return teams;
    }    */
	
	public boolean deleteMeeting(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Meeting/delete/"+id;
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
     
	public void ajouterMeeting(meeting p) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/Meeting/ajouter");
            cr.setPost(true);
            cr.addArgument("_0", p.getDescription());
            //->substring(0,10)
           // String start_date = parseDate(p.getDateofcreation().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "yyyy-MM-dd");
			cr.addArgument("_1", p.getDuration());
			cr.addArgument("_2", p.getNbrmeeting()+"");
			cr.addArgument("_3", p.getTeam_id()+"");
			
			
			
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Ajouter","Meeting ajouté " , "OK",null);

         
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            
        /*   }
        catch (ParseException e1) {
         e1.printStackTrace();
         }*/
    }
	
	private String modifyDateLayout(String inputDate) throws ParseException{
		Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(inputDate);
		return new SimpleDateFormat("MM-dd-yyyy").format(date);
	}
	
	 public String parseDate(String dateTime,String inputPattern, String outputPattern) throws ParseException {

       SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
       SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
	   
       Date date = inputFormat.parse(dateTime);

       String str = outputFormat.format(date);

       return str;
	}
	
	 public void updateMeeting(meeting p,int id) {
       
             
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/Meeting/update/"+id);
            cr.setPost(true);
            cr.addArgument("_0", p.getDescription());

			cr.addArgument("_1", p.getDuration());
			cr.addArgument("_2", p.getNbrmeeting()+"");
			cr.addArgument("_3", p.getTeam_id()+"");
			
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Modifier","meeting Modifié ", "OK",null);

         
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
           
    }
}
