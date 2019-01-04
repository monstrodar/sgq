/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.lote;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author Daniel
 */
public class EstoqueProduto {
    
    private MontagemLote lote;
    private Produto produto;
    private int qtde;
    private LocalDate validade;
    private int vendida;
    private LocalDate data_mov;

    public EstoqueProduto(MontagemLote lote, Produto produto, int qtde, LocalDate validade, int vendida, LocalDate data_mov) {
        this.lote = lote;
        this.produto = produto;
        this.qtde = qtde;
        this.validade = validade;
        this.vendida = vendida;
        this.data_mov = data_mov;
    }

    public MontagemLote getLote() {
        return lote;
    }

    public void setLote(MontagemLote lote) {
        this.lote = lote;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public int getVendida() {
        return vendida;
    }

    public void setVendida(int vendida) {
        this.vendida = vendida;
    }

    public LocalDate getData_mov() {
        return data_mov;
    }

    public void setData_mov(LocalDate data_mov) {
        this.data_mov = data_mov;
    }

   
  
    
    
}
