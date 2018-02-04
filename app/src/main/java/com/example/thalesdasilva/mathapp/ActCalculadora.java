package com.example.thalesdasilva.mathapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thalesdasilva.mathapp.app.MessageBox;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Classe criada para codificar um objeto do tipo calculadora.
 *
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 28/12/2017
 */

public class ActCalculadora extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private EditText edtTela1;
    private EditText edtTela2;

    private TextToSpeech tts;

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnC;
    private Button btnDiv;
    private Button btnX;
    private Button btnSub;
    private Button btnSum;
    private Button btnP;
    private Button btnI;

    private Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_calculadora);
        getSupportActionBar().hide();
        recuperarReferencia();
        ouvintes();
        desabilitarEditTexts();
        inicializarTextToSpeech();
        listenerButtons();
    }

    private void recuperarReferencia() {
        edtTela1    = findViewById(R.id.edtTela1);
        edtTela2    = findViewById(R.id.edtTela2);
        btn0        = findViewById(R.id.btn0);
        btn1        = findViewById(R.id.btn1);
        btn2        = findViewById(R.id.btn2);
        btn3        = findViewById(R.id.btn3);
        btn4        = findViewById(R.id.btn4);
        btn5        = findViewById(R.id.btn5);
        btn6        = findViewById(R.id.btn6);
        btn7        = findViewById(R.id.btn7);
        btn8        = findViewById(R.id.btn8);
        btn9        = findViewById(R.id.btn9);
        btnC        = findViewById(R.id.btnC);
        btnDiv      = findViewById(R.id.btnDiv);
        btnX        = findViewById(R.id.btnX);
        btnSub      = findViewById(R.id.btnSub);
        btnSum      = findViewById(R.id.btnSum);
        btnP        = findViewById(R.id.btnP);
        btnI        = findViewById(R.id.btnI);
    }

    private void ouvintes() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnX.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnSum.setOnClickListener(this);
        btnP.setOnClickListener(this);
        btnI.setOnClickListener(this);
    }

    private void desabilitarEditTexts() {
        edtTela1.setKeyListener(null);
        edtTela2.setKeyListener(null);
    }

    private void inicializarTextToSpeech() {
        tts = new TextToSpeech(this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            tts.setLanguage(Locale.getDefault());
                        }
                    }
                });
    }

    private void listenerButtons() {
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

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

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });
    }

    private void speakOutNow() {
        if (btnP.isPressed()) {
            String speech = btnP.getText().toString();
            String text = edtTela1.getText().toString();
            //edtTela.setText(edtTela.getText() + getString(R.string.P));
            int qtdSeparador = 0;

            loopFor: for (int i = text.length() - 1; i >= 0; i--) {
                // System.out.println(texto.charAt(i));
                if (text.charAt(i) == getString(R.string.P).charAt(0)) {
                    // System.out.println("separador");
                    qtdSeparador++;
                } else if (text.charAt(i) == '+'
                        || text.charAt(i) == '-'
                        || text.charAt(i) == '×'
                        || text.charAt(i) == '÷') {
                    break loopFor;
                }
            }
            // System.out.println(qtdSeparador);
            if (qtdSeparador > 0) {
                return;
            } else {
                edtTela1.setText(text.substring(0, text.length())
                        + getString(R.string.P));
                tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
            }
        }

        if (btn0.isPressed()) {
            String text = btn0.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "0");
        }

        if (btn1.isPressed()) {
            String text = btn1.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "1");
        }

        if (btn2.isPressed()) {
            String text = btn2.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "2");
        }

        if (btn3.isPressed()) {
            String text = btn3.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "3");
        }

        if (btn4.isPressed()) {
            String text = btn4.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "4");
        }

        if (btn5.isPressed()) {
            String text = btn5.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "5");
        }

        if (btn6.isPressed()) {
            String text = btn6.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "6");
        }

        if (btn7.isPressed()) {
            String text = btn7.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "7");
        }

        if (btn8.isPressed()) {
            String text = btn8.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "8");
        }

        if (btn9.isPressed()) {
            String text = btn9.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            edtTela1.setText(edtTela1.getText() + "9");
        }

        if (btnC.isPressed()) {
            btnC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = getString(R.string.cleanOneNumber);
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    limparTelaClickable();

                    if (edtTela2.getText().equals(getResources().getString(R.string.error))) {
                        edtTela2.setText(null);
                    }

                    flag = false;
                }
            });

            btnC.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String text = getString(R.string.clean);
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    limparTelaPressed();
                    flag = false;

                    return true;
                }
            });
        }

        if (btnDiv.isPressed()) {
            if (flag == true) {
                String text = getString(R.string.btnDiv);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                edtTela1.setText(edtTela2.getText() + getString(R.string.btnDiv));

                if (edtTela2 != null) {
                    edtTela2.setText(null);
                }

                flag = false;
            } else {
                String text = getString(R.string.btnDiv);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                edtTela1.setText(edtTela1.getText() + getString(R.string.btnDiv));
            }
        }

        if (btnX.isPressed()) {
            if (flag == true) {
                String text = getString(R.string.btnX);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                edtTela1.setText(edtTela2.getText() + getString(R.string.btnX));

                if (edtTela2 != null) {
                    edtTela2.setText(null);
                }

                flag = false;
            } else {
                String text = getString(R.string.btnX);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                edtTela1.setText(edtTela1.getText() + getString(R.string.btnX));
            }
        }

        if (btnSub.isPressed()) {
            if (flag == true) {
                String text = getString(R.string.minus);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                edtTela1.setText(edtTela2.getText() + "-");

                if (edtTela2 != null) {
                    edtTela2.setText(null);
                }

                flag = false;
            } else {
                String text = getString(R.string.minus);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                edtTela1.setText(edtTela1.getText() + "-");
            }
        }

        if (btnSum.isPressed()) {
            if (flag == true) {
                String text = getString(R.string.sum);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                edtTela1.setText(edtTela2.getText() + "+");

                if (edtTela2 != null) {
                    edtTela2.setText(null);
                }

                flag = false;
            } else {
                String text = getString(R.string.sum);
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                edtTela1.setText(edtTela1.getText() + "+");
            }
        }

        if (btnI.isPressed()) {
            flag = true;

            String edt = String.valueOf(edtTela1.getText()).replace(getString(R.string.P), ".").replace(getString(R.string.btnDiv), "/")
                    .replace(getString(R.string.btnX), "*");

            Symbols symbols = new Symbols();

            try {
                Double result = symbols.eval(String.valueOf(edt));
                DecimalFormat df = new DecimalFormat("##.########");
                String resultF = String.format(String.valueOf(df.format(result))).replace(".", getString(R.string.P)).
                        replace("/", getString(R.string.btnDiv).replace("*", getString(R.string.btnX)));
                edtTela2.setText(String.valueOf(resultF));
                String text = edtTela2.getText().toString();
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            } catch (SyntaxException e) {
                edtTela2.setText(getString(R.string.error));
                tts.speak(getString(R.string.error), TextToSpeech.QUEUE_FLUSH, null);
                edtTela1.setText(null);
            }
        }
    }

    private void limparTelaClickable() {
        String edt = String.valueOf(edtTela1.getText());

        if (edt.length() > 0) {
            String edt1 = edt.substring(0, edt.length() - 1);
            edtTela1.setText(edt1);
        }
    }

    private void limparTelaPressed() {
        edtTela1.setText(null);
        edtTela2.setText(null);
    }

    @Override
    public void onBackPressed() {
        tts.speak("Voltar para o menu principal", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        tts.speak("Voltando para o menu principal", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = tts.isSpeaking();

        do {
            speakingEnd = tts.isSpeaking();
        } while (speakingEnd);

        finish();

        return true;
    }

    @Override
    public void onInit(int text) {
        if (text == TextToSpeech.SUCCESS) {
            int language = tts.setLanguage(Locale.getDefault());

            if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                edtTela1.setEnabled(true);
                speakOutNow();
            }
        } else {
            MessageBox.show(ActCalculadora.this, "Erro", "Erro desconhecido!");
        }
    }

    @Override
    public void onClick(View v) {
    }

}
