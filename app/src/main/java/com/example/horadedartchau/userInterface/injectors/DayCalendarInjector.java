package com.example.horadedartchau.userInterface.injectors;

import static com.example.horadedartchau.userInterface.ConstantesActivities.CHAVE_DADOS_DIA;
import static com.example.horadedartchau.userInterface.ConstantesActivities.CHAVE_DIA_SELECIONADO;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.horadedartchau.R;
import com.example.horadedartchau.dataBase.domain.DadosDia;
import com.example.horadedartchau.userInterface.DadosDiaActivity;
import com.example.horadedartchau.userInterface.SalvarDadosDiaActivity;
import com.example.horadedartchau.userInterface.adapter.GuardiaoMesAno;
import com.example.horadedartchau.userInterface.util.CalendarWeekUseful;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class DayCalendarInjector {



    private final Context context;
    private final ArrayList<DadosDia>dadosDiaArrayList;
    private final Month month;

    public DayCalendarInjector(Context context, ArrayList<DadosDia> dadosDiaArrayList){

        this.context = context;
        this.dadosDiaArrayList = dadosDiaArrayList;
        this.month = Month.of(GuardiaoMesAno.getPosicaoMes());

    }



    public static ArrayList<Integer> getTableRowsIds(){
        ArrayList<Integer> idsTableRow = new ArrayList<Integer>();
        idsTableRow.add(R.id.calendario_activity_table_row_semana_1);
        idsTableRow.add(R.id.calendario_activity_table_row_semana_2);
        idsTableRow.add(R.id.calendario_activity_table_row_semana_3);
        idsTableRow.add(R.id.calendario_activity_table_row_semana_4);
        idsTableRow.add(R.id.calendario_activity_table_row_semana_5);
        idsTableRow.add(R.id.calendario_activity_table_row_semana_6);
        return idsTableRow;
    }

    public boolean setTextInTextView(TextView textView, String text){
        if (text != null && !text.isEmpty()){
            textView.setText(text);
            return true;
        }
        return false;
    }

    public boolean setTextWeekInTextView(TextView textView, DayOfWeek dayOfWeek){
        String text = CalendarWeekUseful.getStringOfDayOfWeekSmallerInPortuguese(dayOfWeek);
        if (text != null && !text.isEmpty()){
            textView.setText(text);
            return true;
        }
        return false;
    }

    public void ImageViewOnClick(ImageView imageView, LocalDate localDateOfDaySelected, DadosDia dadosDia){

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTo;
                if (dadosDia!= null){
                    goTo = new Intent(context, DadosDiaActivity.class)
                            .putExtra(CHAVE_DADOS_DIA, dadosDia);
                }
                else {
                    goTo = new Intent(context,SalvarDadosDiaActivity.class);
                    goTo.putExtra(CHAVE_DIA_SELECIONADO,localDateOfDaySelected);
                }
                context.startActivity(goTo);



            }
        });
    }
    private void setImageOfButtonDay(ImageView imageView,DadosDia dadosDia){

        if (dadosDia != null){
            imageView.setImageResource(R.drawable.fundo_dia_calendario_com_selo);

        }else{
            imageView.setImageResource(R.drawable.fundo_dia_calendario);
        }

    }

    private void setImageButtonDayOfAnotherMonth(ImageView imageView, DadosDia dadosDia){

        if (dadosDia != null){
            imageView.setImageResource(R.drawable.fundo_dia_calendario_nao_utilizado);

        }else{
            imageView.setImageResource(R.drawable.fundo_dia_calendario_com_selo);
        }

    }



    private DadosDia getDadosDiaOfArrayList(LocalDate localDate){
        for (DadosDia dadosDia : this.dadosDiaArrayList){
            if (dadosDia.getLocalDateDia().equals(localDate)){
                return dadosDia;
            }
        }
        return null;
    }

    public void setImageOfButtonToAnotherMonth(ImageView imageView, DadosDia dadosDia){
        if (dadosDia != null){
//            imageView.setImageResource(R.drawable.fundo_dia_calendario_fez_nao_utilizado);

        }else{
            imageView.setImageResource(R.drawable.fundo_dia_calendario_nao_utilizado);
        }

    }

    public LinearLayout generateDaysBefore(LinearLayout linearLayout, LocalDate localDate, LayoutInflater layoutInflater){
        if(!localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            boolean monthAfterIsNovember = false;
            if (localDate.getMonth() == Month.DECEMBER){
                monthAfterIsNovember = true;
            }
            LocalDate localDateOfAfter = this.getLocalDateOfMonthAfter(localDate);
            linearLayout.setBackgroundResource(R.color.gray);
            while(this.getPosicaoMes(localDateOfAfter,monthAfterIsNovember) < localDate.getMonth().getValue()){
                linearLayout = this.addLayoutAfterMonth(linearLayout,localDateOfAfter,layoutInflater);
                localDateOfAfter = localDateOfAfter.plusDays(1);
            }
        }
        return linearLayout;

    }

    private int getPosicaoMes(LocalDate localDateOfAfter,boolean isDecember) {
        if (localDateOfAfter.getMonth().equals(Month.DECEMBER) && !isDecember){
            return -1;
        }
        return localDateOfAfter.getMonth().getValue();
    }


    public LinearLayout addLayoutAfterMonth(LinearLayout linearLayout, LocalDate localDate,LayoutInflater inflater) {
        LinearLayout linearLayoutAfterMonth = this.CreateView(inflater,R.color.gray,localDate);
        linearLayout.addView(linearLayoutAfterMonth);
        return linearLayout;

    }

    public LinearLayout CreateView(LayoutInflater layoutInflater, int colorResource,LocalDate localDate) {
        LinearLayout linearLayoutDia = new LinearLayout(context);
        linearLayoutDia.setBackgroundResource(colorResource);
        View viewCriada = layoutInflater.inflate(
                R.layout.layout_card_dia,linearLayoutDia);

        if (viewCriada != null && localDate.getMonth().equals(month)){
            ImageView imageView = viewCriada.findViewById(R.id.calendario_activity_image_view_day);
            DadosDia dadosDia = this.getDadosDiaOfArrayList(localDate);
            this.setImageOfButtonDay(imageView,dadosDia);
            this.ImageViewOnClick(imageView,localDate,dadosDia);

            this.setTextsInView(viewCriada,localDate);

        } else if (viewCriada != null) {
            ImageView imageView = viewCriada.findViewById(R.id.calendario_activity_image_view_day);
            DadosDia dadosDia = this.getDadosDiaOfArrayList(localDate);
            this.setImageOfButtonToAnotherMonth(imageView, dadosDia);
            this.setTextsInView(viewCriada,localDate);

        }
        return linearLayoutDia;
    }

    private void setTextsInView(View viewCriada, LocalDate localDate) {



        TextView textViewDia = viewCriada.findViewById(R.id.calendario_activity_string_day);
        this.setTextInTextView(textViewDia,Integer.toString(localDate.getDayOfMonth()));

        TextView textViewSemana = viewCriada.findViewById(R.id.calendario_activity_string_day_of_week);
        this.setTextWeekInTextView(textViewSemana,localDate.getDayOfWeek());

    }



    public LocalDate getLocalDateOfMonthAfter(LocalDate localDate) {
       LocalDate anotherLocalDate = localDate;
        while(anotherLocalDate.getDayOfWeek().getValue() < DayOfWeek.SUNDAY.getValue()){
            anotherLocalDate = anotherLocalDate.minusDays(1);
        }
        return anotherLocalDate;
    }

}
