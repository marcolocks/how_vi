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

public class VeiculoActivity extends AppCompatActivity {

    private RecyclerView veiculoRV;
    private static final int ADD_VEICULO_REQUEST = 1;
    private static final int EDIT_VEICULO_REQUEST = 2;
    private ViewModelVeiculo viewModelVeiculo;

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.veiculo_main);

        veiculoRV = findViewById(R.id.idRVVeiculo);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VeiculoActivity.this, NovoVeiculoActivity.class);
                startActivityForResult(intent, ADD_VEICULO_REQUEST);
            }
        });

        veiculoRV.setLayoutManager(new LinearLayoutManager(this));
        veiculoRV.setHasFixedSize(true);

        final VeiculoRVAdapter adapter = new VeiculoRVAdapter();

        veiculoRV.setAdapter(adapter);

        viewModelVeiculo = ViewModelProviders.of(this).get(ViewModelVeiculo.class);

        viewModelVeiculo.getTodosVeiculos().observe(this, new Observer<List<VeiculoModel>>() {
            @Override
            public void onChanged(List<VeiculoModel> veiculoModels) {
                adapter.submitList(veiculoModels);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModelVeiculo.delete(adapter.getVeiculoAt(viewHolder.getAdapterPosition()));
                Toast.makeText(VeiculoActivity.this, "Veiculo Removido", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(veiculoRV);

        adapter.setOnClickListener(new VeiculoRVAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(VeiculoModel model){
                Intent intent = new Intent(VeiculoActivity.this, NovoVeiculoActivity.class);
                intent.putExtra(NovoVeiculoActivity.EXTRA_COD_VEICULO,model.getCoVeiculo());
                intent.putExtra(NovoVeiculoActivity.EXTRA_MODELO_VEICULO,model.getDeMarca());
                intent.putExtra(NovoVeiculoActivity.EXTRA_MARCA_VEICULO,model.getDeModelo());
                intent.putExtra(NovoVeiculoActivity.EXTRA_PLACA_VEICULO,model.getNuPlaca());


                startActivityForResult(intent, EDIT_VEICULO_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_VEICULO_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            String deMarca = data.getStringExtra(NovoVeiculoActivity.EXTRA_MARCA_VEICULO);
            String deModelo = data.getStringExtra(NovoVeiculoActivity.EXTRA_MODELO_VEICULO);
            String nuPlaca = data.getStringExtra(NovoVeiculoActivity.EXTRA_PLACA_VEICULO);


            VeiculoModel veiculoModel = new VeiculoModel(deMarca, deModelo, nuPlaca);
            viewModelVeiculo.insert(veiculoModel);
            Toast.makeText(this, "Veiculo salvo", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_VEICULO_REQUEST && resultCode == RESULT_OK){
            assert data != null;
            int coVeiculo = data.getIntExtra(NovoVeiculoActivity.EXTRA_COD_VEICULO, -1);
            if (coVeiculo == -1){
                Toast.makeText(this, "Veiculo não pode ser alterado", Toast.LENGTH_SHORT).show();
                return;
            }
            String deMarcaVeiculo = data.getStringExtra(NovoVeiculoActivity.EXTRA_MARCA_VEICULO);
            String deModeloVeiculo = data.getStringExtra(NovoVeiculoActivity.EXTRA_MODELO_VEICULO);
            String nuPlacaVeiculo = data.getStringExtra(NovoVeiculoActivity.EXTRA_PLACA_VEICULO);

            VeiculoModel veiculoModel = new VeiculoModel(deMarcaVeiculo, deModeloVeiculo, nuPlacaVeiculo);
            veiculoModel.setCoVeiculo(coVeiculo);
            viewModelVeiculo.update(veiculoModel);
            veiculoModel.setCoVeiculo(coVeiculo);
            Toast.makeText(this, "Veiculo atualizado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Veiculo não foi salvo", Toast.LENGTH_SHORT).show();
        }

    }
}
