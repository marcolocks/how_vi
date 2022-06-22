package com.how_vi.autoescola;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tbInstrutor")
public class InstrutorModel {

    @PrimaryKey(autoGenerate = true)
    private int coInstrutor;
    private String nuCPF;
    private String noInstrutor;
    private String dtNascimento;
    private String nuTelefone;

    public InstrutorModel(String nuCPF,String noInstrutor, String dtNascimento, String nuTelefone){

        this.nuCPF = nuCPF;
        this.noInstrutor = noInstrutor;
        this.dtNascimento = dtNascimento;
        this.nuTelefone = nuTelefone;
    }

    public int getCoInstrutor() {
        return coInstrutor;
    }

    public void setCoInstrutor(int coInstrutor) {
        this.coInstrutor = coInstrutor;
    }

    public String getNuCPF() {
        return nuCPF;
    }

    public void setNuCPF(String nuCPF) {
        this.nuCPF = nuCPF;
    }

    public String getNoInstrutor() {
        return noInstrutor;
    }

    public void setNoInstrutor(String noInstrutor) {
        this.noInstrutor = noInstrutor;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNuTelefone() {
        return nuTelefone;
    }

    public void setNuTelefone(String nuTelefone) {
        this.nuTelefone = nuTelefone;
    }
}
