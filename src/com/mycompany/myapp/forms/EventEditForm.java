/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Event;
import com.mycompany.myapp.models.Project;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceProject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class EventEditForm extends BaseForm {
    
    public EventEditForm(Form previous ,Resources res ,Event EVT)  {
        
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");

        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField titre = new TextField(EVT.getTitreEvent(),"" , 20, TextField.ANY);
        TextField type = new TextField(EVT.getTypeEvent(),"", 20, TextField.ANY);
        TextField capa = new TextField(Integer.toString(EVT.getNumeroEvent()), "" , 20, TextField.ANY);
        
        Picker dd = new Picker();
        dd.setType(Display.PICKER_TYPE_CALENDAR);
        //dd.setDate(EVT.getDateEvent());
        Picker de = new Picker();
        de.setType(Display.PICKER_TYPE_CALENDAR);
        //de.setDate(EVT.getEnddateEvent());
        
        TextField img = new TextField(EVT.getImage_name(), "" , 20, TextField.ANY);

        titre.setSingleLineTextArea(true);
        img.setSingleLineTextArea(true);
        capa.setSingleLineTextArea(true);
        // confirmPassword.setSingleLineTextArea(false);
        Button confirmer = new Button("Confirmer");
        
        
        Container content = BoxLayout.encloseY(
                new Label("Modifier Event", "LogoLabel"),
                new FloatingHint(titre),
                createLineSeparator(),
                type,
                createLineSeparator(),
                new FloatingHint(capa),
                createLineSeparator(),
                dd,
                createLineSeparator(),
                de,
                createLineSeparator(),
                new FloatingHint(img),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                confirmer
              //  FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        confirmer.requestFocus();
        confirmer.addActionListener(e ->
        {
                  
         Event evt = new Event(EVT.getIdev() ,titre.getText() , Integer.parseInt(capa.getText()) ,type.getText()  , dd.getDate(), de.getDate(), img.getText() ); 
            System.out.println(evt);
         ServiceEvent v = new ServiceEvent();
         v.updateEvent(evt);
         new EventListForm(previous,res,TOP).show();           
        }
        
        );
    
    
    } 
}
