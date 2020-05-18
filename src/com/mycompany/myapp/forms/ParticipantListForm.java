/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.Component.TOP;
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
import com.mycompany.myapp.models.Event;
import com.mycompany.myapp.models.Participant;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceParticipant;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ParticipantListForm extends BaseForm{
     InteractionDialog dlg = null; 
    public ParticipantListForm(Form previous, Resources res) {
       
         
        super("Add Event", BoxLayout.y());

        
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("Participants List");
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
                
        Button btn = null;        
        ArrayList<Participant> evs =  new ServiceParticipant().getAllParticipants();
        for (Participant t : evs) {
            addItem(t, res, 
                            btn = new Button(new Command("Events"))
                    , this,  previous);
        }
        //this.add(new SpanLabel(new ServiceParticipant().getAllParticipants().toString() ) );
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
       
    }
    
     public void addItem(Participant e , Resources res, Button btn, Form this_form,  Form previous) {
            
       Button close = new Button("Close");
       //Button delete = new Button("Delete");
       //Button edit = new Button("Edit");
       
      
       

        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        img = new ImageViewer(res.getImage("Software-Git.png"));
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Label n = new Label(e.getNomp());
        Label p = new Label(e.getPrenomp());
        Label ps = new Label(e.getPasswordp());
        Label mail = new Label(e.getEmail());


        mail.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
           
            
            
                dlg = new InteractionDialog("Participant",  new BorderLayout());
        
          dlg.add(BorderLayout.CENTER, BoxLayout.encloseY(
          new SpanLabel("Nom :" + n.getText() + " \n "
                    + "Prenom : " + p.getText() + " \n "
                    + "Password : " + ps.getText() + " \n "
                    + "Mail : " + mail.getText() + " \n "
                    ), close));
          dlg.show(TOP, BOTTOM, LEFT, RIGHT);
                
        
        

            close.addActionListener(
                    (ee) -> {
                        dlg.dispose();
                        dlg.remove();
                        close.remove();
                        
                        removeComponent(dlg);
                                });
                       
            
              
            
        });
        

          
        C2.add(mail);
        
        
        
        C1.add(img);
        C1.add(C2);
        C1.setLeadComponent(mail);
        
        this.add(C1);
        this.add(createLineSeparator(0xeeeeee));
        this.refreshTheme();
         
   
    }


       

       
    
    
}
