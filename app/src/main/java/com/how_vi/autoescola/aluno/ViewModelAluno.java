package com.how_vi.autoescola.aluno;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.how_vi.autoescola.data.model.AlunoModel;

import java.util.List;

public class ViewModelAluno extends AndroidViewModel {

    // Aluno
    private final AlunoRepository repositoryAluno;

    private final LiveData<List<AlunoModel>> todosAlunos;

    // recuperar todos os Alunos
    public LiveData<List<AlunoModel>> getTodosAlunos() {
        return todosAlunos;
    }

    // construtor Aluno
    public ViewModelAluno(@NonNull Application application){
        super(application);
        repositoryAluno = new AlunoRepository(application);
        todosAlunos = repositoryAluno.getTodosAlunos();
    }

    // insert Aluno
    public void insert(AlunoModel model){
        repositoryAluno.insert(model);
    }

    // update Aluno
    public void update(AlunoModel model){
        repositoryAluno.update(model);
    }

    // delete Aluno
    public void delete(AlunoModel model){
        repositoryAluno.delete(model);
    }

    public void deleteTodosAlunos(){
        repositoryAluno.deleteTodosAlunos();
    }

}
