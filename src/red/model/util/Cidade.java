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
public class Cidade {
    
    private int codigo;
    private String nome;
    private UF uf;

    public Cidade() {
    }

    public Cidade(int codigo, String nome, UF uf) {
        this.codigo = codigo;
        this.nome = nome;
        this.uf = uf;
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

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }
    
    
    
    
}
