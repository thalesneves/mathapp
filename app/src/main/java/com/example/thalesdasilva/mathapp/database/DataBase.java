package com.example.thalesdasilva.mathapp.database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.thalesdasilva.mathapp.app.MessageBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 25/01/2018
 */

public class DataBase extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    public Context context;

    public DataBase(Context context) {
        //passar a referência da classe context
        //nome do banco de dados
        //referente a classe cursor, busca de dados no banco
        //versão do banco de dados
        super(context, "bdmathapp", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(ScriptSQL.getCreatePontucoes());
        } catch (SQLiteException e) {
            MessageBox.show(null, "Erro", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Integer buscarPontucao(Integer id) {
        db = this.getReadableDatabase();
        String query = "select * from pontuacoes where id = " + id + "";
        Cursor cursor = db.rawQuery(query, null);
        Integer a;
        Integer b = -1;

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getInt(0);

                if (a.equals(id)) {
                    b = cursor.getInt(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return b;
    }

}
