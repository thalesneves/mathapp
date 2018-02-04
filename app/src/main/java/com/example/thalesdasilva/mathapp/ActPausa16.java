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
 * @since Classe criada em 16/01/2018
 */

public class ActPausa16 extends AppCompatActivity {

    private Button btnRetornar;
    private Button btnIniciarNovamente;
    private Button btnVoltarMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pausa_16);
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
                Act16.TEXT_TO_SPEECH.speak("Retornar para o jogo", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnRetornar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act16.TEXT_TO_SPEECH.speak("Retornando para o jogo", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                final Bundle bundle = getIntent().getExtras();
                Integer tempo = bundle.getInt("TXT_TEMPO");

                final int[] seconds = {tempo};

                Act16.TIMER = new Timer();

                Act16.TIMER.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Act16.TXT_TEMPO.setText(String.valueOf(seconds[0]));
                                seconds[0] -= 1;
                                
                                if (seconds[0] == 5) {
                                    Act16.TEXT_TO_SPEECH.speak("Faltam apenas 5 segundos", TextToSpeech.QUEUE_FLUSH, null);
                                }
                                
                                if (seconds[0] == 0) {
                                    Act16.TIMER.cancel();
                                    Act16.VERIFICAR_ONRESUME = Boolean.TRUE;
                                    Act16.TXT_PONTUACAO_ERRO.setText(String.valueOf(Integer.valueOf((String) Act16.TXT_PONTUACAO_ERRO.getText()) + 1));

                                    if (Act16.TXT_SINAL.getText().toString().equals(getString(R.string.mais))) {
                                        Act16.TEXT_TO_SPEECH.speak("Seu tempo acabou vamos para o próximo desafio, o resultado da expressão " +
                                                Act16.TXT_NUM_1.getText().toString() + " dividido por " + Act16.TXT_NUM_2.getText().toString() +
                                                " mais " + Act16.TXT_NUM_3.getText().toString() + "era de " + String.valueOf(Act16.RESULTADO_CORRETO_TXT),
                                                TextToSpeech.QUEUE_FLUSH, null);
                                    } else if (Act16.TXT_SINAL.getText().toString().equals(getString(R.string.menos))) {
                                        Act16.TEXT_TO_SPEECH.speak("Seu tempo acabou vamos para o próximo desafio, o resultado da expressão " +
                                                        Act16.TXT_NUM_1.getText().toString() + " dividido por " + Act16.TXT_NUM_2.getText().toString() +
                                                " menos " + Act16.TXT_NUM_3.getText().toString() + "era de " + String.valueOf(Act16.RESULTADO_CORRETO_TXT),
                                                TextToSpeech.QUEUE_FLUSH, null);
                                    }

                                    boolean speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();

                                    do {
                                        speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();
                                    } while (speakingEnd);

                                    String pontosAcerto = bundle.getString("TXT_PONTUACAO_ACERTO").toString();
                                    String pontosErro = bundle.getString("TXT_PONTUACAO_ERRO").toString();
                                    Intent it = new Intent(ActPausa16.this, Act16.class);
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
                Act16.TEXT_TO_SPEECH.speak("Iniciar novamente o jogo", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnIniciarNovamente.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act16.TIMER.cancel();
                Act16.TEXT_TO_SPEECH.speak("Iniciando o jogo novamente", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActPausa16.this, Act16.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btnVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act16.TEXT_TO_SPEECH.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnVoltarMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act16.TIMER.cancel();
                Act16.TEXT_TO_SPEECH.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActPausa16.this, ActTreinamento.class);
                startActivity(it);
                finish();

                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Act16.TEXT_TO_SPEECH.speak("Jogo pausado", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onBackPressed() {
        Act16.TEXT_TO_SPEECH.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Act16.TIMER.cancel();
        Act16.TEXT_TO_SPEECH.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();

        do {
            speakingEnd = Act16.TEXT_TO_SPEECH.isSpeaking();
        } while (speakingEnd);

        Intent it = new Intent(ActPausa16.this, ActTreinamento.class);
        startActivity(it);
        finish();

        return true;
    }

}
