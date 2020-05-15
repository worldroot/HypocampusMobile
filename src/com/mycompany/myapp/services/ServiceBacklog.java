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
import com.mycompany.myapp.models.Backlog;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author mehdibehira
 */
public class ServiceBacklog {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Backlog> backlogs;

    public ServiceBacklog() {
        request = DataSource.getInstance().getRequest();
    }
    
    
        public ArrayList<Backlog> getAllBacklogs() {
        String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/ProjectBacklog/index";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                backlogs = parseBacklogs(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return backlogs;
    }
        
        
        
        
    public ArrayList<Backlog> parseBacklogs(String jsonText) {
        try {
            backlogs = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> backlogListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) backlogListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                int points_to_do= (int)Float.parseFloat(obj.get("pointsToDo").toString());
                int points_in_progress= (int)Float.parseFloat(obj.get("pointsInProgress").toString());
                int points_done= (int)Float.parseFloat(obj.get("pointsDone").toString());
                Map<String, Object> project = (Map<String, Object>) obj.get("project");
                int project_id= (int)Float.parseFloat(project.get("id").toString());
                String project_name= (String)(project.get("projetName").toString());
                //int status = (int)Float.parseFloat(obj.get("status").toString());
                //String name = obj.get("name").toString();
                backlogs.add(new Backlog(id, points_to_do, points_in_progress, points_done, project_id, project_name));
            }

        } catch (IOException ex) {
        }

        return backlogs;
    }
    
    public boolean ajouterBacklog(Backlog b){
    String url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/ProjectBacklog/new/"+b.getProject_id();


        request.setUrl(url);
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
}
