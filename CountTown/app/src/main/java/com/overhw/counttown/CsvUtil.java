package com.overhw.counttown;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by LF on 26/01/2018.
 */

public class CsvUtil {

    // read csv file and return an array list of detail for each town
    public static void ReadTownsDetails(Context context){
        ArrayList<Comune> townsList = DatiComuni.dettagli_citta;
        ArrayList<String> nameTown = DatiComuni.nomi_citta;
        if(townsList != null){
            townsList.clear();
        }
        InputStream is = context.getResources().openRawResource(R.raw.towns_details);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";
        try {
            while((line = reader.readLine()) != null){
                // split bt ';'
                String[] tokens = line.split(";");
                Comune comune = new Comune(tokens);
                townsList.add(comune);
                nameTown.add(comune.getNome());
            }
        } catch (IOException e) {
            Log.wtf("CsvUtil", "ERROR: " + line, e);
        }
    }
}
