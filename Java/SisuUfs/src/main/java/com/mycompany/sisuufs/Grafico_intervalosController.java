/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sisuufs;

import java.net.URL;
import java.util.ArrayList;
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

    protected ListaCandidatos listaDeCandidatos = ListaCandidatos.getInstance();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Agora eu preciso ter aqui os dados do pessoal, entao tenho q criar um array de int de 0 a 8, cada 
        //entrada sendo o tanto de pessoas no determinado intervalo. Preciso tbm dar um jeito de pegar isso da main.
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("Quantidade de candidatos");
        // daqui pra baixo eh setando as propriedades do grafico
        ArrayList<Candidato> lCandidatos = listaDeCandidatos.getListaCandidatos();
        int [] qntCandidatos = new int [9];
        for(int i = 0 ; i < 9; i++){
            qntCandidatos[i] = 0;
        }
        for(Candidato c : lCandidatos){
            qntCandidatos[c.grupo]++;
        }
        for(int i = 0 ; i < 9; i++){
            int limInferior = 400 + 50*i, limSuperior = 400 + 50*(i+1);
            String aux = "";
            aux += Integer.toString(limInferior) + " - " + Integer.toString(limSuperior);
            series1.getData().add(new XYChart.Data<>(aux,qntCandidatos[i]));
        }
        graficoIntervalos.setCategoryGap(20);
        graficoIntervalos.getData().addAll(series1);
    }
}
