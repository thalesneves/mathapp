package com.example.thalesdasilva.mathapp;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thalesdasilva.mathapp.app.MessageBox;
import com.example.thalesdasilva.mathapp.database.DataBase;
import com.example.thalesdasilva.mathapp.dominio.RepositorioPontuacao;
import com.example.thalesdasilva.mathapp.entidades.Pontuacao;

import java.io.Serializable;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 13/12/2017
 */

public class Act1 extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener, Serializable {

    public static TextView      TXT_TEMPO;
    public static TextView      TXT_PONTUACAO_ACERTO;
    public static TextView      TXT_PONTUACAO_ERRO;
    public static TextView      TXT_NUM_1;
    public static TextView      TXT_NUM_2;
    public static TextToSpeech  TEXT_TO_SPEECH;
    public static Timer         TIMER;
    public static Integer       RESULTADO_CORRETO;
    public static Boolean       VERIFICAR_ONRESUME;
    public static final Integer ID_CLASSE = 1;
    public static final String  PAR_PONTUACAO = "pontuacao";

    private Button btnResult1;
    private Button btnResult2;
    private Button btnResult3;
    private Button btnResult4;
    private Button btnParar;
    private Button btnRepetir;

    private Random random;

    private Integer numeroAleatorioParaAEscolhaDeQualBotaoASerPreenchido;
    private Integer seconds;

    private Boolean voltarParaOMenuDeTreinamento;
    private Boolean verificarTotalErro;

    private DataBase database;
    private SQLiteDatabase conn;

    private Pontuacao pontuacao;
    private RepositorioPontuacao repositorioPontuacao;

//    private static final int TIMER_RUNTIME = 15000;
//    private boolean mbActive;
//    private ProgressBar mProgressBar;
//    final static int[] minutes = {0};
//    Thread TIMERThread = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_1);
        getSupportActionBar().hide();
        recuperandoReferencia();
        inicializarTextToSpeech();
        inicializarVariaveis();
        carregarEstrutura();
        iniciarTempo();
        ouvintes();
        verificarBundleExtras();
        verificarBundleExtrasPontuacao();
        inicializarBancoSQLite();
//        inicializarProgressBar();
    }

    private void recuperandoReferencia() {
        TXT_TEMPO               = findViewById(R.id.txtTempo);
        TXT_PONTUACAO_ACERTO    = findViewById(R.id.txtPontuacaoAcerto);
        TXT_PONTUACAO_ERRO      = findViewById(R.id.txtPontuacaoErro);
        TXT_NUM_1               = findViewById(R.id.txtNum1);
        TXT_NUM_2               = findViewById(R.id.txtNum2);
        btnResult1              = findViewById(R.id.btnResult1);
        btnResult2              = findViewById(R.id.btnResult2);
        btnResult3              = findViewById(R.id.btnResult3);
        btnResult4              = findViewById(R.id.btnResult4);
        btnParar                = findViewById(R.id.btnParar);
        btnRepetir              = findViewById(R.id.btnRepetir);
//        mProgressBar    = findViewById(R.id.progressBar);
    }

    private void inicializarTextToSpeech() {
        TEXT_TO_SPEECH = new TextToSpeech(this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            TEXT_TO_SPEECH.setLanguage(Locale.getDefault());
                            TEXT_TO_SPEECH.speak("Quanto é " + TXT_NUM_1.getText().toString() + " mais " + TXT_NUM_2.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
    }

    private void inicializarVariaveis() {
        RESULTADO_CORRETO = 0;
        VERIFICAR_ONRESUME = Boolean.FALSE;
        random = new Random();
        numeroAleatorioParaAEscolhaDeQualBotaoASerPreenchido = random.nextInt(4) + 1;
        voltarParaOMenuDeTreinamento = Boolean.FALSE;
        verificarTotalErro = Boolean.FALSE;
        seconds = 30;
    }

    private void carregarEstrutura() {
        desbloquearBotoesResultados();

        int numeroRandomico1 = (int) (Math.random() * 100);
        int numeroRandomico2 = (int) (Math.random() * 100);

        TXT_NUM_1.setText(String.valueOf(numeroRandomico1));
        TXT_NUM_2.setText(String.valueOf(numeroRandomico2));

        TEXT_TO_SPEECH.speak("Quanto é " + String.valueOf(numeroRandomico1) + " mais " +
                String.valueOf(numeroRandomico2), TextToSpeech.QUEUE_FLUSH, null);

        RESULTADO_CORRETO = numeroRandomico1 + numeroRandomico2;

        int numeroRandomicoBtnResult1 = 0;
        int numeroRandomicoBtnResult2 = 0;
        int numeroRandomicoBtnResult3 = 0;
        int numeroRandomicoBtnResult4 = 0;

        if (RESULTADO_CORRETO <= 9) {
            numeroRandomicoBtnResult1 = (int) (Math.random() * 10);
            numeroRandomicoBtnResult2 = (int) (Math.random() * 10);
            numeroRandomicoBtnResult3 = (int) (Math.random() * 10);
            numeroRandomicoBtnResult4 = (int) (Math.random() * 10);
        } else if (RESULTADO_CORRETO <= 99) {
            numeroRandomicoBtnResult1 = (int) (Math.random() * 100);
            numeroRandomicoBtnResult2 = (int) (Math.random() * 100);
            numeroRandomicoBtnResult3 = (int) (Math.random() * 100);
            numeroRandomicoBtnResult4 = (int) (Math.random() * 100);
        } else {
            numeroRandomicoBtnResult1 = (int) (Math.random() * 1000);
            numeroRandomicoBtnResult2 = (int) (Math.random() * 1000);
            numeroRandomicoBtnResult3 = (int) (Math.random() * 1000);
            numeroRandomicoBtnResult4 = (int) (Math.random() * 1000);
        }

        if (numeroRandomicoBtnResult1 == numeroRandomicoBtnResult2) {
            numeroRandomicoBtnResult1++;
        } else if (numeroRandomicoBtnResult1 == numeroRandomicoBtnResult3) {
            numeroRandomicoBtnResult1++;
        } else if (numeroRandomicoBtnResult1 == numeroRandomicoBtnResult4) {
            numeroRandomicoBtnResult1++;
        } else if (numeroRandomicoBtnResult2 == numeroRandomicoBtnResult1) {
            numeroRandomicoBtnResult2++;
        } else if (numeroRandomicoBtnResult2 == numeroRandomicoBtnResult3) {
            numeroRandomicoBtnResult2++;
        } else if (numeroRandomicoBtnResult2 == numeroRandomicoBtnResult4) {
            numeroRandomicoBtnResult2++;
        } else if (numeroRandomicoBtnResult3 == numeroRandomicoBtnResult1) {
            numeroRandomicoBtnResult3++;
        } else if (numeroRandomicoBtnResult3 == numeroRandomicoBtnResult2) {
            numeroRandomicoBtnResult3++;
        } else if (numeroRandomicoBtnResult3 == numeroRandomicoBtnResult4) {
            numeroRandomicoBtnResult3++;
        } else if (numeroRandomicoBtnResult4 == numeroRandomicoBtnResult1) {
            numeroRandomicoBtnResult4++;
        } else if (numeroRandomicoBtnResult4 == numeroRandomicoBtnResult2) {
            numeroRandomicoBtnResult4++;
        } else if (numeroRandomicoBtnResult4 == numeroRandomicoBtnResult3) {
            numeroRandomicoBtnResult4++;
        }

        if (numeroRandomicoBtnResult1 == RESULTADO_CORRETO) {
            numeroRandomicoBtnResult1++;
        } else if (numeroRandomicoBtnResult2 == RESULTADO_CORRETO) {
            numeroRandomicoBtnResult2++;
        } else if (numeroRandomicoBtnResult3 == RESULTADO_CORRETO) {
            numeroRandomicoBtnResult3++;
        } else if (numeroRandomicoBtnResult4 == RESULTADO_CORRETO) {
            numeroRandomicoBtnResult4++;
        }

        if (numeroAleatorioParaAEscolhaDeQualBotaoASerPreenchido == 1) {
            btnResult1.setText(String.valueOf(RESULTADO_CORRETO));
            btnResult2.setText(String.valueOf(numeroRandomicoBtnResult2));
            btnResult3.setText(String.valueOf(numeroRandomicoBtnResult3));
            btnResult4.setText(String.valueOf(numeroRandomicoBtnResult4));

            btnResult1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    acertar();

                    return true;
                }
            });

            btnResult2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

            btnResult3.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

            btnResult4.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

        } else if (numeroAleatorioParaAEscolhaDeQualBotaoASerPreenchido == 2) {
            btnResult1.setText(String.valueOf(numeroRandomicoBtnResult1));
            btnResult2.setText(String.valueOf(RESULTADO_CORRETO));
            btnResult3.setText(String.valueOf(numeroRandomicoBtnResult3));
            btnResult4.setText(String.valueOf(numeroRandomicoBtnResult4));

            btnResult1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

            btnResult2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    acertar();

                    return true;
                }
            });

            btnResult3.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

            btnResult4.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

        } else if (numeroAleatorioParaAEscolhaDeQualBotaoASerPreenchido == 3) {
            btnResult1.setText(String.valueOf(numeroRandomicoBtnResult1));
            btnResult2.setText(String.valueOf(numeroRandomicoBtnResult2));
            btnResult3.setText(String.valueOf(RESULTADO_CORRETO));
            btnResult4.setText(String.valueOf(numeroRandomicoBtnResult4));

            btnResult1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

            btnResult2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

            btnResult3.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    acertar();

                    return true;
                }
            });

            btnResult4.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

        } else if (numeroAleatorioParaAEscolhaDeQualBotaoASerPreenchido == 4) {
            btnResult1.setText(String.valueOf(numeroRandomicoBtnResult1));
            btnResult2.setText(String.valueOf(numeroRandomicoBtnResult2));
            btnResult3.setText(String.valueOf(numeroRandomicoBtnResult3));
            btnResult4.setText(String.valueOf(RESULTADO_CORRETO));

            btnResult1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

            btnResult2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

            btnResult3.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    errar();

                    return true;
                }
            });

            btnResult4.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    acertar();

                    return true;
                }
            });
        }

        btnParar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                irParaActivityPausa1();

                return true;
            }
        });
    }

    private void iniciarTempo() {
        //Declare the TIMER
        TIMER = new Timer();
        //Set the schedule function and rate
        TIMER.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        TXT_TEMPO.setText(String.valueOf(minutes[0])+":"+String.valueOf(seconds[0]));
//                        inicializarProgressBar();
//                        TXT_TEMPO.setText(String.valueOf(minutes[0])+":"+String.valueOf(seconds[0]));
//                        TXT_TEMPO.setText(String.valueOf(seconds[0]));
//                        minutes[0] = 0;

                        if (seconds == 5) {
                            TEXT_TO_SPEECH.speak("Faltam apenas 5 segundos", TextToSpeech.QUEUE_FLUSH, null);
                        }

                        if (seconds == 0) {
                            TIMER.cancel();
                            bloquearBotoesResultados();
                            TXT_PONTUACAO_ERRO.setText(String.valueOf(Integer.valueOf((String) TXT_PONTUACAO_ERRO.getText()) + 1));

                            TEXT_TO_SPEECH.speak("Seu tempo acabou vamos para o próximo desafio, o resultado da expressão " +
                                    TXT_NUM_1.getText().toString() + " mais " + TXT_NUM_2.getText().toString() +
                                    "era de " + String.valueOf(RESULTADO_CORRETO), TextToSpeech.QUEUE_FLUSH, null);

                            boolean speakingEnd = TEXT_TO_SPEECH.isSpeaking();

                            do {
                                speakingEnd = TEXT_TO_SPEECH.isSpeaking();
                            } while (speakingEnd);

                            seconds = 30;
                            iniciarTempo();
                            carregarEstrutura();
                        }

                        TXT_TEMPO.setText(String.valueOf(seconds));
                        seconds -= 1;
                    }
                });
            }
        }, 0, 1000);//delay é a demora pra começar
    }

    private void ouvintes() {
        btnResult1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnResult2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnResult3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnResult4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnRepetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });
    }

    private void verificarBundleExtras() {
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String pontosAcertos = bundle.getString("TXT_PONTUACAO_ACERTO");
            String pontosErros = bundle.getString("TXT_PONTUACAO_ERRO");
            TXT_PONTUACAO_ACERTO.setText(String.valueOf(pontosAcertos));
            TXT_PONTUACAO_ERRO.setText(String.valueOf(pontosErros));
        }
    }

    private void verificarBundleExtrasPontuacao() {
        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey(PAR_PONTUACAO))) {
            pontuacao = (Pontuacao) bundle.getSerializable(PAR_PONTUACAO);
        } else {
            pontuacao = new Pontuacao();
            pontuacao.setId(ID_CLASSE);
        }
    }

    private void inicializarBancoSQLite() {
        try {
            database = new DataBase(Act1.this);
            conn = database.getWritableDatabase();

            repositorioPontuacao = new RepositorioPontuacao(conn);

//            MessageBox.show(Act1.this, "Mensagem", "Conexão criada com sucesso!");
        } catch (SQLException e) {
            MessageBox.show(this, "Erro", "Erro ao criar o Banco de Dados: " + e.getMessage());
        }
    }

    private void acertarSpeak() {
        int numeroRandom = random.nextInt(5) + 1;

        if (numeroRandom == 1) {
            TEXT_TO_SPEECH.speak("Parabéns você acertou", TextToSpeech.QUEUE_FLUSH, null);
        } else if (numeroRandom == 2) {
            TEXT_TO_SPEECH.speak("Excelente acertou", TextToSpeech.QUEUE_FLUSH, null);
        } else if (numeroRandom == 3) {
            TEXT_TO_SPEECH.speak("Parabéns acertou você está ficando bom", TextToSpeech.QUEUE_FLUSH, null);
        } else if (numeroRandom == 4) {
            TEXT_TO_SPEECH.speak("Uau!, você acertou", TextToSpeech.QUEUE_FLUSH, null);
        } else if (numeroRandom == 5) {
            TEXT_TO_SPEECH.speak("Acertou de novo", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void errarSpeak() {
        int numeroRandom = random.nextInt(5) + 1;

        if (numeroRandom == 1) {
            TEXT_TO_SPEECH.speak("Infelizmente você errou, o resultado da expressão " +
                    TXT_NUM_1.getText().toString() + " mais " + TXT_NUM_2.getText().toString() +
                    "era de " + String.valueOf(RESULTADO_CORRETO), TextToSpeech.QUEUE_FLUSH, null);
        } else if (numeroRandom == 2) {
            TEXT_TO_SPEECH.speak("Não foi dessa vez você errou, o resultado da expressão " +
                    TXT_NUM_1.getText().toString() + " mais " + TXT_NUM_2.getText().toString() +
                    "era de " + String.valueOf(RESULTADO_CORRETO), TextToSpeech.QUEUE_FLUSH, null);
        } else if (numeroRandom == 3) {
            TEXT_TO_SPEECH.speak("Tente mais uma vez, o resultado da expressão " +
                    TXT_NUM_1.getText().toString() + " mais " + TXT_NUM_2.getText().toString() +
                    "era de " + String.valueOf(RESULTADO_CORRETO), TextToSpeech.QUEUE_FLUSH, null);
        } else if (numeroRandom == 4) {
            TEXT_TO_SPEECH.speak("Quem sabe da próxima vez, o resultado da expressão " +
                    TXT_NUM_1.getText().toString() + " mais " + TXT_NUM_2.getText().toString() +
                    "era de " + String.valueOf(RESULTADO_CORRETO), TextToSpeech.QUEUE_FLUSH, null);
        } else if (numeroRandom == 5) {
            TEXT_TO_SPEECH.speak("Que pena tente de novo, o resultado da expressão " +
                    TXT_NUM_1.getText().toString() + " mais " + TXT_NUM_2.getText().toString() +
                    "era de " + String.valueOf(RESULTADO_CORRETO), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void acertar() {
        TIMER.cancel();
        acertarSpeak();

        boolean speakingEnd = TEXT_TO_SPEECH.isSpeaking();

        do {
            speakingEnd = TEXT_TO_SPEECH.isSpeaking();
        } while (speakingEnd);

        seconds = 30;
        iniciarTempo();
        carregarEstrutura();
        numeroAleatorioParaAEscolhaDeQualBotaoASerPreenchido = random.nextInt(4) + 1;
        TXT_PONTUACAO_ACERTO.setText(String.valueOf(Integer.valueOf((String) TXT_PONTUACAO_ACERTO.getText()) + 1));
        salvar();
    }

    private void errar() {
        TIMER.cancel();
        errarSpeak();

        boolean speakingEnd = TEXT_TO_SPEECH.isSpeaking();

        do {
            speakingEnd = TEXT_TO_SPEECH.isSpeaking();
        } while (speakingEnd);

        TXT_PONTUACAO_ERRO.setText(String.valueOf(Integer.valueOf((String) TXT_PONTUACAO_ERRO.getText()) + 1));
        verificarTotalErros();
    }

    private void verificarTotalErros() {
        verificarTotalErro = Boolean.TRUE;
        int totalErros = Integer.parseInt(TXT_PONTUACAO_ERRO.getText().toString());

        if (totalErros == 1) {
            TIMER.cancel();
            TEXT_TO_SPEECH.shutdown();

            Intent it = new Intent(Act1.this, ActGrafico.class);
            startActivity(it);
            finish();
        } else {
            seconds = 30;
            iniciarTempo();
            carregarEstrutura();
            numeroAleatorioParaAEscolhaDeQualBotaoASerPreenchido = random.nextInt(4) + 1;
        }
    }

    private void bloquearBotoesResultados() {
        btnResult1.setEnabled(false);
        btnResult2.setEnabled(false);
        btnResult3.setEnabled(false);
        btnResult4.setEnabled(false);
        btnParar.setEnabled(false);
        btnRepetir.setEnabled(false);
    }

    private void desbloquearBotoesResultados() {
        btnResult1.setEnabled(true);
        btnResult2.setEnabled(true);
        btnResult3.setEnabled(true);
        btnResult4.setEnabled(true);
        btnParar.setEnabled(true);
        btnRepetir.setEnabled(true);
    }

    private void irParaActivityPausa1() {
        Integer tempo = Integer.parseInt(String.valueOf(TXT_TEMPO.getText()));
        String pontosAcerto = TXT_PONTUACAO_ACERTO.getText().toString();
        String pontosErro = TXT_PONTUACAO_ERRO.getText().toString();
        TIMER.cancel();
        Intent it = new Intent(Act1.this, ActPausa1.class);
        it.putExtra("TXT_TEMPO", tempo);
        it.putExtra("TXT_PONTUACAO_ACERTO", pontosAcerto);
        it.putExtra("TXT_PONTUACAO_ERRO", pontosErro);
        startActivity(it);
    }

    private void speakOutNow() {
        if (btnResult1.isPressed()) {
            String speech = btnResult1.getText().toString();
            TEXT_TO_SPEECH.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btnResult2.isPressed()) {
            String speech = btnResult2.getText().toString();
            TEXT_TO_SPEECH.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btnResult3.isPressed()) {
            String speech = btnResult3.getText().toString();
            TEXT_TO_SPEECH.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btnResult4.isPressed()) {
            String speech = btnResult4.getText().toString();
            TEXT_TO_SPEECH.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btnParar.isPressed()) {
            TEXT_TO_SPEECH.speak("Pausar o jogo", TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btnRepetir.isPressed()) {
            TEXT_TO_SPEECH.speak("Repetindo a expressão, quanto é " + TXT_NUM_1.getText().toString() + " mais " + TXT_NUM_2.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void salvar() {
        try {
            Integer pontuacaoBuscar = database.buscarPontucao(ID_CLASSE);
            Integer pontuacaoAcerto = Integer.parseInt(TXT_PONTUACAO_ACERTO.getText().toString());

            if (pontuacaoBuscar < pontuacaoAcerto) {
                pontuacao.setPontuacao(pontuacaoAcerto);

                repositorioPontuacao.alterar(pontuacao);
            }
        } catch (Exception e) {
            MessageBox.show(Act1.this, "Erro", "Erro ao salvar os dados" + e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TEXT_TO_SPEECH.speak("Quanto é " + TXT_NUM_1.getText().toString() + " mais " + TXT_NUM_2.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!btnParar.isPressed() && Boolean.FALSE == voltarParaOMenuDeTreinamento && Boolean.FALSE == VERIFICAR_ONRESUME && Boolean.FALSE == verificarTotalErro) {
            irParaActivityPausa1();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public void onBackPressed() {
        TEXT_TO_SPEECH.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        TIMER.cancel();
        voltarParaOMenuDeTreinamento = Boolean.TRUE;
        bloquearBotoesResultados();
        TEXT_TO_SPEECH.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = TEXT_TO_SPEECH.isSpeaking();

        do {
            speakingEnd = TEXT_TO_SPEECH.isSpeaking();
        } while (speakingEnd);

        Intent it = new Intent(Act1.this, ActTreinamento.class);
        startActivity(it);
        finish();

        return true;
    }

    @Override
    public void onInit(int text) {
        if (text == TextToSpeech.SUCCESS) {
            int language = TEXT_TO_SPEECH.setLanguage(Locale.getDefault());

            if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                speakOutNow();
            }
        } else {
            MessageBox.show(Act1.this, "Erro", "Erro no TextSpeech!");
        }
    }

    @Override
    public void onClick(View view) {
    }

//        public void inicializarProgressBar() {
//
//        TIMERThread = new Thread() {
//            @Override
//            public void run() {
//                mbActive = true;
//                try {
//                    int waited = 0;
//                    while (mbActive && (waited < TIMER_RUNTIME)) {
//                        sleep(200);
//                        if (mbActive) {
//                            waited += 200;
//                            updateProgress(waited);
//                        }
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    onContinue();
//                }
//            }
//        };
//        TIMERThread.start();
//
//    }

//        public void updateProgress(final int timePassed) {
//        if (null != mProgressBar) {
//            final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
//            mProgressBar.setProgress(progress);
//        }
//    }

//    public void onContinue() {
//        Log.d("mensagemFinal", "Sua barra de loading acabou de carregar !!!");
//    }
}
