package com.how_vi.autoescola.veiculo;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.how_vi.autoescola.data.AutoEscolaDb;
import com.how_vi.autoescola.data.DAO.VeiculoDao;
import com.how_vi.autoescola.data.model.VeiculoModel;

import java.util.List;

public class VeiculoRepository {

    // criando variavel para o DAO e lista para todos os registros
    private final VeiculoDao dao;
    private final LiveData<List<VeiculoModel>> todosVeiculos;

    // criando construtor e passando as variaveis a ele
    public VeiculoRepository(Application application){
        AutoEscolaDb database = AutoEscolaDb.getInstance(application);
        dao = (VeiculoDao) database.veiculoDao();
        todosVeiculos = dao.getTodosVeiculos();
    }

    // metodos crud
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

    // metodo para insert assincrono
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

    // metodo para update assincrono
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

    // metodo para delete assincrono
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

    // metodo para delete todos assincrono
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
