package com.example.horadedartchau.userInterface;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableRow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.horadedartchau.DatePickerFragment;
import com.example.horadedartchau.R;
import com.example.horadedartchau.dataBase.AplicationDataBase;
import com.example.horadedartchau.dataBase.dao.DadosDiaDAO;
import com.example.horadedartchau.dataBase.domain.DadosDia;
import com.example.horadedartchau.userInterface.adapter.AgendaAdapter;
import com.example.horadedartchau.userInterface.adapter.GuardiaoMesAno;
import com.example.horadedartchau.userInterface.injectors.DayCalendarInjector;
import com.example.horadedartchau.userInterface.util.CalendarMonthUseful;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    ArrayList<DadosDia> dadosDiaMes;
    private AgendaAdapter adapter;
    private DayCalendarInjector insensor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendario);
        this.adapter = new AgendaAdapter(this);
        String mesAtual = CalendarMonthUseful.converterMesInglesPortugues(LocalDate.now().getMonth().toString());
        String anoAtual = Integer.toString(LocalDate.now().getYear());
        GuardiaoMesAno.setStringMes(mesAtual);
        GuardiaoMesAno.setStringAno(anoAtual);


    }

    @Override
    protected void onStart() {
        super.onStart();


        this.insertData();
    }

    private void insertData() {
        Button buttonMounthYear = (Button) findViewById(R.id.calendario_activity_button_month_year);
        buttonMounthYear.setText(GuardiaoMesAno.getStringMes() + " DE " + GuardiaoMesAno.getStringAno());
        ImageButton buttonBack = (ImageButton) findViewById(R.id.calendario_activity_button_month_back);
        adapter.inserirAcaoBotaoMesAnterior(buttonBack,buttonMounthYear);
        ImageButton buttonFront = (ImageButton) findViewById(R.id.calendario_activity_button_month_front);
        adapter.inseirAcaoBotaoMesSequinte(buttonFront,buttonMounthYear);
        this.inflarDia();
        this.gerarPopupSeletorMesAoClicarBotao(buttonMounthYear);
    }

    public void updateData() {
        Button buttonMounthYear = (Button) findViewById(R.id.calendario_activity_button_month_year);
        buttonMounthYear.setText(GuardiaoMesAno.getStringMes() + " DE " + GuardiaoMesAno.getStringAno());
        this.inflarDia();

    }



    public void inflarDia(){

        this.getArrayofDadosDiaforDataBank();
        this.insensor = new DayCalendarInjector( this,this.dadosDiaMes);
        int posicaoMes = CalendarMonthUseful.posicaoMesPortugues(GuardiaoMesAno.getStringMes()) + 1;
        LocalDate localDate =LocalDate.of(GuardiaoMesAno.getStringAnoInInt(),posicaoMes,01);
        ArrayList<Integer> idsTableRow = DayCalendarInjector.getTableRowsIds();
        this.clearTablesRow(idsTableRow);
        int i = 0;
        TableRow tableRow = this.getTableRowInTable(idsTableRow,i);

        int diasMes = localDate.lengthOfMonth();
        LinearLayout linearLayout = new LinearLayout(tableRow.getContext());

        LayoutInflater inflater = this.getLayoutInflater();


        linearLayout.setBackgroundResource(R.color.paper);
        int dia;
        boolean viewToAdd = false;
        linearLayout = insensor.generateDaysBefore(linearLayout,localDate,inflater);
        for (dia = 1; dia <= diasMes; dia++){

            LinearLayout linearLayoutDia = insensor.CreateView(inflater,R.color.paper,
                    localDate);
            linearLayout.addView(linearLayoutDia);
            viewToAdd = true;
            if (localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && i <= 5){
                i++;
                tableRow.addView(linearLayout);
                viewToAdd = false;
                tableRow = this.getTableRowInTable(idsTableRow,i);
                linearLayout = new LinearLayout(tableRow.getContext());
            }



            localDate = localDate.plusDays(1L);

        }

        while(!localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            linearLayout = insensor.addLayoutAfterMonth(linearLayout,localDate,inflater);
            localDate = localDate.plusDays(1);

        }
        if (viewToAdd){
            tableRow.addView(linearLayout);

        }else{
            i--;
        }


        while (i<5) {
            i++;
            tableRow = this.getTableRowInTable(idsTableRow, i);
            linearLayout = this.generateDaysAfter(localDate,inflater);
            tableRow.addView(linearLayout);
        }

    }

    private LinearLayout generateDaysAfter(LocalDate localDate,LayoutInflater layoutInflater){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundResource(R.color.gray);
        linearLayout = insensor.addLayoutAfterMonth(linearLayout,localDate,layoutInflater);
        localDate = localDate.plusDays(1);
        while (!localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            linearLayout = insensor.addLayoutAfterMonth(linearLayout,localDate,layoutInflater);
            localDate = localDate.plusDays(1);
        }
        return linearLayout;
    }





    private void getArrayofDadosDiaforDataBank() {
        AplicationDataBase dataBase = AplicationDataBase.getInstance(this);
        DadosDiaDAO diaDAO = dataBase.dadosDiaDAO();
        LocalDate localDateinicioMes = LocalDate.of(GuardiaoMesAno.getStringAnoInInt(),GuardiaoMesAno.getPosicaoMes(),01);
        LocalDate localDateFimMes = LocalDate.of(GuardiaoMesAno.getStringAnoInInt(),GuardiaoMesAno.getPosicaoMes(),localDateinicioMes.lengthOfMonth());
        this.dadosDiaMes = (ArrayList<DadosDia>) diaDAO.findByDay(localDateinicioMes.format(DateTimeFormatter.ISO_LOCAL_DATE),localDateFimMes.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }


    private void clearTablesRow(ArrayList<Integer> idsTableRow) {
        for (int id :idsTableRow){
            TableRow tableRow = this.findViewById(id);
            tableRow.removeAllViews();
        }
    }

    private TableRow getTableRowInTable(ArrayList<Integer> idsTableRow, int i) {
        return this.findViewById(idsTableRow.get(i));
    }

    private void gerarPopupSeletorMesAoClicarBotao(Button button) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MainActivity activity = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.date_picker_pop_up, null, false);
                DatePickerFragment datePicker = new DatePickerFragment(viewGroup);
                datePicker.configuraBotoes(viewGroup);
                PopupWindow pw = datePicker.getPopUpWindow();
                ImageView imageViewBackground = viewGroup.findViewById(R.id.date_picker_pop_up_image_view_not_use);
                datePicker.encerrarAoClicarFora(imageViewBackground);
                datePicker.onClickButtonConfirm(viewGroup,activity);
                pw.showAtLocation(viewGroup, Gravity.CENTER, 0, 0);

            }
        });
    }







}
