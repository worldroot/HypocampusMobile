/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.meeting;
import com.mycompany.myapp.models.team;
import com.mycompany.myapp.services.ServiceMeeting;
import com.mycompany.myapp.services.ServiceTeam;

/**
 *
 * @author Houcem
 */
public class MeetingForm extends BaseForm {
	
	ComboBox<team> Combo;

	MeetingForm(Form previous, Resources res) {
		super("liste des Meeting", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("liste des Meeting");
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
    

                new AjouterMeetingForm(this,res).show();


            }); 
		fab.bindFabToContainer(this.getContentPane());
          
		  ServiceMeeting sm=new ServiceMeeting();
        
   
        for (meeting T : sm.getAllMeeting() ) {

                  addItem(T,res);
        }
        
         show();

		 this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new BackendForm(res).show();
        });
		
	}
	
	 public void addItem(meeting T,Resources res) {
		 ServiceTeam sp=new ServiceTeam();
		  Combo = new ComboBox();
		  String tn = " ";
		  for (team t : sp.getAllTeams() ) {

                  if(t.getId()  == T.getTeam_id()) {
					  tn = t.getTeamname();
				  }
        }
		 
        
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lbteamname = new Label("Team name : "+tn);
        Label lbdescription = new Label("description : "+T.getDescription());
		Label lbnbrmeeting = new Label("nbrmeeting : "+T.getNbrmeeting());
		Label lbduration = new Label("duration : "+T.getDuration());
		Label delete = new Label("");
		Label edit = new Label("                                                                            ");
        delete.setTextPosition(LEFT);
		 edit.setTextPosition(LEFT);

        
 //Graphic

  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        lbteamname.getAllStyles().setFgColor(0x607D8B);
        lbteamname.getAllStyles().setFont(f);
        lbdescription.getAllStyles().setFgColor(0xFFFFFF);
		lbduration.getAllStyles().setFgColor(0xFFFFFF);
		lbnbrmeeting.getAllStyles().setFgColor(0xFFFFFF);
		
		
        delete.getAllStyles().setFgColor(0xfa626b);
        delete.getAllStyles().setFont(f);     
        
        C.getAllStyles().setBgColor(0x28afd0);
        C.getAllStyles().setBgTransparency(255);
		        edit.getAllStyles().setFgColor(0x6967ce);
				
        C.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        C.getAllStyles().setPadding(20,20,20,20);
        C.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0xFFFFFF)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
		
	FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
	        FontImage.setMaterialIcon(edit, FontImage.MATERIAL_EDIT);

	
	ServiceMeeting s=new ServiceMeeting();
        delete.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
        s.deleteMeeting(T.getId());
        Dialog.show("Succès", "Le Meeting a été supprimé avec succès", "OK", null);
             new MeetingForm(this, res).show();
        });
		 edit.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
			new ModifierMeetingForm(this, res,T).show();
        });
		 
        C1.add(edit);
		C1.add(delete);
		
		C.add(C1);

		C.add(lbteamname);
        C.add(lbdescription);
		C.add(lbduration);
		C.add(lbnbrmeeting);
		
        add(C);
 

    }  
	
}