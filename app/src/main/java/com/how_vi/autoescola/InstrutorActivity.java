package com.how_vi.autoescola;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class InstrutorActivity extends AppCompatActivity {

    private RecyclerView instrutorRV;
    private static final int ADD_INSTRUTOR_REQUEST = 1;
    private static final int EDIT_INSTRUTOR_REQUEST = 2;
    private ViewModelInstrutor viewModelInstrutor;

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.instrutor_main);

        instrutorRV = findViewById(R.id.idRVInstrutor);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstrutorActivity.this, NovoInstrutorActivity.class);
                startActivityForResult(intent, ADD_INSTRUTOR_REQUEST);
            }
        });

        instrutorRV.setLayoutManager(new LinearLayoutManager(this));
        instrutorRV.setHasFixedSize(true);

        final InstrutorRVAdapter adapter = new InstrutorRVAdapter();

        instrutorRV.setAdapter(adapter);

        viewModelInstrutor = ViewModelProviders.of(this).get(ViewModelInstrutor.class);

        viewModelInstrutor.getTodosInstrutores().observe(this, new Observer<List<InstrutorModel>>() {
            @Override
            public void onChanged(List<InstrutorModel> instrutorModels) {
                adapter.submitList(instrutorModels);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModelInstrutor.delete(adapter.getInstrutorAt(viewHolder.getAdapterPosition()));
                Toast.makeText(InstrutorActivity.this, "Curso Removido", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(instrutorRV);

        adapter.setOnClickListener(new InstrutorRVAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(InstrutorModel model){
                Intent intent = new Intent(InstrutorActivity.this, NovoInstrutorActivity.class);
                intent.putExtra(NovoInstrutorActivity.EXTRA_COD_INSTRUTOR,model.getCoInstrutor());
                intent.putExtra(NovoInstrutorActivity.EXTRA_CPF_INSTRUTOR,model.getNuCPF());
                intent.putExtra(NovoInstrutorActivity.EXTRA_NOME_INSTRUTOR,model.getNoInstrutor());
                // todo: ajustar quando houver spinner
                //intent.putExtra(NovoInstrutorActivity.EXTRA_DT_NASC_INSTRUTOR,model.get());

                startActivityForResult(intent, EDIT_INSTRUTOR_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_INSTRUTOR_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            String nuCPF = data.getStringExtra(NovoInstrutorActivity.EXTRA_CPF_INSTRUTOR);
            String noInstrutor = data.getStringExtra(NovoInstrutorActivity.EXTRA_NOME_INSTRUTOR);
            // todo: ajustar quando houver spinner
            //String dtNascimento = data.getStringExtra(NovoInstrutorActivity.EXTRA_DT_NASC_INSTRUTOR);
            InstrutorModel instrutorModel = new InstrutorModel(nuCPF, noInstrutor);
            viewModelInstrutor.insert(instrutorModel);
            Toast.makeText(this, "Instrutor salvo", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_INSTRUTOR_REQUEST && resultCode == RESULT_OK){
            assert data != null;
            int coInstrutor = data.getIntExtra(NovoInstrutorActivity.EXTRA_COD_INSTRUTOR, -1);
            if (coInstrutor == -1){
                Toast.makeText(this, "Instrutor não pode ser alterado", Toast.LENGTH_SHORT).show();
                return;
            }
            String nuCPFInstrutor = data.getStringExtra(NovoInstrutorActivity.EXTRA_CPF_INSTRUTOR);
            String noInstrutor = data.getStringExtra(NovoInstrutorActivity.EXTRA_NOME_INSTRUTOR);
            // todo: ajustar quando houver spinner
            // String dtNascInstrutor = data.getStringExtra(NovoInstrutorActivity.EXTRA_DT_NASC_INSTRUTOR);
            InstrutorModel instrutorModel = new InstrutorModel(nuCPFInstrutor,noInstrutor);
            instrutorModel.setCoInstrutor(coInstrutor);
            viewModelInstrutor.update(instrutorModel);
            instrutorModel.setCoInstrutor(coInstrutor);
            Toast.makeText(this, "Instrutor atualizado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Instrutor não foi salvo", Toast.LENGTH_SHORT).show();
        }

    }
}
