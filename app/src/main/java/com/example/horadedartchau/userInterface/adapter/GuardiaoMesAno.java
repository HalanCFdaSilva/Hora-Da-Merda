package com.example.horadedartchau.userInterface.adapter;

import com.example.horadedartchau.userInterface.util.CalendarMonthUseful;

import java.time.LocalDate;

public class GuardiaoMesAno {
    private static String stringMes = "JANEIRO";
    private static String stringAno = String.valueOf(LocalDate.now().getYear());

    public static String getStringMes() {
        return stringMes;
    }

    public static void setStringMes(String stringMes) {
        GuardiaoMesAno.stringMes = stringMes;
    }

    public static String getStringAno() {
        return stringAno;
    }

    public static void setStringAno(String stringAno) {
        GuardiaoMesAno.stringAno = stringAno;
    }
    public static int getStringAnoInInt(){
        return Integer.parseInt(stringAno);
    }

    public static void setStringMesAnoByButton(CharSequence buttonStringMesAno){
        String stringMesAno = buttonStringMesAno.toString().toUpperCase();

        int posicaoDe = stringMesAno.indexOf("DE ");
        GuardiaoMesAno.setStringMes(stringMesAno.substring(0,posicaoDe -1));
        GuardiaoMesAno.setStringAno(stringMesAno.substring(posicaoDe + 3));

    }

    public static String getStringMesAno() {
        return getStringMes() + " DE " + getStringAno();
    }

    public static LocalDate getLocalDateDia(int day){
        int posicaoMes = CalendarMonthUseful.posicaoMesPortugues(getStringMes());
        return LocalDate.of(getStringAnoInInt(),posicaoMes,day);
    }

    public static String getCompleteString(int dayOfMonth) {
        return dayOfMonth +" "+ getStringMes() + " de " + getStringAno();
    }

    public static int getPosicaoMes() {
        return CalendarMonthUseful.posicaoMesPortugues(getStringMes()) + 1;
    }
}
