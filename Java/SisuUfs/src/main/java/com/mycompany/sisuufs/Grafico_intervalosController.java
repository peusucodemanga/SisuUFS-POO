/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sisuufs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
/**
 * FXML Controller class
 *
 * @author dimit
 */
public class Grafico_intervalosController implements Initializable {


    @FXML
    private BarChart<String, Integer> graficoIntervalos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("Quantidade de candidatos");
        series1.getData().add(new XYChart.Data<>("700 - 750",50));
        series1.getData().add(new XYChart.Data<>("750 - 800",25));
        series1.getData().add(new XYChart.Data<>("800 - 850",5));
        graficoIntervalos.getData().addAll(series1);
    }    
    
}
