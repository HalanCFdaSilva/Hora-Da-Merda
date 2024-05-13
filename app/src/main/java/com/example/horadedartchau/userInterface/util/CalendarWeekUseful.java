package com.example.horadedartchau.userInterface.util;

import java.time.DayOfWeek;

public class CalendarWeekUseful {
    public static String getDiaSemanaPortugues(DayOfWeek dayOfWeek){
        switch (dayOfWeek){
            case SUNDAY: return "DOMINGO";
            case MONDAY: return "SEGUNDA-FEIRA";
            case TUESDAY: return "TERÇA-FEIRA";
            case WEDNESDAY: return "QUARTA-FEIRA";
            case THURSDAY: return "QUINTA-FEIRA";
            case FRIDAY: return "SEXTA-FEIRA";
            case SATURDAY: return "SABADO";
            default: return "";

        }
    }

    public static String getStringOfDayOfWeekSmallerInPortuguese(DayOfWeek dayOfWeek){
        switch (dayOfWeek){
            case SUNDAY: return "DOM";
            case MONDAY: return "SEG";
            case TUESDAY: return "TER";
            case WEDNESDAY: return "QUA";
            case THURSDAY: return "QUI";
            case FRIDAY: return "SEX";
            case SATURDAY: return "SAB";
            default: return "";

        }

    }

}
