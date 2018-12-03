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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import red.dao.util.Conecta;
import red.model.colaborador.Colaborador;
import red.model.producao.lote.MontagemLote;
import red.model.producao.lote.Produto;

/**
 *
 * @author Daniel Ramiro
 */
public class MontagemLoteDAO {
    
    
    
     private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public MontagemLoteDAO() {
        erro=null;
    }
    public MontagemLote buscaDisponivel(int  numeroLote, Produto produto){
        String sql = "select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_estoque  from lote where lo_estoque >0  and  lo_numero=? and pro_codigo=? ";
        ProdutoDAO daoPro = new ProdutoDAO();
    
        Colaborador col = null, colaborador = new Colaborador();
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                   
                     st.setInt(1, numeroLote);
                      st.setInt(2, produto.getCodigo());
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            produto= daoPro.busca(rs.getInt("pro_codigo"));  ///erro
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            return new MontagemLote(rs.getInt("lo_numero"),
                                    produto,
                                    col,
                                    rs.getDate("lo_data_inicio").toLocalDate(),
                                    rs.getDate("lo_data_fim").toLocalDate(),
                                    rs.getInt("lo_estoque"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public MontagemLote busca(int numero) {
        String sql = "select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_estoque  from lote where lo_numero = ? ";
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto produto = null;
        Colaborador col = null, colaborador = new Colaborador();
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, numero);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            produto= daoPro.busca(rs.getInt("pro_codigo"));  ///erro
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            return new MontagemLote(rs.getInt("lo_numero"),
                                    produto,
                                    col,
                                    rs.getDate("lo_data_inicio").toLocalDate(),
                                    rs.getDate("lo_data_fim").toLocalDate(),
                                    rs.getInt("lo_estoque"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
   
    public boolean insere(MontagemLote ml){
     
        String sql="insert into lote(pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_estoque) values("+ml.getProduto().getCodigo()+","
                +ml.getColaborador().getCodigo()+",'"+ml.getData_inicio()+"','"+ml.getData_fim()+"',"+ml.getEstoque()+");";
                try(Connection conn = Conecta.abreConexaoBanco()) {
                    if(conn !=null){
                        try (PreparedStatement ps = conn.prepareStatement(sql)){
                            ps.executeUpdate();
                            return true;
                        } 
                    }
        } catch (SQLException e) {
            erro="erro ao gravar";
        }
       return false; 
    }
    public List<MontagemLote> lista(){
        
        String sql="select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_estoque from lote order by lo_numero;";
      List<MontagemLote> lista =new ArrayList<>();
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto produto = null;
        Colaborador colaborador = new Colaborador();
        Colaborador  col=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            produto= daoPro.busca(rs.getInt("pro_codigo"));
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            lista.add(new MontagemLote(rs.getInt("lo_numero"),
                                    produto,
                                    col,
                                    rs.getDate("lo_data_inicio").toLocalDate(),
                                    rs.getDate("lo_data_fim").toLocalDate(),
                                    rs.getInt("lo_estoque")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
   
    public  boolean altera(MontagemLote ml) {
       
        String sql = "update lote set pro_codigo="+ml.getProduto().getCodigo()+", "
                + "col_codigo="+ml.getColaborador().getCodigo()+", lo_data_inicio= '"+ml.getData_inicio()+"', "
                + "lo_data_fim= '"+ml.getData_fim()+"', lo_estoque="+ml.getEstoque()+"  where lo_numero="+ml.getNumero()+";";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                       
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando produto!";
        }
        return false;
    }
    public boolean exclui(MontagemLote ml) {
        String sql = "delete from lote where lo_numero = "+ml.getNumero()+";";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                  
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
          //  erro = "Erro excluindo produto!";
        }
        return false;
    }
    
    public int QtdLote()
    {
        String sql = "select count(*) from lote;";
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
    
     public List<MontagemLote> pesquisa(String chave){
        
        String sql="select * from lote where lo_numero= ? order by lo_numero;";
        List<MontagemLote> lista =new ArrayList<>();
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto produto = null;
        Colaborador colaborador = null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                           produto= daoPro.busca(rs.getInt("pro_codigo"));
                           colaborador =colaborador.busca(rs.getInt("col_codigo")); 
                            lista.add(new MontagemLote(rs.getInt("lo_numero"),
                                    produto,
                                    colaborador,
                                    rs.getDate("lo_data_inicio").toLocalDate(),
                                    rs.getDate("lo_data_fim").toLocalDate(),
                                    rs.getInt("lo_estoque")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
     public List<MontagemLote> pesquisa2(int numero){
        
        String sql="select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_estoque  from lote where lo_numero = ?  order by lo_numero;";
        List<MontagemLote> lista =new ArrayList<>();
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto produto = null;
       Colaborador colaborador = new Colaborador();
        Colaborador  col=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, numero);
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                           produto= daoPro.busca(rs.getInt("pro_codigo"));
                            col =colaborador.busca(rs.getInt("col_codigo"));; 
                            lista.add(new MontagemLote(rs.getInt("lo_numero"),
                                    produto,
                                    col,
                                    rs.getDate("lo_data_inicio").toLocalDate(),
                                    rs.getDate("lo_data_fim").toLocalDate(),
                                    rs.getInt("lo_estoque")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
     
    public int ultimoNumero(){
        String sql="select lo_numero from lote order by lo_numero;";
        
        int numero=0;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                                    numero=rs.getInt("lo_numero");
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }           
        return numero+1;
    }
    public MontagemLote getSaldo(int numero) {
        String sql = "select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_estoque  from lote where lo_numero = ? ";
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto produto = null;
        Colaborador col = null, colaborador = new Colaborador();
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, numero);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            produto= daoPro.busca(rs.getInt("pro_codigo"));  ///erro
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            return new MontagemLote(rs.getInt("lo_numero"),
                                    produto,
                                    col,
                                    rs.getDate("lo_data_inicio").toLocalDate(),
                                    rs.getDate("lo_data_fim").toLocalDate(),
                                    rs.getInt("lo_estoque"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public boolean atualizaEstoqueLote(MontagemLote lote){
        String sql = "update lote set lo_estoque="+lote.getEstoque()+"  where lo_numero="+lote.getNumero()+";";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                      
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando produto!";
        }
        return false;
    }
    
    public List<MontagemLote> pesquisaData(LocalDate inicio, LocalDate fim){
        
//        String sql="select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
//                + "lo_data_fim, lo_estoque  from lote where  "
//                + "lo_data_inicio >= '"+inicio+"' and lo_data_fim <= '"+fim+"';";
        
        String sql="select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_estoque  from lote where  "
                + "lo_data_inicio between date('"+inicio+"') and date('"+fim+"') or "
                + "lo_data_fim between date('"+inicio+"') and date('"+fim+"') order by lo_numero;";
        
        
        
        List<MontagemLote> lista =new ArrayList<>();
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto produto = null;
       Colaborador colaborador = new Colaborador();
        Colaborador  col=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                           produto= daoPro.busca(rs.getInt("pro_codigo"));
                            col =colaborador.busca(rs.getInt("col_codigo"));; 
                            lista.add(new MontagemLote(rs.getInt("lo_numero"),
                                    produto,
                                    col,
                                    rs.getDate("lo_data_inicio").toLocalDate(),
                                    rs.getDate("lo_data_fim").toLocalDate(),
                                    rs.getInt("lo_estoque")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
     
}
