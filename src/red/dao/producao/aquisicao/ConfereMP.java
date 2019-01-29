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
public class ConfereMP 
{
    private int numero;
    private Colaborador c;
    private Entrada e; // recebimento
    private LocalDate data;
    
    private int lote;

    public ConfereMP(int numero, LocalDate data, int lote) {
        this.numero = numero;
        this.data = data;
        this.lote = lote;
    }
    

    public ConfereMP(int numero, Colaborador c, Entrada e, LocalDate data) {
        this.numero = numero;
        this.c = c;
        this.e = e;
        this.data = data;
    }
    
    public ConfereMP(int numero) {
        this.numero = numero;
        this.c = null;
        this.e = null;
        this.data = null;
    }

    public ConfereMP() {
        this.numero = 0;
        this.c = null;
        this.e = null;
        this.data = null;  
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Colaborador getC() {
        return c;
    }

    public void setC(Colaborador c) {
        this.c = c;
    }

    public Entrada getE() {
        return e;
    }

    public void setE(Entrada e) {
        this.e = e;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }
    
    //DAO Banco-----------------------------------------------------------------
    public boolean gravar(ConfereMP c)
    {
        String sql = "insert into confere_mp (col_codigo, ent_num, conf_data) values (?,?,'"+c.getData()+"');";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, c.getC().getCodigo());
                    ps.setInt(2, c.getE().getNumero());
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
    
    public boolean alterar(ConfereMP c)
    {
        String sql = "update confere_mp set col_codigo = ?, ent_num = ?, conf_data = '"+c.getData()+"' where conf_num = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, c.getC().getCodigo());
                    ps.setInt(2, c.getE().getNumero());
                    ps.setInt(3, c.getNumero());
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
        String sql = "delete from confere_mp where conf_num = ?;";
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
    
    public ConfereMP リカバー(int numero)
    {
        String sql = "select * "
                   + "from confere_mp "
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
                        {                   //int numero, Colaborador c, Entrada e, LocalDate data
                            return new ConfereMP(rs.getInt(1), new Colaborador(rs.getInt(2)), new Entrada(rs.getInt(3)), rs.getDate(4).toLocalDate());
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
        
    
    public Conferencia buscar(int numero)
    {
        String sql = "select * "
                   + "from confere_mp "
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
                            return new Conferencia(rs.getInt(1), new ConfereMP(rs.getInt(2)), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), new MateriaPrima(rs.getInt(7)));
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
    
    public int turningNumber()
    {
        String sql = "select max(conf_num) "
                   + "from confere_mp;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    try(ResultSet rs = ps.executeQuery())
                    {
                        if(rs.next())
                        {
                            return rs.getInt(1);
                        }
                    }    
                } 
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public ArrayList<ConfereMP> buscar(MateriaPrima mp, int lote)
    {
        ArrayList<ConfereMP> lista = new ArrayList<>();
        String sql = "select c.conf_num, c.co_lote, cmp.conf_data "
                   + "from confere_mp cmp, conferencia c "
                   + "where c.conf_num = cmp.conf_num and c.mp_codigo = ? and c.co_lote = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, mp.getCodigo());
                    ps.setInt(2, lote);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new ConfereMP(rs.getInt(1), rs.getDate(3).toLocalDate(), rs.getInt(2)));
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
        
    public ArrayList<ConfereMP> buscar(MateriaPrima mp)
    {
        ArrayList<ConfereMP> lista = new ArrayList<>();
        String sql = "select c.conf_num, c.co_lote, cmp.conf_data "
                   + "from confere_mp cmp, conferencia c "
                   + "where c.conf_num = cmp.conf_num and c.mp_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, mp.getCodigo());
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new ConfereMP(rs.getInt(1), rs.getDate(3).toLocalDate(), rs.getInt(2)));
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
