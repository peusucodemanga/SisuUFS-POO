/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sisuufs;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author Usuário
 */
public class Grafico_SetoresController implements Initializable {

    @FXML
    private PieChart Pizza;
    private ListaCandidatos listaDeCandidatos = ListaCandidatos.getInstance();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Centros teste = new Centros();
        HashMap<String, Integer> centrosHash = teste.separacaoCentro();
        ObservableList<PieChart.Data> pieChartData; 

        //centrosHash.forEach((key, value) -> System.out.println(key + " : " + value));
        ArrayList<PieChart.Data> dados = new ArrayList<>();

        centrosHash.forEach((key, value) -> dados.add(new PieChart.Data(key, value)));
        
        pieChartData = FXCollections.observableArrayList(dados);
        
        Pizza.setTitle("Quantidade de pessoas por centro");
        Pizza.setData(pieChartData);

        for(PieChart.Data d : dados){
            Tooltip tooltip = new Tooltip(d.getName() + ":" + Double.toString(d.getPieValue()));
            Tooltip.install(d.getNode(), tooltip);
        }
    }    
    
}
