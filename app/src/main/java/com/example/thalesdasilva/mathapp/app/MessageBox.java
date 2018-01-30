package com.example.thalesdasilva.mathapp.app;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 25/01/2018
 */

public class MessageBox {

    public static void showInfo(Context ctx, String title, String msg) {
        show(ctx, title, msg, android.R.drawable.ic_dialog_info);
    }

    public static void showAlert(Context ctx, String title, String msg) {
        show(ctx, title, msg, android.R.drawable.ic_dialog_alert);
    }

    public static void show(Context ctx, String title, String msg) {
        show(ctx, title, msg, 0);
    }

    public static void show(Context ctx, String title, String msg, int iconId) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);
        dlg.setIcon(iconId);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

}
