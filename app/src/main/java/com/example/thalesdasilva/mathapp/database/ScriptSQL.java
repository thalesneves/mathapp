package com.example.thalesdasilva.mathapp.database;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 25/01/2018
 */

public class ScriptSQL {

    public static String getCreatePontucoes() {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("create table if not exists pontuacoes ( ");
        sqlBuilder.append("id          integer  not null ");
        sqlBuilder.append("primary key, ");
        sqlBuilder.append("pontuacao   integer ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

}
