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
import com.mycompany.myapp.models.Backlog;
import com.mycompany.myapp.models.Task;
import com.mycompany.myapp.services.ServiceBacklog;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author mehdibehira
 */
public class BacklogTasksForm extends BaseForm {
    InteractionDialog dlg = null;
    
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

         Button btn = null;
        //this.add(new SpanLabel(new ServiceTask().getAllTasks(id_backlog).toString()));
        ArrayList<Task> tasks =  new ServiceTask().getAllTasks(id_backlog);
        for (Task t : tasks) {
            addItem(t, res, 
                            btn = new Button(new Command("taches", t.getId()))
                    , this, id_backlog, previous);
        }

       
        this.show();
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        
   }
   
    public void addItem(Task tache , Resources res, Button btn, Form this_form, int id_backlog, Form previous) {
            
       Button close = new Button("Close");
       Button delete = new Button("Delete");
       String archived_string="";

        ImageViewer img = null;
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        img = new ImageViewer(res.getImage("Software-Git.png"));
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label task_id = new Label(Integer.toString(tache.getId()));
        Label task_name = new Label(tache.getTitle());
        Label story_points = new Label(Integer.toString(tache.getStory_points()));
        Label priority = new Label(Integer.toString(tache.getPriority()));
        if(tache.getArchive() == 0){
            archived_string = "Non Archivée";
        }
        else{
            archived_string = "Archivée";
        }
        Label archived = new Label(archived_string);
        
        Label sprint_name = new Label(tache.getSprint_name());
        Label description_foncti = new Label(tache.getDescription_fonctionnel());
        Label description_technique = new Label(tache.getDescription_technique());
        
        
        

        task_name.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
           
            
            
                dlg = new InteractionDialog("Tache",  new BorderLayout());
        
          dlg.add(BorderLayout.CENTER, BoxLayout.encloseY(new SpanLabel("Id :" + task_id.getText() + " \n "
                    + "Titre : " + task_name.getText() + " \n "
                    + "Story points : "+ story_points.getText()+ " \n "
                    + "Priorite : " + priority.getText() + " \n "
                    + "Archive : "+ archived.getText() + " \n "
                    + "Sprint : "+ sprint_name.getText()+ " \n "
                    + "Description Fonctionnele : "+ description_foncti.getText()+ " \n " 
                    + "Description Technique : "+ description_technique.getText()+ " \n "  ), delete, close));
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
                               if (new ServiceTask().deleteTask(id_backlog, tache.getId()))
                               {
                                 Dialog.show("Alert", "Tache supprimée", new Command("OK"));
                               } else{
                                 Dialog.show("Alert", "Erreur", new Command("OK"));
                               }
                               dlg.dispose();
                               dlg.remove();
                               delete.remove();
                               removeComponent(dlg);
                               this.refreshTheme();
                               this_form.repaint();
                               new BacklogTasksForm(previous, res, id_backlog).show();
                                });
                    
              
            
        });
        

        
           
        
                
                 
        C2.add(task_name);
        C2.add(story_points);
        C2.add(sprint_name);
        
        C1.add(img);
        C1.add(C2);
        C1.setLeadComponent(task_name);
        
        this.add(C1);
        this.add(createLineSeparator(0xeeeeee));
        this.refreshTheme();
         
   
    }
   
   
   

    
}
