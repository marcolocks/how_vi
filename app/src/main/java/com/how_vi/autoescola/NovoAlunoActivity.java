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

public class NovoAlunoActivity extends AppCompatActivity {

    // criando variaveis para campos e botao
    private EditText nuCPFEdt;
    private EditText noAlunoEdt;
    //private Button dtNascimentoDtp;
    private Button alunoBtn;

    // criando constantes para as variaveis
    public static final String EXTRA_COD_ALUNO = "com.gtappdevelopers.gfroomdatabase.EXTRA_COD_ALUNO";
    public static final String EXTRA_CPF_ALUNO = "com.gtappdevelopers.gfroomdatabase.EXTRA_CPF_ALUNO";
    public static final String EXTRA_NOME_ALUNO = "com.gtappdevelopers.gfroomdatabase.EXTRA_NOME_ALUNO";
    //public static final String EXTRA_DT_NASC_ALUNO = "com.gtappdevelopers.gfroomdatabase.EXTRA_DT_NASC_ALUNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_aluno);

        nuCPFEdt = findViewById(R.id.idEdtnuCPFAluno);
        noAlunoEdt = findViewById(R.id.idEdtnoAluno);
        alunoBtn = findViewById(R.id.idBtnSalvarAluno);

        // intent para receber valores via intent
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_COD_ALUNO)) {
            //se receber o aluno, seta os demais atributos
            nuCPFEdt.setText(intent.getStringExtra(EXTRA_CPF_ALUNO));
            noAlunoEdt.setText(intent.getStringExtra(EXTRA_NOME_ALUNO));
        }
        alunoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuCPFAluno = nuCPFEdt.getText().toString();
                String noAluno = noAlunoEdt.getText().toString();
                // todo: adicionar data de nascimento
                if (nuCPFAluno.isEmpty() || noAluno.isEmpty()) {
                    Toast.makeText(NovoAlunoActivity.this, "Preencha os dados do Aluno", Toast.LENGTH_SHORT).show();
                    return;
                }

                // todo: adicionar data de nascimento
                salvarAluno(nuCPFAluno, noAluno);
                finish();
            }
        });

    }
    // todo: adicionar data de nascimento
    private void salvarAluno(String nuCPFAluno, String noAluno){
        // passando os dados via intent
        Intent dados = new Intent();
        // detalhes do curso
        dados.putExtra(EXTRA_CPF_ALUNO, nuCPFAluno);
        dados.putExtra(EXTRA_NOME_ALUNO, noAluno);
        int co_aluno = getIntent().getIntExtra(EXTRA_COD_ALUNO, -1);
        if(co_aluno != -1){
            dados.putExtra(EXTRA_COD_ALUNO, co_aluno);
        }

        setResult(RESULT_OK, dados);
        Toast.makeText(this, "Aluno salvo", Toast.LENGTH_SHORT).show();
    }

/*
        // inicializando variaveis
        nuCPFEdt = findViewById(R.id.idEdtNuCPF);
        noAlunoEdt = findViewById(R.id.idEdtNoAluno);
        dtNascimentoDtp = findViewById(R.id.idDtpDtNascimento);
        dtNascimentoDtp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                com.how_vi.autoescola.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.how_vi.autoescola.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(),"DATE PICK");
            }
        });
        alunoBtn = findViewById(R.id.idBtnSalvarAluno);

        // intent para receber valores via intent
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_COD_ALUNO)){
            //se receber o aluno, seta os demais atributos
            nuCPFEdt.setText(intent.getStringExtra(EXTRA_CPF_ALUNO));
            noAlunoEdt.setText(intent.getStringExtra(EXTRA_NOME_ALUNO));
        }

 */



}