package com.how_vi.autoescola.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.how_vi.autoescola.data.DAO.AlunoDao;
import com.how_vi.autoescola.data.DAO.InstrutorDao;
import com.how_vi.autoescola.data.DAO.VeiculoDao;
import com.how_vi.autoescola.data.model.AlunoModel;
import com.how_vi.autoescola.data.model.InstrutorModel;
import com.how_vi.autoescola.data.model.VeiculoModel;

// adicionando anotacao para as entidades e versao do banco
@Database(entities = {InstrutorModel.class, AlunoModel.class, VeiculoModel.class},version = 1)
public abstract class AutoEscolaDb extends RoomDatabase {

    // instanciando a base de dados
    private static AutoEscolaDb instance;

    // criando DAOs para cada entidade
    public abstract InstrutorDao instrutorDao();
    public abstract AlunoDao alunoDao();
    public abstract VeiculoDao veiculoDao();

    // metodo para instanciar a base de dados
    public static synchronized AutoEscolaDb getInstance(Context context){
        // verifica se ja esta criado
        if (instance == null){
            // criando base de dados passando a classe autoescola
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AutoEscolaDb.class,"autoescola database")
                    // informa room database para destruir (e recriar) a base de dados em migracoes
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        // retorna instancia da base de dados
        return instance;
    }

    // criando callback
    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            // popular dados na criacao da tabela
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // metodo para executar tarefas assincronas em segundo plano
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        @Deprecated
        PopulateDbAsyncTask(AutoEscolaDb instace){
            // Informando os DAOs das entidades
            InstrutorDao instrutorDao = instance.instrutorDao();
            AlunoDao alunoDao = instance.alunoDao();
            VeiculoDao veiculoDao = instance.veiculoDao();
        }
        @Override
        protected Void doInBackground(Void... voids){
            return null;
        }
    }

}
