package com.how_vi.autoescola.instrutor;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.how_vi.autoescola.data.AutoEscolaDb;
import com.how_vi.autoescola.data.DAO.InstrutorDao;
import com.how_vi.autoescola.data.model.InstrutorModel;

import java.util.List;

public class InstrutorRepository {

    // criando variavel para o DAO e lista para todos os registros
    private final InstrutorDao dao;
    private final LiveData<List<InstrutorModel>> todosInstrutores;

    // criando construtor e passando as variaveis a ele
    public InstrutorRepository(Application application){
        AutoEscolaDb database = AutoEscolaDb.getInstance(application);
        dao = (InstrutorDao) database.instrutorDao();
        todosInstrutores = dao.getTodosInstrutores();
    }

    // metodos crud
    public void insert(InstrutorModel model){
        new InsertInstrutorAsyncTask(dao).execute(model);
    }
    public void update(InstrutorModel model){
        new UpdateInstrutorAsyncTask(dao).execute(model);
    }
    public void delete(InstrutorModel model){
        new DeleteInstrutorAsyncTask(dao).execute(model);
    }
    public void deleteTodosInstrutores(){
        new DeleteTodosInstrutoresAsyncTask(dao).execute();
    }
    public LiveData<List<InstrutorModel>> getTodosInstrutores(){
        return todosInstrutores;
    }

    // metodo para insert assincrono
    private static class InsertInstrutorAsyncTask extends AsyncTask<InstrutorModel, Void, Void> {
        private final InstrutorDao dao;
        private InsertInstrutorAsyncTask(InstrutorDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(InstrutorModel... model){
            dao.insertInstrutor(model[0]);
            return null;
        }
    }

    // metodo para update assincrono
    private static class UpdateInstrutorAsyncTask extends AsyncTask<InstrutorModel, Void, Void> {
        private final InstrutorDao dao;
        private UpdateInstrutorAsyncTask(InstrutorDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(InstrutorModel... models){
            dao.updateInstrutor(models[0]);
            return null;
        }
    }

    // metodo para delete assincrono
    private static class DeleteInstrutorAsyncTask extends AsyncTask<InstrutorModel, Void, Void> {
        private final InstrutorDao dao;
        private DeleteInstrutorAsyncTask(InstrutorDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(InstrutorModel... models){
            dao.deleteInstrutor(models[0]);
            return null;
        }
    }

    // metodo para delete todos assincrono
    private static class DeleteTodosInstrutoresAsyncTask extends AsyncTask<Void, Void, Void> {
        private final InstrutorDao dao;
        private DeleteTodosInstrutoresAsyncTask(InstrutorDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            dao.deleteTodosInstrutores();
            return null;
        }
    }
}
