package com.how_vi.autoescola;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tbAluno")
public class AlunoModel {

    @PrimaryKey(autoGenerate = true)
    private int coAluno;
    private String nuCPF;
    private String noAluno;
    //private Date dtNascimento;

    //public AlunoModel(String nuCPF, String noAluno, Date dtNascimento){
    public AlunoModel(String nuCPF, String noAluno){
        this.nuCPF = nuCPF;
        this.noAluno = noAluno;
        //this.dtNascimento = dtNascimento;
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
/*
    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
*/
}
