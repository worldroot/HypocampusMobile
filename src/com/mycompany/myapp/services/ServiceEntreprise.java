/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

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
import com.mycompany.myapp.models.Entreprise;

/**
 *
 * @author Houcem
 */
public class ServiceEntreprise {
	private ConnectionRequest request;
	public boolean resultOK;

    private boolean responseResult;
    public ArrayList<Entreprise> Entreprises;

	public ServiceEntreprise() {
		request = DataSource.getInstance().getRequest();
	}

	public ArrayList<Entreprise> getAllTeams() {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Entreprise/afficher";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Entreprises = parseEntreprise(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Entreprises;
    }
	
	public ArrayList<Entreprise> parseEntreprise(String jsonText)  {
        try {
            Entreprises = new ArrayList<>();
            Entreprise p = new Entreprise();
            JSONParser jp = new JSONParser();
            
           //String s = "["+jsonText+"]";
		   System.out.println(jsonText);
            Map<String, Object> ProjectListJson = jp.parseJSON(new CharArrayReader(find_(jsonText).toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProjectListJson.get("root");
            for (Map<String, Object> obj : list) {
              
				int id = (int)Float.parseFloat(obj.get("id").toString());
                String name=obj.get("name").toString();
				String email = obj.get("email").toString();
   
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateofcreation_=obj.get("createdate").toString();
                Date createdate = formatter.parse(dateofcreation_);  
              

                Entreprises.add(new Entreprise(id, name, email,createdate) );
            }


        } catch (IOException ex) {
			
        }
        catch (ParseException e1) {
         e1.printStackTrace();
        }

        return Entreprises;
    }
	
	public String  find_(String a){
			if(!a.substring(0,1).equals("[")){
                a = "["+a+"]";
			}
        return a;
    }
	
	public ArrayList<Entreprise> FindTeam(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Entreprise/More/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(request.getResponseData()));
                Entreprises = parseEntreprise(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Entreprises;
    }    
	
	public boolean deleteTeam(int id) {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/Entreprise/delete/"+id;
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
     
	public void ajouterTeam(Entreprise p) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/Entreprise/ajouter");
            cr.setPost(false);
            cr.addArgument("name", p.getName());
			cr.addArgument("email", p.getEmail());
            //->substring(0,10)
           // String start_date = parseDate(p.getDateofcreation().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "yyyy-MM-dd");
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(p.getCreatedate());
			System.out.println(date);
			
			
            cr.addArgument("dateofcreation",date);
			
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Ajouter","Team ajouté " +  p.getName(), "OK",null);

         
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
	
	 public void updateTeam(Entreprise p,int id) {
       
             
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/Entreprise/update/"+id);
            cr.setPost(false);
            cr.addArgument("name", p.getName());
			cr.addArgument("email", p.getEmail());

            //->substring(0,10)
            //String start_date = parseDate(p.getDateofcreation().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");

			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(p.getCreatedate());
			System.out.println(date);
			
			
			
            cr.addArgument("dateofcreation",date);
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Modifier","Team Modifié " +  p.getName(), "OK",null);

         
            });     
            NetworkManager.getInstance().addToQueueAndWait(cr);
           
    }
	
}
