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
import red.model.util.Cidade;
import red.model.util.Regiao;
import red.model.util.UF;

/**
 *
 * @author Daniel
 */
public class RegiaoDAO {
    
    public List<Regiao> lista(){//certo
        
        List<Regiao> lista = new ArrayList<>();
        String sql = "select re_codigo, re_nome, cid_codigo from regiao order by re_nome";
        CidadeDAO dao = new CidadeDAO();
        Cidade cidade =null;
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    try (ResultSet rs = st.executeQuery()) {
                        while (rs.next()) {
                            cidade = dao.busca(rs.getInt("cid_codigo"));
                             lista.add(new Regiao(rs.getInt("re_codigo"),
                                    rs.getString("re_nome"),
                                    cidade));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return lista;
    }
    
    
    
    public  Regiao busca(String nome) {
        String sql = "select re_codigo, re_nome, cid_codigo from regiao where re_nome = ? ;";
        CidadeDAO dao = new CidadeDAO();
        Cidade cidade =null;
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, nome);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            cidade = dao.busca(rs.getInt("cid_codigo"));
                            return new Regiao(rs.getInt("re_codigo"),
                                    rs.getString("re_nome"),
                                    cidade);
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public  Regiao busca(int codigo) {//certo
        String sql = "select  *  from regiao where re_codigo = ? ;";
        CidadeDAO dao = new CidadeDAO();
        Cidade cidade =null;
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, codigo);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            cidade = dao.busca(rs.getInt("cid_codigo"));
                            return new Regiao(rs.getInt("re_codigo"),
                                    rs.getString("re_nome"),
                                    cidade);
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
}
