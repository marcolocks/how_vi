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

import com.how_vi.autoescola.utils.MaskEditUtil;

import java.util.Date;

public class NovoInstrutorActivity extends AppCompatActivity {

    // criando variaveis para campos e botao
    private EditText nuCPFEdt;
    private EditText noInstrutorEdt;
    private EditText dtNascimentoEdt;
    private EditText nuTelefoneEdt;
    private Button instrutorBtn;

    // criando constantes para as variaveis
    public static final String EXTRA_COD_INSTRUTOR = "com.gtappdevelopers.gfroomdatabase.EXTRA_COD_INSTRUTOR";
    public static final String EXTRA_CPF_INSTRUTOR = "com.gtappdevelopers.gfroomdatabase.EXTRA_CPF_INSTRUTOR";
    public static final String EXTRA_NOME_INSTRUTOR = "com.gtappdevelopers.gfroomdatabase.EXTRA_NOME_INSTRUTOR";
    public static final String EXTRA_DT_NASC_INSTRUTOR = "com.gtappdevelopers.gfroomdatabase.EXTRA_DT_NASC_INSTRUTOR";
    public static final String EXTRA_NU_TELEFONE_INSTRUTOR = "com.gtappdevelopers.gfroomdatabase.EXTRA_NU_TELEFONE_INSTRUTOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_instrutor);

        //inicializando variaveis para cada campo (view)
        nuCPFEdt = findViewById(R.id.idEdtnuCPFInstrutor);
        noInstrutorEdt = findViewById(R.id.idEdtnoInstrutor);
        dtNascimentoEdt = findViewById(R.id.idEdtDtNascInstrutor);
        nuTelefoneEdt = findViewById(R.id.idEdtNuTelefoneInstrutor);
        instrutorBtn = findViewById(R.id.idBtnSalvarInstrutor);

        // Aplicando mascara para os campos
        nuCPFEdt.addTextChangedListener(MaskEditUtil.mask(nuCPFEdt, MaskEditUtil.FORMAT_CPF));
        dtNascimentoEdt.addTextChangedListener(MaskEditUtil.mask(dtNascimentoEdt, MaskEditUtil.FORMAT_DATE));
        nuTelefoneEdt.addTextChangedListener(MaskEditUtil.mask(nuTelefoneEdt, MaskEditUtil.FORMAT_FONE));


        // intent para receber dados via intent
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_COD_INSTRUTOR)) {
            //se receber o instrutor, seta os demais atributos
            nuCPFEdt.setText(intent.getStringExtra(EXTRA_CPF_INSTRUTOR));
            noInstrutorEdt.setText(intent.getStringExtra(EXTRA_NOME_INSTRUTOR));
            dtNascimentoEdt.setText(intent.getStringExtra(EXTRA_DT_NASC_INSTRUTOR));
            nuTelefoneEdt.setText(intent.getStringExtra(EXTRA_NU_TELEFONE_INSTRUTOR));
        }
        instrutorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuCPFInstrutor = nuCPFEdt.getText().toString();
                String noInstrutor = noInstrutorEdt.getText().toString();
                String dtNascInstrutor = dtNascimentoEdt.getText().toString();
                String nuTelefoneInstrutor = nuTelefoneEdt.getText().toString();
                if (nuCPFInstrutor.isEmpty() || noInstrutor.isEmpty()) {
                    Toast.makeText(NovoInstrutorActivity.this, "Preencha os dados do Instrutor", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Salva os dados preenchidos
                salvarInstrutor(nuCPFInstrutor, noInstrutor, dtNascInstrutor, nuTelefoneInstrutor);
                finish();
            }
        });

    }

    private void salvarInstrutor(String nuCPFInstrutor, String noInstrutor, String dtNascInstrutor, String nuTelefoneInstrutor){
        // passando os dados via intent
        Intent dados = new Intent();
        // detalhes do instrutor
        dados.putExtra(EXTRA_CPF_INSTRUTOR, nuCPFInstrutor);
        dados.putExtra(EXTRA_NOME_INSTRUTOR, noInstrutor);
        dados.putExtra(EXTRA_DT_NASC_INSTRUTOR, dtNascInstrutor);
        dados.putExtra(EXTRA_NU_TELEFONE_INSTRUTOR, nuTelefoneInstrutor);
        int co_instrutor = getIntent().getIntExtra(EXTRA_COD_INSTRUTOR, -1);
        if(co_instrutor != -1){
            dados.putExtra(EXTRA_COD_INSTRUTOR, co_instrutor);
        }
        setResult(RESULT_OK, dados);

        // retorna mensagem de confirmacao
        Toast.makeText(this, "Instrutor salvo", Toast.LENGTH_SHORT).show();
    }

}