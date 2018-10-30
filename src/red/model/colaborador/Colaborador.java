package red.model.colaborador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.conexcao.Conexao;

/**
 *
 * @author Bruno Yoshino
 */
public class Colaborador 
{
    private int codigo;
    private String nome;
    protected String senha;
    private String cargo;
    private boolean status;
    private String cpf;
    private int nivel;
    private String celular;

    public Colaborador(int codigo, String nome, String senha, String cargo, boolean status, String cpf, int nivel, String celular) {
        this.codigo = codigo;
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
        this.status = status;
        this.cpf = cpf;
        this.nivel = nivel;
        this.celular = celular;
    }
    
    public Colaborador() {
        this.codigo = 0;
        this.nome = "";
        this.senha = "";
        this.cargo = "";
        this.status = false;
        this.cpf = "";
        this.nivel = 0;
        this.celular = "";
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    // Area banco  DAL---------------------------------------------------------------------------------
    public boolean gravar(Colaborador c)
    {
        String sql = "insert into colaborador (col_nome, col_senha, col_cargo, col_status, col_cpf, col_nivel, col_celular) values (?,?,?,?,?,?,?);";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, c.nome);
                    ps.setString(2, c.senha);
                    ps.setString(3, c.cargo);
                    ps.setBoolean(4, c.status);
                    ps.setString(5, c.cpf);
                    ps.setInt(6, c.nivel);
                    ps.setString(7, c.celular);
                    return true;
                } 
                
            }
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean alterar(Colaborador c)
    {
        String sql = "update colaborador col_nome=?, col_senha=?, col_cargo=?, col_status=?, col_cpf=?, col_nivel=?, col_celular=? where col_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, c.nome);
                    ps.setString(2, c.senha);
                    ps.setString(3, c.cargo);
                    ps.setBoolean(4, c.status);
                    ps.setString(5, c.cpf);
                    ps.setInt(6, c.nivel);
                    ps.setString(7, c.celular);
                    ps.setInt(8, c.codigo);
                    return true;
                } 
                
            }
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
        
    public boolean excluir (int cod)
    {
        String sql = "update colaborador col_status=false where col_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, cod);
                    return true;
                } 
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
     }
     
    public int ReturnQtdColaboradorNivel(int nivel)
    {
        String sql = "select count(*) "
                    + "from colaborador"
                    + "where col_nivel = ? and col_status != false";
        int qtd = 0;
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, nivel);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        if(rs.next())
                        {
                            qtd = rs.getInt(1);
                        }
                    }    
                } 
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qtd;
    }
        
    public int FirstAcssesNecessary()
    {
        String sql = "select count(*) "
                    + "from colaborador";
        int qtd = 0;
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
                            qtd = rs.getInt(1);
                        }
                    }    
                } 
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return qtd;
    }
    
    public ArrayList<Colaborador> serch(String campo, String cargo)
    {
        ArrayList<Colaborador> lista = new ArrayList<>();
        String sql = "select * "
                   + "from colaborador "
                   + "where (col_nome like ? or col_cpf = ?) and col_cargo = ?"
                   + "order by col_nome";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, "%"+campo+"%");
                    ps.setString(1, campo);
                    ps.setString(3, cargo);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new Colaborador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
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
    
    public ArrayList<Colaborador> serch(String campo)
    {
        ArrayList<Colaborador> lista = new ArrayList<>();
        String sql = "select * "
                   + "from colaborador "
                   + "where col_nome like ? or col_cpf = ? or col_cargo = ?"
                   + "order by col_nome";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, "%"+campo+"%");
                    ps.setString(2, campo);
                    ps.setString(3, campo);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {
                            lista.add(new Colaborador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
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
    
    public Colaborador Logim(String cpf, String senha)
    {
        ArrayList<Colaborador> lista = new ArrayList<>();
        String sql = "select * "
                   + "from colaborador "
                   + "where col_cpf = ? and col_senha = ?"
                   + "order by col_nome";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, cpf);
                    ps.setString(2, senha);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        if(rs.next())
                        {
                            return new Colaborador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getInt(7), rs.getString(8));
                        }
                    }    
                } 
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Colaborador();
    }
}
