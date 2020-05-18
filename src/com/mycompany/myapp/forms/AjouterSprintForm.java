/*
 * To change this license header, choose License Headers in Sprint Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Project;
import com.mycompany.myapp.models.Sprint;
import com.mycompany.myapp.services.Servicesprint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author 21694
 */
public class AjouterSprintForm extends  BaseForm {

    public AjouterSprintForm(Form previous,Resources res,Project project_)  {
 super("ajouter un projet", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("ajouter un projet");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
                Image img = res.getImage("planning-schema-concept.jpg");
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
            Label name_L = new Label("  "+"Nom du Sprint : ");
            TextField name = new TextField("", "Nom");
            Label start_date_L = new Label("  "+"Date de début : ");
            Picker start_date = new Picker();
            Label end_date_L = new Label("  "+"Date fin : ");
            Picker end_date = new Picker();
            Button btn = new Button("Valider");
             //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             //Date endDate = formatter.parse(end_date.getText()); 
             //Date endDate2 = formatter.parse(end_date.getText()); 
             c.add(name_L);
             c.add(name);
             c.add(start_date_L);
             c.add(start_date);
             c.add(end_date_L);
             c.add(end_date);
             c.add(btn);
             add(c);
            //addAll(name,owner,end_date,end_date,description,btn);

            show();
            btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
          // Date dateS=start_date.getDate();//converting string into sql date
           //Date datef=end_date.getDate();
           String a=start_date.getDate().toString();
           System.out.println(a);
           String b=end_date.getDate().toString();
           System.out.println(b);
         
                Sprint p=new Sprint(name.getText(),start_date.getDate(),end_date.getDate(),project_.getId());
                Servicesprint sp=new Servicesprint();
                 
            
                System.out.println(compare(start_date.getDate().toString(),end_date.getDate().toString()));
                if(!name.getText().equals("")){
                    
                if(compare(start_date.getDate().toString(),end_date.getDate().toString())==-1){
                sp.ajouterSprint(p);
                new AfficherSprintForm(this,res,project_).showBack();
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
