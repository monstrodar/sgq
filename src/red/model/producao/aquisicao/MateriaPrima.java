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
import red.model.producao.lote.Produto;


/**
 *
 * @author Daniel
 */
public class MateriaPrima {
    
    private int codigo;
    private String nome;
    private boolean status;

    public MateriaPrima(int codigo, String nome, boolean status) {
        this.codigo = codigo;
        this.nome = nome;
        this.status = status;
    }

    public MateriaPrima() {
    }
    public MateriaPrima(int codigo) {
        this.codigo = codigo;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nome ;
    }
    
    
     @Override
    public boolean equals(Object ob){
        
        
        return ob instanceof MateriaPrima && this.codigo==((MateriaPrima)ob).getCodigo();
    }
        //DAO------------------------------------------------------------------------------------------------------------
    
    public boolean gravar(MateriaPrima mp)
    {
        String sql = "insert into mat_prima (mp_nome, mp_status) values (?,?);";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, mp.getNome());
                    ps.setBoolean(2, mp.isStatus());
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
    
    public boolean alterar(MateriaPrima mp)
    {
        String sql = "update mat_prima set mp_nome = ?, mp_status = ? where mp_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, mp.getNome());
                    ps.setBoolean(2, mp.isStatus());
                    ps.setInt(3, mp.getCodigo());
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
        String sql = "delete from mat_prima where mp_codigo = ?;";
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
        
    public ArrayList<MateriaPrima> serch(String nome)
    {
        ArrayList<MateriaPrima> lista = new ArrayList<>();
        String sql = "select * "
                   + "from mat_prima "
                   + "where mp_nome = ? and mp_status = true";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, nome);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new MateriaPrima(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
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
    
    public ArrayList<MateriaPrima> serch(int cod_for)
    {
        ArrayList<MateriaPrima> lista = new ArrayList<>();
        String sql = "select mp.mp_codigo, mp.mp_nome, mp.mp_status "
                   + "from mat_prima mp, mp_fornecedor mpf "
                   + "where mp.mp_codigo = mpf.mp_codigo and mpf.for_codigo = ? and mp.mp_status = true;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, cod_for);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new MateriaPrima(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
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
    
    public ArrayList<MateriaPrima> Busca(String nome)
    {
        ArrayList<MateriaPrima> lista = new ArrayList<>();
        String sql = "select mp.mp_codigo, mp.mp_nome, mp.mp_status "
                   + "from mat_prima mp "
                   + "where mp.mp_nome = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, nome);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new MateriaPrima(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
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
    
    public ArrayList<MateriaPrima> BuscaComStatus(boolean status, String add)
    {
        ArrayList<MateriaPrima> lista = new ArrayList<>();
        String sql = "select mp.mp_codigo, mp.mp_nome, mp.mp_status "
                   + "from mat_prima mp "
                   + "where mp.mp_status = ? "+add+";";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setBoolean(1, status);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new MateriaPrima(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
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
    
    public ArrayList<MateriaPrima> serch()
    {
        ArrayList<MateriaPrima> lista = new ArrayList<>();
        String sql = "select mp.mp_codigo, mp.mp_nome, mp.mp_status "
                   + "from mat_prima mp "
                   + "where mp.mp_status = true;";
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
                            lista.add(new MateriaPrima(rs.getInt(1), rs.getString(2), rs.getBoolean(3)));
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
    
    public MateriaPrima リカバー(int codigo)
    {
        String sql = "select * "
                   + "from mat_prima "
                   + "where mp_codigo = ?; ";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, codigo);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        if(rs.next())
                        {
                            return new MateriaPrima(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
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
