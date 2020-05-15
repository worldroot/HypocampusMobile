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
import com.mycompany.myapp.models.Sprint;
import com.mycompany.myapp.services.ServiceProject;
import com.mycompany.myapp.services.Servicesprint;
import java.io.IOException;
import java.text.ParseException;



/**
 *
 * @author 21694
 */
public class AfficherProjectForm extends  BaseForm{

    public AfficherProjectForm(Form previous, Resources res) {
        super("liste des projets", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("liste des projets");
        getContentPane().setScrollVisible(false);

        //recherche
        this.setLayout(BoxLayout.y());
        Container cont18 = this.getContentPane();
        this.getToolbar().addSearchCommand((e) -> {
            String text = (String) e.getSource();
            for (Component c : this.getContentPane()) {
                c.setHidden(c instanceof Label && ((Label) c).getText().indexOf(text) < 0);
            }
            this.getComponentForm().animateLayout(150);
        });
        
        

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
        

        for (Project project :  sp.getAllProjects()) {

                  addItem(project,res,previous);
            //Label l = new Label("Label " + i);
            /*
            Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label name = new Label(" Project Name : "+project.getName());
            C.add(name);
            cont18.add(C);
*/
            
        }
        
        
         show();
//Return
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new BackendForm(res).show();
        });
         getToolbar().addCommandToOverflowMenu("Archiver", null, (ActionListener) (ActionEvent evt) -> {

             new ArchiverProjectForm(this, res).show();

         });
    }
    
   public Container addItem(Project project,Resources res,Form previous) {

        
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label Completed = new Label("                                 Completed ");
        Label Progress = new Label("                                  In Progress ");

        Label name = new Label(" Project Name : "+project.getName());
        Label owner = new Label("  "+"Employee Name : "+project.getOwner());
        Label end_date = new Label("  "+"End Date : "+project.getEnd_date());
        Label more = new Label("  "+" ");
        Label archiver = new Label("                                                                          ");
        Label stat = new Label("                                                                          ");
        stat.setTextPosition(LEFT);
 
       
        
 //Graphic

  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        name.getAllStyles().setFgColor(0xFFFFFF);
        name.getAllStyles().setFont(f);
        owner.getAllStyles().setFgColor(0xFFFFFF);
        end_date.getAllStyles().setFgColor(0xFFFFFF);
        Completed.getAllStyles().setFgColor(0xFFFFFF);
        Progress.getAllStyles().setFgColor(0xFFFFFF);
        more.getAllStyles().setFgColor(0x6967ce);
        archiver.getAllStyles().setFgColor(0x6967ce);
        stat.getAllStyles().setFgColor(0xfa626b);
             
        
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
        FontImage.setMaterialIcon(more, FontImage.MATERIAL_MORE_HORIZ);
        FontImage.setMaterialIcon(name, FontImage.MATERIAL_WORK);
        FontImage.setMaterialIcon(archiver, FontImage.MATERIAL_ARCHIVE);
        FontImage.setMaterialIcon(stat, FontImage.MATERIAL_PIE_CHART);
        
        
        ServiceProject sp=new ServiceProject();
        more.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
        new MoreAfficherProjectForm(this, res,project).show();
        });
        archiver.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
          sp.archiverProject(project.getId());
             Dialog.show("Succès", "Le projet a été archivé avec succès", "OK", null);
             new AfficherProjectForm(this, res).show();
        });
       stat.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
           
           if (nbTsprint(project)!=0){
           new BudgetPieChart(previous,res,project).show();
           }
           else {
           Dialog.show("warning", "sprint introuvable", "OK", null);
           }
          
        });

       
        
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
        C1.add(more);
        C1.add(archiver);
        C1.add(stat);
        C.add(C1);
        add(C);
 
return C ;
    }
   
   
   
     private float nbTsprint(Project project) {
    
   int  j=0;
   Servicesprint s=new Servicesprint();
    for (Sprint Sprint :  s.FindSprints(project.getId())) {
    j++;
    
    }
    return j;
    }
    
}
