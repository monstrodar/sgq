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
import red.model.producao.aquisicao.MateriaPrima;

/**
 *
 * @author Daniel
 */
public class MateriaPrimaDAO {
    
    
     private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public MateriaPrimaDAO() {
        erro=null;
    }
    //mp_codigo, mp_nome,  mp_status, mp_status
    public  MateriaPrima busca(int codigo) {//ok
        String sql = "select mp_codigo, mp_nome, mp_status "
                + "from mat_prima where mp_codigo = "+codigo+" ;";

        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                   
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            return new MateriaPrima(rs.getInt("mp_codigo"),
                                    rs.getString("mp_nome"), 
                                    rs.getBoolean("mp_status"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
   
    public List<MateriaPrima> lista(){
        
        String sql="select mp_codigo, mp_nome, mp_status "
                + "from mat_prima order by mp_nome;";
        List<MateriaPrima> lista =new ArrayList<>();
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lista.add(new 
                                    MateriaPrima(rs.getInt("mp_codigo"),
                                    rs.getString("mp_nome"), 
                                    rs.getBoolean("mp_status"))
                            );
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
       
    }
    public List<MateriaPrima> get(String filtro)
    {   
        
        String sql="select * from mat_prima";
        if (!filtro.isEmpty())
           sql+=" where "+filtro+"order by mp_nome";
         List<MateriaPrima> lista =new ArrayList<>();
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lista.add(new 
                                    MateriaPrima(rs.getInt("mp_codigo"),
                                    rs.getString("mp_nome"), 
                                    rs.getBoolean("mp_status"))
                            );
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    

    
    
    
    
    
    
}
