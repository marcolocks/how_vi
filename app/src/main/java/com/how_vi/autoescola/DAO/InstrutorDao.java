package com.how_vi.autoescola.data.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.how_vi.autoescola.data.model.InstrutorModel;

import java.util.List;

@androidx.room.Dao
public interface InstrutorDao {
    // Instrutor
    @Insert
    void insertInstrutor(InstrutorModel model);

    @Update
    void updateInstrutor(InstrutorModel model);

    @Delete
    void deleteInstrutor(InstrutorModel model);

    @Query("DELETE FROM tbInstrutor")
    void deleteTodosInstrutores();

    @Query("SELECT * FROM TBINSTRUTOR ORDER BY NUCPF ASC")
    LiveData<List<InstrutorModel>> getTodosInstrutores();

}
