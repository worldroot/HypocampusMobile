/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Backlog;
import com.mycompany.myapp.models.Project;
import com.mycompany.myapp.models.Task;
import com.mycompany.myapp.services.ServiceBacklog;
import com.mycompany.myapp.services.ServiceProject;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author mehdibehira
 */
public class AjouterBacklogForm extends  BaseForm {
    
    int redflag = 0;
    ComboBox<Project> combo;
    
     public AjouterBacklogForm(Form previous, Resources res){
        
        
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
setUIID("SignIn");
        tb.setBackCommand("", e -> previous.showBack());
                
      
        ServiceProject sp=new ServiceProject();
   
        
       
         
       
         combo = new ComboBox();
   
        for (Project project :  sp.getAllProjects()) {
            combo.addItem(project);
            
           // list_project.add(project.getName());
            // RadioButton  = new RadioButton("Radio 1");
            // addItem(project,res,this);
           // RadioButton radio = new RadioButton(project.getName());
           // this.add(radio);
            
        }
        
        
      
       

         Button confirmer = new Button("Confirmer");
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter BACKLOG", "LogoLabel"),
              
                createLineSeparator(),
                combo,
                createLineSeparator()
               
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                confirmer
              //  FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        confirmer.requestFocus();
        confirmer.addActionListener(e ->
        { 
           
           
    
                    
           Backlog b = new Backlog(0, 0, 0, combo.getSelectedItem().getId());
            ServiceBacklog ServiB = new ServiceBacklog();
           
                
                for(Backlog backlog : ServiB.getAllBacklogs()){
                    if(backlog.getProject_id() == combo.getSelectedItem().getId()){
                         redflag = 1;
                         Dialog.show("WARNING","Ce projet a deja un backlog ", "OK",null);
                    }

                    
                }
                
                if (redflag==0){
                          ServiB.ajouterBacklog(b);
                    new BacklogForm(previous,res).show();
                        Dialog.show("Succes","backlog Ajouter ", "OK",null);
                        
                 }
                 
                
            

            
        }
        
        );
    }
     

    
}
