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
import red.model.producao.lote.EstoqueProduto;
import red.model.producao.lote.MontagemLote;
import red.model.producao.lote.Produto;

/**
 *
 * @author Daniel
 */
public class EstoqueProdutoDAO {
    
    
     private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public EstoqueProdutoDAO() {
        erro=null;
    }
   public boolean insere(EstoqueProduto ml){
     
        String sql="estoque_produto(lo_numero, pro_codigo, est_pro_qtde, est_pro_validade, est_pro_qtde_vendida, est_pro_data_mov)\n" +
        "values("+ml.getLote().getNumero()+"," +ml.getProduto().getCodigo()+","+ml.getQtde()+", null ,"+ml.getVendida()+","
                + "'"+ml.getData_mov()+"');";
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
    public List<EstoqueProduto> lista(){
        
 String sql="select * from estoque_produto;";

      List<EstoqueProduto> lista =new ArrayList<>();
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto pro = null;
        MontagemLoteDAO daoLT = new MontagemLoteDAO();
        MontagemLote  lote=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            pro= daoPro.busca(rs.getInt("pro_codigo"));
                            lote =daoLT.busca(rs.getInt("lo_numero"));
                           lista.add(new EstoqueProduto(lote,
                                    pro,
                                    rs.getInt("est_pro_qtde"),
                                    null,
                                    rs.getInt("est_pro_qtde_vendida"),
                                    rs.getDate("est_pro_data_mov").toLocalDate()));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
    public List<EstoqueProduto> lista2(Produto p){
        
      String sql="select * from estoque_produto where  pro_codigo = "+p.getCodigo()+"  "
              + " order by lo_numero;";
        List<EstoqueProduto> lista =new ArrayList<>();
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto pro = null;
        MontagemLoteDAO daoLT = new MontagemLoteDAO();
        MontagemLote  lote=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            pro= daoPro.busca(rs.getInt("pro_codigo"));
                            lote =daoLT.busca(rs.getInt("lo_numero"));
                           lista.add(new EstoqueProduto(lote,
                                    pro,
                                    rs.getInt("est_pro_qtde"),
                                    null,
                                    rs.getInt("est_pro_qtde_vendida"),
                                    rs.getDate("est_pro_data_mov").toLocalDate()));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
    
     public  boolean altera(EstoqueProduto ml) {
        String sql = "update estoque_produto SET pro_codigo= "+ml.getProduto().getCodigo()+", "
                + "est_pro_qtde= "+ml.getQtde()+", "
                + "est_pro_validade= null, est_pro_qtde_vendida= "+ml.getVendida()+", "
                + "est_pro_data_mov= '"+ml.getData_mov()+"'  WHERE lo_numero= "+ml.getLote().getNumero()+";";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                       
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando estoque!";
        }
        return false;
    }
    
    public EstoqueProduto busca(MontagemLote lote, Produto pro){
        
        String sql ="SELECT lo_numero, pro_codigo, est_pro_qtde, est_pro_validade, "
         + "est_pro_qtde_vendida, est_pro_data_mov" +
"	FROM estoque_produto where "
                + "lo_numero = "+ lote.getNumero()+" and "+ pro.getCodigo()+" = pro_codigo; ";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {

                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                        
                            return new EstoqueProduto(lote,
                                    pro,
                                    rs.getInt("est_pro_qtde"),
                                    null,
                                    rs.getInt("est_pro_qtde_vendida"),
                                    rs.getDate("est_pro_data_mov").toLocalDate());
                            
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    } 
   
 
    public boolean exclui(MontagemLote lote, Produto pro) {
        String sql = "delete from estoque_produto where "
                + "lo_numero = "+lote.getNumero()+" and "+ pro.getCodigo()+" = pro_codigo; ";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                  
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
          //  erro = "Erro excluindo lote!";
        }
        return false;
    }
}
