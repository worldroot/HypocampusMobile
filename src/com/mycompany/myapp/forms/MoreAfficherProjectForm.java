/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Project;
import com.mycompany.myapp.services.ServiceProject;
import com.mycompany.myapp.services.Servicesprint;

/**
 *
 * @author 21694
 */
public class MoreAfficherProjectForm  extends  BaseForm{
    
        public MoreAfficherProjectForm(Form previous, Resources res,Project projectT) {
        super("More", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("More");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
                Image img = res.getImage("welcome-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
                add(LayeredLayout.encloseIn(
                sl
        ));
             
FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
fab.getAllStyles().setBgColor(0x5ed84f);
fab.addActionListener((ActionListener) (ActionEvent evt1) -> {
    

                new AjouterProjectForm(this,res).show();


            });
//fab.bindFabToContainer(this.getContentPane());
//fab.createSubFAB(FontImage.MATERIAL_PEOPLE, "");
//fab.createSubFAB(FontImage.MATERIAL_IMPORT_CONTACTS, "");
fab.bindFabToContainer(this.getContentPane());
          ServiceProject sp=new ServiceProject();
        
      
        for (Project project :  sp.FindProjects(projectT.getId())) {

                  addItem(project,res);
        }

        
        
         show();

         getToolbar().addCommandToOverflowMenu("Modifier", null, (ActionListener) (ActionEvent evt) -> {

             new ModifierProjectForm(this, res,projectT).show();

         });
//Return
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
    }
      
    
     public void addItem(Project project,Resources res) {

        
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label Completed = new Label("                                 Completed ");
        Label Progress = new Label("                                  In Progress ");

        Label name = new Label("Project Name : "+project.getName());
        Label owner = new Label("  "+"Employee Name : "+project.getOwner());
        Label start_date = new Label("  "+"Start Date : "+project.getStart_date());
        Label end_date = new Label("  "+"End Date : "+project.getEnd_date());
        Label description = new Label("  "+"Description :"+"\n"+"  "+project.getDescription());
        Button btnSprint=new Button(" voir les sprints ");
        btnSprint.addActionListener((evt) -> {
            new AfficherSprintForm(this,res,project).show();
        });
 //Graphic

  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        name.getAllStyles().setFgColor(0xFFFFFF);
        name.getAllStyles().setFont(f);
        owner.getAllStyles().setFgColor(0xFFFFFF);
        start_date.getAllStyles().setFgColor(0xFFFFFF);
        end_date.getAllStyles().setFgColor(0xFFFFFF);
        description.getAllStyles().setFgColor(0xFFFFFF);
        Completed.getAllStyles().setFgColor(0xFFFFFF);
        Progress.getAllStyles().setFgColor(0xFFFFFF);
             
        
        C.getAllStyles().setBgColor(0x28afd0);
        C.getAllStyles().setBgTransparency(255);
        C.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        C.getAllStyles().setPadding(20,20,20,20);
        C.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0xFFFFFF)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
        
        Completed.getAllStyles().setBgColor(0x5ed84f);
        Completed.getAllStyles().setBgTransparency(255);
        Completed.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        Completed.getAllStyles().setPadding(20,20,20,20);
        Completed.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0x5ed84f)
                .cornerRadius(2)
                );
        
        Progress.getAllStyles().setBgColor(0x6967ce);
        Progress.getAllStyles().setBgTransparency(255);
        Progress.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        Progress.getAllStyles().setPadding(20,20,20,20);
        Progress.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0x6967ce)
                .cornerRadius(2)
                );
        
        //Icons
        FontImage.setMaterialIcon(name, FontImage.MATERIAL_WORK);
     

        ServiceProject sp=new ServiceProject();
        
   if (sp.ProgressProjects(project.getId()).equals(sp.CompletedProjects(project.getId())))
   {
   C.add(Completed);
   }
   else
   {
   C.add(Progress);
   }
        
        C.add(name);
        C.add(owner);
        C.add(start_date);
        C.add(end_date);
        C.add(description);
        C.add(btnSprint);
        
        add(C);
 

    }  
}
