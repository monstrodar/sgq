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
import red.model.util.*;

/**
 *
 * @author Bruno Yoshino
 */
public class Fornecedor {
    private int codigo;
    private String nome;
    private String rua;
    private int num;
    private String cnpj;
    private Cidade cid;
    private String cep;
    private String telefone;
    private String contato;
    private String email;
    private String telefonecont;

    public Fornecedor(int codigo, String nome, String rua, int num, String cnpj, Cidade cid, String cep, String telefone, String contato, String email, String telefonecont) {
        this.codigo = codigo;
        this.nome = nome;
        this.rua = rua;
        this.num = num;
        this.cnpj = cnpj;
        this.cid = cid;
        this.cep = cep;
        this.telefone = telefone;
        this.contato = contato;
        this.email = email;
        this.telefonecont = telefonecont;
    }

    public Fornecedor() {
        this.codigo = 0;
        this.nome = "";
        this.rua = "";
        this.num = 0;
        this.cnpj = "";
        this.cid = null;
        this.cep = "";
        this.telefone = "";
        this.contato = "";
        this.email = "";
        this.telefonecont = "";
    }
    
    public Fornecedor(int cod) {
        this.codigo = cod;
        this.nome = "";
        this.rua = "";
        this.num = 0;
        this.cnpj = "";
        this.cid = null;
        this.cep = "";
        this.telefone = "";
        this.contato = "";
        this.email = "";
        this.telefonecont = "";
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Cidade getCid() {
        return cid;
    }

    public void setCid(Cidade cid) {
        this.cid = cid;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefonecont() {
        return telefonecont;
    }

    public void setTelefonecont(String telefonecont) {
        this.telefonecont = telefonecont;
    }
    
    @Override
    public String toString()
    {
        return ""+nome;
    }
    //DAO--------------------------------------------------------------------------------------------------------------
    public boolean gravar(Fornecedor f)
    {
        String sql = "insert into fornecedor (for_nome, for_rua, for_num, for_cnpj, cid_codigo, for_cep, for_telefone, for_contato, for_email) values (?,?,?,?,?,?,?,?,?,?);";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, f.getNome());
                    ps.setString(2, f.getRua());
                    ps.setInt(3, f.getNum());
                    ps.setString(4, f.getCnpj());
                    ps.setInt(5, f.getCid().getCodigo());
                    ps.setString(6, f.getCep());
                    ps.setString(7, f.getTelefone());
                    ps.setString(8, f.getContato());
                    ps.setString(9, f.getEmail());
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
    
    public boolean alterar(Fornecedor f)
    {
        String sql = "update fornecedor set for_nome = ?, for_rua = ?, for_num = ?, for_cnpj = ?, cid_codigo = ?, for_cep = ?, for_telefone = ?, for_contato = ?, for_email = ? where for_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, f.getNome());
                    ps.setString(2, f.getRua());
                    ps.setInt(3, f.getNum());
                    ps.setString(4, f.getCnpj());
                    ps.setInt(5, f.getCid().getCodigo());
                    ps.setString(6, f.getCep());
                    ps.setString(7, f.getTelefone());
                    ps.setString(8, f.getContato());
                    ps.setString(9, f.getEmail());
                    ps.setInt(10, f.getCodigo());
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
        String sql = "delete from fornecedor where for_codigo = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(3, codigo);
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
        
    public ArrayList<Fornecedor> carrega(String campo)
    {
        ArrayList<Fornecedor> lista = new ArrayList<>();
        String sql = "select * "
                   + "from fornecedor "
                   + "where for_nome leke ? or for_cnpj = ? ";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setString(1, "%"+campo+"%");
                    ps.setString(2, campo);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        while(rs.next())
                        {//                     int codigo,              String nome,  String rua,     int num,    String cnpj,            Cidade cid,          String cep,      String telefone,  contato,            email, String telefonecont
                            lista.add(new Fornecedor(rs.getInt(1), rs.getString(2), rs.getString(3), 
                                    rs.getInt(4), rs.getString(10), new Cidade(rs.getInt(11), "", null), rs.getString(5), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
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
    
    public ArrayList<Fornecedor> carrega()
    {
        ArrayList<Fornecedor> lista = new ArrayList<>();
        String sql = "select * "
                   + "from fornecedor ;";
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
                            lista.add(new Fornecedor(rs.getInt(1), rs.getString(2), rs.getString(3), 
                                    rs.getInt(4), rs.getString(10), new Cidade(rs.getInt(11), "", null), rs.getString(5), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
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
    
    public Fornecedor serch(int codigo)
    {
        String sql = "select * "
                   + "from fornecedor "
                   + "where for_codigo = ? ;";
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
                            return (new Fornecedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), 
                                    rs.getString(10), new Cidade(rs.getInt(11), "", null), rs.getString(5), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
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
