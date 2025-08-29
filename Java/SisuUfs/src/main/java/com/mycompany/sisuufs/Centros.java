package com.mycompany.sisuufs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Centros{
    private HashMap<String, Integer> centrosHash = new HashMap<>();
    private ListaCandidatos candidatos = ListaCandidatos.getInstance();
    private ArrayList<String> CCAA = new ArrayList<>();
    private ArrayList<String> CCBS = new ArrayList<>();
    private ArrayList<String> CCET = new ArrayList<>();
    private ArrayList<String> CCSA = new ArrayList<>();
    private ArrayList<String> CECH = new ArrayList<>();
    private ArrayList<String> CAMPUSLAG = new ArrayList<>();
    private ArrayList<String> CAMPUSLAR = new ArrayList<>();
    private ArrayList<String> CAMPUSSER = new ArrayList<>();
    private ArrayList<String> CAMPUSITA = new ArrayList<>();

    public Centros(){
        //Colocando todos os centros
        centrosHash.put("CCAA",0);
        centrosHash.put("CCBS",0);
        centrosHash.put("CCET",0);
        centrosHash.put("CCSA",0);
        centrosHash.put("CECH",0);
        
        //Colocando todos os campus
        centrosHash.put("CAMPUSLAG",0);
        centrosHash.put("CAMPUSLAR",0);
        centrosHash.put("CAMPUSSER",0);
        centrosHash.put("CAMPUSITA",0);

        //Fazendo a lista com todos os cursos
        CCAA.addAll(Arrays.asList("ZOOTECNIA BAC (MATUTINO)","MEDICINA VETERINARIA(INTEGRAL)","ENGENHARIA FLORESTAL (MAT)","ENGENHARIA DE PESCA (VESP)","ENGENHARIA AGRONOMICA (MAT)","ENGENHARIA AGRICOLA (MATUTINO)"));
        CCBS.addAll(Arrays.asList("ODONTOLOGIA (INTEGRAL)","NUTRICAO - BAC (INTEGRAL)","MEDICINA (INTEGRAL)","FONOAUDIOLOGIA - BAC (MAT)","FISIOTERAPIA - BAC (MATUTINO)","FISICA MEDICA BAC (VESPERTINO)","FARMACIA (VESPERTINO)","ENFERMAGEM - BAC (INTEGRAL)","EDUCACAO FISICA - LIC (VESP)","EDUCACAO FISICA - BAC (MAT)","ECOLOGIA - BAC. (MATUTINO)","C. BIOLOGICAS - LIC (VESP)","C. BIOLOGICAS - LIC (NOTURNO)","C. BIOLOGICAS - BAC (MATUTINO)"));
        CCET.addAll(Arrays.asList("SISTEMAS DE INFORMACAO (NOT)","QUIMICA LIC (NOTURNO)","QUIMICA INDUSTRIAL (MATUTINO)","QUIMICA BAC (VESPERTINO)","MATEMATICA APLICADA BAC (VESP)","MATEMATICA BAC (VESPERTINO)","MATEMATICA LIC (NOTURNO)","MATEMATICA LIC (VESPERTINO)","FISICA-BAC HAB ASTRONOMIA(VES)","FISICA LIC (NOTURNO)","FISICA BAC (VESPERTINO)","ESTATISTICA BAC (NOTURNO)","ENGENHARIA QUIMICA (MATUTINO)","ENGENHARIA MECANICA (MATUTINO)","ENGENHARIA ELETRONICA (MAT)","ENGENHARIA ELETRICA (MATUTINO)","ENGENHARIA DE PRODUCAO (VES)","ENGENHARIA DE MATERIAIS (VESP)","ENGENHARIA DE PETROLEO (MAT)","ENGENHARIA DE COMPUTACAO (VES)","ENGENHARIA DE ALIMENTOS (MAT)","ENGENHARIA CIVIL (VESPERTINO)","ENGENHARIA AMBIENTAL(MATUTINO)","CIENCIAS ATUARIAIS - BAC (NOT)","C. DA COMPUTACAO BAC (VESP)"));
        CCSA.addAll(Arrays.asList("TURISMO - BAC (VESPERTINO)","SERVICO SOCIAL BAC (NOTURNO)","SECRETARIADO EXECUTIVO (NOT)","REL. INTERNACIONAIS-BAC(VESP)","DIREITO BAC (VESPERTINO)","DIREITO BAC (NOTURNO)","CIENCIAS ECONOMICAS BAC(VESP)","CIENCIAS ECONOMICAS BAC (NOT","CIENCIAS CONTABEIS BAC (NOT)","BIBLIOTECONOMIA E DOC-BAC(NOT)","ADMINISTRACAO BAC (NOTURNO)","ADMINISTRACAO BAC (VESPERTINO)"));
        CECH.addAll(Arrays.asList("TEATRO - LIC (NOTURNO)","PUBLICIDADE E PROPAGANDA (VES)","PSICOLOGIA BAC (VESPERTINO)","PEDAGOGIA LIC (VESPERTINO)","PEDAGOGIA LIC (NOTURNO)","LETRAS ESPANHOL LIC (NOTURNO)","LETRAS INGLES LIC (NOTURNO)","LETRAS PORT-ESPANHOL-LIC(VESP)","LETRAS PORT-FRANCES LIC (NOT)","LETRAS PORT-INGLES LIC (MAT)","LETRAS PORTUGUES LIC (NOTURNO)","LETRAS PORTUGUES LIC(MATUTINO)","JORNALISMO BAC (MATUTINO)","HISTORIA LIC (MATUTINO)","HISTORIA LIC (NOTURNO)","GEOLOGIA - BAC (MATUTINO)","GEOGRAFIA LIC (MATUTINO)","GEOGRAFIA - LIC (NOTURNO)","GEOGRAFIA - BAC (MATUTINO)","FILOSOFIA LIC (NOTURNO)","DESIGN - BAC. (NOTURNO)","CINEMA E AUDIOVISUAL BAC(VESP)","CIENCIAS SOCIAIS BAC (VESP)","CIENCIAS DA RELIGIAO LIC (NOT)","ARTES-LIC EM A. VISUAIS (VESP)"));
        CAMPUSLAG.addAll(Arrays.asList("ODONTOLOGIA - BAC (INTEGRAL)","NUTRICAO - BAC (INTEGRAL)","MEDICINA - BAC (INTEGRAL)","FONOAUDIOLOGIA-BAC (INTEGRAL)","FISIOTERAPIA - BAC (INTEGRAL)","FARMACIA - BAC (INTEGRAL)","ENFERMAGEM - BAC (INTEGRAL)"));
        CAMPUSLAR.addAll(Arrays.asList("TERAPIA OCUPACIONAL-BAC (INT)","MUSEOLOGIA - BAC (MATUTINO)","DANCA - LIC (MATUTINO)","ARQUITETURA E URBANISMO (INT)","ARQUEOLOGIA - BAC (VESPERTINO)"));
        CAMPUSSER.addAll(Arrays.asList("ZOOTECNIA - BACHARELADO (INT)","MEDICINA VETERINARIA-BAC (INT)","ENGENHARIA AGRONOMICA-BAC(INT)","AGROINDUSTRIA - BAC (INT)"));
        CAMPUSITA.addAll(Arrays.asList("SISTEMAS DE INFORMACAO (MAT)","QUIMICA - LICENCIATURA (MAT)","PEDAGOGIA - LIC (NOTURNO)","MATEMATICA-LICENCIATURA (VESP)","LETRAS PORTUGUES - LIC (NOT)","GEOGRAFIA - LICENCIATURA(VESP)","FISICA - LICENCIATURA (NOT)","C. BIOLOGICAS - LIC (VESP)","ADMINISTRACAO - BAC (NOTURNO)"));
    }

    public HashMap<String, Integer> separacaoCentro(){
        ArrayList<Candidato> Lcandidatos = candidatos.getListaCandidatos();

        for(Candidato candidato: Lcandidatos){
            String curso = candidato.curso;
        if(CCAA.contains(curso)){
            int contagemAtual = centrosHash.get("CCAA");
            centrosHash.put("CCAA", ++contagemAtual);
        }
        if(CCBS.contains(curso)){
            int contagemAtual = centrosHash.get("CCBS");
            centrosHash.put("CCBS", ++contagemAtual);
        }
        if(CCET.contains(curso)){
            int contagemAtual = centrosHash.get("CCET");
            centrosHash.put("CCET", ++contagemAtual);
        }
        if(CCSA.contains(curso)){
            int contagemAtual = centrosHash.get("CCSA");
            centrosHash.put("CCSA", ++contagemAtual);
        }
        if(CECH.contains(curso)){
            int contagemAtual = centrosHash.get("CECH");
            centrosHash.put("CECH", ++contagemAtual);
        }
        if(CAMPUSLAG.contains(curso)){
            int contagemAtual = centrosHash.get("CAMPUSLAG");
            centrosHash.put("CAMPUSLAG", ++contagemAtual);
        }
        if(CAMPUSLAR.contains(curso)){
            int contagemAtual = centrosHash.get("CAMPUSLAR");
            centrosHash.put("CAMPUSLAR", ++contagemAtual);
        }
        if(CAMPUSSER.contains(curso)){
            int contagemAtual = centrosHash.get("CAMPUSSER");
            centrosHash.put("CAMPUSSER", ++contagemAtual);
        }
        if(CAMPUSITA.contains(curso)){
            int contagemAtual = centrosHash.get("CAMPUSITA");
            centrosHash.put("CAMPUSITA", ++contagemAtual);
        }
        }
        return centrosHash;
}
}
