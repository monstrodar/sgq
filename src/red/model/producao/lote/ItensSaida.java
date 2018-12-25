
package red.model.producao.lote;

import red.model.producao.aquisicao.EstoqueMP;
import red.model.producao.aquisicao.MateriaPrima;

/**
 *
 * @author Daniel
 */
public class ItensSaida { //itens Lote
    
    private EstoqueMP lt_materia_prima;
    private MontagemLote lote;
    private MateriaPrima mp;
    private int qtde;

    public EstoqueMP getLt_materia_prima() {
        return lt_materia_prima;
    }

    public void setLt_materia_prima(EstoqueMP lt_materia_prima) {
        this.lt_materia_prima = lt_materia_prima;
    }

    public MontagemLote getLote() {
        return lote;
    }

    public void setLote(MontagemLote lote) {
        this.lote = lote;
    }

    public MateriaPrima getMp() {
        return mp;
    }

    public void setMp(MateriaPrima mp) {
        this.mp = mp;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

   
//    
//    @Override
//    public String toString(){
//        return lt_materia_prima.getLote_mp();
//    }
//   

    public ItensSaida(EstoqueMP lt_materia_prima, MontagemLote lote, MateriaPrima mp, int qtde) {
        this.lt_materia_prima = lt_materia_prima;
        this.lote = lote;
        this.mp = mp;
        this.qtde = qtde;
    }
    
    
    
}
