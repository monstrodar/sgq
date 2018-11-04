/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.lote;

import red.model.producao.aquisicao.MateriaPrima;

/**
 *
 * @author Daniel
 */
public class ItensSaida {
    
    private MateriaPrima materia_prima;
    private MontagemLote lote;
    private int qtde;

    public ItensSaida(MateriaPrima materia_prima, MontagemLote lote, int qtde) {
        this.materia_prima = materia_prima;
        this.lote = lote;
        this.qtde = qtde;
    }

    public MateriaPrima getMateria_prima() {
        return materia_prima;
    }

    public void setMateria_prima(MateriaPrima materia_prima) {
        this.materia_prima = materia_prima;
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
        return  materia_prima + " " + lote + " " + qtde ;
    }
    
    
    
}
