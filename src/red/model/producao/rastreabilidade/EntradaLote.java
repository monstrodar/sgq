/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.rastreabilidade;

import red.model.producao.lote.MontagemLote;

/**
 *
 * @author Daniel
 */
public class EntradaLote {
    
    private MontagemCarga carga;
    private MontagemLote lote;
    private int qtde;

    public EntradaLote(MontagemCarga carga, MontagemLote lote, int qtde) {
        this.carga = carga;
        this.lote = lote;
        this.qtde = qtde;
    }

    public MontagemCarga getCarga() {
        return carga;
    }

    public void setCarga(MontagemCarga carga) {
        this.carga = carga;
    }

    public MontagemLote getLote() {
        return lote;
    }

    public void setLote(MontagemLote lote) {
        this.lote = lote;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    @Override
    public String toString() {
        return "carga " + carga  ;
    }
    
    
    
    
    
    
}
