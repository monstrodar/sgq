/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.dao.producao.aquisicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.conexcao.Conexao;
import red.model.colaborador.Colaborador;
import red.model.producao.aquisicao.Entrada;
import red.model.producao.aquisicao.MateriaPrima;

/**
 *
 * @author Bruno Yoshino
 * @author 羽根川
 */
public class Conferencia 
{
    private int codigo;
    private ConfereMP cmp;
    private int qtd;
    private String lote;
    private int aprovado;
    private int descarte;
    private String motivo;
    private MateriaPrima mp;
    private int status;
    private LocalDate validade;

    public Conferencia(int codigo, ConfereMP cmp, int qtd, String lote, int aprovado, int descarte, String motivo, MateriaPrima mp, int status, LocalDate validade) {
        this.codigo = codigo;
        this.cmp = cmp;
        this.qtd = qtd;
        this.lote = lote;
        this.aprovado = aprovado;
        this.descarte = descarte;
        this.motivo = motivo;
        this.mp = mp;
        this.status = status;
        this.validade = validade;
    }
    
    public Conferencia(int codigo, ConfereMP cmp, int qtd, String lote, int aprovado, int descarte, MateriaPrima mp) {
        this.codigo = codigo;
        this.cmp = cmp;
        this.qtd = qtd;
        this.lote = lote;
        this.aprovado = aprovado;
        this.descarte = descarte;
        this.motivo = "";
        this.mp = mp;
        this.status = 0;
        this.validade = LocalDate.now();
    }

    public Conferencia() {
        this.codigo = 0;
        this.cmp = null;
        this.qtd = 0;
        this.lote = "";
        this.aprovado = 0;
        this.descarte = 0;
        this.motivo = "";
        this.mp = null;
        this.status = 0;
        this.validade = LocalDate.now();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ConfereMP getCmp() {
        return cmp;
    }

    public void setCmp(ConfereMP cmp) {
        this.cmp = cmp;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getAprovado() {
        return aprovado;
    }

    public void setAprovado(int aprovado) {
        this.aprovado = aprovado;
    }

    public int getDescarte() {
        return descarte;
    }

    public void setDescarte(int descarte) {
        this.descarte = descarte;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public MateriaPrima getMp() {
        return mp;
    }

    public void setMp(MateriaPrima mp) {
        this.mp = mp;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }
    
    
    //DAO Banco-----------------------------------------------------------------
    public boolean gravar(Conferencia c)
    {
        String sql = "insert into itens_conferencia "
                + " (conf_num, rec_numero, mp_codigo, it_conf_qtde_entrada, it_conf_qtde_aprovada, it_conf_qtde_descarte, it_conf_motivo, it_conf_status, it_conf_lote_mp, it_conf_validade) values (?,?,?,?,?,?,?,?,?,'"+c.getValidade()+"');";
        //String sql = "insert into itens_conferencia (conf_numero, conf_qtde, conf_lote, conf_qtd_descarte, conf_motivo, mp_codigo) values (?,?,?,?,?,?);";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {//1 ~ 10
                    ps.setInt(1, c.getCmp().getNumero());
                    ps.setInt(2, c.getCmp().getE().getNumero());
                    ps.setInt(3, c.getMp().getCodigo());
                    ps.setInt(4, c.getQtd());
                    ps.setInt(5, c.getAprovado());
                    ps.setInt(6, c.getDescarte());
                    ps.setString(7, c.getMotivo());
                    ps.setInt(8, c.getStatus());
                    ps.setString(9, c.getLote());
                    //ps.setInt(5, c.getMotivo());
                    //ps.setInt(6, c.getMp().getCodigo());
                    /*
                    
                    it_conf_qtde_entrada, it_conf_qtde_aprovada, 
                    it_conf_qtde_descarte, it_conf_motivo, it_conf_status, 
                    it_conf_lote_mp, it_conf_validade
                    */
                    ps.executeUpdate();
                    return true;
                } 
                
            }
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
       
}
