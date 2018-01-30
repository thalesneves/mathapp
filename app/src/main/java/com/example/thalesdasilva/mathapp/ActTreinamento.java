package com.example.thalesdasilva.mathapp;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 12/12/2017
 */

public class ActTreinamento extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener, Serializable {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn14;
    private Button btn15;
    private Button btn16;

    private TextToSpeech textToSpeech;

    private TextView txtPontucaoAct1;

    private DataBase database;
    private SQLiteDatabase conn;

    private Pontuacao pontuacao;
    private RepositorioPontuacao repositorioPontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_treinamento);

        getSupportActionBar().hide();

        recuperandoAReferencia();

        textToSpeech = new TextToSpeech(this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            textToSpeech.setLanguage(Locale.getDefault());
                        }
                    }
                });

        ouvintes();

        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act1.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act2.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act3.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act4.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act5.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act6.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act7.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act8.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act9.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act10.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act11.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act12.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn13.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act13.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn14.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act14.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn15.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act15.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        btn16.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent it = new Intent(ActTreinamento.this, Act16.class);
                startActivity(it);
                finish();

                return true;
            }
        });

        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("pontuacao"))) {
            pontuacao = (Pontuacao) bundle.getSerializable("pontuacao");
        } else {
            pontuacao = new Pontuacao();
        }

        try {
            database = new DataBase(ActTreinamento.this);
            conn = database.getWritableDatabase();

            repositorioPontuacao = new RepositorioPontuacao(conn);

//            MessageBox.show(ActTreinamento.this, "Mensagem", "Conexão criada com sucesso!");
        } catch (SQLException e) {
            MessageBox.show(this, "Erro", "Erro ao criar o Banco de Dados: " + e.getMessage());
        }

        verificar();
    }

    public void verificar() {
        Integer pontuacaoBuscar = database.buscarPontucaoAct1(1);

        if (pontuacaoBuscar == -1) {
            pontuacao.setPontuacao(0);

            repositorioPontuacao.inserir(pontuacao);
            txtPontucaoAct1.setText(String.valueOf(pontuacao.getPontuacao()));
        } else {
            txtPontucaoAct1.setText(String.valueOf(pontuacaoBuscar));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        verificar();
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
        textToSpeech.speak("Voltar para o menu principal", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        textToSpeech.speak("Voltando para o menu principal", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = textToSpeech.isSpeaking();

        do {
            speakingEnd = textToSpeech.isSpeaking();
        } while (speakingEnd);

        Intent it = new Intent(ActTreinamento.this, MainActivity.class);
        startActivity(it);
        finish();

        return true;
    }

    public void recuperandoAReferencia() {
        btn1            = findViewById(R.id.btn1);
        btn2            = findViewById(R.id.btn2);
        btn3            = findViewById(R.id.btn3);
        btn4            = findViewById(R.id.btn4);
        btn5            = findViewById(R.id.btn5);
        btn6            = findViewById(R.id.btn6);
        btn7            = findViewById(R.id.btn7);
        btn8            = findViewById(R.id.btn8);
        btn9            = findViewById(R.id.btn9);
        btn10           = findViewById(R.id.btn10);
        btn11           = findViewById(R.id.btn11);
        btn12           = findViewById(R.id.btn12);
        btn13           = findViewById(R.id.btn13);
        btn14           = findViewById(R.id.btn14);
        btn15           = findViewById(R.id.btn15);
        btn16           = findViewById(R.id.btn16);
        txtPontucaoAct1 = findViewById(R.id.txtPontuacaoAct1);
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
            MessageBox.show(ActTreinamento.this, "Erro", "Erro desconhecido");
        }
    }

    private void speakOutNow() {
        if (btn1.isPressed()) {
            String speech = btn1.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn2.isPressed()) {
            textToSpeech.speak("A menos B igual a", TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn3.isPressed()) {
            String speech = btn3.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn4.isPressed()) {
            String speech = btn4.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn5.isPressed()) {
            String speech = btn5.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn6.isPressed()) {
            textToSpeech.speak("A mais B menos C igual a", TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn7.isPressed()) {
            String speech = btn7.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn8.isPressed()) {
            String speech = btn8.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn9.isPressed()) {
            String speech = btn9.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn10.isPressed()) {
            String speech = btn10.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn11.isPressed()) {
            String speech = btn11.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn12.isPressed()) {
            String speech = btn12.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn13.isPressed()) {
            String speech = btn13.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn14.isPressed()) {
            String speech = btn14.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn15.isPressed()) {
            String speech = btn15.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btn16.isPressed()) {
            String speech = btn16.getText().toString();
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void ouvintes() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });
    }

}
