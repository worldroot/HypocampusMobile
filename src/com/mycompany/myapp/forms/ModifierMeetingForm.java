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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.meeting;
import com.mycompany.myapp.models.team;
import com.mycompany.myapp.services.ServiceMeeting;
import com.mycompany.myapp.services.ServiceTeam;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Houcem
 */
public class ModifierMeetingForm extends  BaseForm {
	ComboBox<team> combo;
    public ModifierMeetingForm(Form previous,Resources res ,meeting T)  {
		
		super("modifier meeting", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier un team");
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
            Label lbDiscription = new Label("  "+"Description : ");
            TextField tfDiscription = new TextField(T.getDescription(), "Description");
            
            Label lbDuration = new Label("  "+"Duration : ");
            TextField tfDuration= new TextField(T.getDuration(), "Duration");
			
			Label lbnbrmeeting = new Label("  "+"nbrmeeting : ");
            TextField tfnbrmeeting = new TextField(T.getNbrmeeting()+"", "nbrmeeting");
			
			

			Button btn = new Button("Valider");
             c.add(lbDiscription);
             c.add(tfDiscription);
             c.add(lbDuration);
             c.add(tfDuration);
			 c.add(lbnbrmeeting);
             c.add(tfnbrmeeting);
			 
			 
			 combo = new ComboBox();
			 ServiceTeam st = new ServiceTeam();
			 
			 for ( team tt : st.getAllTeams() ) {
				 combo.addItem(tt);
			 }
             c.add(combo);
             c.add(btn);	
             add(c);
            //addAll(name,owner,end_date,end_date,description,btn);

            show();
            btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
          
           
           
         
                meeting TTT=new meeting(combo.getSelectedItem().getId(),tfDiscription.getText(), tfDuration.getText() , Integer.parseInt(tfnbrmeeting.getText())) ;
                ServiceMeeting sp=new ServiceMeeting();
                 
            
                if(!tfDiscription.getText().equals("")&&!tfDuration.getText().equals("")&&!tfnbrmeeting.getText().equals("")){
                    
					
					sp.updateMeeting(TTT,T.getId());
					
					
					
					new MeetingForm(this,res).showBack();
					
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