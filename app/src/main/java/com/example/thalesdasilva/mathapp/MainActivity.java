package com.example.thalesdasilva.mathapp;

import android.content.Intent;
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
 * @since Classe criada em 12/12/2017
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private Button btnTreinamento;
    private Button btnCalculadora;
    private Button btnSobre;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        textToSpeech = new TextToSpeech(this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            textToSpeech.setLanguage(Locale.getDefault());
                        }
                    }
                });

        btnTreinamento  = findViewById(R.id.btnTreinamento);
        btnCalculadora  = findViewById(R.id.btnCalculadora);
        btnSobre        = findViewById(R.id.btnSobre);

        btnTreinamento.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                textToSpeech.speak("Indo ao treinamento", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = textToSpeech.isSpeaking();

                do {
                    speakingEnd = textToSpeech.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(MainActivity.this, ActTreinamento.class);
                startActivity(it);

                return true;
            }
        });

        btnCalculadora.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                textToSpeech.speak("Abrindo a calculadora", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = textToSpeech.isSpeaking();

                do {
                    speakingEnd = textToSpeech.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(MainActivity.this, ActCalculadora.class);
                startActivity(it);

                return true;
            }
        });

        btnSobre.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                textToSpeech.speak("Abrindo a tela sobre", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = textToSpeech.isSpeaking();

                do {
                    speakingEnd = textToSpeech.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(MainActivity.this, ActSobre.class);
                startActivity(it);

                return true;
            }
        });

        ouvintes();
    }

    @Override
    public void onBackPressed() {
        textToSpeech.speak("Sair do aplicativo", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        textToSpeech.speak("Saindo do aplicativo", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = textToSpeech.isSpeaking();

        do {
            speakingEnd = textToSpeech.isSpeaking();
        } while (speakingEnd);

        finish();

        return true;
    }

    @Override
    public void onInit(int text) {
        if (text == TextToSpeech.SUCCESS) {
            int language = textToSpeech.setLanguage(Locale.getDefault());

            if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                speakOutNow();
            }
        } else {
            MessageBox.show(MainActivity.this, "Erro", "Erro no TextSpeech!");
        }
    }

    private void speakOutNow() {
        if (btnTreinamento.isPressed()) {
            String speech = btnTreinamento.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btnCalculadora.isPressed()) {
            String speech = btnCalculadora.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btnSobre.isPressed()) {
            String speech = btnSobre.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onClick(View view) {
    }

    public void ouvintes() {
        btnTreinamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });
    }

}
