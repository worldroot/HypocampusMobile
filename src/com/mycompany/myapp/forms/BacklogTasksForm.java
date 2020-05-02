/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Backlog;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author mehdibehira
 */
public class BacklogTasksForm extends BaseForm {
    
   public BacklogTasksForm(Form previous, Resources res, int id_backlog) {
        super("Taches list", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("Taches Index");
        getContentPane().setScrollVisible(false);

        
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


        this.add(new SpanLabel(new ServiceTask().getAllTasks(id_backlog).toString()));

       
        this.show();
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
   }
   

    
}
