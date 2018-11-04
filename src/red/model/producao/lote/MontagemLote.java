/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.lote;

import java.sql.Timestamp;
import red.model.colaborador.Colaborador;

/**
 *
 * @author Daniel
 */
public class MontagemLote {
    
    private int numero;
    private Produto produto;
    //private int qtde_produto;
    private Colaborador colaborador;
    private Timestamp data_inicio; 
    private Timestamp data_fim;;
    private int estoque;

    public MontagemLote(int numero, Produto produto  /*,int qtde_produto,*/  ,Colaborador colaborador, Timestamp data_inicio, Timestamp data_fim, int estoque) {
        this.numero = numero;
        this.produto = produto;
    //    this.qtde_produto=qtde_produto;
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

    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Timestamp getData_fim() {
        return data_fim;
    }

    public void setData_fim(Timestamp data_fim) {
        this.data_fim = data_fim;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

//    public int getQtde_produto() {
//        return qtde_produto;
//    }
//
//    public void setQtde_produto(int qtde_produto) {
//        this.qtde_produto = qtde_produto;
//    }
    
    @Override
    public String toString() {
        return  "Lote: " + numero ;
    }
    

    
    
    
}
