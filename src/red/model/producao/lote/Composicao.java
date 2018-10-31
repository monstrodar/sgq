/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.lote;

import java.util.List;
import red.model.producao.aquisicao.MateriaPrima;

/**
 *
 * @author Daniel
 */
public class Composicao {
    
    private MateriaPrima materia_prima;
    private Produto produto;
    private int qtde;
    

    public Composicao() {
    }

    public Composicao(MateriaPrima materia_prima, Produto produto, int qtde) {
        this.materia_prima = materia_prima;
        this.produto = produto;
        this.qtde = qtde;
    }

    public MateriaPrima getMateria_prima() {
        return materia_prima;
    }

    public void setMateria_prima(MateriaPrima materia_prima) {
        this.materia_prima = materia_prima;
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

    @Override
    public String toString() {
        return materia_prima.getNome();
    }
    
    
    
}
