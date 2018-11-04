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
import red.dao.producao.aquisicao.MateriaPrimaDAO;
import red.dao.util.Conecta;
import red.model.producao.aquisicao.MateriaPrima;
import red.model.producao.lote.ItensSaida;
import red.model.producao.lote.MontagemLote;

/**
 *
 * @author Daniel
 */
public class ItensSaidaDAO {
    
     private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public ItensSaidaDAO() {
        erro=null;
    }
    
    public ItensSaida busca(int lo_numero) {
        String sql = "select * from itens_saida where lo_numero=?;";
        MontagemLoteDAO daoLt = new MontagemLoteDAO();
        MontagemLote lote= null;
        MateriaPrimaDAO daoMp = new MateriaPrimaDAO();
        MateriaPrima materia_prima = null; 
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, lo_numero);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            lote = daoLt.busca(rs.getInt("lo_numero"));
                            materia_prima = daoMp.busca(rs.getInt("mp_codigo"));
                            return new ItensSaida(materia_prima, 
                                    lote,rs.getInt("is_qtde"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public boolean insere(ItensSaida is){
        
        String sql="insert into itens_saida(mp_codigo,lo_numero,is_qtde) values(?,?,?);";
                try(Connection conn = Conecta.abreConexaoBanco()) {
                    if(conn !=null){
                        try (PreparedStatement ps = conn.prepareStatement(sql)){
                           ps.setInt(1, is.getMateria_prima().getCodigo());
                            ps.setInt(2, is.getLote().getNumero());
                            ps.setInt(3, is.getQtde());
                            ps.executeUpdate();
                            return true;
                        } 
                    }
        } catch (SQLException e) {
            erro="erro ao gravar";
        }
       return false; 
    }
    public List<ItensSaida> lista(){
        
        String sql="select mp_codigo,lo_numero,is_qtde from itens_saida order by  lo_numero;";
        List<ItensSaida> lista =new ArrayList<>();
        MontagemLoteDAO daoLt = new MontagemLoteDAO();
        MontagemLote lote= null;
        MateriaPrimaDAO daoMp = new MateriaPrimaDAO();
        MateriaPrima materia_prima = null; 
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            
                            lote = daoLt.busca(rs.getInt("lo_numero"));
                            materia_prima = daoMp.busca(rs.getInt("mp_codigo"));
                            lista.add(new  ItensSaida(materia_prima, 
                                    lote,rs.getInt("is_qtde")));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
    public  boolean altera(ItensSaida is) {
        String sql = "update itens_saida set mp_codigo=? ,lo_numero=? ,is_qtde=? "
                + "where lo_numero = "+is.getLote().getNumero()+" ; ";
  
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                            ps.setInt(1, is.getMateria_prima().getCodigo());
                            ps.setInt(2, is.getLote().getNumero());
                            ps.setInt(3, is.getQtde());
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando!";
        }
        return false;
    }
    public List<ItensSaida> buscaItensDoLote(int numero)
    {   
        String sql="select * from itens_saida where lo_numero=?";
         List<ItensSaida> lista =new ArrayList<>();
       
         MontagemLoteDAO daoLt = new MontagemLoteDAO();
        MontagemLote lote= null;
        MateriaPrimaDAO daoMp = new MateriaPrimaDAO();
        MateriaPrima materia_prima = null;
      
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                  ps.setInt(1, numero);
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                           
                            lote = daoLt.busca(rs.getInt("lo_numero"));
                            
                            materia_prima = daoMp.busca(rs.getInt("mp_codigo"));
                     
                            lista.add(new  ItensSaida(materia_prima, 
                                    lote,rs.getInt("is_qtde")));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    public boolean exclui(ItensSaida is) {
        String sql = "delete from itens_saida where lo_numero = ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, is.getLote().getNumero());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
          //  erro = "Erro excluindo produto!";
        }
        return false;
    }
    public boolean exclui2(ItensSaida is) {
        String sql = "delete from itens_saida where lo_numero= ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, is.getLote().getNumero());
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
