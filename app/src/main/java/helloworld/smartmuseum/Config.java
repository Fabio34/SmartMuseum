package helloworld.smartmuseum;

/**
 * Created by robby on 25/06/2017.
 *
 * Classe di configurazione delle informazioni base
 *
 */

class Config {

    Config(){

    }

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
            default:risultato = null;
        }
        return risultato;
    }

    String getDATAURL(){
        return "http://192.168.1.76"+this.DATA_URL;
    }

    String getJSONARRAY(){
        return  this.JSON_ARRAY;
    }

}
