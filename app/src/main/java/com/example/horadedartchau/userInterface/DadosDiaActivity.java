package com.example.horadedartchau.userInterface;

import static com.example.horadedartchau.userInterface.ConstantesActivities.CHAVE_DADOS_DIA;
import static com.example.horadedartchau.userInterface.ConstantesActivities.CHAVE_ESTADO_FEZES;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.horadedartchau.R;
import com.example.horadedartchau.dataBase.AplicationDataBase;

import com.example.horadedartchau.dataBase.dao.DadosDiaDAO;
import com.example.horadedartchau.dataBase.dao.EstadoFezesDAO;
import com.example.horadedartchau.dataBase.domain.DadosDia;
import com.example.horadedartchau.dataBase.domain.EstadoFezesDia;
import com.example.horadedartchau.userInterface.adapter.DadosDiaAdapter;
import com.example.horadedartchau.userInterface.adapter.GuardiaoMesAno;

public class DadosDiaActivity extends AppCompatActivity {

    private DadosDiaDAO dadosDiaDAO;
    private EstadoFezesDAO estadoFezesDAO;
    private DadosDia dadosDia;
    private EstadoFezesDia estadoFezesDia;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dados_dia);
        getDados();
        this.setAppBar();

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.insertData();
        this.setButtonsFunction();
    }

    private void setAppBar() {


        Toolbar myToolbar = (Toolbar) findViewById(R.id.dados_dia_activity_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("");
        Button buttonBack = this.findViewById(R.id.dados_dia_activity_toolbar_button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView textAppBar = this.findViewById(R.id.dados_dia_activity_toolbar_textView_day_selected);
        textAppBar.setText(GuardiaoMesAno.getCompleteString(dadosDia.getLocalDateDia().getDayOfMonth()));
    }


    private void insertData() {

        TextView textViewObservacoes = this.findViewById(R.id.dados_dia_activity_string_observacoes);
        DadosDiaAdapter diaAdapter = new DadosDiaAdapter(this);
        diaAdapter.setTextInTextView(textViewObservacoes,dadosDia.getObservacoes());

        TextView textViewQuantidade = this.findViewById(R.id.dados_dia_activity_string_total_vezes);
        int numeroVezes = dadosDia.getNumeroVezes();
        diaAdapter.setTextInTextView(textViewQuantidade,Integer.toString(numeroVezes));

        LinearLayout layoutEstadoFezes = this.findViewById(R.id.dados_dia_activity_linear_layout);
        diaAdapter.setEstadoFezesDia(estadoFezesDia);
        layoutEstadoFezes.removeAllViews();
        for (int i = 1; i<=numeroVezes;i++){
            diaAdapter.inserirEstadosFezesLayout(this.getLayoutInflater(),layoutEstadoFezes,i);
        }
    }

    private void setButtonsFunction() {
        Button buttonEdit = this.findViewById(R.id.dados_dia_activity_button_edit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abreFormularioModoEditaAluno();
            }
        });

        Button buttonDelete = this.findViewById(R.id.dados_dia_activity_button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dadosDia.setAtivo(false);
                estadoFezesDia.setAtivo(false);
                dadosDiaDAO.update(dadosDia);
                estadoFezesDAO.update(estadoFezesDia);
                finish();
            }
        });
    }


    private void abreFormularioModoEditaAluno() {
        Intent vaiParaSalvarDadosActivity = new Intent(DadosDiaActivity.this, SalvarDadosDiaActivity.class);
        vaiParaSalvarDadosActivity.putExtra(CHAVE_DADOS_DIA, dadosDia);
        vaiParaSalvarDadosActivity.putExtra(CHAVE_ESTADO_FEZES, estadoFezesDia);

        startActivity(vaiParaSalvarDadosActivity);
        finish();
    }

    private void getDados(){
        Intent intent = this.getIntent();
        if (intent.hasExtra(CHAVE_DADOS_DIA)){
            this.dadosDia = (DadosDia) intent.getSerializableExtra(CHAVE_DADOS_DIA);
            getDaos();
            this.estadoFezesDia = estadoFezesDAO.findByDay(dadosDia.getDia());
        }else {
            finish();
        }


    }


    private void getDaos(){
        AplicationDataBase dataBase = AplicationDataBase.getInstance(this);
        this.estadoFezesDAO = dataBase.estadoFezesDAO();
        this.dadosDiaDAO = dataBase.dadosDiaDAO();

    }
}
