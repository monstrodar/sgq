/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.rastreabilidade;

import java.sql.Timestamp;
import java.time.LocalDate;
import red.model.colaborador.Colaborador;
import red.model.util.Regiao;

/**
 *
 * @author Daniel
 */
public class MontagemCarga {
    
    private int codigo;
    private Regiao regiao; //subistitui no lugar de regiao por estdo
    private Colaborador colaborador;
    private LocalDate data_inicio; 
    private LocalDate data_fim;
    private boolean status;

    public MontagemCarga(int codigo, Regiao regiao, Colaborador colaborador, LocalDate data_inicio, LocalDate data_fim, boolean status) {
        this.codigo = codigo;
        this.regiao = regiao;
        this.colaborador = colaborador;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.status = status;
    }
     public MontagemCarga(int numero) {
        this.codigo = numero;
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Código:  " + codigo ;
    }
    
    
    
    
}
