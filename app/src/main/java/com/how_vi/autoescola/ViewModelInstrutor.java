package com.how_vi.autoescola;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ViewModelInstrutor extends AndroidViewModel {

    // Instrutor
    private final InstrutorRepository repositoryInstrutor;

    private final LiveData<List<InstrutorModel>> todosInstrutores;

    // recuperar todos os instrutores
    public LiveData<List<InstrutorModel>> getTodosInstrutores() {
        return todosInstrutores;
    }

    // construtor instrutor
    public ViewModelInstrutor(@NonNull Application application){
        super(application);
        repositoryInstrutor = new InstrutorRepository(application);
        todosInstrutores = repositoryInstrutor.getTodosInstrutores();
    }

    // insert instrutor
    public void insert(InstrutorModel model){
        repositoryInstrutor.insert(model);
    }

    // update instrutor
    public void update(InstrutorModel model){
        repositoryInstrutor.update(model);
    }

    // delete instrutor
    public void delete(InstrutorModel model){
        repositoryInstrutor.delete(model);
    }

    public void deleteTodosInstrutores(){
        repositoryInstrutor.deleteTodosInstrutores();
    }
}
