/*
 * To change this license header, choose License Headers in Project Properties.
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
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Project;
import com.mycompany.myapp.services.ServiceProject;
import java.io.IOException;
import java.text.ParseException;



/**
 *
 * @author 21694
 */
public class ArchiverProjectForm extends  BaseForm{

    public ArchiverProjectForm(Form previous, Resources res) {
        super("les projets a été archivée", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("les projets a été archivée");
        getContentPane().setScrollVisible(false);
        /*
        //recherche
        Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");

TextField searchField = new TextField("", "Toolbar Search"); 
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);
getToolbar().setTitleComponent(searchField);
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
searchField.addDataChangeListener((i1, i2) -> { 
    String t = searchField.getText();
    if(t.length() < 1) {
        for(Component cmp : this.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
    } else {
        t = t.toLowerCase();
        for(Component cmp : this.getContentPane()) {
            String val = null;
            if(cmp instanceof Label) {
                val = ((Label)cmp).getText();
            } else {
                if(cmp instanceof Container) {
                    val = ((TextArea)cmp).getText();
                } else {
                    val = (String)cmp.getPropertyValue("text");
                }
            }
            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
            cmp.setHidden(!show); 
            cmp.setVisible(show);
        }
    }
    getContentPane().animateLayout(250);
});
getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
    searchField.startEditingAsync(); 
});
*/

        super.addSideMenu(res);
        
                Image img = res.getImage("planning-projet.jpg");
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
             

          ServiceProject sp=new ServiceProject();
        
   
        for (Project project :  sp.getAllProjects_archiver()) {

                  addItem(project,res);
        }
        
        
         show();
//Return
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new BackendForm(res).show();
        });
        
    }
    
   public void addItem(Project project,Resources res) {

        
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label Completed = new Label("                                 Completed ");
        Label Progress = new Label("                                  In Progress ");

        Label name = new Label(" Project Name : "+project.getName());
        Label owner = new Label("  "+"Employee Name : "+project.getOwner());
        Label end_date = new Label("  "+"End Date : "+project.getEnd_date());
        Label more = new Label("  "+" ");
        Label delete = new Label("                                                                          ");
        delete.setTextPosition(LEFT);
       
        
 //Graphic

  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        name.getAllStyles().setFgColor(0xFFFFFF);
        name.getAllStyles().setFont(f);
        owner.getAllStyles().setFgColor(0xFFFFFF);
        end_date.getAllStyles().setFgColor(0xFFFFFF);
        Completed.getAllStyles().setFgColor(0xFFFFFF);
        Progress.getAllStyles().setFgColor(0xFFFFFF);
        more.getAllStyles().setFgColor(0x5ed84f);
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
        
        //Icons assignment_return
        FontImage.setMaterialIcon(more, FontImage.MATERIAL_ASSIGNMENT_RETURN);
        FontImage.setMaterialIcon(name, FontImage.MATERIAL_WORK);
        FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
        
        
        ServiceProject sp=new ServiceProject();
        
        more.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
             sp.NOTarchiverProject(project.getId());
             Dialog.show("Succès", "Ce projet a été restauré", "OK", null);
             new AfficherProjectForm(this, res).show();
        });
        delete.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            sp.deleteProject(project.getId());
             Dialog.show("Succès", "Le projet a été supprimé avec succès", "OK", null);
             new AfficherProjectForm(this, res).show();

        });

        C1.add(more);
        C1.add(delete);
        C.add(C1);
        
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
        C.add(end_date);

        add(C);
 

    }
   
   
   
   
    
}
