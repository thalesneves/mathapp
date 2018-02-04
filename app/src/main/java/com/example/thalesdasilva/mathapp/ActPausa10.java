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
 * @since Classe criada em 04/01/2018
 */

public class ActPausa10 extends AppCompatActivity {

    private Button btnRetornar;
    private Button btnIniciarNovamente;
    private Button btnVoltarMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pausa_10);
        getSupportActionBar().hide();
        recuperandoReferencia();
        ouvintesDosBotoes();
    }

    private void recuperandoReferencia() {
        btnRetornar = findViewById(R.id.btnRetornar);
        btnIniciarNovamente = findViewById(R.id.btnIniciarNovamente);
        btnVoltarMenu = findViewById(R.id.btnVoltarMenu);
    }

    private void ouvintesDosBotoes() {
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act10.TEXT_TO_SPEECH.speak("Retornar para o jogo", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnRetornar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act10.TEXT_TO_SPEECH.speak("Retornando para o jogo", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                final Bundle bundle = getIntent().getExtras();
                Integer tempo = bundle.getInt("TXT_TEMPO");

                final int[] seconds = {tempo};

                Act10.TIMER = new Timer();

                Act10.TIMER.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Act10.TXT_TEMPO.setText(String.valueOf(seconds[0]));
                                seconds[0] -= 1;

                                if (seconds[0] == 5) {
                                    Act10.TEXT_TO_SPEECH.speak("Faltam apenas 5 segundos", TextToSpeech.QUEUE_FLUSH, null);
                                }

                                if (seconds[0] == 0) {
                                    Act10.TIMER.cancel();
                                    Act10.VERIFICAR_ONRESUME = Boolean.TRUE;
                                    Act10.TXT_PONTUACAO_ERRO.setText(String.valueOf(Integer.valueOf((String) Act10.TXT_PONTUACAO_ERRO.getText()) + 1));
                                    Act10.TEXT_TO_SPEECH.speak("Seu tempo acabou vamos para o próximo desafio, o resultado da expressão " +
                                                    Act10.TXT_NUM_1.getText().toString() + getString(R.string.btnDiv) + Act10.TXT_NUM_2.getText().toString() +
                                                    "era de " + String.format(String.valueOf(Act10.DF.format(Act10.RESULTADO_CORRETO))),
                                            TextToSpeech.QUEUE_FLUSH, null);

                                    boolean speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();

                                    do {
                                        speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();
                                    } while (speakingEnd);

                                    String pontosAcerto = bundle.getString("TXT_PONTUACAO_ACERTO").toString();
                                    String pontosErro = bundle.getString("TXT_PONTUACAO_ERRO").toString();
                                    Intent it = new Intent(ActPausa10.this, Act10.class);
                                    it.putExtra("TXT_PONTUACAO_ACERTO", pontosAcerto);
                                    it.putExtra("TXT_PONTUACAO_ERRO", pontosErro);
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
                Act10.TEXT_TO_SPEECH.speak("Iniciar novamente o jogo", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnIniciarNovamente.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act10.TEXT_TO_SPEECH.speak("Iniciando o jogo novamente", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActPausa10.this, Act10.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btnVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act10.TEXT_TO_SPEECH.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnVoltarMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act10.TIMER.cancel();
                Act10.TEXT_TO_SPEECH.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActPausa10.this, ActTreinamento.class);
                startActivity(it);
                finish();

                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Act10.TEXT_TO_SPEECH.speak("Jogo pausado", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onBackPressed() {
        Act10.TEXT_TO_SPEECH.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Act10.TIMER.cancel();
        Act10.TEXT_TO_SPEECH.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();

        do {
            speakingEnd = Act10.TEXT_TO_SPEECH.isSpeaking();
        } while (speakingEnd);

        Intent it = new Intent(ActPausa10.this, ActTreinamento.class);
        startActivity(it);
        finish();

        return true;
    }

}
