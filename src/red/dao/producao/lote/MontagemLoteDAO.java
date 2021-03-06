
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
                + "lo_data_fim, lo_qtde_prod_montado , lo_validade from montagem_lote where lo_qtde_pro >0  and  lo_numero== "+numeroLote+""
                + " and pro_codigo== "+produto.getCodigo()+" ;";
        ProdutoDAO daoPro = new ProdutoDAO();
    
        Colaborador col = null, colaborador = new Colaborador();
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement st = conn.prepareStatement(sql)) {

                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            col =colaborador.busca(rs.getInt("col_codigo"));
                            return new MontagemLote(rs.getInt("lo_numero"),
                                    produto,
                                    col,
                                    rs.getDate("lo_data_inicio").toLocalDate(),
                                    rs.getDate("lo_data_fim").toLocalDate(),
                                    rs.getInt("lo_qtde_prod_montado"),
                                 //   rs.getDate("lo_validade").toLocalDate()
                                    null
                            );
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
                + "lo_data_fim, lo_qtde_prod_montado , lo_validade from montagem_lote where lo_numero = ? ";
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
                                    rs.getInt("lo_qtde_prod_montado"),
                                  //  rs.getDate("lo_validade").toLocalDate()
                            null);
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
   
    public boolean insere(MontagemLote ml){//ok

        String sql="insert into montagem_lote(pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_qtde_prod_montado, lo_validade) values("+ml.getProduto().getCodigo()+","
                +ml.getColaborador().getCodigo()+",'"+ml.getData_inicio()+"','"+ml.getData_fim()+"',"
                +ml.getQtde_montada()+",null);";
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
    public List<MontagemLote> lista(){//metodo conferido
 
        String sql="select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_qtde_prod_montado, lo_validade from montagem_lote";

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
                                    rs.getInt("lo_qtde_prod_montado"),
                                   // rs.getDate("lo_validade").toLocalDate())
                           null) );
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
    
   
    public  boolean altera(MontagemLote ml) {
       
        String sql = "UPDATE montagem_lote set pro_codigo="+ml.getProduto().getCodigo()+", "
                + "col_codigo="+ml.getColaborador().getCodigo()+", lo_data_inicio= '"+ml.getData_inicio()+"', "
                + "lo_data_fim= '"+ml.getData_fim()+"', lo_qtde_prod_montado= "+ml.getQtde_montada()+" "
                + ",lo_validade= null where lo_numero="+ml.getNumero()+";";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                       
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando o lote!";
        }
        return false;
    }
    public boolean exclui(MontagemLote ml) {
        String sql = "delete from montagem_lote where lo_numero = "+ml.getNumero()+";";
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
    
    public int QtdLote()
    {
        String sql = "select count(*) from montagem_lote;";
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
        
        String sql="select * from montagem_lote where lo_numero= ? order by lo_numero;";
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
                                    rs.getInt("lo_qtde_prod_montado"),
                                //    rs.getDate("lo_validade").toLocalDate())
                            null));
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
                + "lo_data_fim, lo_qtde_prod_montado, lo_validade from montagem_lote where lo_numero = ?  order by lo_numero;";
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
                                    rs.getInt("lo_qtde_prod_montado"),
                                //    rs.getDate("lo_validade").toLocalDate()
                            null));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
     
    public int ultimoNumero(){
        String sql="select lo_numero from montagem_lote order by lo_numero;";
        
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
        return numero;
    }
    public MontagemLote getSaldo(int numero) {
        String sql = "select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_qtde_prod_montado, lo_validade from montagem_lote where lo_numero = ? ";
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
                                    rs.getInt("lo_qtde_prod_montado"),
                             //       rs.getDate("lo_validade").toLocalDate()
                            null);
                        }
                    }
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public boolean atualizaEstoqueLote(MontagemLote lote){
        String sql = "update montagem_lote set lo_estoque="+0+"  where lo_numero="+lote.getNumero()+";";
        try (Connection conn = Conecta.abreConexaoBanco()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                      
                            ps.executeUpdate();
                            return true;
                }
            }
        } catch (SQLException e) {
            erro = "Erro alterando lote!";
        }
        return false;
    }
    
    public List<MontagemLote> pesquisaData(LocalDate inicio, LocalDate fim){
        
//        String sql="select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
//                + "lo_data_fim, lo_estoque  from lote where  "
//                + "lo_data_inicio >= '"+inicio+"' and lo_data_fim <= '"+fim+"';";
        
        String sql="select lo_numero, pro_codigo, col_codigo, lo_data_inicio, "
                + "lo_data_fim, lo_qtde_prod_montado, lo_validade from montagem_lote where  "
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
                                    rs.getInt("lo_qtde_prod_montado"),
                                //    rs.getDate("lo_validade").toLocalDate()
                            null));
                        }
                    } 
                } 
            }
        } catch (SQLException e) {
        }              
        return lista;
    }
     
}
