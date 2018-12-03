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
import red.dao.util.Conecta;
import red.dao.util.RegiaoDAO;
import red.model.colaborador.Colaborador;
import red.model.producao.rastreabilidade.MontagemCarga;
import red.model.util.Regiao;

/**
 *
 * @author Daniel
 */
public class MontagemCargaDAO {
    
 
     private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public MontagemCargaDAO() {
        erro=null;
    }
    
    
    public MontagemCarga busca(int codigo) {
        String sql = "select ca_codigo, re_codigo, col_codigo, ca_data_inicio, "
                + "ca_data_fim, ca_status  from cargas where ca_codigo = ? ";
        RegiaoDAO daoRegiao = new RegiaoDAO();
       Regiao regiao = null;
        Colaborador col = null, colaborador = new Colaborador();
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, codigo);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            regiao= daoRegiao.busca(rs.getInt("re_codigo"));  
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            return new MontagemCarga(rs.getInt("ca_codigo"),
                                    regiao,
                                    col,
                                    rs.getDate("ca_data_inicio").toLocalDate(),
                                    rs.getDate("ca_data_fim").toLocalDate(),
                                    rs.getBoolean("ca_status"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
   
    public boolean insere(MontagemCarga ca){//certo
     
        String sql="insert into cargas(re_codigo, col_codigo, ca_data_inicio, "
                + "ca_data_fim, ca_status) values("+ca.getRegiao().getCodigo()+","+
                 ca.getColaborador().getCodigo()+",'"+ca.getData_inicio()+" ','"+
                ca.getData_fim()+"' ,"+ca.isStatus()+");";
                try(Connection conn = Conecta.abreConexaoBanco()) {
                    if(conn !=null){
                        try (PreparedStatement ps = conn.prepareStatement(sql)){
                        
                            ps.executeUpdate();
                            return true;
                        } 
                    }
        } catch (SQLException e) {
            erro="erro ao gravar";
                    System.out.println(e);
        }
       return false; 
    }
    public List<MontagemCarga> lista(){
        
        String sql="select ca_codigo, re_codigo, col_codigo, ca_data_inicio, "
                + "ca_data_fim, ca_status from cargas";
      List<MontagemCarga> lista =new ArrayList<>();
        RegiaoDAO daoRegiao = new RegiaoDAO();
       Regiao regiao = null;
        Colaborador colaborador = new Colaborador();
        Colaborador  col=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                           regiao= daoRegiao.busca(rs.getInt("re_codigo"));  
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            lista.add(new MontagemCarga(rs.getInt("ca_codigo"),
                                    regiao,
                                    col,
                                    rs.getDate("ca_data_inicio").toLocalDate(),
                                    rs.getDate("ca_data_fim").toLocalDate(),
                                    rs.getBoolean("ca_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
   
    public  boolean altera(MontagemCarga carga) {
        String sql = "update cargas set re_codigo="+carga.getRegiao().getCodigo()+","
                + " col_codigo="+carga.getColaborador().getCodigo()+","
                + " ca_data_inicio='"+carga.getData_inicio()+" ',"
                + "ca_data_fim='"+carga.getData_inicio()+" ',"+""
                + " ca_status="+carga.isStatus()+" where ca_codigo="+carga.getCodigo()+";";
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
    public boolean exclui(MontagemCarga carga) {
        String sql = "delete from cargas where ca_codigo = "+carga.getCodigo()+";";
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
        String sql = "select count(*) from cargas;";
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
    
     public List<MontagemCarga> pesquisa(String chave){
        
       
         String sql="select ca_codigo, re_codigo, col_codigo, ca_data_inicio, "
                + "ca_data_fim, ca_status  from cargas where "
                + "like '%"+chave+"%' ;";
        
        List<MontagemCarga> lista =new ArrayList<>();
        RegiaoDAO daoRegiao = new RegiaoDAO();
       Regiao regiao = null;
        Colaborador colaborador = null;
        Colaborador  col=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                          regiao= daoRegiao.busca(rs.getInt("re_codigo"));  
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            lista.add(new MontagemCarga(rs.getInt("ca_codigo"),
                                    regiao,
                                    col,
                                    rs.getDate("ca_data_inicio").toLocalDate(),
                                    rs.getDate("ca_data_fim").toLocalDate(),
                                    rs.getBoolean("ca_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
     public List<MontagemCarga> pesquisa2(int numero){
        
        String sql="select ca_codigo, re_codigo, col_codigo, ca_data_inicio, "
                + "ca_data_fim, ca_status from cargas where ca_codigo = ? ";
       
        
        List<MontagemCarga> lista =new ArrayList<>();
        RegiaoDAO daoRegiao = new RegiaoDAO();
       Regiao regiao = null;
        Colaborador colaborador = new Colaborador();
        Colaborador  col=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, numero);
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                         regiao= daoRegiao.busca(rs.getInt("re_codigo"));  //certo
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            lista.add(new MontagemCarga(rs.getInt("ca_codigo"),
                                    regiao,
                                    col,
                                    rs.getDate("ca_data_inicio").toLocalDate(),
                                    rs.getDate("ca_data_fim").toLocalDate(),
                                    rs.getBoolean("ca_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
     
    public int ultimoNumero(){
        String sql="select ca_codigo from cargas order by ca_codigo;";
        
        int numero=0;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                                    numero=rs.getInt("ca_codigo");
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }           
        return numero+1;
    }
     public List<MontagemCarga> pesquisa3(int numero){
        
        String sql="select ca_codigo, re_codigo, col_codigo, ca_data_inicio, "
                + "ca_data_fim, ca_status from cargas where re_codigo = ? ";
       
        
        List<MontagemCarga> lista =new ArrayList<>();
        RegiaoDAO daoRegiao = new RegiaoDAO();
       Regiao regiao = null;
        Colaborador colaborador = new Colaborador();
        Colaborador  col=null;
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, numero);
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                         regiao= daoRegiao.busca(rs.getInt("re_codigo"));  //certo
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            lista.add(new MontagemCarga(rs.getInt("ca_codigo"),
                                    regiao,
                                    col,
                                    rs.getDate("ca_data_inicio").toLocalDate(),
                                    rs.getDate("ca_data_fim").toLocalDate(),
                                    rs.getBoolean("ca_status")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
    
    
    
    
    
    
}
