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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.conexcao.Conexao;
import red.model.colaborador.Colaborador;

/**
 *
 * @author Bruno Yoshino
 */
public class ItensMP 
{
    private MateriaPrima mp;
    private PedidoMP p;
    private int qtd;
    public String nomeMP;

    public ItensMP(MateriaPrima mp, PedidoMP p, int qtd) {
        this.mp = mp;
        this.p = p;
        this.qtd = qtd;
    }

    public ItensMP() {
        this.mp = null;
        this.p = null;
        this.qtd = 0;
    }

    public MateriaPrima getMp() {
        return mp;
    }

    public void setMp(MateriaPrima mp) {
        this.mp = mp;
    }

    public PedidoMP getP() {
        return p;
    }

    public void setP(PedidoMP p) {
        this.p = p;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    @Override
    public String toString()
    {
        return mp.getNome()+"";
    }
    
    public void setName()
    {
        this.nomeMP = mp.getNome();
    }
    
    //DAO -----------------------------------------------------------------------------------------------------------
    
    public boolean gravar(ItensMP imp, PedidoMP pmp)
    {
        String sql = "insert into itens_mp (mp_codigo, pmp_numero, imp_qtde) values (?,?,?);";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, imp.getMp().getCodigo());
                    ps.setInt(2, pmp.getNumero());
                    ps.setInt(3, imp.getQtd());
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
    
    public boolean alterar(ItensMP imp, PedidoMP pmp)
    {
        String sql = "update itens_mp set (imp_qtde = ?) where pmp_numero = ? and mp_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, imp.getQtd());
                    ps.setInt(2, pmp.getNumero());
                    ps.setInt(3, imp.getMp().getCodigo());
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
    
    public boolean excluir(ItensMP imp, PedidoMP pmp)
    {
        String sql = "delete from itens_mp where pmp_numero = ? and mp_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, pmp.getNumero());
                    ps.setInt(2, imp.getMp().getCodigo());
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
    
    public boolean excluir(int codigo)
    {
        String sql = "delete from itens_mp where pmp_numero = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, codigo);
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
        
    public ArrayList<ItensMP> carregar(int numeroP)
    {
        ArrayList<ItensMP> lista = new ArrayList<>();
        String sql = "select * "
                   + "from itens_mp "
                   + "where pmp_numero = ? ;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, numeroP);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new ItensMP(new MateriaPrima(rs.getInt(1)), new PedidoMP(rs.getInt(2)), rs.getInt(3)));
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
    
    public boolean serchExist(ItensMP imp, PedidoMP pmp)
    {
      ArrayList<ItensMP> lista = new ArrayList<>();
        String sql = "select * "
                   + "from itens_mp "
                   + "where pmp_numero = ? and mp_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, pmp.getNumero());
                    ps.setInt(1, imp.getMp().getCodigo());
                    try(ResultSet rs = ps.executeQuery())
                    {
                        if(rs.next())
                        {
                            return true;
                        }
                    }    
                } 
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
