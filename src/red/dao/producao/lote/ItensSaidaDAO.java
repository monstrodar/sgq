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
import red.dao.producao.aquisicao.EstoqueMpDAO;
import red.dao.producao.aquisicao.MateriaPrimaDAO;
import red.dao.util.Conecta;
import red.model.producao.aquisicao.EstoqueMP;
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
        String sql = "select * from itens_mont_lote where lo_numero=?;";
        EstoqueMpDAO daoEMP = new EstoqueMpDAO();
        EstoqueMP lote_mp= null;
        MontagemLoteDAO daoMl = new MontagemLoteDAO();
        MontagemLote lote_pro = null; 
        MateriaPrimaDAO daoMp = new MateriaPrimaDAO();
        MateriaPrima materia_prima = null; 
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, lo_numero);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            lote_pro= daoMl.busca(rs.getInt("lo_numero"));
                            materia_prima = daoMp.busca(rs.getInt("mp_codigo"));
                            lote_mp = daoEMP.busca(rs.getString("est_mp_lote"),materia_prima);
                            return new ItensSaida(lote_mp,lote_pro,materia_prima,rs.getInt("it_m_lt_qtde"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    //  public ItensSaida(EstoqueMP lt_materia_prima, MontagemLote lote, int qtde) {
  
    public boolean insere(ItensSaida is){
        
        String sql="insert into itens_mont_lote(est_mp_lote,lo_numero, mp_codigo, it_m_lt_qtde) values(?,?,?,?);";
                try(Connection conn = Conecta.abreConexaoBanco()) {
                    if(conn !=null){
                        try (PreparedStatement ps = conn.prepareStatement(sql)){
                            ps.setString(1,""+ is.getLt_materia_prima());
                            ps.setInt(2, is.getLote().getNumero());
                            ps.setInt(3, is.getMp().getCodigo());
                            ps.setInt(4, is.getQtde());
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
        
        String sql="select lo_numero,est_mp_lote, it_m_lt_qtde"
                + " from itens_mont_lote order by lo_numero;";
        List<ItensSaida> lista =new ArrayList<>();
        EstoqueMpDAO daoEMP = new EstoqueMpDAO();
        EstoqueMP lote_mp= null;
        MontagemLoteDAO daoMl = new MontagemLoteDAO();
        MontagemLote lote_pro = null; 
        MateriaPrimaDAO daoMp = new MateriaPrimaDAO();
        MateriaPrima materia_prima = null; 

        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lote_pro= daoMl.busca(rs.getInt("lo_numero"));
                            materia_prima = daoMp.busca(rs.getInt("mp_codigo"));
                            lote_mp = daoEMP.busca(rs.getString("est_mp_lote"),materia_prima);
                            lista.add(new  ItensSaida(lote_mp,lote_pro,materia_prima,rs.getInt("it_m_lt_qtde")));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
    public  boolean altera(ItensSaida is) {
        String sql = "update itens_mont_lote set est_mp_lote=? ,lo_numero=? ,mp_codigo=?, it_m_lt_qtde=? "
                + "where lo_numero = "+is.getLote().getNumero()+" and est_mp_lote = "+
                is.getLt_materia_prima()+" and mp_codigo= "+is.getMp().getCodigo()+";";
  
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                            ps.setString(1,""+ is.getLt_materia_prima());
                            ps.setInt(2, is.getLote().getNumero());
                            ps.setInt(3, is.getMp().getCodigo());
                            ps.setInt(4, is.getQtde());
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando!";
        }
        return false;
    }
    public List<ItensSaida> buscaItensDoProduto(int numero)//ok
    {   
        String sql="select est_mp_lote, lo_numero, mp_codigo, it_m_lt_qtde"
                + " from itens_mont_lote  where lo_numero = "+numero+" order by mp_codigo ";
         List<ItensSaida> lista =new ArrayList<>();
       
        EstoqueMpDAO daoEMP = new EstoqueMpDAO();
        EstoqueMP lote_mp= null;
        MontagemLoteDAO daoMl = new MontagemLoteDAO();
        MontagemLote lote_pro = null; 
        MateriaPrimaDAO daoMp = new MateriaPrimaDAO();
        MateriaPrima materia_prima = null; 
      
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                 // ps.setInt(1, numero);
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                           
                            lote_pro= daoMl.busca(rs.getInt("lo_numero"));
                            materia_prima = daoMp.busca(rs.getInt("mp_codigo"));
                            lote_mp = daoEMP.busca(rs.getString("est_mp_lote"),materia_prima);
                            lista.add(new  ItensSaida(lote_mp,lote_pro,materia_prima,rs.getInt("it_m_lt_qtde")));
                           
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    public boolean excluiNoAlteraListaItens(ItensSaida is) {
        String sql = "delete from itens_mont_lote where lo_numero = "+is.getLote().getNumero()+";";
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
    public boolean exclui(ItensSaida is) {
        String sql = "delete from itens_mont_lote where lo_numero = "+is.getLote().getNumero()+""
                + " and est_mp_lote == "+is.getLt_materia_prima()+";";
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
    public boolean exclui2(ItensSaida is) {
        String sql = "delete from itens_mont_lote where lo_numero= ?;";
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
