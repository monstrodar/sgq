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
 */
public class Conferencia 
{
    private int codigo;
    private ConfereMP cmp;
    private int qtd;
    private int lote;
    private int estoque;
    private int descarte;
    private String motivo;
    private MateriaPrima mp;

    public Conferencia(int codigo, ConfereMP cmp, int qtd, int lote, int estoque, int descarte, String motivo, MateriaPrima mp) {
        this.codigo = codigo;
        this.cmp = cmp;
        this.qtd = qtd;
        this.lote = lote;
        this.estoque = estoque;
        this.descarte = descarte;
        this.motivo = motivo;
        this.mp = mp;
    }
    
    public Conferencia(int codigo, ConfereMP cmp, int qtd, int lote, int estoque, int descarte, MateriaPrima mp) {
        this.codigo = codigo;
        this.cmp = cmp;
        this.qtd = qtd;
        this.lote = lote;
        this.estoque = estoque;
        this.descarte = descarte;
        this.motivo = "";
        this.mp = mp;
    }

    public Conferencia() {
        this.codigo = 0;
        this.cmp = null;
        this.qtd = 0;
        this.lote = 0;
        this.estoque = 0;
        this.descarte = 0;
        this.motivo = "";
        this.mp = null;
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

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
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
    
    
    
    
    
    //DAO Banco-----------------------------------------------------------------
    public boolean gravar(Conferencia c)
    {
        String sql = "insert into conferencia (conf_num, co_qtde, co_lote, co_qtd_descarte, mp_codigo, co_estoque) values (?,?,?,?,?,0);";
        //String sql = "insert into conferencia (conf_numero, co_qtde, co_lote, co_qtd_descarte, co_motivo, mp_codigo) values (?,?,?,?,?,?);";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, c.getCmp().getNumero());
                    ps.setInt(2, c.getQtd());
                    ps.setInt(3, c.getLote());
                    ps.setInt(4, c.getDescarte());
                    ps.setInt(5, c.getMp().getCodigo()); 
                    //ps.setInt(5, c.getMotivo());
                    //ps.setInt(6, c.getMp().getCodigo());
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
    
    public void Estoque()
    {
        
    }
    
    public boolean alterar(Conferencia c)
    {
        String sql = "update conferencia set conf_numero = ?, co_qtde = ?, co_lote = ?, co_qtd_descarte = ?, mp_codigo = ? where co_codigo = ?;";
        //String sql = "update conferencia set conf_numero = ?, co_qtde = ?, co_lote = ?, co_qtd_descarte = ?, co_motivo, mp_codigo = ? where co_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, c.getCmp().getNumero());
                    ps.setInt(2, c.getQtd());
                    ps.setInt(3, c.getLote());
                    ps.setInt(4, c.getDescarte());
                    ps.setInt(5, c.getMp().getCodigo()); 
                    ps.setInt(6, c.getCodigo()); 
                    //ps.setInt(5, c.getMotivo());
                    //ps.setInt(6, c.getMp().getCodigo());
                    //ps.setInt(7, c.getCodigo());
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
    
    public boolean excluir(int numero)
    {
        String sql = "delete from conferencia where conf_num = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, numero);
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
    
    public Conferencia buscar(int numero)
    {
        ArrayList<Conferencia> lista = new ArrayList<>();
        String sql = "select * "
                   + "from conferencia "
                   + "where conf_numero = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, numero);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            return new Conferencia(rs.getInt(1), new ConfereMP(rs.getInt(2)), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), new MateriaPrima(rs.getInt(7)));
                            //return new Conferencia(rs.getInt(1), new ConfereMP(rs.getInt(2)), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), new MateriaPrima(rs.getInt(8)));
                        }
                    }    
                } 
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   
}
