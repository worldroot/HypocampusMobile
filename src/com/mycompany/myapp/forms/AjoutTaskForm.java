/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.FloatingHint;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Task;
import com.mycompany.myapp.services.ServiceTask;
import java.util.Date;


/**
 *
 * @author mehdibehira
 */
public class AjoutTaskForm extends BaseForm {
    
    public AjoutTaskForm(Form previous, Resources res, int id_backlog){
        
        
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");

        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField titre = new TextField("", "Titre", 20, TextField.ANY);
        Picker story_points = new Picker();
        story_points.setType(Display.PICKER_TYPE_STRINGS);
        Picker finished_date = new Picker();
        finished_date.setType(Display.PICKER_TYPE_CALENDAR);
        story_points.setStrings("0", "1", "2", "3",
        "5", "8", "13");
        
        Picker state = new Picker();
        state.setType(Display.PICKER_TYPE_STRINGS);
        state.setStrings("To Do", "Doing", "Done");
        
        TextField priority = new TextField("", "Priorite", 1, TextArea.NUMERIC);
        
        TextField description_fonctionnel = new TextField("", "Description F", 25, TextField.ANY);

        titre.setSingleLineTextArea(true);
        description_fonctionnel.setSingleLineTextArea(false);
        priority.setSingleLineTextArea(true);
        // confirmPassword.setSingleLineTextArea(false);
        Button confirmer = new Button("Confirmer");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(titre),
                createLineSeparator(),
                story_points,
                createLineSeparator(),
                state,
                createLineSeparator(),
                new FloatingHint(priority),
                createLineSeparator(),
                new FloatingHint(description_fonctionnel),
                createLineSeparator(),
                finished_date,
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
                    
           
         Task t = new Task(id_backlog, titre.getText(), description_fonctionnel.getText(), "Description", Integer.parseInt(story_points.getText()), finished_date.getDate(), finished_date.getDate(), state.getText(), Integer.parseInt(priority.getText()), 0, 5);
            ServiceTask st = new ServiceTask();
            st.addTask(t);
            new BacklogForm(previous,res).show();
            
        }
        
        );
    }
    

    }

