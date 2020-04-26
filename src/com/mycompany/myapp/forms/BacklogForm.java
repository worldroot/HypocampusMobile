/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceBacklog;

/**
 *
 * @author mehdibehira
 */
public class BacklogForm extends Form {
    
        public BacklogForm(Form previous) {
        super("Backlog Menu", BoxLayout.y());

        Button btnAddBacklog = new Button("Add Backlog");
        Button btnBacklogList = new Button("Backlog List");

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
                btnBacklogList.addActionListener((evt) -> {
            new BacklogListForm(this).show();
        });

        this.addAll(new Label("Choose an option :"), btnAddBacklog, btnBacklogList);
    }
    
}
