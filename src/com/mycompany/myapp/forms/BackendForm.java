/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
/**
 *
 * @author mehdibehira
 */
public class BackendForm extends BaseForm {
        public BackendForm(Resources res) {
        super("Backend", BoxLayout.y());
                        Toolbar tb = new Toolbar(true);
                               setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("Backend");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
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
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("hypo.png"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));
        
        
        
       
        Button btnBacklog = new Button("Backlog Menu");
        
        btnBacklog.addActionListener((evt) -> {
            new BacklogForm(this, res).show();
        });
        Button btnProject = new Button("Projets");
        
        btnProject.addActionListener((evt) -> {
            new AfficherProjectForm(this, res).show();
        });

        this.addAll(new Label("Choose an option :"), btnBacklog,btnProject);
    }
    
}
