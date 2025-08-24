/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sisuufs;

import java.util.ArrayList;

/**
 *
 * @author dimit
 */
public class ListaCandidatos{
    
    private static ListaCandidatos instance;
    private ArrayList<Candidato> lista;

    private ListaCandidatos(){

    }
    public static ListaCandidatos getInstance(){
        if (instance == null) {
            instance = new ListaCandidatos();
        }
        return instance;
    }
    public ArrayList<Candidato> getListaCandidatos(){ 
        return lista; 
    }
    public void setListaCandidatos(ArrayList<Candidato> lista){ 
        this.lista = lista; 
    }
}
