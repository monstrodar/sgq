/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.dao.producao.lote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import red.dao.util.Conecta;
import red.model.producao.lote.Produto;

/**
 *
 * @author Daniel
 */
public class ProdutoDAO {
    
    private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public ProdutoDAO() {
        erro=null;
    }
    
    public Produto busca(int codigo) {
        String sql = "select pro_codigo,pro_nome, pro_descricao, pro_status from produto where pro_codigo = ? ";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, codigo);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            return new Produto(rs.getInt("pro_codigo"),
                                    rs.getString("pro_nome"), 
                                    rs.getString("pro_descricao"),
                                    rs.getBoolean("pro_status"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
   
    public boolean insere(Produto p){
     
        String sql="insert into produto(pro_nome,pro_descricao, pro_status) values(?,?,?);";
                try(Connection conn = Conecta.abreConexaoBanco()) {
                    if(conn !=null){
                        try (PreparedStatement ps = conn.prepareStatement(sql)){
                            ps.setString(1, p.getNome());
                            ps.setString(2, p.getDescricao());
                            ps.setBoolean(3, p.isStatus());  
                            ps.executeUpdate();
                            return true;
                        } 
                    }
        } catch (SQLException e) {
            erro="erro ao gravar";
        }
       return false; 
    }
    public List<Produto> lista(){
        
        String sql="select pro_codigo, pro_nome, pro_descricao, pro_status from produto  "
                + "order by pro_nome;";
        List<Produto> lista =new ArrayList<>();
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lista.add(new Produto(rs.getInt("pro_codigo"),
                                    rs.getString("pro_nome"), 
                                    rs.getString("pro_descricao"),
                                    rs.getBoolean("pro_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
    public int ultimoId(){
        String sql="select pro_codigo from produto order by pro_codigo;";
        
        int cod=0;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                                    cod=rs.getInt("pro_codigo");
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }           
        return cod+1;
    }
    public  boolean altera(Produto p) {
        String sql = "update produto set pro_nome = ?, pro_descricao=?, pro_status=? where pro_codigo=?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                         
                            ps.setString(1, p.getNome());
                            ps.setString(2, p.getDescricao());
                            ps.setBoolean(3, p.isStatus());
                            ps.setInt(4,p.getCodigo());
                        //    ps.executeLargeUpdate();
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando produto!";
        }
        return false;
    }
    public boolean exclui(Produto p) {
        String sql = "delete from produto where pro_codigo = ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, p.getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
          //  erro = "Erro excluindo produto!";
        }
        return false;
    }
    
    public int QtdProduto()
    {
        String sql = "select count(*) from produto;";
        int qtd=0;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        if(rs.next()){
                                    qtd=rs.getInt("count");
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }           
        return qtd;
    }
    
     public List<Produto> pesquisa(String chave){
        
        String sql="select pro_codigo, pro_nome, pro_descricao, pro_status from produto where upper(pro_nome) "
                + "like '%"+chave+"%' order by pro_nome;";
        List<Produto> lista =new ArrayList<>();
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lista.add(new Produto(rs.getInt("pro_codigo"),
                                    rs.getString("pro_nome"), 
                                    rs.getString("pro_descricao"),
                                    rs.getBoolean("pro_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
      public List<Produto> listaAtivo(){
        
        String sql="select pro_codigo, pro_nome, pro_descricao, pro_status from produto where pro_status='true'  "
                + "order by pro_nome;";
        List<Produto> lista =new ArrayList<>();
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lista.add(new Produto(rs.getInt("pro_codigo"),
                                    rs.getString("pro_nome"), 
                                    rs.getString("pro_descricao"),
                                    rs.getBoolean("pro_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
      public List<Produto> listaInativo(){
        
        String sql="select pro_codigo, pro_nome, pro_descricao, pro_status from produto where pro_status='false'   "
                + "order by pro_nome;";
        List<Produto> lista =new ArrayList<>();
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lista.add(new Produto(rs.getInt("pro_codigo"),
                                    rs.getString("pro_nome"), 
                                    rs.getString("pro_descricao"),
                                    rs.getBoolean("pro_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
      public List<Produto> pesquisaAtivo(String chave){
        
        String sql="select pro_codigo, pro_nome, pro_descricao, pro_status from produto where upper(pro_nome) "
                + "like '%"+chave+"%'  and pro_status='true' order by pro_nome;";
        List<Produto> lista =new ArrayList<>();
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lista.add(new Produto(rs.getInt("pro_codigo"),
                                    rs.getString("pro_nome"), 
                                    rs.getString("pro_descricao"),
                                    rs.getBoolean("pro_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
      public List<Produto> pesquisaInativo(String chave){
        
        String sql="select pro_codigo, pro_nome, pro_descricao, pro_status from produto where upper(pro_nome) "
                + "like '%"+chave+"%'  and pro_status='false' order by pro_nome;";
        List<Produto> lista =new ArrayList<>();
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lista.add(new Produto(rs.getInt("pro_codigo"),
                                    rs.getString("pro_nome"), 
                                    rs.getString("pro_descricao"),
                                    rs.getBoolean("pro_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
}
