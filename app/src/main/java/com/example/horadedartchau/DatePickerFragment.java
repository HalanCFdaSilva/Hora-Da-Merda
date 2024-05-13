package com.example.horadedartchau;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import com.example.horadedartchau.userInterface.MainActivity;
import com.example.horadedartchau.userInterface.adapter.GuardiaoMesAno;
import com.example.horadedartchau.userInterface.util.CalendarMonthUseful;

import java.util.Calendar;

public class DatePickerFragment  {
    private final PopupWindow pw;
    private int width = LinearLayout.LayoutParams.MATCH_PARENT;
    private int heigth = LinearLayout.LayoutParams.MATCH_PARENT;

    public DatePickerFragment(ViewGroup viewGroup) {
        this.pw = new PopupWindow(viewGroup,
                width,heigth, true);
    }

    public PopupWindow getPopUpWindow(){

        return pw;

    }
    public void configuraBotoes(ViewGroup layout) {

        final NumberPicker monthPicker = layout.findViewById(R.id.date_picker_pop_up_number_picker_month);
        final NumberPicker yearPicker = layout.findViewById(R.id.date_picker_pop_up_number_picker_year);
        String[] meses = CalendarMonthUseful.getArrayMesesIniciaisPortugues();
        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(11);
        monthPicker.setDisplayedValues(meses);
        layout.setAlpha(1.0f);


        int year = Calendar.getInstance().get(Calendar.YEAR);
        yearPicker.setMinValue(1990);
        yearPicker.setMaxValue(2055);
        yearPicker.setValue(year);




    }

    public void onClickButtonConfirm(ViewGroup layout, MainActivity activity){
        final NumberPicker monthPicker = layout.findViewById(R.id.date_picker_pop_up_number_picker_month);
        final NumberPicker yearPicker = layout.findViewById(R.id.date_picker_pop_up_number_picker_year);
        final Button button = layout.findViewById(R.id.date_picker_pop_up_button_confirm);
        button. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int monthValue = monthPicker.getValue();
                int yearValue = yearPicker.getValue();

                String monthSelected = CalendarMonthUseful.getMonth(monthValue);
                String yearSelected = Integer.toString(yearValue);
                GuardiaoMesAno.setStringAno(yearSelected);
                GuardiaoMesAno.setStringMes(monthSelected);
                pw.dismiss();
                activity.updateData();
            }
        });
    }
    public void encerrarAoClicarFora(ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pw.dismiss();
            }
        });
    }
}