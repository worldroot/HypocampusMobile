/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.ComboBox;
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
import com.mycompany.myapp.models.team;
import com.mycompany.myapp.services.ServiceTeam;

/**
 *
 * @author Houcem
 */
public class TeamForm extends BaseForm {
	
	ComboBox<team> Combo;

	TeamForm(Form previous, Resources res) {
		super("liste des Teams", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("liste des Teams");
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
    

                new AjouterTeamForm(this,res).show();


            }); 
		fab.bindFabToContainer(this.getContentPane());
          ServiceTeam sp=new ServiceTeam();
        Combo = new ComboBox();
   
        for (team T :  sp.getAllTeams()) {

                  addItem(T,res);
        }
        
         show();

		 this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new BackendForm(res).show();
        });
		
	}
	
	 public void addItem(team T,Resources res) {

        
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label teamname = new Label("Team name : "+T.getTeamname());
        Label date = new Label("Date of creatiion : "+T.getDateofcreation());
		Label delete = new Label("");
		Label edit = new Label("                                                                            ");
        delete.setTextPosition(LEFT);
		 edit.setTextPosition(LEFT);

        
 //Graphic

  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        teamname.getAllStyles().setFgColor(0x607D8B);
        teamname.getAllStyles().setFont(f);
        date.getAllStyles().setFgColor(0xFFFFFF);
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

	
	ServiceTeam s=new ServiceTeam();
        delete.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
        s.deleteTeam(T.getId());
        Dialog.show("Succès", "Le Team a été supprimé avec succès", "OK", null);
             new TeamForm(this, res).show();
        });
		 edit.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
        new ModifierTeamForm(this, res,T).show();
        });
		 
        C1.add(edit);
		C1.add(delete);
		
		C.add(C1);
        
		C.add(teamname);
        C.add(date);
		
        add(C);
 

    }  
	
}
