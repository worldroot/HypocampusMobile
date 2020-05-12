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
import com.mycompany.myapp.models.Task;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mehdibehira
 */
public class ServiceTask {
    
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Task> tasks;

    public ServiceTask() {
        request = DataSource.getInstance().getRequest();
    }
    
    public ArrayList<Task> getAllTasks(int id_backlog) {
        String url = Statics.BASE_URL + "/ProjetPi/Hypocampus/web/app_dev.php/api/ProjectBacklog/view/"+id_backlog;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return tasks;
    }
    
    public ArrayList<Task> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String title= (String)(obj.get("title").toString());
                String description_fonctio= (String)(obj.get("descriptionFonctionnel").toString());
                String description_tech= (String)(obj.get("descriptionTechnique").toString());
                int story_points= (int)Float.parseFloat(obj.get("storyPoints").toString());
                String state= (String)(obj.get("state").toString());
                int priorite= (int)Float.parseFloat(obj.get("priority").toString());
                int archived= (int)Float.parseFloat(obj.get("archive").toString());
                

                Map<String, Object> backlog = (Map<String, Object>) obj.get("backlog");
                int backlog_id= (int)Float.parseFloat(backlog.get("id").toString());
                
                Map<String, Object> project = (Map<String, Object>) backlog.get("project");
                int project_id= (int)Float.parseFloat(project.get("id").toString());
                String project_name= (String)(project.get("projetName").toString());
                
                Map<String, Object> sprint = (Map<String, Object>) obj.get("sprint");
                int sprint_id= (int)Float.parseFloat(sprint.get("id").toString());
                String sprint_name= (String)(sprint.get("sprintName").toString());     
                
                //Date created_date = (Date)(obj.get("createdDate"));
               // Date finished_date = (Date)(obj.get("finishedDate"));
                //int status = (int)Float.parseFloat(obj.get("status").toString());
                //String name = obj.get("name").toString();
                Task T = new Task(id, backlog_id,title, description_fonctio,description_tech,story_points,state,priorite, archived, sprint_id,sprint_name);

                tasks.add(T);
            }

        } catch (IOException ex) {
        }

        return tasks;
    }
    
    public boolean deleteTask(int id_b, int id) {
        String url = Statics.BASE_URL + "/ProjetPi/Hypocampus/web/app_dev.php/api/ProjectBacklog/view/"+id_b+"/task/remove/"+id;

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
    
    public boolean addTask(Task task) {
         String url = Statics.BASE_URL + "/ProjetPi/Hypocampus/web/app_dev.php/api/ProjectBacklog/view/" + task.getBacklog_id() +""
                 + "/task/add/" + task.getTitle() + "/" + task.getStory_points() + "/" + task.getState() + "/" + task.getPriority() + "/" +task.getDescription_fonctionnel()+"/"
                 +task.getSprint_id()+"/"+task.getFinished_date();


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
