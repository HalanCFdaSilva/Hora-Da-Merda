package com.example.horadedartchau.userInterface;

import static com.example.horadedartchau.userInterface.ConstantesActivities.CHAVE_DADOS_DIA;
import static com.example.horadedartchau.userInterface.ConstantesActivities.CHAVE_DIA_SELECIONADO;
import static com.example.horadedartchau.userInterface.ConstantesActivities.CHAVE_ESTADO_FEZES;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.horadedartchau.R;
import com.example.horadedartchau.dataBase.AplicationDataBase;
import com.example.horadedartchau.dataBase.CategoriasEstadoFezes;
import com.example.horadedartchau.dataBase.dao.DadosDiaDAO;
import com.example.horadedartchau.dataBase.dao.EstadoFezesDAO;
import com.example.horadedartchau.dataBase.domain.DadosDia;
import com.example.horadedartchau.dataBase.domain.EstadoFezesDia;
import com.example.horadedartchau.userInterface.adapter.SalvarDadosDiaAdapter;

import java.time.LocalDate;
import java.util.ArrayList;

public class SalvarDadosDiaActivity extends AppCompatActivity {

    SalvarDadosDiaAdapter adapter;
    private DadosDia dadosDia = null;
    private EstadoFezesDia estadoFezesDia = null;

    private LocalDate dataSelecionada = null;
    private DadosDiaDAO dadosDao;
    private EstadoFezesDAO estadoDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salvar_dados_dia_base);
        this.getDAOS();
        this.carregaDados();
        this.salvarDados();

    }

    private void getDAOS() {
        AplicationDataBase dataBase = AplicationDataBase.getInstance(this);
        this.dadosDao = dataBase.dadosDiaDAO();
        this.estadoDao = dataBase.estadoFezesDAO();
    }

    private void carregaDados() {
        this.pegarDados();
        this.geraAdapter();
        this.ativarSpinnerQuantidade();
        EditText observacoesDadosDia = this.findViewById(R.id.salvar_dados_dia_edit_text_observacao);
        observacoesDadosDia.setText(dadosDia.getObservacoes());
    }

    private void pegarDados() {
        Intent intent = this.getIntent();

        if (intent.hasExtra(CHAVE_DIA_SELECIONADO)){
            this.dataSelecionada = (LocalDate) intent.getSerializableExtra(CHAVE_DIA_SELECIONADO);
            this.dadosDia = new DadosDia();
            this.estadoFezesDia = new EstadoFezesDia();

        }else{
            if (intent.hasExtra(CHAVE_DADOS_DIA)){
                this.dadosDia = (DadosDia) intent.getSerializableExtra(CHAVE_DADOS_DIA);
            }
            if (intent.hasExtra(CHAVE_ESTADO_FEZES)){
                this.estadoFezesDia =(EstadoFezesDia) intent.getSerializableExtra(CHAVE_ESTADO_FEZES);
            }
        }
    }

    private void salvarDados() {
        Button button = this.findViewById(R.id.salvar_dados_dia_button_salvar);
        EditText observacoesEditText = this.findViewById(R.id.salvar_dados_dia_edit_text_observacao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dadosDia.setObservacoes(observacoesEditText.getText().toString());
                Log.i("TAG", "onClick: "+ estadoFezesDia.getDia());
                if (dadosDia.getDia() != null && estadoFezesDia.getDia()!= null){
                    Log.i("TAG", "onClick: "+ dadosDia.getDia());
                    dadosDao.update(dadosDia);
                    estadoDao.update(estadoFezesDia);
                }else if (dataSelecionada != null){
                    criarElementoBanco();
                }
                finish();

            }
        });
    }

    private void criarElementoBanco() {
        this.dadosDia.setDia(this.dataSelecionada);
        this.estadoFezesDia.setDia(this.dataSelecionada);
        DadosDia dadosDiaBanco = this.dadosDao.findByDayNotAtivo(this.dadosDia.getDia());
        if (dadosDiaBanco != null){
            dadosDiaBanco.copyOFDadosDia(this.dadosDia);
            this.dadosDao.update(dadosDiaBanco);
        }else{
            this.dadosDao.insert(this.dadosDia);
        }

        EstadoFezesDia estadoFezesBanco = this.estadoDao.findByDayNotAtivo(this.dadosDia.getDia());

        if (estadoFezesBanco != null){
            estadoFezesBanco.copyOfPoopState(this.estadoFezesDia);
            this.estadoDao.update(estadoFezesBanco);
        }else{

            this.estadoDao.insert(this.estadoFezesDia);
        }

    }

    private void geraAdapter() {


        this.adapter = new SalvarDadosDiaAdapter(estadoFezesDia, this);
    }

    private void ativarSpinnerQuantidade(){



        ArrayList<Integer> dados = new ArrayList<Integer>();
        for (int i = 1; i<=10;i++){
            dados.add(i);
        }

        Spinner spinner = this.findViewById(R.id.salvar_dados_dia_spinner_quantidade);
        ArrayAdapter arrayAdapter = adapter.InserirAdapterSpinnerquantidade(spinner,dados);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer numeroVezes = (Integer) parent.getItemAtPosition(position);
                inserirEstadosFezesLayout(numeroVezes,adapter);
                dadosDia.setNumeroVezes(numeroVezes);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setSelection(arrayAdapter.getPosition(dadosDia.getNumeroVezes()));
    }


    public void inserirEstadosFezesLayout(int quantidadeInsersoes, SalvarDadosDiaAdapter adapter){
        LinearLayout layout = this.findViewById(R.id.salvar_dados_dia_layout_linear);
        layout.removeAllViews();
        LayoutInflater inflater = this.getLayoutInflater();
        for (int i = 1; i <= quantidadeInsersoes;i++){
            adapter.inserirEstadosFezesLayout(inflater,layout,i);

        }
    }


}
