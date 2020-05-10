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
import com.codename1.ui.TextField;
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
import com.mycompany.myapp.models.Sprint;
import com.mycompany.myapp.services.Servicesprint;
import java.io.IOException;
import java.text.ParseException;



/**
 *
 * @author 21694
 */
public class AfficherSprintForm extends  BaseForm{

    public AfficherSprintForm(Form previous, Resources res,Project project_) {
        super("liste des Sprints", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("liste des Sprints");
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
    

                  new AjouterSprintForm(this,res,project_).show();



            });
//fab.bindFabToContainer(this.getContentPane());
//fab.createSubFAB(FontImage.MATERIAL_PEOPLE, "");
//fab.createSubFAB(FontImage.MATERIAL_IMPORT_CONTACTS, "");
fab.bindFabToContainer(this.getContentPane());

Label name_project = new Label("             Project Name : "+project_.getName());
  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        name_project.getAllStyles().setFgColor(0x28afd0);
        name_project.getAllStyles().setFont(f);
          Servicesprint s=new Servicesprint();
         add(name_project); 
   
        for (Sprint Sprint :  s.FindSprints(project_.getId())) {

                  addItem(Sprint,res,project_);
        }
        
        
         show();
//Return
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new AfficherProjectForm(this,res).show();
        });
        
    }
    
   public void addItem(Sprint Sprint,Resources res,Project project_) {

        
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label Completed = new Label("                                 Completed ");
        Label Progress = new Label("                                  In Progress ");
        Label delete = new Label("");
        delete.setTextPosition(LEFT);
        Label name = new Label(" Sprint Name : "+Sprint.getName());
        Label start_date = new Label("  "+"Start Date : "+Sprint.getStart_date_sprint());
        Label end_date = new Label("  "+"End Date : "+Sprint.getEnd_date_sprint());
        Label edit = new Label("                                                                        ");
        Button btnSprint_task=new Button(" voir les taches ");
        btnSprint_task.addActionListener((evt) -> {
            new NOT_DRAG_DROP_Form(this,res,Sprint).show();
        });
        edit.setTextPosition(LEFT);
       
        
 //Graphic

  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        name.getAllStyles().setFgColor(0xFFFFFF);
        name.getAllStyles().setFont(f);
        start_date.getAllStyles().setFgColor(0xFFFFFF);
        end_date.getAllStyles().setFgColor(0xFFFFFF);
        Completed.getAllStyles().setFgColor(0xFFFFFF);
        Progress.getAllStyles().setFgColor(0xFFFFFF);
        edit.getAllStyles().setFgColor(0x6967ce);
        delete.getAllStyles().setFgColor(0xfa626b);
        delete.getAllStyles().setFont(f);
        
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
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        FontImage.setMaterialIcon(edit, FontImage.MATERIAL_EDIT);
        FontImage.setMaterialIcon(name, FontImage.MATERIAL_TABLE_CHART);
        Servicesprint s=new Servicesprint();
        delete.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
        s.deleteSprint(Sprint.getId());
        Dialog.show("Succès", "Le Sprint a été supprimé avec succès", "OK", null);
             new AfficherSprintForm(this, res,project_).show();
        });
        edit.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
        new ModifierSprintForm(this, res,Sprint,project_).show();
        });
      C1.add(edit);
      C1.add(delete);
      C.add(C1);
   if (Sprint.getEtat()==1)
   {
   C.add(Completed);
   }
   else
   {
   C.add(Progress);
   }
        
        C.add(name);
        C.add(start_date);
        C.add(end_date);
        C.add(btnSprint_task);
        add(C);
 

    }  
  
}
