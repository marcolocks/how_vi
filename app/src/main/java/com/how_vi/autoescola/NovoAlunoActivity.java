package com.how_vi.autoescola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.how_vi.autoescola.utils.MaskEditUtil;

public class NovoAlunoActivity extends AppCompatActivity {

    // criando variaveis para campos e botao
    private EditText nuCPFEdt;
    private EditText noAlunoEdt;
    private EditText dtNascimentoEdt;
    private EditText nuTelefoneEdt;
    private Button alunoBtn;

    // criando constantes para as variaveis
    public static final String EXTRA_COD_ALUNO = "com.gtappdevelopers.gfroomdatabase.EXTRA_COD_ALUNO";
    public static final String EXTRA_CPF_ALUNO = "com.gtappdevelopers.gfroomdatabase.EXTRA_CPF_ALUNO";
    public static final String EXTRA_NOME_ALUNO = "com.gtappdevelopers.gfroomdatabase.EXTRA_NOME_ALUNO";
    public static final String EXTRA_DT_NASC_ALUNO = "com.gtappdevelopers.gfroomdatabase.EXTRA_DT_NASC_ALUNO";
    public static final String EXTRA_NU_TELEFONE_ALUNO = "com.gtappdevelopers.gfroomdatabase.EXTRA_NU_TELEFONE_ALUNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_aluno);

        //inicializando variaveis para cada campo (view)
        nuCPFEdt = findViewById(R.id.idEdtnuCPFAluno);
        noAlunoEdt = findViewById(R.id.idEdtnoAluno);
        dtNascimentoEdt = findViewById(R.id.idEdtDtNascAluno);
        nuTelefoneEdt = findViewById(R.id.idEdtnuTelAluno);
        alunoBtn = findViewById(R.id.idBtnSalvarAluno);

        // Aplicando mascara para os campos
        nuCPFEdt.addTextChangedListener(MaskEditUtil.mask(nuCPFEdt, MaskEditUtil.FORMAT_CPF));
        dtNascimentoEdt.addTextChangedListener(MaskEditUtil.mask(dtNascimentoEdt, MaskEditUtil.FORMAT_DATE));
        nuTelefoneEdt.addTextChangedListener(MaskEditUtil.mask(nuTelefoneEdt,MaskEditUtil.FORMAT_FONE));


        // intent para receber valores via intent
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_COD_ALUNO)) {
            //se receber o aluno, seta os demais atributos
            nuCPFEdt.setText(intent.getStringExtra(EXTRA_CPF_ALUNO));
            noAlunoEdt.setText(intent.getStringExtra(EXTRA_NOME_ALUNO));
            dtNascimentoEdt.setText(intent.getStringExtra(EXTRA_DT_NASC_ALUNO));
            nuTelefoneEdt.setText(intent.getStringExtra(EXTRA_NU_TELEFONE_ALUNO));
        }
        alunoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuCPFAluno = nuCPFEdt.getText().toString();
                String noAluno = noAlunoEdt.getText().toString();
                String dtNascimento = dtNascimentoEdt.getText().toString();
                String nuTelefone = nuTelefoneEdt.getText().toString();
                if (nuCPFAluno.isEmpty() || noAluno.isEmpty() || dtNascimento.isEmpty() || nuTelefone.isEmpty()) {
                    Toast.makeText(NovoAlunoActivity.this, "Preencha os dados do Aluno", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Salva os dados preenchidos
                salvarAluno(nuCPFAluno, noAluno, dtNascimento, nuTelefone);
                finish();
            }
        });

    }

    //metodo para salvar os dados recebidos da activity
    private void salvarAluno(String nuCPFAluno, String noAluno, String dtNascimento, String nuTelefone) {
        // passando os dados via intent
        Intent dados = new Intent();
        // detalhes do aluno
        dados.putExtra(EXTRA_CPF_ALUNO, nuCPFAluno);
        dados.putExtra(EXTRA_NOME_ALUNO, noAluno);
        dados.putExtra(EXTRA_DT_NASC_ALUNO, dtNascimento);
        dados.putExtra(EXTRA_NU_TELEFONE_ALUNO, nuTelefone);
        int co_aluno = getIntent().getIntExtra(EXTRA_COD_ALUNO, -1);
        if (co_aluno != -1) {
            dados.putExtra(EXTRA_COD_ALUNO, co_aluno);
        }
        setResult(RESULT_OK, dados);

        // retorna mensagem de confirmacao
        Toast.makeText(this, "Aluno salvo", Toast.LENGTH_SHORT).show();
    }

}