package com.how_vi.autoescola;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface VeiculoDao {
    //Veiculo
    @Insert
    void insertVeiculo(VeiculoModel model);

    @Update
    void updateVeiculo(VeiculoModel model);

    @Delete
    void deleteVeiculo(VeiculoModel model);

    @Query("DELETE FROM tbVeiculo")
    void deleteTodosVeiculos();

    @Query("SELECT * FROM TBVEICULO ORDER BY DEMARCA ASC")
    LiveData<List<VeiculoModel>> getTodosVeiculos();

}
