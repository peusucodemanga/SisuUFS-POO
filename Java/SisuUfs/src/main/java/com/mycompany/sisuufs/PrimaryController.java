/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sisuufs;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author dimit
 */
public class PrimaryController implements Initializable {


    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void lerCSV(ActionEvent event) {
        try {
            //Inicializando variaveis uteis
            Scanner varredor = new Scanner (new File (textField.getText()));
            ArrayList<Candidato> lista = new ArrayList<>();
            String str = ""; // essa string eh a que to botando na tela com o nome de geral
            int [] maiores = new int[7]; // aqui vou usar pra printar bonitinho a galera
            for(int i = 0; i < 7; i++) maiores[i] = 0;

            //aqui acontece a leitura do csv
            while (varredor.hasNext()){
                String linha = varredor.nextLine();
                Candidato aux;
                String [] dadosAux = linha.split(",");

                if(linha.charAt(0) != 'I'){
                    aux = new Candidato(dadosAux);
                    lista.add(aux);
                    int i = 0;
                    for(String s : dadosAux){
                        if(s.length() > maiores[i]) maiores[i] = s.length();
                        i++;
                    }
                }
                else {
                    for(int i = 0; i < dadosAux.length; i++){
                        if(i <=2) str += dadosAux[i] + "\t\t\t\t\t\t\t";
                        else if(i >= dadosAux.length - 3) str += dadosAux[i] + "\t\t";
                        else str += dadosAux[i] + "\t\t\t";
                    } 
                }
            }
            str += "\n";
            //Collections.sort(lista, new Candidato()); // -> se precisar ordenar com base nos criterios da prova
            varredor.close();
            for(Candidato c : lista){
                int i = 0;
                for(String s : c.dados){
                    String formatada = String.format("%" + maiores[i] + "s",s);
                    i++;
                    str += formatada + "        ";
                }
                str+= "\n";
            }
            textArea.setText(str);
        }
        catch (FileNotFoundException e) { //telinha de erro :D
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("File not found exception");
            alert.setContentText("Arquivo nao encontrado: " + textField.getText());
            alert.showAndWait();
        }
        catch (Exception e) {
            System.out.println ("Falha inesperada");
        }
    }

    @FXML
    private void gerarGrafico(ActionEvent event){
        try {
            Stage telaGrafico = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("grafico_intervalos.fxml"));
            Parent grafico = loader.load();
            telaGrafico.setTitle("Receba.");
            telaGrafico.setScene(new Scene(grafico));
            telaGrafico.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}
