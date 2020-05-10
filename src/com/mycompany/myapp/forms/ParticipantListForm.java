/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceParticipant;

/**
 *
 * @author ASUS
 */
public class ParticipantListForm extends BaseForm{
    
    public ParticipantListForm(Form previous, Resources res) {
         
         
        super("Add Event", BoxLayout.y());

        
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("Participant");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        
                Image img = res.getImage("welcome-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        
        
                add(LayeredLayout.encloseIn(
                sl
        ));
                
        
        this.add(new SpanLabel(new ServiceParticipant().getAllParticipants().toString() ) );
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
       
    }


       

       
    
    
}
