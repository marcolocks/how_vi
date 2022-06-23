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

    // criando as variaveis do Recycler View
    private RecyclerView instrutorRV;
    private static final int ADD_INSTRUTOR_REQUEST = 1;
    private static final int EDIT_INSTRUTOR_REQUEST = 2;
    private ViewModelInstrutor viewModelInstrutor;

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.instrutor_main);

        // inicializando variaveis e botao flutuante (novo)
        instrutorRV = findViewById(R.id.idRVInstrutor);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        // click listener para o botao novo
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chama Activity de cadastro
                Intent intent = new Intent(InstrutorActivity.this, NovoInstrutorActivity.class);
                startActivityForResult(intent, ADD_INSTRUTOR_REQUEST);
            }
        });

        // layout manager para o adapter
        instrutorRV.setLayoutManager(new LinearLayoutManager(this));
        instrutorRV.setHasFixedSize(true);

        //inicializa adapter para o Recycler View
        final InstrutorRVAdapter adapter = new InstrutorRVAdapter();

        instrutorRV.setAdapter(adapter);

        // passando os dados para o View Model
        viewModelInstrutor = ViewModelProviders.of(this).get(ViewModelInstrutor.class);

        // pega todos os registros para o View Model
        viewModelInstrutor.getTodosInstrutores().observe(this, new Observer<List<InstrutorModel>>() {
            @Override
            public void onChanged(List<InstrutorModel> instrutorModels) {
                adapter.submitList(instrutorModels);
            }
        });

        // metodo para arrastar para o lado e deletar o item do Recycler View
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            // deletando
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModelInstrutor.delete(adapter.getInstrutorAt(viewHolder.getAdapterPosition()));
                Toast.makeText(InstrutorActivity.this, "Instrutor Removido", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(instrutorRV);

        // click listener para o item do Recycler View (editar)
        adapter.setOnClickListener(new InstrutorRVAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(InstrutorModel model){
                Intent intent = new Intent(InstrutorActivity.this, NovoInstrutorActivity.class);
                intent.putExtra(NovoInstrutorActivity.EXTRA_COD_INSTRUTOR,model.getCoInstrutor());
                intent.putExtra(NovoInstrutorActivity.EXTRA_CPF_INSTRUTOR,model.getNuCPF());
                intent.putExtra(NovoInstrutorActivity.EXTRA_NOME_INSTRUTOR,model.getNoInstrutor());
                intent.putExtra(NovoInstrutorActivity.EXTRA_DT_NASC_INSTRUTOR,model.getDtNascimento());
                intent.putExtra(NovoInstrutorActivity.EXTRA_NU_TELEFONE_INSTRUTOR,model.getNuTelefone());
                // chama activity para edicao
                startActivityForResult(intent, EDIT_INSTRUTOR_REQUEST);
            }
        });

    }

    // atualiza e exibe mensagem de confirmacao
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_INSTRUTOR_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            String nuCPF = data.getStringExtra(NovoInstrutorActivity.EXTRA_CPF_INSTRUTOR);
            String noInstrutor = data.getStringExtra(NovoInstrutorActivity.EXTRA_NOME_INSTRUTOR);
            String dtNascimento = data.getStringExtra(NovoInstrutorActivity.EXTRA_DT_NASC_INSTRUTOR);
            String nuTelefone = data.getStringExtra(NovoInstrutorActivity.EXTRA_NU_TELEFONE_INSTRUTOR);
            InstrutorModel instrutorModel = new InstrutorModel(nuCPF, noInstrutor, dtNascimento, nuTelefone);
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
            String dtNascInstrutor = data.getStringExtra(NovoInstrutorActivity.EXTRA_DT_NASC_INSTRUTOR);
            String nuTelefoneInstutor = data.getStringExtra(NovoInstrutorActivity.EXTRA_NU_TELEFONE_INSTRUTOR);
            InstrutorModel instrutorModel = new InstrutorModel(nuCPFInstrutor,noInstrutor,dtNascInstrutor,nuTelefoneInstutor);
            instrutorModel.setCoInstrutor(coInstrutor);
            viewModelInstrutor.update(instrutorModel);
            instrutorModel.setCoInstrutor(coInstrutor);
            Toast.makeText(this, "Instrutor atualizado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Instrutor não foi salvo", Toast.LENGTH_SHORT).show();
        }
    }
}
