/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.dao.producao.rastreabilidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import red.dao.producao.lote.MontagemLoteDAO;
import red.dao.util.Conecta;
import red.model.producao.lote.MontagemLote;
import red.model.producao.rastreabilidade.EntradaLote;
import red.model.producao.rastreabilidade.MontagemCarga;

/**
 *
 * @author Daniel
 */
public class EntradaLoteDAO {
   
      private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public EntradaLoteDAO() {
        erro=null;
    }
    
    public EntradaLote busca(int codigo) {
        String sql = "select ca_codigo, lo_numero, el_qtde from entrada_lote where ca_codigo=?;";
        MontagemLoteDAO daoLt = new MontagemLoteDAO();
        MontagemLote lote= null;
        MontagemCargaDAO daoCarga = new MontagemCargaDAO();
        MontagemCarga carga = null; 
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, codigo);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            lote = daoLt.busca(rs.getInt("lo_numero"));
                            carga = daoCarga.busca(rs.getInt("ca_codigo"));
                            return new EntradaLote(carga, 
                                    lote,rs.getInt("el_qtde"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public boolean insere(EntradaLote el){
        
        String sql="insert into entada_lote (ca_codigo,lo_numero,el_qtde) values(?,?,?);";
                try(Connection conn = Conecta.abreConexaoBanco()) {
                    if(conn !=null){
                        try (PreparedStatement ps = conn.prepareStatement(sql)){
                           ps.setInt(1, el.getCarga().getCodigo());
                            ps.setInt(2, el.getLote().getNumero());
                            ps.setInt(3, el.getQtde());
                            ps.executeUpdate();
                            return true;
                        } 
                    }
        } catch (SQLException e) {
            erro="erro ao gravar";
        }
       return false; 
    }
    public List<EntradaLote> lista(){
        
        String sql="select ca_codigo,lo_numero,el_qtde from entrada_lote order by  ca_codigo;";
        List<EntradaLote> lista =new ArrayList<>();
        MontagemLoteDAO daoLt = new MontagemLoteDAO();
        MontagemLote lote= null;
        MontagemCargaDAO daoCarga = new MontagemCargaDAO();
        MontagemCarga carga = null; 
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            
                             lote = daoLt.busca(rs.getInt("lo_numero"));
                            carga = daoCarga.busca(rs.getInt("ca_codigo"));
                          
                            lista.add(new EntradaLote(carga, 
                                    lote,rs.getInt("el_qtde")));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
    public  boolean altera(EntradaLote el) {
        String sql = "update entrada_lote set ca_codigo=? ,lo_numero=? ,el_qtde=? "
                + "where ca_codigo = "+el.getCarga().getCodigo()+" ; ";
  
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                            ps.setInt(1, el.getCarga().getCodigo());
                            ps.setInt(2, el.getLote().getNumero());
                            ps.setInt(3, el.getQtde());
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando!";
        }
        return false;
    }
    public List<EntradaLote> buscaItensDaCarga(int codigo)
    {   
        String sql="select * from entrada_lote where ca_codigo=?";
         List<EntradaLote> lista =new ArrayList<>();
       MontagemLoteDAO daoLt = new MontagemLoteDAO();
        MontagemLote lote= null;
        MontagemCargaDAO daoCarga = new MontagemCargaDAO();
        MontagemCarga carga = null; 
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                  ps.setInt(1, codigo);
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                           
                            lote = daoLt.busca(rs.getInt("lo_numero"));
                            carga = daoCarga.busca(rs.getInt("ca_codigo"));
                          
                            lista.add(new EntradaLote(carga, 
                                    lote,rs.getInt("el_qtde")));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    public boolean exclui(EntradaLote el) {
        String sql = "delete from entrada_lote where lo_numero = ? and ca_codigo=?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, el.getLote().getNumero());
                       ps.setInt(2, el.getCarga().getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
          //  erro = "Erro excluindo produto!";
        }
        return false;
    }
    public boolean exclui2(EntradaLote el) {
        String sql = "delete from entrada_lote where ca_codigo= ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, el.getCarga().getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
          //  erro = "Erro excluindo produto!";
        }
        return false;
    }
    
}
