package com.how_vi.autoescola;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {InstrutorModel.class, AlunoModel.class, VeiculoModel.class},version = 1)
public abstract class AutoEscolaDb extends RoomDatabase {
    //@TypeConverters({Converters.class})
    private static AutoEscolaDb instance;

    public abstract InstrutorDao instrutorDao();
    public abstract AlunoDao alunoDao();
    public abstract VeiculoDao veiculoDao();

    public static synchronized AutoEscolaDb getInstance(Context context){

        if (instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AutoEscolaDb.class,"autoescola database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        @Deprecated
        PopulateDbAsyncTask(AutoEscolaDb instace){
            InstrutorDao instrutorDao = instance.instrutorDao();
            AlunoDao alunoDao = instance.alunoDao();
            VeiculoDao veiculoDao = instance.veiculoDao();
            //Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids){
            return null;
        }
    }
}
