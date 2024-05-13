package com.example.horadedartchau.userInterface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.horadedartchau.R;
import com.example.horadedartchau.dataBase.CategoriasEstadoFezes;
import com.example.horadedartchau.dataBase.domain.EstadoFezesDia;
import com.example.horadedartchau.userInterface.injectors.GenerateBristolChartPopUp;

import java.util.ArrayList;

public class SalvarDadosDiaAdapter {


    private final EstadoFezesDia estadoFezesDia;
    private final Context context;


    public SalvarDadosDiaAdapter( EstadoFezesDia estadoFezesDia, Context context) {

        this.estadoFezesDia = estadoFezesDia;
        this.context = context;
    }

    public ArrayAdapter InserirAdapterSpinnerquantidade(Spinner spinner, ArrayList<Integer> listaInserir){
        ArrayAdapter adapter = new ArrayAdapter<>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listaInserir);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return adapter;
    }


    public void inserirEstadosFezesLayout(LayoutInflater inflater, ViewGroup layout, int i) {

        ArrayAdapter adapter = ArrayAdapter.createFromResource(context,R.array.tipos_fezes
                ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        View viewGerada = inflater.inflate(R.layout.salvar_dados_dia_tipo_fezes,layout,false);
        Spinner spinner = viewGerada.findViewById(R.id.salvar_dados_dia_spinner_tipo_fezes);
        spinner.setAdapter(adapter);
        ImageButton buttonInfo = viewGerada.findViewById(R.id.salvar_dados_dia_activity_imagebutton_tipo_fezes);
        GenerateBristolChartPopUp.gerarPopupSeletorMesAoClicarBotao(buttonInfo,context);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                item = item.replace(" ","_");
                CategoriasEstadoFezes categoriasEstadoFezes = CategoriasEstadoFezes.valueOf(item);
                getEstadoFezesDia().setEstadoFezes(categoriasEstadoFezes,i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String estadoFezesSelecionado = getEstadoFezesDia().getEstadoFezes(i);
        if (estadoFezesSelecionado != null){
            estadoFezesSelecionado = estadoFezesSelecionado.replace("_", " ");
            spinner.setSelection(adapter.getPosition(estadoFezesSelecionado));
        }

        layout.addView(viewGerada);



    }

    public EstadoFezesDia getEstadoFezesDia() {
        return estadoFezesDia;
    }
}
