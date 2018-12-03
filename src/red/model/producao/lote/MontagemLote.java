/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.lote;

import java.sql.Timestamp;
import java.time.LocalDate;
import red.model.colaborador.Colaborador;

/**
 *
 * @author Daniel
 */
public class MontagemLote {
    
    private int numero;
    private Produto produto;
    private Colaborador colaborador;
    private LocalDate data_inicio; 
    private LocalDate data_fim;;
    private int estoque;

    public MontagemLote(int numero, Produto produto  ,Colaborador colaborador, LocalDate data_inicio, LocalDate data_fim, int estoque) {
        this.numero = numero;
        this.produto = produto;
        this.colaborador = colaborador;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.estoque = estoque;
    }
     public MontagemLote(int numero) {
        this.numero = numero;
        
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return  "Lote: " + numero ;
    }
    

    
    
    
}
