package com.example.horadedartchau.userInterface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.horadedartchau.R;
import com.example.horadedartchau.dataBase.domain.EstadoFezesDia;
import com.example.horadedartchau.userInterface.injectors.GenerateBristolChartPopUp;

public class DadosDiaAdapter {

    private EstadoFezesDia estadoFezesDia;

    private final Context context;
    public DadosDiaAdapter(Context context) {
        this.context = context;
        this.estadoFezesDia = null;
    }

    public void inserirEstadosFezesLayout(LayoutInflater inflater, ViewGroup layout, int i) {


        if (estadoFezesDia != null){
            View viewGerada = inflater.inflate(R.layout.dados_dia_tipo_fezes,layout,false);

            String estadoFezesSelecionado = getEstadoFezesDia().getEstadoFezes(i);
            if (estadoFezesSelecionado != null){
                TextView textViewTipoFezes = viewGerada.findViewById(R.id.dados_dia_activity_string_tipo_fezes);
                estadoFezesSelecionado = estadoFezesSelecionado.replace("_", " ");
                textViewTipoFezes.setText(estadoFezesSelecionado);

                ImageButton buttonInfo = viewGerada.findViewById(R.id.dados_dia_activity_image_button_info);
                GenerateBristolChartPopUp.gerarPopupSeletorMesAoClicarBotao(buttonInfo,context);
            }

            layout.addView(viewGerada);
        }



    }

    public void setTextInTextView(TextView textView, String text){
        if (!text.isEmpty() && text != null){
            textView.setText(text);
        }
    }

    public EstadoFezesDia getEstadoFezesDia() {
        return estadoFezesDia;
    }

    public void setEstadoFezesDia(EstadoFezesDia estadoFezesDia) {
        this.estadoFezesDia = estadoFezesDia;
    }
}
