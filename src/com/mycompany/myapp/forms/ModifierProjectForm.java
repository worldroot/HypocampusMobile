/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Project;
import com.mycompany.myapp.models.User;
import com.mycompany.myapp.services.ServiceProject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author 21694
 */
public class ModifierProjectForm extends  BaseForm {
   ComboBox<User> owner;
    public ModifierProjectForm(Form previous,Resources res ,Project projectT)  {
 super("ajouter un projet", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("ajouter un projet");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
                Image img = res.getImage("planning-projet.jpg");
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
        
         Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label name_L = new Label("  "+"Nom du projet : ");
            TextField name = new TextField("",projectT.getName());
            Label owner_L = new Label("  "+"Propriétaire : ");
         
            Label start_date_L = new Label("  "+"Date de début : ");
            Picker start_date = new Picker();
            start_date.setDate(projectT.getStart_date());
            Label end_date_L = new Label("  "+"Date fin : ");
            Picker end_date = new Picker();
            end_date.setDate(projectT.getEnd_date());
            Label description_L = new Label("  "+"Description : ");
            TextField description = new TextField("",projectT.getDescription());
            Button btn = new Button("Valider");
            owner = new ComboBox();
                        
	    ServiceProject s = new ServiceProject();
            for ( User T : s.getAllProjects_user()) {
	          owner.addItem(T);
		}
             c.add(name_L);
             c.add(name);
             c.add(owner_L);
             c.add(owner);
             c.add(start_date_L);
             c.add(start_date);
             c.add(end_date_L);
             c.add(end_date);
             c.add(description_L);
             c.add(description);
             c.add(btn);
             add(c);
          

            show();
            btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
          // Date dateS=start_date.getDate();//converting string into sql date
           //Date datef=end_date.getDate();
           String a=start_date.getDate().toString();
                System.out.println(a);
           String b=end_date.getDate().toString();
            System.out.println(b);
         
                Project p=new Project(name.getText(),owner.getSelectedItem().getUsername(),start_date.getDate(),end_date.getDate(),description.getText(),0);
                ServiceProject sp=new ServiceProject();
                 
            
                System.out.println(compare(start_date.getDate().toString(),end_date.getDate().toString()));
                if(!name.getText().equals("")&&!description.getText().equals("")){
                    
                if(compare(start_date.getDate().toString(),end_date.getDate().toString())==-1){
                sp.updateProject(p,projectT.getId());
                new AfficherProjectForm(this,res).showBack();
                }
                       else{
                Dialog.show("WARNING","vérifier date de création du projet ", "OK",null);
                }
                }
                else{
                 Dialog.show("WARNING"," verifié vos parametre ", "OK",null);
                }

 

            });
        
        //Return
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
  public  int compare(String Sd1, String Sd2) {
      try {
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                
       String Sd2d = parseDate(Sd1,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");
       String Sd2f = parseDate(Sd2,"EEE MMM dd HH:mm:ss zzz yyyy", "MM-dd-yyyy");
       Date d1 = formatter.parse(Sd2d);
       Date d2 = formatter.parse(Sd2f);
        if (d1 == null) return d2 == null ? 0 : -1;
        if (d2 == null) return 1;
        if (d1.getTime() < d2.getTime()) {
            return -1;
        } else if (d1.getTime() > d2.getTime()) {
            return 1;
        }
      }
         catch (ParseException e1) {
         e1.printStackTrace();
         }
        return 0;
    }
      public String parseDate(String dateTime,String inputPattern, String outputPattern) throws ParseException {

       SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
       SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

       Date date = inputFormat.parse(dateTime);
       String str = outputFormat.format(date);

       return str;
}
}
