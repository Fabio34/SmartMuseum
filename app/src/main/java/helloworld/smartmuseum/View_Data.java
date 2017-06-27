package helloworld.smartmuseum;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by robby on 25/06/2017.
 */

public class View_Data extends AppCompatActivity {

    private TextView ViewTitolo;
    private TextView ViewAutore;
    private TextView ViewPeriodo;
    private TextView ViewCategoria;
    private TextView ViewLocazione;
    private TextView ViewCultura;
    private TextView ViewDominio;
    private TextView ViewMateriali;
    private TextView ViewTecniche;
    private TextView ViewCondizioni;
    private TextView ViewValore;
    private TextView ViewOriginale;
    private TextView ViewOrigini;
    private TextView ViewNomeProprietario;
    private TextView ViewDescrizione;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.struttura_lista);

        ViewTitolo = (TextView) findViewById(R.id.textViewTitolo);
        ViewAutore = (TextView) findViewById(R.id.textViewAutore);
        ViewPeriodo = (TextView) findViewById(R.id.textViewPeriodo);
        ViewCategoria = (TextView) findViewById(R.id.textViewCategoria);
        ViewLocazione = (TextView) findViewById(R.id.textViewLocazione);
        ViewCultura = (TextView) findViewById(R.id.textViewCultura);
        ViewDominio = (TextView) findViewById(R.id.textViewDominio);
        ViewMateriali = (TextView) findViewById(R.id.textViewMateriali);
        ViewTecniche = (TextView) findViewById(R.id.textViewTecniche);
        ViewCondizioni = (TextView) findViewById(R.id.textViewCondizioni);
        ViewValore = (TextView) findViewById(R.id.textViewValore);
        ViewOriginale = (TextView) findViewById(R.id.textViewOriginale);
        ViewOrigini = (TextView) findViewById(R.id.textViewOrigini);
        ViewNomeProprietario = (TextView) findViewById(R.id.textViewNomeProprietario);
        ViewDescrizione = (TextView) findViewById(R.id.textViewDescrizione);
        ViewDescrizione.setMovementMethod(new ScrollingMovementMethod());

        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    if(isOnline())
                    {
                        readReperto();
                        Toast.makeText(View_Data.this,"Connessione presente",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(View_Data.this,"Connessione internet assente",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(View_Data.this);
        builder.setMessage("Inserire le cuffie").setPositiveButton("Va bene", dialogClickListener).show();
    }

    private void dialogo(){

    }

    private void readReperto(){
        Intent i = getIntent();
        final int NumPassaporto = i.getIntExtra("NumPassaporto", 0);
        if (NumPassaporto != 0) {
            getData(NumPassaporto);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(View_Data.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void getData(int NumPassaporto) {

        final ProgressDialog loading = ProgressDialog.show(this, "Attendi...", "Caricamento...", false, false);

        String url = Config.DATA_URL+NumPassaporto;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(View_Data.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String Titolo="";
        String Autore="";
        String Periodo = "";
        String Categoria="";
        String Locazione="";
        String Cultura = "";
        String Dominio="";
        String Materiali="";
        String Tecniche = "";
        String Condizioni="";
        String Valore="";
        String Originale = "";
        String Origini="";
        String NomeProprietario="";
        String Descrizione = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            Titolo = collegeData.getString(Config.Titolo);
            Autore = collegeData.getString(Config.Autore);
            Periodo = collegeData.getString(Config.Periodo);
            Categoria = collegeData.getString(Config.Categoria);
            Locazione = collegeData.getString(Config.Locazione);
            Cultura = collegeData.getString(Config.Cultura);
            Dominio = collegeData.getString(Config.Dominio);
            Materiali = collegeData.getString(Config.Materiali);
            Tecniche = collegeData.getString(Config.Tecniche);
            Condizioni = collegeData.getString(Config.Condizioni);
            Valore = collegeData.getString(Config.Valore);
            Originale = collegeData.getString(Config.Originale);
            Origini = collegeData.getString(Config.Origini);
            NomeProprietario = collegeData.getString(Config.NomeProprietario);
            Descrizione = collegeData.getString(Config.Descrizione);
            //Log.d("Autoreeee",Descrizione);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ViewTitolo.setText("Titolo:\t"+Titolo);
        ViewAutore.setText("Autore:\n"+Autore);
        ViewPeriodo.setText("Periodo:\n"+Periodo);
        ViewCategoria.setText("Categoria:\n"+Categoria);
        ViewLocazione.setText("Locazione:\n"+Locazione);
        ViewCultura.setText("Cultura:\n"+Cultura);
        ViewDominio.setText("Dominio:\n"+Dominio);
        ViewTecniche.setText("Tecniche:\n"+Tecniche);
        ViewMateriali.setText("Materiali:\n"+Materiali);
        ViewCondizioni.setText("Condizioni:\n"+Condizioni);
        ViewValore.setText("Valore:\n"+Valore);
        ViewOriginale.setText("Originale:\n"+Originale);
        ViewOrigini.setText("Origini:\n"+Origini);
        ViewNomeProprietario.setText("NomeProprietario:\n"+NomeProprietario);
        ViewDescrizione.setText("Descrizione:\t"+Descrizione);

    }

}
