/*
 * To change this license header, choose License Headers in Sprint Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.db.Database;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Project;
import com.mycompany.myapp.models.Sprint;
import com.mycompany.myapp.models.Task;
import com.mycompany.myapp.services.Servicesprint;
import java.io.IOException;
import java.text.ParseException;



/**
 *
 * @author 21694
 */
public class NOT_DRAG_DROP_Form extends  BaseForm{

    public NOT_DRAG_DROP_Form(Form previous, Resources res,Sprint Sprint_) {
        super("liste des Sprints", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("liste des Sprints");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
               Image img = res.getImage("planning-schema-concept.jpg");
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
             
             


Label name_project = new Label("             Sprint Name : "+Sprint_.getName());
  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        name_project.getAllStyles().setFgColor(0x28afd0);
        name_project.getAllStyles().setFont(f);
          Servicesprint s=new Servicesprint();
         add(name_project); 
   
        for (Task Task :  s.Find_sprint_Task(Sprint_.getId())) {

                  addItem(Task,res,Sprint_,previous);
        }
        
        
         show();
//Return
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new AfficherProjectForm(this,res).show();
        });
        
    }
    
   public void addItem(Task task,Resources res,Sprint Sprint_,Form previous) {

        
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label Completed = new Label("                                 Completed ");
        Label Progress = new Label("                                  In Progress ");
        Label delete = new Label("");
        delete.setTextPosition(LEFT);
        Label name = new Label(" Task Name : "+task.getTitle());
        Label start_date = new Label("  "+"Start Date : "+task.getCreated_date());
        Label end_date = new Label("  "+"End Date : "+task.getFinished_date());
        Label Dfonctionnel = new Label("  "+"End Date : "+task.getDescription_fonctionnel());
        Label Dtechnique = new Label("  "+"End Date : "+task.getDescription_technique());
        Label State = new Label(" Etat : "+task.getState());
        ComboBox cb = new ComboBox("To Do", "In Progress", "Done");
        cb.setSelectedItem(task.getState());
        Label edit = new Label("                                                                        ");
        edit.setTextPosition(LEFT);
       
        
 //Graphic

  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        name.getAllStyles().setFgColor(0xFFFFFF);
        name.getAllStyles().setFont(f);
        start_date.getAllStyles().setFgColor(0xFFFFFF);
        end_date.getAllStyles().setFgColor(0xFFFFFF);
        Dfonctionnel.getAllStyles().setFgColor(0xFFFFFF);
        Dtechnique.getAllStyles().setFgColor(0xFFFFFF);
        State.getAllStyles().setFgColor(0xFFFFFF);
        Completed.getAllStyles().setFgColor(0xFFFFFF);
        Progress.getAllStyles().setFgColor(0xFFFFFF);
        edit.getAllStyles().setFgColor(0x6967ce);
        cb.getAllStyles().setFgColor(0xFFFFFF);
        delete.getAllStyles().setFgColor(0xfa626b);
        delete.getAllStyles().setFont(f);
        


        
        
 if (task.getState().equals("To Do")){
        
         FontImage.setMaterialIcon(name, FontImage.MATERIAL_CHECK_BOX_OUTLINE_BLANK);
        cb.getAllStyles().setBgColor(0x28afd0);
        cb.getAllStyles().setBgTransparency(255);
        cb.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        cb.getAllStyles().setPadding(20,20,20,20);
        cb.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0xFFFFFF)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
        C.getAllStyles().setBgColor(0x28afd0);
        C.getAllStyles().setBgTransparency(255);
        C.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        C.getAllStyles().setPadding(20,20,20,20);
        C.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0xFFFFFF)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
 }
 else if (task.getState().equals("In Progress")){
        
        FontImage.setMaterialIcon(name, FontImage.MATERIAL_INDETERMINATE_CHECK_BOX);
        cb.getAllStyles().setBgColor(0xfdb901);
        cb.getAllStyles().setBgTransparency(255);
        cb.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        cb.getAllStyles().setPadding(20,20,20,20);
        cb.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0xFFFFFF)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
        C.getAllStyles().setBgColor(0xfdb901);
        C.getAllStyles().setBgTransparency(255);
        C.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        C.getAllStyles().setPadding(20,20,20,20);
        C.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0xFFFFFF)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
 }
 else{
        
        FontImage.setMaterialIcon(name, FontImage.MATERIAL_CHECK_BOX);
        cb.getAllStyles().setBgColor(0x5ed84f);
        cb.getAllStyles().setBgTransparency(255);
        cb.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        cb.getAllStyles().setPadding(20,20,20,20);
        cb.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0xFFFFFF)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
 
        C.getAllStyles().setBgColor(0x5ed84f);
        C.getAllStyles().setBgTransparency(255);
        C.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        C.getAllStyles().setPadding(20,20,20,20);
        C.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0xFFFFFF)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
 
 }
   cb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

    Servicesprint s=new Servicesprint();
    if(cb.getSelectedItem()=="To Do"){
        s.update_drag_drop(Sprint_.getId(),task.getId(),"999");
    new NOT_DRAG_DROP_Form(previous,res,Sprint_).show();
    }
    if(cb.getSelectedItem()=="In Progress"){
        s.update_drag_drop(Sprint_.getId(),task.getId(),"888");
    new NOT_DRAG_DROP_Form(previous,res,Sprint_).show();
    }
    if(cb.getSelectedItem()=="Done"){
        s.update_drag_drop(Sprint_.getId(),task.getId(),"777");
    new NOT_DRAG_DROP_Form(previous,res,Sprint_).show();
    }
            
            }
        });

        C.add(cb);
        C.add(name);
        C.add(start_date);
        C.add(end_date);
        C.add(Dfonctionnel);
        C.add(Dtechnique);
        C.add(State);
        
        add(C);
 

    }  
  
}
