package com.how_vi.autoescola;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface AlunoDao {
    //Aluno
    @Insert
    void insertAluno(AlunoModel model);

    @Update
    void updateAluno(AlunoModel model);

    @Delete
    void deleteAluno(AlunoModel model);

    @Query("DELETE FROM tbAluno")
    void deleteTodosAlunos();

    @Query("SELECT * FROM TBALUNO ORDER BY NUCPF ASC")
    LiveData<List<AlunoModel>> getTodosAlunos();

}


