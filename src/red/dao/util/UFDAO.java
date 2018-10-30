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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import red.model.util.UF;

/**
 *
 * @author Daniel
 */
public class UFDAO {
    
    private String erro;

    public UFDAO() {
        erro = null;
    }

    public String getErro() {
        return erro;
    }
    private UF novo(ResultSet rs)
            throws SQLException {
        return new UF(
                rs.getInt("est_codigo"),
                rs.getString("est_nome")
                
        );
    }
    public static UF busca(int codigo) {
        String sql = "select est_codigo, est_nome from estado where est_codigo = ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, codigo);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            return new UF(
                                    rs.getInt("est_codigo"),
                                    rs.getString("est_nome")
                                    
                            );
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public static List<UF> lista() {
        List<UF> lista = new ArrayList<>();
        String sql = "select est_codigo, est_nome from estado order by est_nome";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sql)) {
                        while (rs.next()) {
                            lista.add(new UF(
                                    rs.getInt("est_codigo"),
                                    rs.getString("est_nome")
                                   
                            ));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    public boolean insere(UF estado) {
        String sql = "insert into estado (est_codigo, est_nome) values (?,?);";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, estado.getCodigo());
                    ps.setString(2, estado.getNome());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro inserindo registro!";
        }
        return false;
    }

    public boolean altera(UF estado) {
        String sql = "update estado set est_nome = ? where est_codigo = ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, estado.getNome());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando estado!";
        }
        return false;
    }

    public static boolean exclui(UF estado) {
        String sql = "delete from estado where est_codigo = ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, estado.getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            //erro = "Erro excluindo estado.";
        }
        return false;
    }
    
    
}
