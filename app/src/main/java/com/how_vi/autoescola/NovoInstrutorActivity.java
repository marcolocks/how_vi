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

public class NovoInstrutorActivity extends AppCompatActivity {

    // criando variaveis para campos e botao
    private EditText nuCPFEdt;
    private EditText noInstrutorEdt;
    //private Button dtNascimentoDtp;
    private Button instrutorBtn;

    // criando constantes para as variaveis
    public static final String EXTRA_COD_INSTRUTOR = "com.gtappdevelopers.gfroomdatabase.EXTRA_COD_INSTRUTOR";
    public static final String EXTRA_CPF_INSTRUTOR = "com.gtappdevelopers.gfroomdatabase.EXTRA_CPF_INSTRUTOR";
    public static final String EXTRA_NOME_INSTRUTOR = "com.gtappdevelopers.gfroomdatabase.EXTRA_NOME_INSTRUTOR";
    //public static final String EXTRA_DT_NASC_INSTRUTOR = "com.gtappdevelopers.gfroomdatabase.EXTRA_DT_NASC_INSTRUTOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_instrutor);

        nuCPFEdt = findViewById(R.id.idEdtnuCPFInstrutor);
        noInstrutorEdt = findViewById(R.id.idEdtnoInstrutor);
        instrutorBtn = findViewById(R.id.idBtnSalvarInstrutor);

        // intent para receber valores via intent
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_COD_INSTRUTOR)) {
            //se receber o instrutor, seta os demais atributos
            nuCPFEdt.setText(intent.getStringExtra(EXTRA_CPF_INSTRUTOR));
            noInstrutorEdt.setText(intent.getStringExtra(EXTRA_NOME_INSTRUTOR));
        }
        instrutorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuCPFInstrutor = nuCPFEdt.getText().toString();
                String noInstrutor = noInstrutorEdt.getText().toString();
                // todo: adicionar data de nascimento
                if (nuCPFInstrutor.isEmpty() || noInstrutor.isEmpty()) {
                    Toast.makeText(NovoInstrutorActivity.this, "Preencha os dados do Instrutor", Toast.LENGTH_SHORT).show();
                    return;
                }

                // todo: adicionar data de nascimento
                salvarInstrutor(nuCPFInstrutor, noInstrutor);
                finish();
            }
        });

    }
    // todo: adicionar data de nascimento
    private void salvarInstrutor(String nuCPFInstrutor, String noInstrutor){
        // passando os dados via intent
        Intent dados = new Intent();
        // detalhes do curso
        dados.putExtra(EXTRA_CPF_INSTRUTOR, nuCPFInstrutor);
        dados.putExtra(EXTRA_NOME_INSTRUTOR, noInstrutor);
        int co_instrutor = getIntent().getIntExtra(EXTRA_COD_INSTRUTOR, -1);
        if(co_instrutor != -1){
            dados.putExtra(EXTRA_COD_INSTRUTOR, co_instrutor);
        }

        setResult(RESULT_OK, dados);
        Toast.makeText(this, "Instrutor salvo", Toast.LENGTH_SHORT).show();
    }

/*
        // inicializando variaveis
        nuCPFEdt = findViewById(R.id.idEdtNuCPF);
        noInstrutorEdt = findViewById(R.id.idEdtNoInstrutor);
        dtNascimentoDtp = findViewById(R.id.idDtpDtNascimento);
        dtNascimentoDtp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                com.how_vi.autoescola.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.how_vi.autoescola.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(),"DATE PICK");
            }
        });
        instrutorBtn = findViewById(R.id.idBtnSalvarInstrutor);

        // intent para receber valores via intent
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_COD_INSTRUTOR)){
            //se receber o instrutor, seta os demais atributos
            nuCPFEdt.setText(intent.getStringExtra(EXTRA_CPF_INSTRUTOR));
            noInstrutorEdt.setText(intent.getStringExtra(EXTRA_NOME_INSTRUTOR));
        }

 */



}