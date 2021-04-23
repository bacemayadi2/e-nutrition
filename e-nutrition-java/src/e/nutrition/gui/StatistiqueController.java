/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.FicheConsultation;
import e.nutrition.Services.ServiceFicheConsultation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class StatistiqueController implements Initializable {

    @FXML
    private StackedBarChart<String, Number> barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ServiceFicheConsultation serr = new ServiceFicheConsultation();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        List<String> list = serr.DisplayDate();
        for (String aux : list)
        {
        series.getData().add(new XYChart.Data<>(aux.toString(), serr.calculer(aux.toString())));
        }
        
        series.setName("Répartition des consultations");
        
      //  series.getData().add(new XYChart.Data<>("Non Approuvé", serr.calculer()));
      //  series.getData().add(new XYChart.Data<>("Approuvé", serr.calculerapp()));
        barChart.getData().addAll(series);
    }    
    
}
