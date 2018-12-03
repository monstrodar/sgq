/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.aquisicao;

/**
 *
 * @author Daniel
 */
public class MateriaPrima {
    
    private int codigo;
    private String nome;
    private String num_lote;
    private int estoque;
    private boolean status;

    public MateriaPrima(int codigo, String nome, String num_lote, int estoque, boolean status) {
        this.codigo = codigo;
        this.nome = nome;
        this.num_lote = num_lote;
        this.estoque = estoque;
        this.status = status;
    }

    public MateriaPrima() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNum_lote() {
        return num_lote;
    }

    public void setNum_lote(String num_lote) {
        this.num_lote = num_lote;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nome ;
    }
    
    
    
    
}
