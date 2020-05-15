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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Event;
import com.mycompany.myapp.services.ServiceEvent;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class EventListForm extends BaseForm {
    InteractionDialog dlg = null;
    public EventListForm(Form previous,Resources res, int idev) {
        
        super("Event List", BoxLayout.y());

       
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("Event");
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

       // this.add(new SpanLabel(new ServiceEvent().getAllEvents().toString()));
         Button btn = null;
        //this.add(new SpanLabel(new ServiceTask().getAllTasks(id_backlog).toString()));
        ArrayList<Event> evs =  new ServiceEvent().getAllEvents(idev);
        for (Event t : evs) {
            addItem(t, res, 
                            btn = new Button(new Command("Events", t.getIdev()))
                    , this, idev, previous);
        }
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
    }
    
    
    public void addItem(Event e , Resources res, Button btn, Form this_form, int idev, Form previous) {
            
       Button close = new Button("Close");
       Button delete = new Button("Delete");
       Button edit = new Button("Edit");
       

        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        img = new ImageViewer(res.getImage("Software-Git.png"));
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label ide = new Label(Integer.toString(e.getIdev()));
        Label txt = new Label("Titres d'events :");
        Label tit = new Label(e.getTitreEvent());
        Label typ = new Label(e.getTypeEvent());
        Label image = new Label(e.getImage_name());
        Label num = new Label(Integer.toString(e.getNumeroEvent()));
        Label de = new Label(""+"Date : "+e.getDateEvent());
        Label df = new Label(""+"End Date : "+e.getEnddateEvent());

        tit.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
           
            
            
                dlg = new InteractionDialog("Event",  new BorderLayout());
        
          dlg.add(BorderLayout.CENTER, BoxLayout.encloseY(new SpanLabel("Idev :" + ide.getText() + " \n "
                    + "Titre : " + tit.getText() + " \n "
                    + "Type : " + typ.getText() + " \n "
                    + "Numero : " + num.getText() + " \n "
                    + "" + "15/05/2020" + " \n "
                    + "" + "15/05/2020" + " \n "
                    + "Image : " + image.getText() + " \n "
                    ), delete, edit ,close));
          dlg.show(TOP, BOTTOM, LEFT, RIGHT);
                
        
        

            close.addActionListener(
                    (ee) -> {
                        dlg.dispose();
                        dlg.remove();
                        close.remove();
                        
                        removeComponent(dlg);
                                });
                       
            delete.addActionListener(
                    (ee) -> {
                               if (new ServiceEvent().deleteEvent(e))
                               {
                                 Dialog.show("Alert", "Event supprimée", new Command("OK"));
                               } else{
                                 Dialog.show("Alert", "Erreur", new Command("OK"));
                               }
                                dlg.dispose();
                                dlg.remove();
                                delete.remove();
                                removeComponent(dlg);
                                this.refreshTheme();
                                this_form.repaint();
                                new EventListForm(previous, res, idev).show();
                                });
                    
             edit.addActionListener(
                     (ee) -> {
                new EventEditForm(previous, res, e).show();
        });  
            
        });
        

        C2.add(txt);  
        C2.add(tit);
        
        
        
        C1.add(img);
        C1.add(C2);
        C1.setLeadComponent(tit);
        
        this.add(C1);
        this.add(createLineSeparator(0xeeeeee));
        this.refreshTheme();
         
   
    }
           

    
    
        
}



   

