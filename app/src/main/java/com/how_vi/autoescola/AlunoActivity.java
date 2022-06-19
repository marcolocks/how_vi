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

public class AlunoActivity extends AppCompatActivity {

    private RecyclerView alunoRV;
    private static final int ADD_ALUNO_REQUEST = 1;
    private static final int EDIT_ALUNO_REQUEST = 2;
    private ViewModelAluno viewModelAluno;

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.aluno_main);

        alunoRV = findViewById(R.id.idRVAluno);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlunoActivity.this, NovoAlunoActivity.class);
                startActivityForResult(intent, ADD_ALUNO_REQUEST);
            }
        });

        alunoRV.setLayoutManager(new LinearLayoutManager(this));
        alunoRV.setHasFixedSize(true);

        final AlunoRVAdapter adapter = new AlunoRVAdapter();

        alunoRV.setAdapter(adapter);

        viewModelAluno = ViewModelProviders.of(this).get(ViewModelAluno.class);

        viewModelAluno.getTodosAlunos().observe(this, new Observer<List<AlunoModel>>() {
            @Override
            public void onChanged(List<AlunoModel> alunoModels) {
                adapter.submitList(alunoModels);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModelAluno.delete(adapter.getAlunoAt(viewHolder.getAdapterPosition()));
                Toast.makeText(AlunoActivity.this, "Aluno Removido", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(alunoRV);

        adapter.setOnClickListener(new AlunoRVAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(AlunoModel model){
                Intent intent = new Intent(AlunoActivity.this, NovoAlunoActivity.class);
                intent.putExtra(NovoAlunoActivity.EXTRA_COD_ALUNO,model.getCoAluno());
                intent.putExtra(NovoAlunoActivity.EXTRA_CPF_ALUNO,model.getNuCPF());
                intent.putExtra(NovoAlunoActivity.EXTRA_NOME_ALUNO,model.getNoAluno());
                // todo: ajustar quando houver spinner
                //intent.putExtra(NovoAlunoActivity.EXTRA_DT_NASC_ALUNO,model.get());

                startActivityForResult(intent, EDIT_ALUNO_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_ALUNO_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            String nuCPF = data.getStringExtra(NovoAlunoActivity.EXTRA_CPF_ALUNO);
            String noAluno = data.getStringExtra(NovoAlunoActivity.EXTRA_NOME_ALUNO);
            // todo: ajustar quando houver spinner
            //String dtNascimento = data.getStringExtra(NovoAlunoActivity.EXTRA_DT_NASC_ALUNO);
            AlunoModel alunoModel = new AlunoModel(nuCPF, noAluno);
            viewModelAluno.insert(alunoModel);
            Toast.makeText(this, "Aluno salvo", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_ALUNO_REQUEST && resultCode == RESULT_OK){
            assert data != null;
            int coAluno = data.getIntExtra(NovoAlunoActivity.EXTRA_COD_ALUNO, -1);
            if (coAluno == -1){
                Toast.makeText(this, "Aluno não pode ser alterado", Toast.LENGTH_SHORT).show();
                return;
            }
            String nuCPFAluno = data.getStringExtra(NovoAlunoActivity.EXTRA_CPF_ALUNO);
            String noAluno = data.getStringExtra(NovoAlunoActivity.EXTRA_NOME_ALUNO);
            // todo: ajustar quando houver spinner
            // String dtNascAluno = data.getStringExtra(NovoAlunoActivity.EXTRA_DT_NASC_ALUNO);
            AlunoModel alunoModel = new AlunoModel(nuCPFAluno,noAluno);
            alunoModel.setCoAluno(coAluno);
            viewModelAluno.update(alunoModel);
            alunoModel.setCoAluno(coAluno);
            Toast.makeText(this, "Aluno atualizado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Aluno não foi salvo", Toast.LENGTH_SHORT).show();
        }

    }
}
