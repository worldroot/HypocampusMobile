/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.mycompany.myapp.forms;



import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.models.Event;
import com.mycompany.myapp.services.ServiceEvent;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Imen BenAbderrahmen
 */
public class EventStatForm extends BaseForm {

    
    public EventStatForm(Form previous,Resources res) {
        
        super("Statistiques", new BorderLayout());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
         
        getTitleArea().setUIID("Container");
        setTitle("Event");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        
        
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
        createPieChartForm(this);
    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(30);
        renderer.setLegendTextSize(30);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
//        for (double value : values) {
            series.add("Formations" , values[0]);
            series.add("Cours" , values[2]);
             series.add("Workshop" , values[1]);
//        }
        return series;
    }

    public void createPieChartForm(Form f) {

        
      ServiceEvent sv = new ServiceEvent();
      List<Integer> li=sv.Stati();
       
       
        
        // Generate the values
        double[] values = new double[]{li.get(0).doubleValue()/29, li.get(1).doubleValue()/29,li.get(2).doubleValue()/29};
        System.out.println("Values "+li.get(0).doubleValue()/29);
        
        
        
        int[] colors = new int[]{0xf4b342, 0x52b29a, 33023};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(80);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        
        PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, c);
         Label prc = new Label("Stati");
       
        f.addComponent(BorderLayout.SOUTH, prc);
        
    }
}

