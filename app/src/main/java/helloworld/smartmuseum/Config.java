package helloworld.smartmuseum;

import java.io.CharArrayReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by robby on 25/06/2017.
 *
 * Classe di configurazione delle informazioni base
 *
 */

class Config {

    private final ArrayList<String> valori = new ArrayList<>();

    Config(){
        valori.add(TITOLO);
    }
    private String ipHost = "\"192.168.1.76\"";
    private final String DATA_URL = "/smartmuseum/api.php?NumPassaporto=";
    private final String JSON_ARRAY = "result";

    private final String TITOLO = "Titolo";
    private final String AUTORE = "Autore";
    private final String PERIODO = "Periodo";
    private final String CATEGORIA = "Categoria";
    private final String LOCAZIONE = "Locazione";
    private final String CULTURA = "Cultura";
    private final String DOMINIO = "Dominio";
    private final String MATERIALI = "Materiali";
    private final String TECNICHE = "Tecniche";
    private final String CONDIZIONI = "Condizioni";
    private final String VALORE = "Valore";
    private final String ORIGINALE = "Originale";
    private final String ORIGINI = "Origini";
    private final String NOME_PROPRIETARIO = "NomeProprietario";
    private final String DESCRIZIONE = "Descrizione";


    String getFieldName(int i){
        String risultato;
        switch (i){
            case 0:risultato = this.TITOLO;break;
            case 1:risultato = this.AUTORE;break;
            case 2:risultato = this.PERIODO;break;
            case 3:risultato = this.CATEGORIA;break;
            case 4:risultato = this.LOCAZIONE;break;
            case 5:risultato = this.CULTURA;break;
            case 6:risultato = this.DOMINIO;break;
            case 7:risultato = this.MATERIALI;break;
            case 8:risultato = this.TECNICHE;break;
            case 9:risultato = this.CONDIZIONI;break;
            case 10:risultato = this.VALORE;break;
            case 11:risultato = this.ORIGINALE;break;
            case 12:risultato = this.ORIGINI;break;
            case 13:risultato = this.NOME_PROPRIETARIO;break;
            case 14:risultato = this.DESCRIZIONE;break;
            default:risultato = "stringa vuota";
        }
        return risultato;
    }

    /**
     * per una migliore sicurezza del codice si splitta lo switch
     * @param i - indice del nome del campo da ritrovare
     * @return - nome del campo
     */

    private String subGetFieldName(int i){
        String risultato;
        switch (i){
            case 7:risultato = this.MATERIALI;break;
            case 8:risultato = this.TECNICHE;break;
            case 9:risultato = this.CONDIZIONI;break;
            case 10:risultato = this.VALORE;break;
            case 11:risultato = this.ORIGINALE;break;
            case 12:risultato = this.ORIGINI;break;
            case 13:risultato = this.NOME_PROPRIETARIO;break;
            case 14:risultato = this.DESCRIZIONE;break;
            default:risultato = "stringa vuota";
        }
        return risultato;
    }

    private String clear(String url){
        int i = url.length();
        String nuova = new String();
        for (int j = 1; j<i-1;j++){
            nuova = nuova + url.toCharArray()[j];
        }
        return nuova;
    }

    String getDATAURL(){
        return "http://"+clear(ipHost)+this.DATA_URL;
    }

    String getJSONARRAY(){
        return  this.JSON_ARRAY;
    }

}
