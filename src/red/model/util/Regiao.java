/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.util;

/**
 *
 * @author Daniel
 */
public class Regiao {
    
    
     
    
    
    private int codigo;
    private String nome;
    private Cidade cidade;

    public Regiao(int codigo, String nome, Cidade cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return nome ;
    }
    
    
    @Override
    public boolean equals(Object ob){
        
        
        return ob instanceof Regiao && this.codigo==((Regiao)ob).getCodigo();
    }
    
    
   
    
   
    
}
