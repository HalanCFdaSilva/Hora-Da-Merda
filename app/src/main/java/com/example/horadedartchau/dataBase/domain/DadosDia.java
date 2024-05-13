package com.example.horadedartchau.dataBase.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(tableName = "dados_dia")
public class DadosDia implements Serializable {

    @PrimaryKey
    @NonNull
    private String dia;

    @NonNull
    private boolean ativo;
    @ColumnInfo(name = "numero_vezes")
    private int numeroVezes = 1;

    public DadosDia() {
        this.ativo = true;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String localDateString) {
        this.dia = localDateString;
    }
    public void setDia(LocalDate localDate) {
        this.dia = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public LocalDate getLocalDateOfDay(){
        LocalDate date = LocalDate.parse(dia, DateTimeFormatter.ISO_LOCAL_DATE);
        return date;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getNumeroVezes() {
        return numeroVezes;
    }

    public void setNumeroVezes(int numeroVezes) {
        this.numeroVezes = numeroVezes;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String observacoes) {
        Observacoes = observacoes;
    }

    @ColumnInfo(name = "observacoes")
    private String Observacoes;


    public LocalDate getLocalDateDia() {
        return LocalDate.parse(dia,DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public void copyOFDadosDia(DadosDia dadosDia) {
        this.ativo = true;
        this.setObservacoes(dadosDia.getObservacoes());
        this.setNumeroVezes(dadosDia.getNumeroVezes());
    }
}
