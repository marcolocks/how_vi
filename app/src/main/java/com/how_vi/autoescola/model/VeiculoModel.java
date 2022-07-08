package com.how_vi.autoescola.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbVeiculo")
public class VeiculoModel {

    @PrimaryKey(autoGenerate = true)
    private int coVeiculo;
    private String deMarca;
    private String deModelo;
    private String nuPlaca;

    public VeiculoModel(String deMarca,String deModelo,String nuPlaca){
        this.deMarca = deMarca;
        this.deModelo = deModelo;
        this.nuPlaca = nuPlaca;
    }

    public int getCoVeiculo() {
        return coVeiculo;
    }

    public void setCoVeiculo(int coVeiculo) {
        this.coVeiculo = coVeiculo;
    }

    public String getDeMarca() {
        return deMarca;
    }

    public void setDeMarca(String deMarca) {
        this.deMarca = deMarca;
    }

    public String getDeModelo() {
        return deModelo;
    }

    public void setDeModelo(String deModelo) {
        this.deModelo = deModelo;
    }

    public String getNuPlaca() {
        return nuPlaca;
    }

    public void setNuPlaca(String nuPlaca) {
        this.nuPlaca = nuPlaca;
    }
}
