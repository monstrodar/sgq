
package red.model.producao.aquisicao;

import java.time.LocalDate;
import red.model.producao.lote.Produto;

/**
 *
 * @author Daniel
 */
public class EstoqueMP {
    
    private String lote_mp;
    private MateriaPrima materia_prima;
    private int qtde_rec;
    private int qtde_aprovada;
    private int qtde_conferir;
    private int qtde_descarte;
    private int qtde_montada;
    private LocalDate validade;
    private LocalDate data_movimentacao;
    private Entrada recebimento_mp;

    public EstoqueMP(String lote_mp, MateriaPrima materia_prima, int qtde_rec, 
            int qtde_aprovada, int qtde_conferir, int qtde_descarte, int qtde_montada, 
            LocalDate validade, LocalDate data_movimentacao, 
            Entrada recebimento_mp) {
        this.lote_mp = lote_mp;
        this.materia_prima = materia_prima;
        this.qtde_rec = qtde_rec;
        this.qtde_aprovada = qtde_aprovada;
        this.qtde_conferir = qtde_conferir;
        this.qtde_descarte = qtde_descarte;
        this.qtde_montada = qtde_montada;
        this.validade = validade;
        this.data_movimentacao = data_movimentacao;
        this.recebimento_mp = recebimento_mp;
    }

    public EstoqueMP() {
    }

    public String getLote_mp() {
        return lote_mp;
    }

    public void setLote_mp(String lote_mp) {
        this.lote_mp = lote_mp;
    }

    public MateriaPrima getMateria_prima() {
        return materia_prima;
    }

    public void setMateria_prima(MateriaPrima materia_prima) {
        this.materia_prima = materia_prima;
    }

    public int getQtde_rec() {
        return qtde_rec;
    }

    public void setQtde_rec(int qtde_rec) {
        this.qtde_rec = qtde_rec;
    }

    public int getQtde_aprovada() {
        return qtde_aprovada;
    }

    public void setQtde_aprovada(int qtde_aprovada) {
        this.qtde_aprovada = qtde_aprovada;
    }

    public int getQtde_conferir() {
        return qtde_conferir;
    }

    public void setQtde_conferir(int qtde_conferir) {
        this.qtde_conferir = qtde_conferir;
    }

    public int getQtde_descarte() {
        return qtde_descarte;
    }

    public void setQtde_descarte(int qtde_descarte) {
        this.qtde_descarte = qtde_descarte;
    }

    public int getQtde_montada() {
        return qtde_montada;
    }

    public void setQtde_montada(int qtde_montada) {
        this.qtde_montada = qtde_montada;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public LocalDate getData_movimentacao() {
        return data_movimentacao;
    }

    public void setData_movimentacao(LocalDate data_movimentacao) {
        this.data_movimentacao = data_movimentacao;
    }

    public Entrada getRecebimento_mp() {
        return recebimento_mp;
    }

    public void setRecebimento_mp(Entrada recebimento_mp) {
        this.recebimento_mp = recebimento_mp;
    }

    @Override
    public String toString() {
        return lote_mp ;
    }

    
 
    
    @Override
    public boolean equals(Object ob){
        
        
        return ob instanceof EstoqueMP && this.lote_mp==((EstoqueMP)ob).getLote_mp();
    }
    
    
}
