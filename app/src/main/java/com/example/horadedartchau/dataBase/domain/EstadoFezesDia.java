package com.example.horadedartchau.dataBase.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.horadedartchau.dataBase.CategoriasEstadoFezes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Entity(tableName = "estado_fezes_dia")
public class EstadoFezesDia implements Serializable {

    @PrimaryKey
    @NonNull
    private String dia;

    private boolean ativo;

    @ColumnInfo(name = "estado_fezes_1")
    private String estadoFezes1;
    @ColumnInfo(name = "estado_fezes_2")
    private String estadoFezes2;
    @ColumnInfo(name = "estado_fezes_3")
    private String estadoFezes3;
    @ColumnInfo(name = "estado_fezes_4")
    private String estadoFezes4;
    @ColumnInfo(name = "estado_fezes_5")
    private String estadoFezes5;
    @ColumnInfo(name = "estado_fezes_6")
    private String estadoFezes6;
    @ColumnInfo(name = "estado_fezes_7")
    private String estadoFezes7;
    @ColumnInfo(name = "estado_fezes_8")
    private String estadoFezes8;
    @ColumnInfo(name = "estado_fezes_9")
    private String estadoFezes9;
    @ColumnInfo(name = "estado_fezes_10")
    private String estadoFezes10;

    public EstadoFezesDia() {
        this.ativo = true;
        for (int i = 1; i <=10;i++){
            this.setEstadoFezes(CategoriasEstadoFezes.TIPO_1,i);
        }
    }


    public String getDia() {
        return dia;
    }

    public LocalDate getLocalDateOfDay(){
        LocalDate date = LocalDate.parse(dia, DateTimeFormatter.ISO_LOCAL_DATE);
        return date;
    }

    public void setDia(LocalDate localDate) {
        this.dia = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    public void setDia(String localDateString) {
        this.dia = localDateString;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getEstadoFezes1() {
        return estadoFezes1;
    }

    public void setEstadoFezes1(String estadoFezes1) {
        this.estadoFezes1 = estadoFezes1;
    }
    public void setEstadoFezes1(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes1 = categoriasEstadoFezes.toString();
    }


    public String getEstadoFezes2() {
        return estadoFezes2;
    }

    public void setEstadoFezes2(String estadoFezes2) {
        this.estadoFezes2 = estadoFezes2;
    }

    public void setEstadoFezes2(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes2 = categoriasEstadoFezes.toString();
    }

    public String getEstadoFezes3() {
        return estadoFezes3;
    }

    public void setEstadoFezes3(String estadoFezes3) {
        this.estadoFezes3 = estadoFezes3;
    }
    public void setEstadoFezes3(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes3 = categoriasEstadoFezes.toString();
    }
    public String getEstadoFezes4() {
        return estadoFezes4;
    }

    public void setEstadoFezes4(String estadoFezes4) {
        this.estadoFezes4 = estadoFezes4;
    }
    public void setEstadoFezes4(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes4 = categoriasEstadoFezes.toString();
    }

    public String getEstadoFezes5() {
        return estadoFezes5;
    }

    public void setEstadoFezes5(String estadoFezes5) {
        this.estadoFezes5 = estadoFezes5;
    }
    public void setEstadoFezes5(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes5 = categoriasEstadoFezes.toString();
    }


    public String getEstadoFezes6() {
        return estadoFezes6;
    }

    public void setEstadoFezes6(String estadoFezes6) {
        this.estadoFezes6 = estadoFezes6;
    }
    public void setEstadoFezes6(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes6 = categoriasEstadoFezes.toString();
    }

    public String getEstadoFezes7() {
        return estadoFezes7;
    }

    public void setEstadoFezes7(String estadoFezes7) {
        this.estadoFezes7 = estadoFezes7;
    }
    public void setEstadoFezes7(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes7 = categoriasEstadoFezes.toString();
    }


    public String getEstadoFezes8() {
        return estadoFezes8;
    }

    public void setEstadoFezes8(String estadoFezes8) {
        this.estadoFezes8 = estadoFezes8;
    }
    public void setEstadoFezes8(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes8 = categoriasEstadoFezes.toString();
    }


    public String getEstadoFezes9() {
        return estadoFezes9;
    }

    public void setEstadoFezes9(String estadoFezes9) {
        this.estadoFezes9 = estadoFezes9;
    }
    public void setEstadoFezes9(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes9 = categoriasEstadoFezes.toString();
    }

    public String getEstadoFezes10() {
        return estadoFezes10;
    }

    public void setEstadoFezes10(String estadoFezes10) {
        this.estadoFezes10 = estadoFezes10;
    }

    public void setEstadoFezes10(CategoriasEstadoFezes categoriasEstadoFezes) {

        this.estadoFezes10 = categoriasEstadoFezes.toString();
    }

    public void setEstadoFezes(CategoriasEstadoFezes categoriasEstadoFezes, int posicao) {
        if (posicao == 1){
            setEstadoFezes1(categoriasEstadoFezes);
        }
        if (posicao == 2){
            setEstadoFezes2(categoriasEstadoFezes);
        }

        if (posicao == 3){
            setEstadoFezes3(categoriasEstadoFezes);
        }

        if (posicao == 4){
            setEstadoFezes4(categoriasEstadoFezes);
        }

        if (posicao == 5){
            setEstadoFezes5(categoriasEstadoFezes);
        }

        if (posicao == 6){
            setEstadoFezes6(categoriasEstadoFezes);
        }

        if (posicao == 7){
            setEstadoFezes7(categoriasEstadoFezes);
        }

        if (posicao == 8){
            setEstadoFezes8(categoriasEstadoFezes);
        }

        if (posicao == 9){
            setEstadoFezes9(categoriasEstadoFezes);

        }if (posicao == 10){
            setEstadoFezes10(categoriasEstadoFezes);
        }


    }

    public String getEstadoFezes(int posicao) {

        if (posicao == 1){
            return getEstadoFezes1();
        }
        if (posicao == 2){
            return getEstadoFezes2();
        }

        if (posicao == 3){
            return getEstadoFezes3();
        }

        if (posicao == 4){
            return getEstadoFezes4();
        }

        if (posicao == 5){
            return getEstadoFezes5();
        }

        if (posicao == 6){
            return getEstadoFezes6();
        }

        if (posicao == 7){
            return getEstadoFezes7();
        }

        if (posicao == 8){
            return getEstadoFezes8();
        }

        if (posicao == 9){
            return getEstadoFezes9();

        }if (posicao == 10){
            return getEstadoFezes10();
        }
        return null;
    }

    public void copyOfPoopState(EstadoFezesDia estadoFezesDia) {
        this.ativo = true;
        this.setEstadoFezes1(estadoFezesDia.getEstadoFezes1());
        this.setEstadoFezes2(estadoFezesDia.getEstadoFezes2());
        this.setEstadoFezes3(estadoFezesDia.getEstadoFezes3());
        this.setEstadoFezes4(estadoFezesDia.getEstadoFezes4());
        this.setEstadoFezes5(estadoFezesDia.getEstadoFezes5());
        this.setEstadoFezes6(estadoFezesDia.getEstadoFezes6());
        this.setEstadoFezes7(estadoFezesDia.getEstadoFezes7());
        this.setEstadoFezes8(estadoFezesDia.getEstadoFezes8());
        this.setEstadoFezes9(estadoFezesDia.getEstadoFezes9());
        this.setEstadoFezes10(estadoFezesDia.getEstadoFezes10());

    }
}
