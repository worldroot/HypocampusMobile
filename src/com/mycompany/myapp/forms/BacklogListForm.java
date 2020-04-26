/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceBacklog;

/**
 *
 * @author mehdibehira
 */
public class BacklogListForm extends Form {
        public BacklogListForm(Form previous) {
        super("Backlog list", BoxLayout.y());

        this.add(new SpanLabel(new ServiceBacklog().getAllBacklogs().toString()));

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
    
}
