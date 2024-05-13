package com.example.horadedartchau.dataBase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.horadedartchau.dataBase.domain.DadosDia;
import com.example.horadedartchau.dataBase.domain.EstadoFezesDia;

@Dao
public interface EstadoFezesDAO {
    @Query("SELECT * FROM estado_fezes_dia WHERE dia LIKE :localDateString AND ativo = 1 LIMIT 1")
    EstadoFezesDia findByDay(String localDateString);

    @Query("SELECT * FROM estado_fezes_dia WHERE dia LIKE :localDateString LIMIT 1")
    EstadoFezesDia findByDayNotAtivo(String localDateString);
    @Update
    void update(EstadoFezesDia estadoFezesDia);

    @Delete
    void delete(EstadoFezesDia estadoFezesDia);

    @Insert
    void insert(EstadoFezesDia estadoFezesDia);


}

