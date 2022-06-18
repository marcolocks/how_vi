package com.how_vi.autoescola;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VeiculoRepository {

    private final VeiculoDao dao;
    private final LiveData<List<VeiculoModel>> todosVeiculos;

    public VeiculoRepository(Application application){
        AutoEscolaDb database = AutoEscolaDb.getInstance(application);
        dao = (VeiculoDao) database.veiculoDao();
        todosVeiculos = dao.getTodosVeiculos();
    }

    public void insert(VeiculoModel model){
        new InsertVeiculoAsyncTask(dao).execute(model);
    }

    public void update(VeiculoModel model){
        new UpdateVeiculoAsyncTask(dao).execute(model);
    }

    public void delete(VeiculoModel model){
        new DeleteVeiculoAsyncTask(dao).execute(model);
    }

    public void deleteTodosVeiculos(){
        new DeleteTodosVeiculosAsyncTask(dao).execute();
    }

    public LiveData<List<VeiculoModel>> getTodosVeiculos(){
        return todosVeiculos;
    }

    private static class InsertVeiculoAsyncTask extends AsyncTask<VeiculoModel, Void, Void> {
        private final VeiculoDao dao;
        private InsertVeiculoAsyncTask(VeiculoDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(VeiculoModel... model){
            dao.insertVeiculo(model[0]);
            return null;
        }
    }

    private static class UpdateVeiculoAsyncTask extends AsyncTask<VeiculoModel, Void, Void> {
        private final VeiculoDao dao;
        private UpdateVeiculoAsyncTask(VeiculoDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(VeiculoModel... models){
            dao.updateVeiculo(models[0]);
            return null;
        }
    }

    private static class DeleteVeiculoAsyncTask extends AsyncTask<VeiculoModel, Void, Void> {
        private final VeiculoDao dao;
        private DeleteVeiculoAsyncTask(VeiculoDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(VeiculoModel... models){
            dao.deleteVeiculo(models[0]);
            return null;
        }
    }

    private static class DeleteTodosVeiculosAsyncTask extends AsyncTask<Void, Void, Void> {
        private final VeiculoDao dao;
        private DeleteTodosVeiculosAsyncTask(VeiculoDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            dao.deleteTodosVeiculos();
            return null;
        }
    }
}
