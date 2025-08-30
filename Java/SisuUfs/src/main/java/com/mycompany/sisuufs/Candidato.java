package com.mycompany.sisuufs;

public class Candidato{
    protected String [] dados;
    protected String curso;
    protected String demanda;
    protected String nome;
    protected double nota;
    protected String id;
    protected String campus;
    protected int colocacao;
    protected int grupo;

    public Candidato(String [] listaDados){
        dados = listaDados.clone();

        id = listaDados[0];
        nome = listaDados[1];
        curso = listaDados[2];
        campus = listaDados[3];
        demanda = listaDados[4];
        nota = Double.parseDouble(listaDados[5]);
        colocacao = Integer.parseInt(listaDados[6]);
        /* calculando o grupo em que o candidato estara, sabendo que 400 - 450 -> grupo 0 e 800 - 850 -> grupo 8, 
        vamos precisar dessa variavel pra dividir os candidatos em grupos e exibir no grafico. */
        grupo = (int)(nota/50) - 8; 
        if(grupo >= 9) grupo = 8;
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

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public String getDemanda() {
        return demanda;
    }

    public double getNota() {
        return nota;
    }

    public String getId() {
        return id;
    }

    public String getCampus() {
        return campus;
    }

    public int getColocacao() {
        return colocacao;
    }

}