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
import com.mycompany.myapp.models.User;
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
public class ServiceUser {
	private ConnectionRequest request;

    private boolean responseResult;
    public boolean resultOK;
    public ArrayList<User> users;
    public ArrayList<User> Users;
	User scarra = new User();
	
	public ServiceUser() {
        request = DataSource.getInstance().getRequest();
    }
	
	public User loginUser(User u)
	{
		
        request.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/User/login");
		
        request.setPost(false);
		request.addArgument("uname", u.getUsername());
        request.addArgument("pword", u.getPassword());
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                scarra = getLogin(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
		NetworkManager.getInstance().addToQueueAndWait(request);

		return scarra;
	}
	
	public User getLogin(String jsonText)  {
		User u = new User();
		u.setRoles("null");
		if (jsonText.equals("null")) {
			return u;
		}
        try {
            JSONParser jp = new JSONParser();
            
           //String s = "["+jsonText+"]";
            Map<String, Object> ProjectListJson = jp.parseJSON(new CharArrayReader(find_(jsonText).toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProjectListJson.get("root");
            for (Map<String, Object> obj : list) {
                u.setId((int)Float.parseFloat(obj.get("id").toString()));
				u.setUsername(obj.get("username").toString());         
                u.setEmail(obj.get("email").toString());
				u.setPassword(obj.get("password").toString());
				u.setRoles(obj.get("roles").toString());
            }
			


        } catch (IOException ex) {
        }
        

        return u;
    }
	
	public String  find_(String a) {
            if(!a.substring(0,1).equals("[")) {
                a = "["+a+"]";
            }  
        return a;
        }
	
	public ArrayList<User> getAllTeams() {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/User/afficher";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
				try {
					Users = parseEntreprise(new String(request.getResponseData()));
				} catch (IOException ex) {
				}
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Users;
    }
	
	public ArrayList<User> parseEntreprise(String jsonText) throws IOException  {
        try {
            Users = new ArrayList<>();
            User p = new User();
            JSONParser jp = new JSONParser();
            
           //String s = "["+jsonText+"]";
		   System.out.println(jsonText);
            Map<String, Object> ProjectListJson = jp.parseJSON(new CharArrayReader(find_(jsonText).toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProjectListJson.get("root");
            for (Map<String, Object> obj : list) {
              
				int id = (int)Float.parseFloat(obj.get("id").toString());
                String username=obj.get("username").toString();
				String email = obj.get("email").toString();
				String roles = obj.get("roles").toString();
				String password = obj.get("password").toString();
              

                Users.add(new User(id, username, password, roles, email));
            }


        } catch (IOException ex) {
			
        }
        

        return Users;
    }
	
	
	
	
	
	public boolean deleteTeam(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/User/delete/"+id;
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
     
	public void ajouterTeam(User p) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/User/ajouter");
            cr.setPost(false);
            cr.addArgument("username", p.getUsername());
			cr.addArgument("email", p.getEmail());
			cr.addArgument("password", p.getPassword());
			cr.addArgument("roles", p.getRoles());
			
            //->substring(0,10)
           // String start_date = parseDate(p.getDateofcreation().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "yyyy-MM-dd");
			
			
			
			
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Ajouter","User ajouté " +  p.getUsername(), "OK",null);

         
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            
        
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
	
	 public void updateTeam(User p,int id) {
       
             
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/User/update/"+id);
            cr.setPost(false);
            cr.addArgument("username", p.getUsername());
			cr.addArgument("email", p.getEmail());
			cr.addArgument("password", p.getPassword());
			cr.addArgument("roles", p.getRoles());

			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Modifier","User Modifié " +  p.getUsername(), "OK",null);

         
            });     
            NetworkManager.getInstance().addToQueueAndWait(cr);
           
    }
	
}
