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
import android.util.Log;
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

import java.util.ArrayList;

/**
 * Created by robby on 25/06/2017.
 *
 * In questa classe si comunicherà con il server per la lettura dei dati e la loro stampa.
 */

public class ViewData extends AppCompatActivity {

    private ArrayList<TextView> views = new ArrayList<>();
    private Config config = new Config();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.struttura_lista);

        views.add((TextView) findViewById(R.id.textViewTitolo));
        views.add((TextView) findViewById(R.id.textViewAutore));
        views.add((TextView) findViewById(R.id.textViewPeriodo));
        views.add((TextView) findViewById(R.id.textViewCategoria));
        views.add((TextView) findViewById(R.id.textViewLocazione));
        views.add((TextView) findViewById(R.id.textViewCultura));
        views.add((TextView) findViewById(R.id.textViewDominio));
        views.add((TextView) findViewById(R.id.textViewMateriali));
        views.add((TextView) findViewById(R.id.textViewTecniche));
        views.add((TextView) findViewById(R.id.textViewCondizioni));
        views.add((TextView) findViewById(R.id.textViewValore));
        views.add((TextView) findViewById(R.id.textViewOriginale));
        views.add((TextView) findViewById(R.id.textViewOrigini));
        views.add((TextView) findViewById(R.id.textViewNomeProprietario));
        views.add((TextView) findViewById(R.id.textViewDescrizione));
        views.get(14).setMovementMethod(new ScrollingMovementMethod());

        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    if(isOnline())
                    {
                        readReperto();
                    }
                    else{
                        Toast.makeText(ViewData.this,"Connessione internet assente",Toast.LENGTH_SHORT).show();
                    }
                    break;
                default: break;
            }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(ViewData.this);
        builder.setMessage("Inserire le cuffie").setPositiveButton("Va bene", dialogClickListener).show();
    }

    /**
     * Legge il numero del passaporto del reperto ed effettua la richiesta al db
     */
    private void readReperto(){
        Intent i = getIntent();
        final int NUM_PASSAPORTO = i.getIntExtra("NumPassaporto", 0);
        if (NUM_PASSAPORTO != 0) {
            getData(NUM_PASSAPORTO);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(ViewData.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Richiedo in ingresso il numero del passaporto per effettuare la richiesta al db
     * @param numPassaporto
     */
    private void getData(int numPassaporto) {

        final ProgressDialog loading = ProgressDialog.show(this, "Attendi...", "Caricamento...", false, false);

        String url = config.getDATAURL()+numPassaporto;
        Log.d("url",url);

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
                        Toast.makeText(ViewData.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * stampo il risultato e avvaloro i campi dell'interfaccia
     * @param response
     */
    private void showJSON(String response){
        ArrayList<String> valori = new ArrayList<>();
        String aCapo = "\n";
        try {
            Log.d("json","sono in showJson");
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(config.getJSONARRAY());
            JSONObject collegeData = result.getJSONObject(0);
            for (int i = 0; i < 15; i++)
            {
                valori.add(i,collegeData.getString(config.getFieldName(i)));
            }

        } catch (JSONException e) {
            Log.d("err","errore nel json");
        }

        views.get(0).setText(config.getFieldName(0)+":\t"+valori.get(0));
        for(int i = 1; i < 15; i++){
            views.get(i).setText(config.getFieldName(i)+":"+aCapo+valori.get(i));
        }

    }

}
