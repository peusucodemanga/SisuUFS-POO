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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
    @FXML
    private Button botaoMudarTela;
    @FXML
    private Button botaoLerArquivo;
    private boolean janelaAberta = false;

    protected ListaCandidatos listaDeCandidatos = ListaCandidatos.getInstance();
    protected ArrayList<Candidato> lista;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botaoLerArquivo = new Button();
        botaoLerArquivo.setOnAction((actionEvent) -> {
            lerCSV(actionEvent);
        });
        textField.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER) botaoLerArquivo.fire();
        });
    }    
    
    @FXML
    private void lerCSV(ActionEvent event) {
        try {
            //Inicializando variaveis uteis
        if(!janelaAberta){
            Scanner varredor = new Scanner (new File (textField.getText()));
            lista = new ArrayList<>();
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
            listaDeCandidatos.setListaCandidatos(lista); //aqui eu to passando a minha lista tbm pra cena com o grafico
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
            mudarTela1(event);
            //botaoMudarTela.setDisable(false);
        }
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
    private void mudarTela1(ActionEvent event){
        try {
            janelaAberta = true;
            Parent tela2 = FXMLLoader.load(getClass().getResource("secondary.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(tela2,640,480);

            stage.setScene(scene);
            stage.setTitle ("Opções de análise 8000");
            stage.setMinWidth(450);
            stage.setMinHeight(350);
            stage.show();
            //Coloca o boleano verificador como falso
            stage.setOnCloseRequest(e -> {janelaAberta = false;});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}
