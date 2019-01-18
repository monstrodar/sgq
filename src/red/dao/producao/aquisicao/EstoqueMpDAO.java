/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.dao.producao.aquisicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import red.dao.util.Conecta;
import red.model.producao.aquisicao.Entrada;
import red.model.producao.aquisicao.EstoqueMP;
import red.model.producao.aquisicao.MateriaPrima;

/**
 *
 * @author Daniel
 * @author 羽根川
 */
public class EstoqueMpDAO {
    
    
     private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public EstoqueMpDAO() {
        erro=null;
    }
   public boolean insere(EstoqueMP ml){
     
        String sql="insert into estoque_mp(est_mp_lote, mp_codigo, est_mp_qtde_rec, "
                + "est_mp_qtde_aprovado, est_mp_qtde_conferir, est_mp_qtde_descarte,"
                + "est_mp_qtde_montado, est_mp_validade, est_mp_data_mov, rec_numero) values("+ml.getLote_mp()+","
                +ml.getMateria_prima().getCodigo()+","+ml.getQtde_rec()+","+ml.getQtde_aprovada()+","+ml.getQtde_conferir()+","
                +ml.getQtde_descarte()+","+ml.getQtde_montada()+",null,'"+ml.getData_movimentacao()+"',"
                +ml.getRecebimento_mp().getNumero()+");";
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
    public List<EstoqueMP> lista(){
        
 String sql="select est_mp_lote, mp_codigo, est_mp_qtde_rec, "
                + "est_mp_qtde_aprovado, est_mp_qtde_conferir, est_mp_qtde_descarte,"
                + "est_mp_qtde_montado, est_mp_validade, est_mp_data_mov, rec_numero"
                + " from estoque_mp order by est_mp_lote;";
// String sql="select * from estoque_mp order by mp_codigo;";
      List<EstoqueMP> lista =new ArrayList<>();
        MateriaPrimaDAO daoMP = new MateriaPrimaDAO();
        MateriaPrima mp = null;
        Entrada entrada = new Entrada();
        Entrada  ent=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            mp= daoMP.busca(rs.getInt("mp_codigo"));
                            ent =entrada.busca(rs.getInt("rec_numero"));
                           lista.add(new EstoqueMP(rs.getString("est_mp_lote"),
                                    mp,
                                    rs.getInt("est_mp_qtde_rec"),
                                    rs.getInt("est_mp_qtde_aprovado"),
                                    rs.getInt("est_mp_qtde_conferir"),
                                    rs.getInt("est_mp_qtde_descarte"),
                                    rs.getInt("est_mp_qtde_montado"),
                                   // rs.getDate("est_mp_validade").toLocalDate(),
                                    null,
                                    rs.getDate("est_mp_data_mov").toLocalDate(),
                                    ent));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    public List<EstoqueMP> lista2(MateriaPrima mp){//ok//ok
        
//       String sql="select est_mp_lote, mp_codigo, est_mp_qtde_rec, "
//                + "est_mp_qtde_aprovado, est_mp_qtde_conferir, est_mp_qtde_descarte,"
//                + "est_mp_qtde_montado, est_mp_validade, est_mp_data_mov, rec_numero"
//                + " from estoque_mp where mp_codigo = "+mp.getCodigo()+"  order by est_mp_lote;";
 String sql="select * from estoque_mp where  mp_codigo = "+mp.getCodigo()+"  order by est_mp_lote;";
      List<EstoqueMP> lista =new ArrayList<>();
        MateriaPrimaDAO daoMP = new MateriaPrimaDAO();
        Entrada entrada = new Entrada();
        Entrada  ent=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            mp= daoMP.busca(rs.getInt("mp_codigo"));
                            ent =entrada.busca(rs.getInt("rec_numero"));//col e pmp esta retornndo null e caussandoexption
                            lista.add(new EstoqueMP(rs.getString("est_mp_lote"),
                                    mp,
                                    rs.getInt("est_mp_qtde_rec"),
                                    rs.getInt("est_mp_qtde_aprovado"),
                                    rs.getInt("est_mp_qtde_conferir"),
                                    rs.getInt("est_mp_qtde_descarte"),
                                    rs.getInt("est_mp_qtde_montado"),
                                    null,
                                //    rs.getDate("est_mp_validade").toLocalDate(),
                                    rs.getDate("est_mp_data_mov").toLocalDate(),
                                    ent
                            ));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
     public  boolean altera(EstoqueMP ml) {
       
        String sql = "update estoque_mp set est_mp_lote= "+ml.getLote_mp()+", "
                + "mp_codigo= "+ml.getMateria_prima().getCodigo()+", est_mp_qtde_rec= "+ml.getQtde_rec()+", "
                + "est_mp_qtde_aprovado= "+ml.getQtde_aprovada()+", est_mp_qtde_conferir= "+ml.getQtde_conferir()+", "
                + "est_mp_qtde_descarte= "+ml.getQtde_descarte()+", est_mp_qtde_montado= "+ml.getQtde_montada()+","
                + "est_mp_validade=null, est_mp_data_mov= '"+ml.getData_movimentacao()+"',"
                +" where lo_numero= "+ml.getRecebimento_mp().getNumero()+";";
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
    
    public EstoqueMP busca(String  numeroLote, MateriaPrima mp){//ok
        String sql = "select est_mp_lote, mp_codigo, est_mp_qtde_rec, "
                + "est_mp_qtde_aprovado, est_mp_qtde_conferir, est_mp_qtde_descarte,"
                + "est_mp_qtde_montado, est_mp_validade, est_mp_data_mov, rec_numero"
                + " from estoque_mp where "
                + "est_mp_lote = '"+ numeroLote+"' and "+ mp.getCodigo()+" = mp_codigo; ";
        Entrada entrada = new Entrada();
        Entrada  ent=null;
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {

                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                        
                            ent =entrada.busca(rs.getInt("rec_numero"));
                            return new EstoqueMP(rs.getString("est_mp_lote"),
                                    mp,
                                    rs.getInt("est_mp_qtde_rec"),
                                    rs.getInt("est_mp_qtde_aprovado"),
                                    rs.getInt("est_mp_qtde_conferir"),
                                    rs.getInt("est_mp_qtde_descarte"),
                                    rs.getInt("est_mp_qtde_montado"),
                                    null,
                                //    rs.getDate("est_mp_validade").toLocalDate(),
                                    rs.getDate("est_mp_data_mov").toLocalDate(),
                                    ent
                            );
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    } 
    public List<EstoqueMP> listaMPs(MateriaPrima mp){
        
        String sql="select est_mp_lote, mp_codigo, est_mp_qtde_rec, "
                + "est_mp_qtde_aprovado, est_mp_qtde_conferir, est_mp_qtde_descarte,"
                + "est_mp_qtde_montado, est_mp_validade, est_mp_data_mov, est_mp_rec_numero"
                + " from estoque_mp where "
                + mp.getCodigo()+" = mp_codigo order by mp_codigo;";
      List<EstoqueMP> lista =new ArrayList<>();
     //   MateriaPrimaDAO daoMP = new MateriaPrimaDAO();
     //   MateriaPrima mp = null;
        Entrada entrada = new Entrada();
        Entrada  ent=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                //            mp= daoMP.busca(rs.getInt("mp_codigo"));
                            ent =entrada.busca(rs.getInt("est_mp_rec_numero"));
                            lista.add(new EstoqueMP(rs.getString("est_mp_lote"),
                                    mp,
                                    rs.getInt("est_mp_qtde_rec"),
                                    rs.getInt("est_mp_qtde_aprovado"),
                                    rs.getInt("est_mp_qtde_conferir"),
                                    rs.getInt("est_mp_qtde_descarte"),
                                    rs.getInt("est_mp_qtde_montado"),
                                    rs.getDate("est_mp_validade").toLocalDate(),
                                    rs.getDate("est_mp_data_mov").toLocalDate(),
                                    ent));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
    public MateriaPrima busca(String  numeroLote){
        String sql = "select  mp_codigo from estoque_mp where "
                + "est_mp_lote = "+ numeroLote+"; ";
        Entrada entrada = new Entrada();
        Entrada  ent=null;
        MateriaPrimaDAO daoMP = new MateriaPrimaDAO();
        MateriaPrima mp = null;
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                   
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                         mp= daoMP.busca(rs.getInt("mp_codigo"));  ///erro

return mp;
//                            ent =entrada.busca(rs.getInt("rec_numero"));
//                            return new EstoqueMP(
//                                    rs.getString("est_mp_lote"),
//                                    mp,
//                                    rs.getInt("est_mp_qtde_rec"),
//                                    rs.getInt("est_mp_qtde_aprovado"),
//                                    rs.getInt("est_mp_qtde_conferir"),
//                                    rs.getInt("est_mp_qtde_descarte"),
//                                    rs.getInt("est_mp_qtde_montado"),
//                                    rs.getDate("est_mp_validade").toLocalDate(),
//                                    rs.getDate("est_mp_data_mov").toLocalDate(),
//                                    ent
                          //  );
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    } 
    public boolean exclui(String numeroLote, MateriaPrima mp) {
        String sql = "delete from estoque_mp where "
                + "est_mp_lote = '"+ numeroLote+"' and "+ mp.getCodigo()+" = mp_codigo; ";
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
    
    public boolean Exist(EstoqueMP mp)//Add 羽根川
    {
        String sql="select est_mp_lote, mp_codigo, est_mp_qtde_rec, "
                + "est_mp_qtde_aprovado, est_mp_qtde_conferir, est_mp_qtde_descarte,"
                + "est_mp_qtde_montado, est_mp_validade, est_mp_data_mov, est_mp_rec_numero"
                + " from estoque_mp where "
                + mp.getMateria_prima().getCodigo()+" = mp_codigo and "+mp.getLote_mp()+" = est_mp_lote;";
        List<EstoqueMP> lista =new ArrayList<>();
        Entrada entrada = new Entrada();
        Entrada  ent=null;
        try (Connection conn = Conecta.abreConexaoBanco())
        {
            if(conn !=null)
            {
                try(PreparedStatement ps = conn.prepareStatement(sql)) 
                {
                    try(ResultSet rs = ps.executeQuery()) 
                    {
                        if(rs.next())
                        {
                            return true;
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return false;
    }
}
