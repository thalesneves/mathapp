package com.example.thalesdasilva.mathapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.thalesdasilva.mathapp.app.MessageBox;

import java.util.Locale;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 07/01/2018
 */

public class ActSobre extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private Button btnSpeak;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sobre);

        getSupportActionBar().hide();

        btnSpeak = findViewById(R.id.btnSpeak);

        textToSpeech = new TextToSpeech(this,
                new TextToSpeech.OnInitListener() {

                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            textToSpeech.setLanguage(Locale.getDefault());
                            textToSpeech.speak("Trabalho de graduação, Instituição: Fatec Ourinhos, Tema: Software educacional voltado para o ensino da matemática, Nome: Thales da Silva Neves, Orientador: Jean Daniel Henri Merlin Andreazza", TextToSpeech.QUEUE_FLUSH, null);

                            boolean speakingEnd = textToSpeech.isSpeaking();

                            do {
                                speakingEnd = textToSpeech.isSpeaking();
                            } while (speakingEnd);

                            textToSpeech.speak("Se desejar que o texto seja repetido por favor pressione o botão abaixo", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onInit(int text) {
        if (text == TextToSpeech.SUCCESS) {
            int language = textToSpeech.setLanguage(Locale.getDefault());

            if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                speakOutNow();
            }
        } else {
            MessageBox.show(ActSobre.this, "Erro", "Erro desconhecido!");
        }
    }

    private void speakOutNow() {
        if (btnSpeak.isPressed()) {
            textToSpeech.speak("Trabalho de graduação, Instituição: Fatec Ourinhos, Tema: Software educacional voltado para o ensino da matemática, Nome: Thales da Silva Neves, Orientador: Jean Daniel Henri Merlin Andreazza", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onBackPressed() {
        textToSpeech.speak("Voltar para o menu principal", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        textToSpeech.speak("Voltando para o menu principal", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = textToSpeech.isSpeaking();

        do {
            speakingEnd = textToSpeech.isSpeaking();
        } while (speakingEnd);

        finish();

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.shutdown();
    }

}
