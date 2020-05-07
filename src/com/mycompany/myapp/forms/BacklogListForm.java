/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Backlog;
import com.mycompany.myapp.services.ServiceBacklog;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mehdibehira
 */
public class BacklogListForm extends BaseForm {
    int backlog_clicked = -1;
    InteractionDialog dlg = null;
        public BacklogListForm(Form previous, Resources res) {
        super("Backlog list", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("Backlog Index");
        getContentPane().setScrollVisible(false);

        
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

                Button btn = null;
                Button btn2 = null;
        //this.add(new SpanLabel(new ServiceBacklog().getAllBacklogs().toString()));
        ArrayList<Backlog> backlogs =  new ServiceBacklog().getAllBacklogs();
        for (Backlog b : backlogs) {
            addItem(b, res, 
                            btn = new Button(new Command("taches", b.getId())),
                            btn2 = new Button(new Command("Ajouter Tache", b.getId()))
                    
                    , this);
        }
        
        
        this.show();


        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
        
        
        
        
        public int addItem(Backlog backlog , Resources res, Button btn,Button btn2, Form this_form) {
            
                             Button close = new Button("Close");           

            ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

       img = new ImageViewer(res.getImage("book-lightbulb.png"));
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label backlog_id = new Label("Backlog :"+Integer.toString(backlog.getId()));
        Label points_to_do = new Label(Integer.toString(backlog.getPoints_to_do()));
        Label points_in_progress = new Label(Integer.toString(backlog.getPoints_to_do()));
        Label points_done = new Label(Integer.toString(backlog.getPoints_done()));
        Label project_name = new Label(backlog.getProject_name());
        

        backlog_id.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {

            dlg = new InteractionDialog("Backlog",  new BorderLayout());
          dlg.add(BorderLayout.CENTER, BoxLayout.encloseY(new SpanLabel("" + backlog_id.getText() + " \n "
                    + "Points to do : " + points_to_do.getText() + " \n "
                    + "Points in progress : "+ points_in_progress.getText()+ " \n "
                    + "Points Done : " + points_done.getText() + " \n "
                    + "Project name : "+ project_name.getText()), btn, btn2, close));
            

                        
                        
                dlg.show(TOP, BOTTOM, LEFT, RIGHT);
            close.addActionListener((ee) -> 
            {           dlg.dispose();
                         dlg.remove();
                        close.remove();
                        btn.remove();
                        btn2.remove();
                        
                        removeComponent(dlg);
            }
                    
                    
            );
              
            
        });
        
                btn.addActionListener((evt) -> {

                             new BacklogTasksForm(BacklogListForm.this, res, backlog.getId()).show();
 
                     });
                btn2.addActionListener((evt) -> {

                     new AjoutTaskForm(BacklogListForm.this, res, backlog.getId()).show();
 
                     });
        
           
        
                
                 
        C2.add(backlog_id);
        C2.add(project_name);
         C1.add(img);
        C1.add(C2);
        C1.setLeadComponent(backlog_id);
        
        this.add(C1);
        this.add(createLineSeparator(0xeeeeee));
        this.refreshTheme();
        
        
         System.out.println("end boucle "+backlog_clicked);


        
        
        return backlog_clicked;

    }
    
}
