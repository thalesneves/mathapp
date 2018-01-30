package com.example.thalesdasilva.mathapp.app;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 25/01/2018
 */

public class ViewHelper {

    public static ArrayAdapter<String> createArrayAdapter(Context ctx, Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        return arrayAdapter;
    }

}//fim da classe
