package com.overhw.counttown;

import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

/**
 * Created by Luca Fabris on 26/01/2018.
 */

public class DatiComuni extends AppCompatActivity{
    protected static Comune dettagli_comune = null;
    protected static ArrayList<String> nomi_citta = new ArrayList<>();
    protected static ArrayList<Appalto> appalti = new ArrayList<>();


  public static String getNomeAggiornato(){
      return dettagli_comune.getNome();

  }
}
