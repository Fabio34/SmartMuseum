package helloworld.smartmuseum;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Il main necessita delle librerie ZXingScannerView per lo scan del QRCode
 */

public class MainActivity extends AppCompatActivity implements  ZXingScannerView.ResultHandler{

    private ZXingScannerView zXingScannerView;
    private boolean flag = false;
    private static final int ACTIVITY_RESULT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        startActivityForResult(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS), ACTIVITY_RESULT);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        flag=true;
                        break;
                    default: break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Attivare Text To Speech?").setPositiveButton("Procedi", dialogClickListener)
                .setNegativeButton("Già Attivo", dialogClickListener).show();

    }

    /**
     * Controlla che la l'app possa accedere alla fotocamera
     * Se si avvia la scansione.
     * @param view
     */
    public void check(View view){
        final int PERMESSO = 0;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
                    PERMESSO );
        }
        else {
            scannerizza(view);
        }
    }

    /**
     * Metodo per la scansione del codice
     * @param view
     */
    public void scannerizza(View view){
        zXingScannerView = new ZXingScannerView(MainActivity.this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    protected void onPause(){
        super.onPause();
        zXingScannerView.stopCamera();
    }

    protected void onResume(){
        super.onResume();
        if(flag){
            check(zXingScannerView);
        }
    }

    public void handleResult (Result result){
        Intent i = new Intent(this,ViewData.class);
        i.putExtra("NumPassaporto", Integer.parseInt(result.getText()));
        startActivity(i);
    }
}
