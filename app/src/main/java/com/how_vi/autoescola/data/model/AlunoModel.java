package com.how_vi.autoescola.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity(tableName = "tbAluno")
public class AlunoModel {

    @PrimaryKey(autoGenerate = true)
    private int coAluno;
    private String nuCPF;
    private String noAluno;
    private String dtNascAluno;
    private String nuTelefoneAluno;

    public AlunoModel(String nuCPF, String noAluno, String dtNascAluno, String nuTelefoneAluno){
        this.nuCPF = nuCPF;
        this.noAluno = noAluno;
        this.dtNascAluno = dtNascAluno;
        this.nuTelefoneAluno = nuTelefoneAluno;
    }

    public int getCoAluno() {
        return coAluno;
    }

    public void setCoAluno(int coAluno) {
        this.coAluno = coAluno;
    }

    public String getNuCPF() {
        return nuCPF;
    }

    public void setNuCPF(String nuCPF) {
        this.nuCPF = nuCPF;
    }

    public String getNoAluno() {
        return noAluno;
    }

    public void setNoAluno(String noAluno) {
        this.noAluno = noAluno;
    }

    public String getDtNascAluno() {
        return dtNascAluno;
    }

    public void setDtNascimento(String dtNascAluno) {
        this.dtNascAluno = dtNascAluno;

    }

    public String getNuTelefoneAluno() {
        return nuTelefoneAluno;
    }

    public void setNuTelefone(String nuTelefoneAluno) {
        this.nuTelefoneAluno = nuTelefoneAluno;
    }

}
