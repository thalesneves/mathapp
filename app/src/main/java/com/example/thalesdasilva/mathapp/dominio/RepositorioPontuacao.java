package com.example.thalesdasilva.mathapp.dominio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.thalesdasilva.mathapp.entidades.Pontuacao;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 25/01/2018
 */

public class RepositorioPontuacao {

    private SQLiteDatabase conn;

    public RepositorioPontuacao(SQLiteDatabase conn) {
        this.conn = conn;
    }

    private ContentValues preencheContentValues(Pontuacao pontuacao) {
        ContentValues values = new ContentValues();
        values.put(Pontuacao.PONTUACAO, pontuacao.getPontuacao());

        return values;
    }

    public void inserir(Pontuacao pontuacao) {
        ContentValues values = preencheContentValues(pontuacao);
        conn.insertOrThrow(Pontuacao.TABELA, null, values);
    }

    public void alterar(Pontuacao pontuacao) {
        ContentValues values = preencheContentValues(pontuacao);
        conn.update(Pontuacao.TABELA, values, "id = ?", new String[]{String.valueOf(pontuacao.getId())});
    }

    public void excluir(Long id) {
        conn.delete(Pontuacao.TABELA, "id = ?", new String[]{String.valueOf(id)});
    }

}