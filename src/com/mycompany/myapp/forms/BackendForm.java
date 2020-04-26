/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author mehdibehira
 */
public class BackendForm extends Form {
        public BackendForm() {
        super("Backend", BoxLayout.y());
       
        Button btnBacklog = new Button("Backlog Menu");
        
        btnBacklog.addActionListener((evt) -> {
            new BacklogForm(this).show();
        });

        this.addAll(new Label("Choose an option :"), btnBacklog);
    }
    
}
