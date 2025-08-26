/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.sisuufs;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author dimit
 */
public class SecondaryController implements Initializable {


    @FXML
    private ChoiceBox<String> boxCampus = new ChoiceBox<>();
    @FXML
    private Button botaoMudarTela2;
    @FXML
    private Button botaoGerarGrafico;
    private boolean janelaAberta = false;
    private Stage telaGrafico;

    protected ListaCandidatos listaDeCandidatos = ListaCandidatos.getInstance(); // vou usar pra gerar a choicebox com os campus

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<Candidato> lCandidatos = listaDeCandidatos.getListaCandidatos();
        TreeSet<String> sCampus = new TreeSet<>(); //usando um conjunto pra nn repetir os elementos
        boxCampus.getItems().add("TODOS");
        boxCampus.getSelectionModel().selectFirst();
        for(Candidato c : lCandidatos){
            sCampus.add(c.dados[3]);
        }
        for (String s : sCampus) {
            boxCampus.getItems().add(s);
        }
    }    
    
    // @FXML
    // private void mudarTela2(ActionEvent event){
    //     try {
    //         Parent tela2 = FXMLLoader.load(getClass().getResource("primary.fxml"));
    //         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    //         Scene scene = new Scene(tela2,640,480);
    //         stage.setScene(scene);
    //         stage.setTitle ("Leitor de CSV 8000");
    //         stage.setMinWidth(450);
    //         stage.setMinHeight(350);
    //         stage.show();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // @FXML
    // private void filtrarCampus(ActionEvent event){
    //     String campusSelected = boxCampus.getValue();
    //     listaDeCandidatos.setCampus(campusSelected);
    //     botaoGerarGrafico.setDisable(false);
    // }

    @FXML
    private void gerarGrafico(ActionEvent event){
        try {
            
            String campusSelected = boxCampus.getValue();
            listaDeCandidatos.setCampus(campusSelected);
            
            //só abre uma janela nova se não tiver uma aberta.
            if (telaGrafico == null || !telaGrafico.isShowing()) {
                telaGrafico = new Stage();
                janelaAberta = true;
            }
            //atualizando variavel de verificação
            telaGrafico.setOnCloseRequest(e -> {
                janelaAberta = false;
            });
    
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("grafico_intervalos.fxml"));
            Parent grafico = loader.load();
            //eu preciso dar um jeito de passar o grupo de cada candidato pra outra cena.
            telaGrafico.setTitle("Grafico de aprovados filtrados");
            Scene temp = new Scene(grafico);
            
            //atualizando a variavel se a aba for fechada
            telaGrafico.setOnCloseRequest(e -> {
            janelaAberta = false;
            });
            
            //if(lista == null) throw new NullPointerException(); // usando essa excecao pra nao printar um grafico sem nada
            telaGrafico.setScene(temp);
            telaGrafico.setMinWidth(750);
            telaGrafico.setMinHeight(600);
            telaGrafico.show();

        } catch (NullPointerException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("ERRO! ARQUIVO NAO CARREGADO!");
            alert.setContentText("Nenhum arquivo foi carregado, impossível gerar o gráfico.");
            alert.showAndWait();
        }
        catch(Exception e ){
            e.printStackTrace();
        }
    }

}