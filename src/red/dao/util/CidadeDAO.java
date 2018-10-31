/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import red.model.producao.lote.Produto;
import red.model.util.Cidade;
import red.model.util.UF;

/**
 *
 * @author Daniel
 */
public class CidadeDAO {
    
    private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    

    public CidadeDAO() {
        erro=null;
    }
    
    public  Cidade busca(String nome) {
        String sql = "select cid_codigo, cid_nome, uf_codigo from cidade where cid_nome = ? ;";
        UFDAO dao = new UFDAO();
        UF uf =null;
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, nome);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            uf = dao.busca(rs.getInt("est_codigo"));
                            return new Cidade(rs.getInt("cid_codigo"),
                                    rs.getString("cid_nome"),
                                    uf);
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public  Cidade busca(int codigo) {
        String sql = "select  *  from cidade where cid_codigo = ? ;";
        UFDAO dao = new UFDAO();
        UF uf =null;
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, codigo);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            uf = dao.busca(rs.getInt("est_codigo"));
                            return new Cidade(rs.getInt("cid_codigo"),
                                    rs.getString("cid_nome"),
                                    uf);
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public List<Cidade> lista(){
        
        List<Cidade> lista = new ArrayList<>();
        String sql = "select cid_codigo, cid_nome, uf_codigo from cidade order by cid_nome";
        UFDAO dao = new UFDAO();
        UF uf =null;
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    try (ResultSet rs = st.executeQuery()) {
                        while (rs.next()) {
                            uf = dao.busca(rs.getInt("uf_codigo"));
                             lista.add(new Cidade(rs.getInt("cid_codigo"),
                                    rs.getString("cid_nome"),
                                    uf));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return lista;
    }
    public  boolean altera(Cidade cidade) {
        String sql = "update cidade set cid_nome = ?, uf_codigo =? where cid_codigo = ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, cidade.getCodigo());
                    ps.setString(2, cidade.getNome());
                    ps.setInt(3, cidade.getUf().getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            //erro = "Erro alterando cidade!";
        }
        return false;
    }
    public boolean insere(Cidade cidade) {
        String sql = "insert into cidade(cid_codigo, cid_nome ,uf_codigo) values(?,?,?);";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, cidade.getCodigo());
                    ps.setString(2, cidade.getNome());
                    ps.setInt(3, cidade.getUf().getCodigo());
                    
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
           // erro = "Erro inserindo cidade!";
        }
        return false;
    }
    public boolean exclui(Cidade cidade) {
        String sql = "delete from cidade where cid_codigo = ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, cidade.getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro excluindo cidade!";
        }
        return false;
    }
     public List<Cidade> get(int codigo)
    {   
        String sql="select * from cidade where est_codigo="+codigo;
        List<Cidade> lista =new ArrayList<>();
        UFDAO dao = new UFDAO();
        UF uf =null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            uf = dao.busca(rs.getInt("est_codigo"));
                            lista.add(new Cidade(rs.getInt("cid_codigo"),
                                    rs.getString("cid_nome"),
                                    uf)
                            );
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
}
