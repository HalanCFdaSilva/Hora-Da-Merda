package com.example.horadedartchau.userInterface.util;

import java.util.ArrayList;

public class CalendarYearUseful {



    public static ArrayList<String> getAnos(){
        ArrayList<String> anos = new ArrayList<>();
        for (int i = 1990;i<= 2055;i++){
            anos.add(Integer.toString(i));
        }

        return anos;
    }

    public static String proximoAno(String stringAno) {
        ArrayList<String> listaAnos = CalendarYearUseful.getAnos();
        String proximoAnoString = stringAno;
        for (String ano : listaAnos){
            if (ano.equalsIgnoreCase(stringAno)){
                int proximoAno = Integer.parseInt(ano) + 1;
                proximoAnoString = Integer.toString(proximoAno);
                break;
            }
        }
        return proximoAnoString;
    }

    public static String AnoAnterior(String stringAno) {
        ArrayList<String> listaAnos = CalendarYearUseful.getAnos();
        String proximoAnoString = stringAno;
        for (String ano : listaAnos){
            if (ano.equalsIgnoreCase(stringAno)){
                int proximoAno = Integer.parseInt(ano) - 1;
                proximoAnoString = Integer.toString(proximoAno);
                break;
            }
        }
        return proximoAnoString;
    }
}
