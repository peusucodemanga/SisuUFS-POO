/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sisuufs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author dimit
 */
public class PrimaryController implements Initializable {


    @FXML
    private TableView<Candidato> tableView = new TableView<Candidato>();
    @FXML
    private TextField textField;
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
                String nomeArq = textField.getText();
                Scanner varredor = new Scanner (new File (nomeArq));
                if(!nomeArq.contains(".csv")) throw new IOException();
                lista = new ArrayList<>();

                //aqui acontece a leitura do csv
                while (varredor.hasNext()){
                    String linha = varredor.nextLine();
                    Candidato aux;
                    String [] dadosAux = linha.split(",");

                    if(linha.charAt(0) != 'I'){
                        aux = new Candidato(dadosAux);
                        lista.add(aux);
                    }
                    else{
                        tableView.getColumns().clear();
                        for(String str : dadosAux){
                            TableColumn<Candidato, String> colunaNome = new TableColumn<>(str);
                            colunaNome.setCellValueFactory(new PropertyValueFactory<>(str.toLowerCase()));
                            tableView.getColumns().add(colunaNome);
                        }
                    }
                }   
                listaDeCandidatos.setListaCandidatos(lista); //aqui eu to passando a minha lista tbm pra cena com o grafico
                ObservableList<Candidato> data = FXCollections.observableArrayList(lista);
                tableView.setItems(data);
                varredor.close();
                mudarTela1(event);
            }
        }
        catch (FileNotFoundException e) { //telinha de erro :D
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("File not found exception");
            alert.setContentText("Arquivo nao encontrado: " + textField.getText());
            alert.showAndWait();
        }
        catch (IOException e ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("IOException");
            alert.setContentText("O arquivo " + textField.getText() + "\nnão respeita o formato exigido!");
            alert.showAndWait();
        }
        catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("Falha inesperada");
            alert.setContentText("O arquivo " + textField.getText() + "\nnão pode ser lido!");
            alert.showAndWait();
        }
    }

    private void mudarTela1(ActionEvent event){
        try {
            janelaAberta = true;
            Parent tela2 = FXMLLoader.load(getClass().getResource("secondary.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(tela2,640,480);

            stage.setScene(scene);
            stage.setTitle ("Opções de análise 8000");
            stage.setMinWidth(450);
            stage.setMinHeight(450);
            stage.show();
            //Coloca o boleano verificador como falso
            stage.setOnCloseRequest(e -> {janelaAberta = false;});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}
