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
import red.model.producao.lote.Composicao;
import red.model.producao.lote.Produto;

/**
 *
 * @author Daniel
 */
public class ComposicaoDAO {
    
    private String erro="";

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    
    public ComposicaoDAO() {
        erro=null;
    }
    
    public Composicao busca(int cod_produto) {
        String sql = "select * from composicao where pro_codigo=?;";
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto produto = null;
        MateriaPrimaDAO daoMp = new MateriaPrimaDAO();
        MateriaPrima materia_prima = null; 
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, cod_produto);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            produto = daoPro.busca(rs.getInt("pro_codigo"));
                            materia_prima = daoMp.busca(rs.getInt("mp_codigo"));
                            return new Composicao(materia_prima, 
                                    produto,rs.getInt("comp_qtde"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public boolean insere(Composicao c){
        
        String sql="insert into composicao(mp_codigo,pro_codigo,comp_qtde) values(?,?,?);";
                try(Connection conn = Conecta.abreConexaoBanco()) {
                    if(conn !=null){
                        try (PreparedStatement ps = conn.prepareStatement(sql)){
                           ps.setInt(1, c.getMateria_prima().getCodigo());
                            ps.setInt(2, c.getProduto().getCodigo());
                            ps.setInt(3, c.getQtde());
                            ps.executeUpdate();
                            return true;
                        } 
                    }
        } catch (SQLException e) {
            erro="erro ao gravar";
        }
       return false; 
    }
    public List<Composicao> lista(){
        
        String sql="select mp_codigo,pro_codigo,comp_qtde from composicao order by mp_codigo;";
        List<Composicao> lista =new ArrayList<>();
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto produto = null;
        MateriaPrimaDAO daoMp = new MateriaPrimaDAO();
        MateriaPrima materia_prima = null; 
        try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                            produto = daoPro.busca(rs.getInt("pro_codigo"));
                            materia_prima = daoMp.busca(rs.getInt("mp_codigo"));
                            lista.add(new Composicao(materia_prima, 
                                    produto,rs.getInt("comp_qtde")));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
    public  boolean altera(Composicao c) {
        String sql = "update composicao set mp_codigo = ?, pro_codigo=?, comp_qtde=? "
                + "where mp_codigo =  "+c.getMateria_prima().getCodigo()+"  AND  pro_codigo= "+c.getProduto().getCodigo()+" ; ";
  
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                            ps.setInt(1, c.getMateria_prima().getCodigo());
                            ps.setInt(2, c.getProduto().getCodigo());
                            ps.setInt(3, c.getQtde());
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando a composicao!";
        }
        return false;
    }
    public List<Composicao> get(int codigo)
    {   
        String sql="select * from composicao where pro_codigo="+codigo+";";
         List<Composicao> lista =new ArrayList<>();
        ProdutoDAO daoPro = new ProdutoDAO();
        Produto produto = null;
        MateriaPrimaDAO daoMp = new MateriaPrimaDAO();
        MateriaPrima materia_prima = null; 
         try (Connection conn = Conecta.abreConexaoBanco()){
            if(conn !=null){
                try(PreparedStatement ps = conn.prepareStatement(sql)) {
                    try(ResultSet rs = ps.executeQuery()) {
                        while(rs.next()){
                           produto = daoPro.busca(rs.getInt("pro_codigo"));
                            materia_prima = daoMp.busca(rs.getInt("mp_codigo"));
                            
                            lista.add(new Composicao(materia_prima, 
                                    produto,rs.getInt("comp_qtde")));
                            
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    public boolean exclui(Composicao p) {
        String sql = "delete from composicao where mp_codigo = ? and pro_codigo=?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, p.getMateria_prima().getCodigo());
                     ps.setInt(2, p.getProduto().getCodigo());
                    ps.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
          //  erro = "Erro excluindo produto!";
        }
        return false;
    }
    public boolean exclui2(Composicao p) {
        String sql = "delete from composicao where mp_codigo = ? and pro_codigo = ?;";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, p.getMateria_prima().getCodigo());
                    ps.setInt(2, p.getProduto().getCodigo());
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
