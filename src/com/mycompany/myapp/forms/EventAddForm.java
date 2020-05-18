/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.FloatingHint;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Event;
import com.mycompany.myapp.services.ServiceEvent;



/**
 *
 * @author ASUS
 */
public class EventAddForm extends BaseForm{
    
    public EventAddForm(Form previous, Resources res){
        
        
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");

        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField titre = new TextField("", "Titre", 20, TextField.ANY);
        
        Picker type = new Picker();
        type.setType(Display.PICKER_TYPE_STRINGS);
        type.setStrings("Cours", "Workshop", "Formation");
        
        TextField capa = new TextField("", "Capacite", 20, TextField.ANY);
        
        Picker dd = new Picker();
        dd.setType(Display.PICKER_TYPE_CALENDAR);
        Picker de = new Picker();
        de.setType(Display.PICKER_TYPE_CALENDAR);
        TextField img = new TextField("", "IMG", 20, TextField.ANY);

        titre.setSingleLineTextArea(true);
        img.setSingleLineTextArea(true);
        capa.setSingleLineTextArea(true);
        // confirmPassword.setSingleLineTextArea(false);
        Button confirmer = new Button("Confirmer");
        
        
        
        Container content = BoxLayout.encloseY(
                new Label("Ajout Event", "LogoLabel"),
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
                  
         Event evt = new Event(titre.getText(), type.getText() , Integer.parseInt(capa.getText()), dd.getDate(), de.getDate(), img.getText() );  
         ServiceEvent v = new ServiceEvent();
         v.addEvent(evt);
         new EventForm(previous,res).show(); 
         Message m = new Message("Forum Created");
            Display.getInstance().sendMessage(new String[] {"boughzala.ghassen@gmail.com"}, "New sujet"+ titre.getText(), m);
        }
        
        );
    }
    
    
    
    
}    
        
    /*    
        setTitle("Add Event");
        setLayout(BoxLayout.y());
        
        TextField tfTitre = new TextField("","Titre");
        TextField tfType = new TextField("","Type");
        TextField tfCap = new TextField("","Capacité");
        
        Button btnValider = new Button("Add Event");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if( (tfTitre.getText().length()==0) || (tfType.getText().length()==0) || (tfCap.getText().length()==0) ) {
                    Dialog.show("Alert", "You need to fill aal the fields", new Command("OK"));
                }
                else{
                    try{
                        Event e = new Event(tfTitre.getText(),tfType.getText(), Integer.parseInt(tfCap.getText()) );
                        if(new ServiceEvent().addEvent(e))
                           Dialog.show("Success", "Done", new Command("OK"));
                        else
                           Dialog.show("ERROR", "Server ERROR", new Command("OK"));
                    } catch (NumberFormatException e){
                           Dialog.show("ERROR", "Check your fields, Capacite must be a number !", new Command("OK"));
                    }
                    
                }
                    
            } 
        });
        
        addAll(tfTitre,tfType,tfCap,btnValider);
        
        
        
        
*/
   
      

