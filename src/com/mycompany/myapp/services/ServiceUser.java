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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.models.User;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
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
	User scarra = new User();
	
	public ServiceUser() {
        request = DataSource.getInstance().getRequest();
    }
	
	public User loginUser(User u)
	{
		
        request.setUrl(Statics.BASE_URL+"/Hypocampus/web/app_dev.php/api/User/login");
		
        request.setPost(true);
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
}
