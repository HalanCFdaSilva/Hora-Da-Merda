package com.example.horadedartchau.userInterface.adapter;

import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;

import com.example.horadedartchau.userInterface.MainActivity;
import com.example.horadedartchau.userInterface.util.CalendarMonthUseful;
import com.example.horadedartchau.userInterface.util.CalendarYearUseful;
import java.util.ArrayList;
public class AgendaAdapter  {


    private final MainActivity activity;



    public AgendaAdapter(MainActivity mainActivity) {

        this.activity = mainActivity;
    }





    public void inserirAcaoBotaoMesAnterior(ImageButton imageButtonBack, Button buttonStringMesAno){

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GuardiaoMesAno.setStringMesAnoByButton(buttonStringMesAno.getText());


                if (GuardiaoMesAno.getStringMes().equalsIgnoreCase("JANEIRO")){
                    String anoAnterior = CalendarYearUseful.AnoAnterior(GuardiaoMesAno.getStringAno());
                    if (GuardiaoMesAno.getStringAnoInInt() == 1990){
                        anoAnterior = Integer.toString(2055);
                    }
                    GuardiaoMesAno.setStringAno(anoAnterior);
                }
                GuardiaoMesAno.setStringMes(CalendarMonthUseful.MesAnterior(GuardiaoMesAno.getStringMes()));

                activity.updateData();

            }
        });
    }

    public void inseirAcaoBotaoMesSequinte(ImageButton imageButtonNext, Button buttonStringMesAno){

        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GuardiaoMesAno.setStringMesAnoByButton(buttonStringMesAno.getText());


                if (GuardiaoMesAno.getStringMes().equalsIgnoreCase("DEZEMBRO")){
                    String anoNovo = CalendarYearUseful.proximoAno(GuardiaoMesAno.getStringAno());
                    if (GuardiaoMesAno.getStringAnoInInt() == 2055){
                        anoNovo = Integer.toString(1990);
                    }
                    GuardiaoMesAno.setStringAno(anoNovo);
                }
                GuardiaoMesAno.setStringMes(CalendarMonthUseful.proximoMes(GuardiaoMesAno.getStringMes()));
                activity.updateData();



            }
        });
    }


    public  void dadosDia(String mes, int ano){
        boolean bissexto = false;
        int anoBissexto = 1992;
        while(ano >= anoBissexto){
            if (ano == anoBissexto){
                bissexto = true;
            }
            anoBissexto += 4;
        }

        ArrayList<String> meses = CalendarMonthUseful.getArrayListMesesPortugues();
        if (mes.equals(meses.get(1))){
            if (bissexto){


            }
        }
    }


}
