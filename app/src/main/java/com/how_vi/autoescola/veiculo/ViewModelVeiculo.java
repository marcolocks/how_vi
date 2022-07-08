package com.how_vi.autoescola.veiculo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.how_vi.autoescola.data.model.VeiculoModel;

import java.util.List;

public class ViewModelVeiculo extends AndroidViewModel {

    // Veiculo
    private final VeiculoRepository repositoryVeiculo;

    private final LiveData<List<VeiculoModel>> todosVeiculos;

    // recuperar todos os Veiculos
    public LiveData<List<VeiculoModel>> getTodosVeiculos() {
        return todosVeiculos;
    }

    // construtor Veiculo
    public ViewModelVeiculo(@NonNull Application application){
        super(application);
        repositoryVeiculo = new VeiculoRepository(application);
        todosVeiculos = repositoryVeiculo.getTodosVeiculos();
    }

    // insert Veiculo
    public void insert(VeiculoModel model){
        repositoryVeiculo.insert(model);
    }

    // update Veiculo
    public void update(VeiculoModel model){
        repositoryVeiculo.update(model);
    }

    // delete Veiculo
    public void delete(VeiculoModel model){
        repositoryVeiculo.delete(model);
    }

    public void deleteTodosVeiculos(){
        repositoryVeiculo.deleteTodosVeiculos();
    }
    
}
