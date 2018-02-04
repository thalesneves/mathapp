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
 * @since Classe criada em 14/01/2018
 */

public class ActPausa13 extends AppCompatActivity {

    private Button btnRetornar;
    private Button btnIniciarNovamente;
    private Button btnVoltarMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pausa_13);
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
                Act13.TEXT_TO_SPEECH.speak("Retornar para o jogo", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnRetornar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act13.TEXT_TO_SPEECH.speak("Retornando para o jogo", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                final Bundle bundle = getIntent().getExtras();
                Integer tempo = bundle.getInt("TXT_TEMPO");

                final int[] seconds = {tempo};

                Act13.TIMER = new Timer();

                Act13.TIMER.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Act13.TXT_TEMPO.setText(String.valueOf(seconds[0]));
                                seconds[0] -= 1;

                                if (seconds[0] == 5) {
                                    Act13.TEXT_TO_SPEECH.speak("Faltam apenas 5 segundos", TextToSpeech.QUEUE_FLUSH, null);
                                }

                                if (seconds[0] == 0) {
                                    Act13.TIMER.cancel();
                                    Act13.VERIFICAR_ONRESUME = Boolean.TRUE;
                                    Act13.TXT_PONTUACAO_ERRO.setText(String.valueOf(Integer.valueOf((String) Act13.TXT_PONTUACAO_ERRO.getText()) + 1));

                                    if ((Act13.TXT_SINAL.getText().toString().equals(getString(R.string.mais))) || (Act13.TXT_SINAL.getText().toString().equals(getString(R.string.menos)))) {
                                        Act13.TEXT_TO_SPEECH.speak("Seu tempo acabou vamos para o próximo desafio, o resultado era de " + String.valueOf(Act13.NUMERO_RANDOMICO_2),
                                                TextToSpeech.QUEUE_FLUSH, null);
                                    } else if (Act13.TXT_SINAL.getText().toString().equals(getString(R.string.porcentagem))) {
                                        Act13.TEXT_TO_SPEECH.speak("Seu tempo acabou vamos para o próximo desafio, o resultado era de" +
                                                        String.valueOf(String.format(String.valueOf(Act13.DF.format(Act13.NUMERO_RANDOM_FLOAT)).replace(".", ","))),
                                                TextToSpeech.QUEUE_FLUSH, null);
                                    }

                                    boolean speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();

                                    do {
                                        speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();
                                    } while (speakingEnd);

                                    String pontosAcerto = bundle.getString("TXT_PONTUACAO_ACERTO").toString();
                                    String pontosErro = bundle.getString("TXT_PONTUACAO_ERRO").toString();
                                    Intent it = new Intent(ActPausa13.this, Act13.class);
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
                Act13.TEXT_TO_SPEECH.speak("Iniciar novamente o jogo", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnIniciarNovamente.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act13.TIMER.cancel();
                Act13.TEXT_TO_SPEECH.speak("Iniciando o jogo novamente", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActPausa13.this, Act13.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btnVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Act13.TEXT_TO_SPEECH.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        btnVoltarMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Act13.TIMER.cancel();
                Act13.TEXT_TO_SPEECH.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();

                do{
                    speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActPausa13.this, ActTreinamento.class);
                startActivity(it);
                finish();

                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Act13.TEXT_TO_SPEECH.speak("Jogo pausado", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onBackPressed() {
        Act13.TEXT_TO_SPEECH.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Act13.TIMER.cancel();
        Act13.TEXT_TO_SPEECH.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();

        do {
            speakingEnd = Act13.TEXT_TO_SPEECH.isSpeaking();
        } while (speakingEnd);

        Intent it = new Intent(ActPausa13.this, ActTreinamento.class);
        startActivity(it);
        finish();

        return true;
    }

}
