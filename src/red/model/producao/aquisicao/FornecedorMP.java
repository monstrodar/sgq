/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.aquisicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.conexcao.Conexao;
import red.model.colaborador.Colaborador;


/**
 *
 * @author Bruno Yoshino
 */
public class FornecedorMP {
    private MateriaPrima mp;
    private Fornecedor forn;
    private boolean status;

    public FornecedorMP(MateriaPrima mp, Fornecedor forn, boolean status) {
        this.mp = mp;
        this.forn = forn;
        this.status = status;
    }

    public FornecedorMP() {
        this.mp = null;
        this.forn = null;
        this.status = false;
    }

    public MateriaPrima getMp() {
        return mp;
    }

    public void setMp(MateriaPrima mp) {
        this.mp = mp;
    }

    public Fornecedor getForn() {
        return forn;
    }

    public void setForn(Fornecedor forn) {
        this.forn = forn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    //DAO------------------------------------------------------------------------------------------------------
    public boolean gravar(FornecedorMP fmp)
    {
        String sql = "insert into mp_fornecedor (mp_codigo, for_codigo, mpf_status) values (?,?,?);";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, fmp.getMp().getCodigo());
                    ps.setInt(2, fmp.getForn().getCodigo());
                    ps.setBoolean(3, fmp.isStatus());
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
    
    public boolean alterar(FornecedorMP fmp)
    {
        String sql = "update mp_fornecedor set mpf_status = ? where mp_codigo = ? and for_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setBoolean(1, fmp.isStatus());
                    ps.setInt(2, fmp.getMp().getCodigo());
                    ps.setInt(3, fmp.getForn().getCodigo());
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
    
    public boolean excluir(FornecedorMP fmp)
    {
        String sql = "delete form mp_fornecedor where mp_codigo = ? and for_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, fmp.getMp().getCodigo());
                    ps.setInt(2, fmp.getForn().getCodigo());
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
        
    public ArrayList<FornecedorMP> carregar(int forn)
    {
        ArrayList<FornecedorMP> lista = new ArrayList<>();
        String sql = "select * "
                   + "from mp_fornecedor "
                   + "where for_codigo = ? ;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, forn);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new FornecedorMP(new MateriaPrima(rs.getInt(1)), new Fornecedor(rs.getInt(2)), rs.getBoolean(3)));
                        }
                    }    
                } 
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
