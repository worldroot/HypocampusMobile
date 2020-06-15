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
import com.mycompany.myapp.models.Entreprise;
import com.mycompany.myapp.services.ServiceEntreprise;

/**
 *
 * @author Houcem
 */
public class ModifierEntrepriseForm extends BaseForm {
	
	public ModifierEntrepriseForm(Form previous,Resources res, Entreprise E) {
		super("modifier un Entreprise", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier un Entreprise");
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
            Label lbTeamname = new Label("  "+" Name : ");
            TextField tfTeamname = new TextField(E.getName(), " name");
			
			Label lbEmail = new Label("  "+" Email : ");
            TextField tfEmail = new TextField(E.getEmail(), " email");
            
            Label lbDate = new Label(E.getCreatedate()+"Date : ");
            Picker pDate = new Picker();

			Button btn = new Button("Valider");
             //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             //Date endDate = formatter.parse(end_date.getText()); 
             //Date endDate2 = formatter.parse(end_date.getText()); 
             c.add(lbTeamname);
             c.add(tfTeamname);
			 c.add(lbEmail);
             c.add(tfEmail);
             c.add(lbDate);
             c.add(pDate);
             
             c.add(btn);	
             add(c);
            //addAll(name,owner,end_date,end_date,description,btn);

            show();
            btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
          
           String a=pDate.getDate().toString();
                System.out.println(a);
           
         
                Entreprise T=new Entreprise(tfTeamname.getText(),tfEmail.getText(),pDate.getDate());
                ServiceEntreprise sp=new ServiceEntreprise();
                 
            
                if(!tfTeamname.getText().equals("")){
                    
					
					sp.updateTeam(T,E.getId());
					new EntrepriseForm(this,res).showBack();
					
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