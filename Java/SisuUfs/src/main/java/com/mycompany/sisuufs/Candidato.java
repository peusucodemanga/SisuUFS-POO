package com.mycompany.sisuufs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Candidato implements Comparator<Candidato>{
    protected String [] dados;
    protected String curso;
    protected String demanda;
    protected String nome;
    protected String nota;
    protected int grupo;


    public Candidato(String [] listaDados){
        dados = listaDados.clone();
        curso = listaDados[2];
        demanda = listaDados[4];
        nome = listaDados[1];
        nota = listaDados[5];
        /* calculando o grupo em que o candidato estara, sabendo que 400 - 450 -> grupo 0 e 800 - 850 -> grupo 8, 
        vamos precisar dessa variavel pra dividir os candidatos em grupos e exibir no grafico. */
        grupo = (int)(Double.parseDouble(nota)/50) - 8; 
    }

    public Candidato(){

    }

    @Override
    public String toString(){
        String aux = "";
        for(String elem : dados){
            aux += elem + "\t\t";
        }
        return aux;
    }

    @Override
    public int compare(Candidato c1, Candidato c2){ //primeiro comparava 
        int comp1 = c1.curso.compareTo(c2.curso);
        if(comp1 == 0){
            int comp2 = c1.demanda.compareTo(c2.demanda);
            if(comp2 != 0) comp1 = comp2;
            else return c2.nota.compareTo(c1.nota);
        }
        return comp1;
    }

    public static ArrayList<Candidato> lerDados(String nomeArq){
        ArrayList<Candidato> lista = new ArrayList<>();
        try{
            @SuppressWarnings("resource")
            Scanner varredor = new Scanner(new File(nomeArq));
            Candidato aux = new Candidato(new String[]  {"a","e","i","o","u","y"});
            while(varredor.hasNext()){
                String linha = varredor.nextLine();
                String [] dados = linha.split("\\s{2}");
                aux = new Candidato(dados);
                lista.add(aux);
            }
            Collections.sort(lista, aux);
        }catch(FileNotFoundException e){
            System.out.println("deu erro ai paizao");
        }
        return lista;
    }

    public static HashMap<String,Candidato> lerCandidatos(ArrayList<Candidato> lista){
        HashMap<String,Candidato> mapa = new HashMap<>();
        for(Candidato cand : lista){
            String nome = cand.nome;
            mapa.put(nome,cand);
        }
        return mapa;
    }

    // public static void main(String[] args) throws FileNotFoundException {
    //     // Candidato teste = new Candidato(new String[]{"202400018449","Bruno Lopes","Ciencia da Computacao","nao sei","A0","750,9"});
    //     // System.out.println(teste);
    //     ArrayList<Candidato> candidatos = lerDados("teste.txt");
    //     HashMap<String,Candidato> mapa = lerCandidatos(candidatos); 
    //     for(String nome : mapa.keySet()){
    //         System.out.println(mapa.get(nome));
    //     }

    // }

}