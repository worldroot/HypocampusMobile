/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.User;
import com.mycompany.myapp.services.ServiceUser;

/**
 *
 * @author Houcem
 */
public class ModifierUserForm extends BaseForm {
	
	ComboBox<String> roles;
	
	public ModifierUserForm(Form previous,Resources res, User u) {
		super("modifier un User", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier un User");
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
        
         Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label lbTeamname = new Label("  "+" Username : ");
            TextField tfTeamname = new TextField(u.getUsername(), " username");
			
			Label lbEmail = new Label("  "+" Email : ");
            TextField tfEmail = new TextField(u.getEmail(), " email");			
			
			Label lbPassword = new Label("  "+" Password : ");
			TextField tfpassword = new TextField("", "Password", 20, TextField.PASSWORD);
			
			roles = new ComboBox();
			
			roles.addItem("ScrumMaster");
			roles.addItem("Admin");
            

			Button btn = new Button("Valider");
             //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             //Date endDate = formatter.parse(end_date.getText()); 
             //Date endDate2 = formatter.parse(end_date.getText()); 
             c.add(lbTeamname);
             c.add(tfTeamname);
			 c.add(lbEmail);
             c.add(tfEmail);
             c.add(lbPassword);
             c.add(tfpassword);
             
             c.add(btn);	
             add(c);
            //addAll(name,owner,end_date,end_date,description,btn);

            show();
            btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
          
           
					//User(String username, String password, String roles, String email);

                User T=new User(tfTeamname.getText(),tfpassword.getText(),"a:1:{i:0;s:17:\"ROLE_SCRUM_MASTER\";}",tfEmail.getText());
                ServiceUser sp=new ServiceUser();
                 
            
                if(!tfTeamname.getText().equals("") && !tfpassword.getText().equals("") && !tfEmail.getText().equals("")){
                    
					
					sp.updateTeam(T, u.getId());
					new UserForm(this,res).showBack();
					
                }
                else{
                 Dialog.show("WARNING"," verifié vos parametre ", "OK",null);
                }

 

            });
        
        //Return
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
	}
}