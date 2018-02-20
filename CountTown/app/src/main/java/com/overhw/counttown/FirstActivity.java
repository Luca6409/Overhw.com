package com.overhw.counttown;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;

public class FirstActivity extends AppCompatActivity {

    private final String TAG = "FirstActivityRequest";
    private static int MY_PERMISSIONS_REQUEST_WRITE = 1;

    private static boolean permissionWrite = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        requestPermissionWrite();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                    if(permissionWrite) {
                        Intent home = new Intent(FirstActivity.this, HomeActivity.class);
                        startActivity(home);
                        finish();
                    }
                    else{
                        finish();
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

    /* FUNZIONI PER RICHIEDERE IL PERMESSO PER L'ARCHIVIAZIONE DEI DATI */
    private void requestPermissionWrite(){
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(FirstActivity.this, permissions, MY_PERMISSIONS_REQUEST_WRITE);
    }

    // NUOVA RICHIESTA SALVATAGGIO FILE
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        switch(requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permissionWrite = true;
                }
                else if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                    boolean should = ActivityCompat.shouldShowRequestPermissionRationale(FirstActivity.this, permissions[0]);
                    if(should){
                        AlertDialog.Builder builder = new AlertDialog.Builder(FirstActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                        builder.setTitle("Attenzione");
                        builder.setMessage(Html.fromHtml("Per garantire un corretto funzionamento dell'app è necessario approvare l'autorizzazione per l'archiviazione dei dati all'interno del dispositivo."));//\nIn "+"<b>"+"Impostazioni"+"</b>" + " accedere ad " + "<b>" + "Autorizzazioni" + "</b>" + " e attivare la voce " + "<b>" + "Archiviazione"+"</b>" + "."));
                        builder.setPositiveButton("Ho capito", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                requestPermissionWrite();
                            }
                        });
                        builder.setNegativeButton("Chiudi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                                System.exit(0);
                            }
                        });
                        builder.show();
                    }
                    else{
                        //user has denied with `Never Ask Again`, go to settings
                        promptSettings();
                    }
                }
                break;
        }
    }

    private void promptSettings() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FirstActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.setTitle("Attenzione");
        builder.setMessage(Html.fromHtml("Per garantire un corretto funzionamento dell'app è necessario approvare l'autorizzazione per l'archiviazione dei dati all'interno del dispositivo.\nIn "+
                "<b>"+"Impostazioni"+"</b>" + " accedere ad " + "<b>" + "Autorizzazioni" + "</b>" + " e attivare la voce " + "<b>" + "Archiviazione"+"</b>" + "."));

        builder.setPositiveButton("Impostazioni", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                goToSettings();
            }
        });
        builder.setNegativeButton("Chiudi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                System.exit(0);
            }
        });
        builder.show();
    }

    private void goToSettings() {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + HomeActivity.class.getPackage().getName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myAppSettings);
    }
}
