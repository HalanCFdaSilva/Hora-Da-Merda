package com.example.horadedartchau.userInterface.util;

import java.util.ArrayList;

public abstract class CalendarMonthUseful {

    public static String[] getArrayMesesIniciaisPortugues(){
        String[] meses = new String[]{"JAN","FEV","MAR","ABR","MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ"};
        return meses;
    }

    public static ArrayList<String> getArrayListMesesPortugues(){
        ArrayList<String> mesesPortugues = new ArrayList<>();
        mesesPortugues.add("JANEIRO");
        mesesPortugues.add("FEVEREIRO");
        mesesPortugues.add("MARÇO");
        mesesPortugues.add("ABRIL");
        mesesPortugues.add("MAIO");
        mesesPortugues.add("JUNHO");
        mesesPortugues.add("JULHO");
        mesesPortugues.add("AGOSTO");
        mesesPortugues.add("SETEMBRO");
        mesesPortugues.add("OUTUBRO");
        mesesPortugues.add("NOVEMBRO");
        mesesPortugues.add("DEZEMBRO");
        return mesesPortugues;
    }



    public static int posicaoMesPortugues(String mesAVerificar) {
        ArrayList<String> meses = CalendarMonthUseful.getArrayListMesesPortugues();
        int i = 0;
        for (String mes : meses){
            if (mes.equalsIgnoreCase(mesAVerificar)){
                return i;
            }else{
                i++;
            }
        }
        return 0;
    }

    public static String converterMesInglesPortugues(String mesEmIngles){
        mesEmIngles = mesEmIngles.toUpperCase();
        ArrayList<String> mesesPortugues = CalendarMonthUseful.getArrayListMesesPortugues();
        int i = CalendarMonthUseful.posicaoMesIngles(mesEmIngles);
        return mesesPortugues.get(i);
    }
    private static ArrayList<String> getArrayListMesIngles() {

        ArrayList<String> mesesIngles = new ArrayList<>();
        mesesIngles.add("JANUARY");
        mesesIngles.add("FEBRUARY");
        mesesIngles.add("MARCH");
        mesesIngles.add("APRIL");
        mesesIngles.add("MAY");
        mesesIngles.add("JUNE");
        mesesIngles.add("JULY");
        mesesIngles.add("AUGUST");
        mesesIngles.add("SEPTEMBER");
        mesesIngles.add("OCTOBER");
        mesesIngles.add("NOVEMBER");
        mesesIngles.add("DECEMBER");
        return mesesIngles;

    }
    public static int posicaoMesIngles(String mesAVerificar) {
        ArrayList<String> meses = CalendarMonthUseful.getArrayListMesIngles();
        int i = 0;
        for (String mes : meses){
            if (mes.equalsIgnoreCase(mesAVerificar)){
                return i;
            }
            i++;
        }
        return 0;
    }

    public static String MesAnterior(String stringMes){
        ArrayList<String> mesesPortugues = CalendarMonthUseful.getArrayListMesesPortugues();
        stringMes = stringMes.replaceAll(" ","");

        String mesAnterior = mesesPortugues.get(11);
        for (String mes: mesesPortugues){
            if (mes.equalsIgnoreCase( stringMes)){
                return mesAnterior;
            }
            mesAnterior = mes;
        }
        return stringMes;
    }

    public static String proximoMes(String stringMes) {
        ArrayList<String> mesesPortugues = CalendarMonthUseful.getArrayListMesesPortugues();
        int mesSeguinte = 1;
        for (String mes: mesesPortugues){
            if (mes.equalsIgnoreCase( stringMes)){
                return mesesPortugues.get(mesSeguinte);
            }
            if (mesSeguinte == 11){
                mesSeguinte = 0;
            }else {
                mesSeguinte++;
            }

        }
        return stringMes;
    }


    public static String getMonth(int monthValue) {

        return CalendarMonthUseful.getArrayListMesesPortugues().get(monthValue);

    }
}
