/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceParticipant;

/**
 *
 * @author ASUS
 */
public class ParticipantListForm extends Form{
    
    public ParticipantListForm(Form previous) {
              
        super("Participant list", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("Participant Index");
        getContentPane().setScrollVisible(false);


        this.add(new SpanLabel(new ServiceParticipant().getAllParticipants().toString() ) );

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
    
}
