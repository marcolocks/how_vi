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
//    private Date dtNascimento;

    //public InstrutorModel(String nuCPF,String noInstrutor, Date dtNascimento){
    public InstrutorModel(String nuCPF,String noInstrutor){
        this.nuCPF = nuCPF;
        this.noInstrutor = noInstrutor;
      //  this.dtNascimento = dtNascimento;
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

   /* public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    } */
}
