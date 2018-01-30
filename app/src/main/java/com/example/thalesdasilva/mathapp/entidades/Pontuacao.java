package com.example.thalesdasilva.mathapp.entidades;

import java.io.Serializable;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 25/01/2018
 */

public class Pontuacao implements Serializable {

    public static String TABELA = "pontuacoes";
    public static String ID = "id";
    public static String PONTUACAO = "pontuacao";

    private Integer id;
    private Integer pontuacao;

    public Pontuacao() {
        id = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

}