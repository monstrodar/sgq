/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.lote;
import java.util.ArrayList;
import java.util.List;
import red.model.producao.lote.Composicao;
/**
 *
 * @author Daniel
 */
public class Produto {
    
    
    private int codigo;
    private String nome;
    private String descricao;


    private boolean status;

    public Produto(int codigo, String nome, String descricao, boolean status) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
 
    }
    public Produto(int codigo) {
        this.codigo = codigo;
        
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
    
    @Override
    public boolean equals(Object ob){
        
        
        return ob instanceof Produto && this.codigo==((Produto)ob).getCodigo();
    }
}
