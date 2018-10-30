/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.dao.parametrizacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import red.dao.util.CidadeDAO;
import red.dao.util.Conecta;
import red.model.parametrizacao.Parametrizacao;
import red.model.producao.lote.Produto;
import red.model.util.Cidade;


/**
 *
 * @author Daniel
 */
public class ParametrizacaoDAO {
    
    private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public ParametrizacaoDAO() {
        erro=null;
    }
     public boolean insere(Parametrizacao p){
        
        String sql="insert into parametrizacao(par_codigo,par_razao_social, par_cnpj, par_rua,"
                + "par_numero, par_bairro, par_cep, par_email, par_telefone, cid_codigo"
                + " values(?,?,?,?,?,?,?,?,?,?);";
                try(Connection conn = Conecta.abreConexaoBanco()) {
                    if(conn !=null){
                        try (PreparedStatement ps = conn.prepareStatement(sql)){
                            ps.setInt(1, p.getCodigo());
                            ps.setString(2, p.getRazao_social());
                            ps.setString(3, p.getCnpj());
                            ps.setString(4, p.getRua());
                            ps.setInt(5, p.getNumero());
                            ps.setString(6, p.getBairro());
                            ps.setString(7, p.getCep());
                            ps.setString(8, p.getEmail());
                            ps.setString(9, p.getTelefone());
                            ps.setInt(10, p.getCidade().getCodigo());
                            ps.executeUpdate();
                            return true;
                        } 
                    }
        } catch (SQLException e) {
        }
       return false; 
    }
     
     public  boolean altera(Parametrizacao p) {
        String sql = "update parametrizacao set par_razao_social = ?,par_cnpj = ?, "
                + "par_rua = ?, par_numero = ?, par_bairro = ?, par_cep = ?, "
                + "par_email = ?, par_telefone = ?, cid_codigo = ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, p.getCodigo());
                    ps.setString(2, p.getRazao_social());
                    ps.setString(3, p.getCnpj());
                    ps.setString(4, p.getRua());
                    ps.setInt(5, p.getNumero());
                    ps.setString(6, p.getBairro());
                    ps.setString(7, p.getCep());
                    ps.setString(8, p.getEmail());
                    ps.setString(9, p.getTelefone());
                    ps.setInt(10, p.getCidade().getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            //erro = "Erro alterando produto!";
        }
        return false;
    }
     
     public static Parametrizacao busca(int codigo) {
        String sql = "select par_codigo,par_razao_social, par_cnpj, par_rua,"
                + "par_numero, par_bairro, par_cep, par_email, par_telefone, cid_codigo"
                + "from parametrizacao where par_codigo = ? ;";
       CidadeDAO dao = new CidadeDAO();
       Cidade cidade = null;
       
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, codigo);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            
                            cidade = dao.busca(rs.getInt("cid_codigo"));
                            return new Parametrizacao(rs.getInt("par_codigo"),
                                    rs.getString("par_razao_social"), 
                                    rs.getString("par_cnpj"),
                                    rs.getString("par_rua"),
                                    rs.getInt("par_numero"),
                                    rs.getString("par_bairro"),
                                    rs.getString("par_cep"),
                                    rs.getString("par_email"),
                                    rs.getString("par_telefone"),
                                    cidade
                                    );
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
     
}
