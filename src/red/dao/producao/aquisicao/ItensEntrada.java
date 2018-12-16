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
public class ItensEntrada {
    private Entrada ent;
    private MateriaPrima mp;
    private int qtd;

    public ItensEntrada(Entrada ent, MateriaPrima mp, int qtd) {
        this.ent = ent;
        this.mp = mp;
        this.qtd = qtd;
    }

    public ItensEntrada() {
        this.ent = null;
        this.mp = null;
        this.qtd = 0;
    }

    public Entrada getEnt() {
        return ent;
    }

    public void setEnt(Entrada ent) {
        this.ent = ent;
    }

    public MateriaPrima getMp() {
        return mp;
    }

    public void setMp(MateriaPrima mp) {
        this.mp = mp;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    //DAO Banco ----------------------------------------------------------------
    
    public boolean gravar(Conferencia c)
    {
        return false;
    }
    
    public boolean alterar(Conferencia c)
    {
        return false;
    }
    
    public boolean excluir(Conferencia c)
    {
        return false;
    }
    
    public ArrayList<ItensEntrada> ロード(int numero)
    {
        ArrayList<ItensEntrada> lista = new ArrayList<>();
//        String sql = "select ie.ent_num, ie.mp_codigo, ie.ie_qtde "
//                   + "from itens_entrada ie, confere_mp cmp, conferencia c "
//                   + "where ie.ent_num = ? and cmp.ent_num = ie.ent_num and c.conf_num = cmp.conf_num and c.mp_codigo != ie.mp_codigo;";
        String sql = "select ie.ent_num, ie.mp_codigo, ie.ie_qtde from itens_entrada ie where ie.ent_num = ?;";
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
                            lista.add(new ItensEntrada(new Entrada(rs.getInt(1)), new MateriaPrima(rs.getInt(2)), rs.getInt(3)));
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
    
    public ItensEntrada busca(int numero, int codigoMP)
    {
        
//        String sql = "select ie.ent_num, ie.mp_codigo, ie.ie_qtde "
//                   + "from itens_entrada ie, confere_mp cmp, conferencia c "
//                   + "where ie.ent_num = ? and cmp.ent_num = ie.ent_num and c.conf_num = cmp.conf_num and c.mp_codigo != ie.mp_codigo;";
        String sql = "select ie.ent_num, ie.mp_codigo, ie.ie_qtde from itens_entrada ie where ie.ent_num = ?;";
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
                            return (new ItensEntrada(new Entrada(rs.getInt(1)), new MateriaPrima(rs.getInt(2)), rs.getInt(3)));
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
    
    public ItensEntrada serch(int numero, int codigo)
    {
        String sql = "select ent_num, mp_codigo, ie_qtde "
                   + "from itens_entrada  "
                   + "where ent_num = ? and mp_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, numero);
                    ps.setInt(2, codigo);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            return new ItensEntrada(new Entrada(rs.getInt(1)), new MateriaPrima(rs.getInt(2)), rs.getInt(3));
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
