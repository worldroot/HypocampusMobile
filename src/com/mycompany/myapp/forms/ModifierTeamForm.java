/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.models.team;
import com.mycompany.myapp.services.ServiceTeam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author 21694
 */
public class ModifierTeamForm extends  BaseForm {

    public ModifierTeamForm(Form previous,Resources res ,team T)  {
 super("modifier team", BoxLayout.y());
        
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
            Label name_L = new Label("  "+"Team name : ");
            TextField name = new TextField("",T.getTeamname());
            Label start_date_L = new Label("  "+"Date : ");
            Picker start_date = new Picker();
            start_date.setDate(T.getDateofcreation());
            Button btn = new Button("Valider");
             
			
             c.add(name_L);
             c.add(name);
             c.add(start_date_L);
             c.add(start_date);
             c.add(btn);
             add(c);

            show();
            btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
          
         
         
                team p=new team(name.getText(),start_date.getDate());
                ServiceTeam sp=new ServiceTeam();
                 
            
                if(!name.getText().equals("")){
                    
                sp.updateTeam(p,T.getId());
                new TeamForm(this,res).showBack();
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
        public String parseDate(String dateTime,String inputPattern, String outputPattern) throws ParseException {

				SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
				SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

				Date date = inputFormat.parse(dateTime);
				String str = outputFormat.format(date);

				return str;
		 }
}
