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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.conexcao.Conexao;
import red.model.colaborador.Colaborador;
import java.util.Date;

/**
 *
 * @author Bruno Yoshino
 */
public class PedidoMP {
    private int numero;
    private Fornecedor forn;
    private Colaborador col;
    private LocalDate dataPedido;
    private LocalDate dataPrevisao;

    public PedidoMP(int numero, Fornecedor forn, Colaborador col, LocalDate dataPedido, LocalDate dataPrevisao) {
        this.numero = numero;
        this.forn = forn;
        this.col = col;
        this.dataPedido = dataPedido;
        this.dataPrevisao = dataPrevisao;
    }

    public PedidoMP() {
        this.numero = 0;
        this.forn = null;
        this.col = null;
        this.dataPedido = null;
        this.dataPrevisao = null;
    }

    public PedidoMP(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Fornecedor getForn() {
        return forn;
    }

    public void setForn(Fornecedor forn) {
        this.forn = forn;
    }

    public Colaborador getCol() {
        return col;
    }

    public void setCol(Colaborador col) {
        this.col = col;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDate getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(LocalDate dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }
    
    //DAO -------------------------------------------------------------------------------------------------------------
    
    public boolean gravar(PedidoMP pmp)
    {
        String sql = "insert into pedidos_mp (for_codigo, col_codigo, pmp_data, pmp_data_prev) values (?,?,'"+pmp.getDataPedido()+"','"+pmp.getDataPrevisao()+"');";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, pmp.getForn().getCodigo());
                    ps.setInt(2, pmp.getCol().getCodigo());
//                    ps.setDate(4, (java.sql.Date) Date.from(pmp.getDataPrevisao().atStartOfDay(ZoneId.systemDefault()).toInstant()));
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
    
    public boolean alterar(PedidoMP pmp)
    {
        String sql = "update pedidos_mp set for_codigo = ?, col_codigo = ?, pmp_data = ?, pmp_data_prev = ? where pmp_numero = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, pmp.getForn().getCodigo());
                    ps.setInt(2, pmp.getCol().getCodigo());
                    ps.setDate(3, (java.sql.Date) Date.from(pmp.getDataPedido().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    ps.setDate(4, (java.sql.Date) Date.from(pmp.getDataPrevisao().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    ps.setInt(5, pmp.getNumero());
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
        String sql = "delete from pedidos_mp where pmp_numero = ?;";
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
    
    public ArrayList<PedidoMP> serch(LocalDate inicio, LocalDate fim, int numero)
    {
        ArrayList<PedidoMP> lista = new ArrayList<>();
        String sql = "select * "
                   + "from pedidos_mp "
                   + "where pmp_numero = ? or pmp_data between ? and ? "
                   + "order by pmp_numero";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, numero);
                    ps.setDate(2, (java.sql.Date) Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    ps.setDate(3, (java.sql.Date) Date.from(fim.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new PedidoMP(rs.getInt(1), new Fornecedor(rs.getInt(2)), new Colaborador(rs.getInt(3)), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate()));
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
        
    public ArrayList<PedidoMP> serch(LocalDate inicio, LocalDate fim)
    {
        ArrayList<PedidoMP> lista = new ArrayList<>();
        String sql = "select * "
                   + "from pedidos_mp "
                   + "where pmp_data between ? and ? "
                   + "order by pmp_numero";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setDate(1, (java.sql.Date) Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    ps.setDate(2, (java.sql.Date) Date.from(fim.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new PedidoMP(rs.getInt(1), new Fornecedor(rs.getInt(2)), new Colaborador(rs.getInt(3)), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate()));
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
    
    public ArrayList<PedidoMP> serch(int numero)
    {
        ArrayList<PedidoMP> lista = new ArrayList<>();
        String sql = "select * "
                   + "from pedidos_mp "
                   + "where pmp_nuemro = ? ";
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
                            lista.add(new PedidoMP(rs.getInt(1), new Fornecedor(rs.getInt(2)), new Colaborador(rs.getInt(3)), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate()));
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
    
    public ArrayList<PedidoMP> serch()
    {
        ArrayList<PedidoMP> lista = new ArrayList<>();
        String sql = "select * "
                   + "from pedidos_mp ";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new PedidoMP(rs.getInt(1), new Fornecedor(rs.getInt(2)), new Colaborador(rs.getInt(3)), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate()));
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
    
    public PedidoMP リカバー(int numero)
    {
        String sql = "select * "
                   + "from pedidos_mp "
                   + "where pmp_numero = ? ";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, numero);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        if(rs.next())
                        {
                            return new PedidoMP(rs.getInt(1), new Fornecedor(rs.getInt(2)), new Colaborador(rs.getInt(3)), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate());
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
    
    public boolean チェック(int numero)//EntradaMPにあるかどうか。
    {
        String sql = "select * "
           + "from entrada_mp "
           + "where pmp_numero = ? ";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, numero);
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
    
    public int Atualizar()
    {
        String sql = "select max(pmp_numero) "
                   + "from pedidos_mp ";
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
}
