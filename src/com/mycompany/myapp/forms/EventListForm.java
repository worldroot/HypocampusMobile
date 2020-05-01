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
import com.mycompany.myapp.services.ServiceEvent;

/**
 *
 * @author ASUS
 */
public class EventListForm extends Form {
        
          public EventListForm(Form previous) {
              
        super("Event list", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("Event Index");
        getContentPane().setScrollVisible(false);


        this.add(new SpanLabel(new ServiceEvent().getAllEvents().toString()));

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
    
}
