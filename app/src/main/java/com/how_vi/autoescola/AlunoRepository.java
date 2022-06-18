package com.how_vi.autoescola;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AlunoRepository {

    private final AlunoDao dao;
    private final LiveData<List<AlunoModel>> todosAlunos;

    public AlunoRepository(Application application){
        AutoEscolaDb database = AutoEscolaDb.getInstance(application);
        dao = (AlunoDao) database.alunoDao();
        todosAlunos = dao.getTodosAlunos();
    }

    public void insert(AlunoModel model){
        new InsertAlunoAsyncTask(dao).execute(model);
    }

    public void update(AlunoModel model){
        new UpdateAlunoAsyncTask(dao).execute(model);
    }

    public void delete(AlunoModel model){
        new DeleteAlunoAsyncTask(dao).execute(model);
    }

    public void deleteTodosAlunos(){
        new DeleteTodosAlunosAsyncTask(dao).execute();
    }

    public LiveData<List<AlunoModel>> getTodosAlunos(){
        return todosAlunos;
    }

    private static class InsertAlunoAsyncTask extends AsyncTask<AlunoModel, Void, Void> {
        private final AlunoDao dao;
        private InsertAlunoAsyncTask(AlunoDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AlunoModel... model){
            dao.insertAluno(model[0]);
            return null;
        }
    }

    private static class UpdateAlunoAsyncTask extends AsyncTask<AlunoModel, Void, Void> {
        private final AlunoDao dao;
        private UpdateAlunoAsyncTask(AlunoDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AlunoModel... models){
            dao.updateAluno(models[0]);
            return null;
        }
    }

    private static class DeleteAlunoAsyncTask extends AsyncTask<AlunoModel, Void, Void> {
        private final AlunoDao dao;
        private DeleteAlunoAsyncTask(AlunoDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AlunoModel... models){
            dao.deleteAluno(models[0]);
            return null;
        }
    }

    private static class DeleteTodosAlunosAsyncTask extends AsyncTask<Void, Void, Void> {
        private final AlunoDao dao;
        private DeleteTodosAlunosAsyncTask(AlunoDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            dao.deleteTodosAlunos();
            return null;
        }
    }
}
