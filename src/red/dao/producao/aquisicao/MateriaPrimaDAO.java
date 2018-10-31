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
import red.model.producao.lote.Produto;

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
    //mp_codigo, mp_nome, mp_num_lote, mp_estoque, mp_status
    public  MateriaPrima busca(int codigo) {
        String sql = "select mp_codigo, mp_nome, mp_num_lote, mp_estoque, mp_status "
                + "from materia_prima where mp_codigo = ? ;";

        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, codigo);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            return new MateriaPrima(rs.getInt("mp_codigo"),
                                    rs.getString("mp_nome"), 
                                    rs.getString("mp_num_lote"),
                                    rs.getInt("mp_estoque"),
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
        
        String sql="select mp_codigo, mp_nome, mp_num_lote, mp_estoque, mp_status from materia_prima order by mp_nome;";
        List<MateriaPrima> lista =new ArrayList<>();
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            lista.add(new 
                                    MateriaPrima(rs.getInt("mp_codigo"),
                                    rs.getString("mp_nome"), 
                                    rs.getString("mp_num_lote"),
                                    rs.getInt("mp_estoque"),
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
        
        String sql="select * from materia_prima";
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
                                    rs.getString("mp_num_lote"),
                                    rs.getInt("mp_estoque"),
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
