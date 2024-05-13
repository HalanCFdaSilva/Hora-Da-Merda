package com.example.horadedartchau.dataBase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.horadedartchau.dataBase.domain.DadosDia;

import java.util.List;


@Dao
public interface DadosDiaDAO {
    @Query("SELECT * FROM dados_dia WHERE dia LIKE :localDateString AND ativo = 1 LIMIT 1")
    DadosDia findByDay(String localDateString);

    @Query("SELECT * FROM dados_dia WHERE dia LIKE :localDateString LIMIT 1")
    DadosDia findByDayNotAtivo(String localDateString);
    @Query("SELECT * FROM dados_dia WHERE dia >= :localDateStringInicio AND dia <= :localDateStringFim AND ativo = 1")
    List<DadosDia> findByDay(String localDateStringInicio, String localDateStringFim);

    @Update
    void update(DadosDia dadosDia);

    @Delete
    void delete(DadosDia dadosDia);

    @Insert
    void insert(DadosDia dadosDia);
}
