package com.example.horadedartchau.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.horadedartchau.dataBase.dao.DadosDiaDAO;
import com.example.horadedartchau.dataBase.dao.EstadoFezesDAO;
import com.example.horadedartchau.dataBase.domain.DadosDia;
import com.example.horadedartchau.dataBase.domain.EstadoFezesDia;
import com.example.horadedartchau.userInterface.SalvarDadosDiaActivity;

@Database(entities = {DadosDia.class, EstadoFezesDia.class},version = 1)
public abstract class AplicationDataBase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "hora_da_merda.db";
    public static AplicationDataBase getInstance(Context context) {

            return Room
                    .databaseBuilder(context, AplicationDataBase.class, NOME_BANCO_DE_DADOS)
                    .allowMainThreadQueries()
                    .build();

    }

    public abstract DadosDiaDAO dadosDiaDAO();

    public abstract EstadoFezesDAO estadoFezesDAO();
}
