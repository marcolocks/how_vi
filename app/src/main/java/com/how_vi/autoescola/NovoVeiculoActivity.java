package com.how_vi.autoescola;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class NovoVeiculoActivity extends AppCompatActivity {

    // criando variaveis para campos e botao
    private EditText deMarcaVeiculoEdt;
    private EditText deModeloVeiculoEdt;
    private EditText nuPlacaVeiculoEdt;
    private Button veiculoBtn;

    // criando constantes para as variaveis
    public static final String EXTRA_COD_VEICULO = "com.gtappdevelopers.gfroomdatabase.EXTRA_COD_VEICULO";
    public static final String EXTRA_MARCA_VEICULO = "com.gtappdevelopers.gfroomdatabase.EXTRA_MARCA_VEICULO";
    public static final String EXTRA_MODELO_VEICULO = "com.gtappdevelopers.gfroomdatabase.EXTRA_MODELO_VEICULO";
    public static final String EXTRA_PLACA_VEICULO = "com.gtappdevelopers.gfroomdatabase.EXTRA_PLACA_VEICULO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_veiculo);

        //inicializando variaveis para cada campo (view)
        deMarcaVeiculoEdt = findViewById(R.id.idEdtdeMarcaVeiculo);
        deModeloVeiculoEdt = findViewById(R.id.idEdtdeModeloVeiculo);
        nuPlacaVeiculoEdt = findViewById(R.id.idEdtnuPlacaVeiculo);
        veiculoBtn = findViewById(R.id.idBtnSalvarVeiculo);

        // intent para receber dados via intent
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_COD_VEICULO)) {
            //se receber o veiculo, seta os demais atributos
            deMarcaVeiculoEdt.setText(intent.getStringExtra(EXTRA_MARCA_VEICULO));
            deModeloVeiculoEdt.setText(intent.getStringExtra(EXTRA_MODELO_VEICULO));
            nuPlacaVeiculoEdt.setText(intent.getStringExtra(EXTRA_PLACA_VEICULO));
        }
        veiculoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deMarcaVeiculo = deMarcaVeiculoEdt.getText().toString();
                String deModeloVeiculo = deModeloVeiculoEdt.getText().toString();
                String nuPlacaVeiculo = nuPlacaVeiculoEdt.getText().toString();

                if (deMarcaVeiculo.isEmpty() || deModeloVeiculo.isEmpty() || nuPlacaVeiculo.isEmpty()) {
                    Toast.makeText(NovoVeiculoActivity.this, "Preencha os dados do Veiculo", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Salva os dados preenchidos
                salvarVeiculo(deMarcaVeiculo, deModeloVeiculo, nuPlacaVeiculo);
                finish();
            }
        });

    }
    private void salvarVeiculo(String deMarcaVeiculo, String deModeloVeiculo, String nuPlacaVeiculo){
        // passando os dados via intent
        Intent dados = new Intent();
        // detalhes do veiculo
        dados.putExtra(EXTRA_MARCA_VEICULO, deMarcaVeiculo);
        dados.putExtra(EXTRA_MODELO_VEICULO, deModeloVeiculo);
        dados.putExtra(EXTRA_PLACA_VEICULO, nuPlacaVeiculo);
        int co_veiculo = getIntent().getIntExtra(EXTRA_COD_VEICULO, -1);
        if(co_veiculo != -1){
            dados.putExtra(EXTRA_COD_VEICULO, co_veiculo);
        }

        setResult(RESULT_OK, dados);

        // retorna mensagem de confirmacao
        Toast.makeText(this, "Veiculo salvo", Toast.LENGTH_SHORT).show();
    }

}