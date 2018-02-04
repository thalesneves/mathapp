package com.example.thalesdasilva.mathapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.example.thalesdasilva.mathapp.app.MessageBox;
import com.example.thalesdasilva.mathapp.database.DataBase;
import com.example.thalesdasilva.mathapp.dominio.RepositorioPontuacao;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Thales da Silva Neves (thales.neves@fatec.sp.gov.br)
 * @since Classe criada em 30/01/2018
 */

public class ActGrafico extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener, Serializable {

    private Button btnTreinamento;
    private Button btnIniciarNovamente;

    private TextToSpeech text_to_speech;

    private BarChart barChart;
    
    private DataBase database;
    private SQLiteDatabase conn;
    private RepositorioPontuacao repositorioPontuacao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_grafico);
        getSupportActionBar().hide();
        setupPieChart();
        recuperarReferencia();
        inicializarTextToSpeech();
        ouvintes();
    }

    private void recuperarReferencia() {
        barChart            = findViewById(R.id.bargraph);
        btnTreinamento      = findViewById(R.id.btnTreinamento);
        btnIniciarNovamente = findViewById(R.id.btnIniciarNovamente);
    }

    private void inicializarTextToSpeech() {
        text_to_speech = new TextToSpeech(this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            text_to_speech.setLanguage(Locale.getDefault());
                            text_to_speech.speak("Infelizmente você excedeu os limites de erros e foi redirecionado para a tela de gráficos", TextToSpeech.QUEUE_FLUSH, null);

                            boolean speakingEnd = text_to_speech.isSpeaking();

                            do {
                                speakingEnd = text_to_speech.isSpeaking();
                            } while (speakingEnd);

                            text_to_speech.speak("Esta tela possui dois botões", TextToSpeech.QUEUE_FLUSH, null);

                            do {
                                speakingEnd = text_to_speech.isSpeaking();
                            } while (speakingEnd);

                            text_to_speech.speak("No canto inferior esquerdo serve para voltar ao menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

                            do {
                                speakingEnd = text_to_speech.isSpeaking();
                            } while (speakingEnd);

                            text_to_speech.speak("E do canto inferior direito serve para iniciar o jogo novamente", TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
    }

    private void ouvintes() {
        btnTreinamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });
        
        btnTreinamento.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                text_to_speech.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
                
                boolean speakingEnd = text_to_speech.isSpeaking();

                do {
                    speakingEnd = text_to_speech.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActGrafico.this, ActTreinamento.class);
                startActivity(it);
                finish();
                
                return true;
            }
        });

        btnIniciarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOutNow();
            }
        });

        btnIniciarNovamente.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                text_to_speech.speak("Iniciando o jogo novamente", TextToSpeech.QUEUE_FLUSH, null);

                boolean speakingEnd = text_to_speech.isSpeaking();

                do {
                    speakingEnd = text_to_speech.isSpeaking();
                } while (speakingEnd);

                Intent it = new Intent(ActGrafico.this, Act1.class);
                startActivity(it);
                finish();

                return true;
            }
        });
    }

    private void setupPieChart() {
        recuperarReferencia();
        inicializarBancoSQLite();

        Integer dados = database.buscarPontucao(8);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarEntry> barEntries1 = new ArrayList<>();

        barEntries.add(new BarEntry(1, dados));
        barEntries1.add(new BarEntry(2, dados));

        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        BarDataSet barDataSet = new BarDataSet(barEntries, "Recorde");
        barDataSet.setColor(getResources().getColor(R.color.grafico1));

        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "Pontos atuais");
        barDataSet1.setColor(getResources().getColor(R.color.grafico2));

        //Tira os números do topo.
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setEnabled(false);

        BarData barData = new BarData(barDataSet, barDataSet1);
        barData.setBarWidth(0.7f);

        barChart.setData(barData);
        barChart.setExtraOffsets(0, 0, 0, 20);
        barChart.setData(barData);
        barChart.animateXY(2000, 2000);
        barChart.invalidate();
    }

    private void inicializarBancoSQLite() {
        database = new DataBase(ActGrafico.this);
        conn = database.getWritableDatabase();
        repositorioPontuacao = new RepositorioPontuacao(conn);
    }

    private void speakOutNow() {
        if (btnTreinamento.isPressed()) {
            text_to_speech.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
        }

        if (btnIniciarNovamente.isPressed()) {
            text_to_speech.speak("Iniciar o jogo novamente", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setupPieChart();
    }

    @Override
    public void onBackPressed() {
        text_to_speech.speak("Voltar para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        text_to_speech.speak("Voltando para o menu de treinamento", TextToSpeech.QUEUE_FLUSH, null);

        boolean speakingEnd = text_to_speech.isSpeaking();

        do {
            speakingEnd = text_to_speech.isSpeaking();
        } while (speakingEnd);

        Intent it = new Intent(ActGrafico.this, ActTreinamento.class);
        startActivity(it);
        finish();

        return true;
    }

    @Override
    public void onInit(int text) {
        if (text == TextToSpeech.SUCCESS) {
            int language = text_to_speech.setLanguage(Locale.getDefault());

            if (language == TextToSpeech.LANG_MISSING_DATA || language == TextToSpeech.LANG_NOT_SUPPORTED) {
                speakOutNow();
            }
        } else {
            MessageBox.show(ActGrafico.this, "Erro", "Erro no TextSpeech!");
        }
    }

    @Override
    public void onClick(View view) {
    }

}
