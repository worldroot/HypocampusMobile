/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.forms;

/**
 *
 * @author mehdibehira
 */





import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utils.Statics;



public class BacklogStatForm extends BaseForm{


    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(40);
        renderer.setLegendTextSize(40);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }


    protected CategorySeries buildCategoryDataset(String title, double[] values, int id_backlog, int id_user) {

        CategorySeries series = new CategorySeries(title);
        Label l1 = new Label();
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/ProjectBacklog/stat/todo/backlog/"+id_backlog+"/user/"+id_user;
        con.setUrl(Url);
     //   String a=new String(con.getResponseData());
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            // Dialog.show("Succés", "Article", "ok", null);
            System.out.println(str);
            System.out.println("aman"+str);
            l1.setText(str);
            System.out.println(l1.getText());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);


      //  ServiceArticle ser=new ServiceArticle();
        //String x=ser.stat();


        double v1 = (double) Float.parseFloat(l1.getText());


        Label l2 = new Label();
        ConnectionRequest con2 = new ConnectionRequest();
        String Url2 = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/ProjectBacklog/stat/inprogress/backlog/"+id_backlog+"/user/"+id_user;
        con2.setUrl(Url2);
        //   String a=new String(con.getResponseData());
        con2.addResponseListener((e) -> {
            String str = new String(con2.getResponseData());
            // Dialog.show("Succés", "Article", "ok", null);
            System.out.println(str);
            System.out.println("aman"+str);
            l2.setText(str);
            System.out.println(l2.getText());

        });
        NetworkManager.getInstance().addToQueueAndWait(con2);

        double v2 = (double) Float.parseFloat(l2.getText());


        Label l3 = new Label();
        ConnectionRequest con3 = new ConnectionRequest();
        String Url3 = Statics.BASE_URL + "/Hypocampus/web/app_dev.php/api/ProjectBacklog/stat/done/backlog/"+id_backlog+"/user/"+id_user;
        con3.setUrl(Url3);
        //   String a=new String(con.getResponseData());
        con3.addResponseListener((e) -> {
            String str = new String(con3.getResponseData());
            // Dialog.show("Succés", "Article", "ok", null);
            System.out.println(str);
            System.out.println("aman"+str);
            l3.setText(str);
            System.out.println(l3.getText());

        });
        NetworkManager.getInstance().addToQueueAndWait(con3);

        double v3 = (double) Float.parseFloat(l2.getText());

        series.add("Taches a faire", v1);
        series.add("Taches en cours", v2);
        series.add("Taches terminer ", v3);
        
        return series;

    }

    public Form createPieChartForm1(Resources theme, Form previous,int id_backlog, int id_user, Resources res)  {

        // Generate the values
        double[] values = new double[]{50, 99, 11, 30, 25, 60};

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.MAGENTA, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(45);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.

        PieChart chart = new PieChart(buildCategoryDataset("Pourcentages", values, id_backlog, id_user), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("Statistique");
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, c);

        // menu  cc= new menu(theme);
        //Toolbar
        Toolbar.setGlobalToolbar(true);

        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_HOME, s);

//
//        f.getToolbar().addCommandToLeftBar("Home", icon, (e) -> {
//
//
//
//            Log.p("Clicked");
//              cc.getF().show();
//
//
//
//
//                });

        f.getToolbar().addMaterialCommandToLeftBar("Back",FontImage.MATERIAL_ARROW_BACK,(e)-> {
      
        new BacklogListForm(previous, res).show(); 
        });
        return f;
    }

}