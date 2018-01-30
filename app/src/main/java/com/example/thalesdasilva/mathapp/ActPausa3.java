package com.example.thalesdasilva.mathapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 25/12/2017
 */

public class ActPausa3 extends AppCompatActivity {

    private Button btnRetornar;
    private Button btnIniciarNovamente;
    private Button btnVoltarMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pausa_3);

        getSupportActionBar().hide();

        btnRetornar = findViewById(R.id.btnRetornar);
        btnIniciarNovamente = findViewById(R.id.btnIniciarNovamente);
        btnVoltarMenu = findViewById(R.id.btnVoltarMenu);

        ouvintesDosBotoes();
    }

    private void ouvintesDosBotoes() {
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act3.TEXT_TO_SPEECH.speak("Retornar para o jogo", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnRetornar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act3.TEXT_TO_SPEECH.speak("Retornando para o jogo", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                final Bundle bundle = getIntent().getExtras();
                Integer tempo = bundle.getInt("TXT_TEMPO");

                final int[] seconds = {tempo};

                Act3.TIMER = new Timer();

                Act3.TIMER.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Act3.TXT_TEMPO.setText(String.valueOf(seconds[0]));
                                seconds[0] -= 1;

                                if (seconds[0] == 5) {
                                    Act3.TEXT_TO_SPEECH.speak("Faltam apenas 5 segundos", TextToSpeech.QUEUE_FLUSH, null);
                                }

                                if (seconds[0] == 0) {
                                    Act3.TIMER.cancel();
                                    Act3.VERIFICAR_ONRESUME = Boolean.TRUE;
                                    
                                    if (Act3.TXT_SINAL.getText().toString().equals(getString(R.string.mais))) {
                                        Act3.TEXT_TO_SPEECH.speak("Seu tempo acabou vamos para o próximo desafio, o resultado da expressão " +
                                                Act3.TXT_NUM_1.getText().toString() + " mais " + Act3.TXT_NUM_2.getText().toString() +
                                                "era de " + String.valueOf(Act3.RESULTADO_CORRETO), TextToSpeech.QUEUE_FLUSH, null);
                                    } else if (Act3.TXT_SINAL.getText().toString().equals(getString(R.string.menos))) {
                                        Act3.TEXT_TO_SPEECH.speak("Seu tempo acabou vamos para o próximo desafio, o resultado da expressão " +
                                                Act3.TXT_NUM_1.getText().toString() + " menos " + Act3.TXT_NUM_2.getText().toString() +
                                                "era de " + String.valueOf(Act3.RESULTADO_CORRETO), TextToSpeech.QUEUE_FLUSH, null);
                                    }

                                    boolean speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();

                                    do {
                                        speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();
                                    } while (speakingEnd);

                                    String pontos = bundle.getString("TXT_PONTUACAO").toString();
                                    Intent it = new Intent(ActPausa3.this, Act3.class);
                                    it.putExtra("TXT_PONTUACAO", pontos);
                                    startActivity(it);
                                }
                            }
                        });
                    }
                }, 0, 1000);

                finish();

                return true;
            }
        });

        btnIniciarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act3.TEXT_TO_SPEECH.speak("Iniciar novamente o jogo", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnIniciarNovamente.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act3.TIMER.cancel();
                Act3.TEXT_TO_SPEECH.speak("Iniciando o jogo novamente", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActPausa3.this, Act3.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btnVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act3.TEXT_TO_SPEECH.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnVoltarMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act3.TIMER.cancel();
                Act3.TEXT_TO_SPEECH.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActPausa3.this, ActTreinamento.class);
                startActivity(it);
                finish();

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Act3.TEXT_TO_SPEECH.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Act3.TIMER.cancel();
        Act3.TEXT_TO_SPEECH.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();

        do {
            speakingEnd = Act3.TEXT_TO_SPEECH.isSpeaking();
        } while (speakingEnd);

        Intent it = new Intent(ActPausa3.this, ActTreinamento.class);
        startActivity(it);
        finish();

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Act3.TEXT_TO_SPEECH.speak("Jogo pausado", TextToSpeech.QUEUE_FLUSH, null);
    }

}
