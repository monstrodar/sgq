/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.model.producao.aquisicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.conexcao.Conexao;
import red.dao.producao.aquisicao.ItensEntrada;
import red.model.colaborador.Colaborador;

/**
 *
 * @author Bruno Yoshino
 */
public class Entrada {
    private int numero;
    private Colaborador col;
    private LocalDate data;
    private PedidoMP pmp;

    private int lote;
    
    public Entrada(int numero, LocalDate data, int lote) {
        this.numero = numero;
        this.data = data;
        this.lote = lote;
        this.col = null;
        this.pmp = null;
    }

    public Entrada(int numero, Colaborador col, LocalDate data, PedidoMP pmp) {
        this.numero = numero;
        this.col = col;
        this.data = data;
        this.pmp = pmp;
    }

    public Entrada(int numero) {
        this.numero = numero;
    }

    public Entrada() {
        this.numero = 0;
        this.col = null;
        this.data = null;
        this.pmp = null;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Colaborador getCol() {
        return col;
    }

    public void setCol(Colaborador col) {
        this.col = col;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public PedidoMP getPmp() {
        return pmp;
    }

    public void setPmp(PedidoMP pmp) {
        this.pmp = pmp;
    }
    
    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }
    
    //DAO Banco ----------------------------------------------------------------
    
    public Entrada busca(int numero)
    {
        
//        String sql = "select ie.ent_num, ie.mp_codigo, ie.ie_qtde "
//                   + "from itens_entrada ie, confere_mp cmp, conferencia c "
//                   + "where ie.ent_num = ? and cmp.ent_num = ie.ent_num and c.conf_num = cmp.conf_num and c.mp_codigo != ie.mp_codigo;";
        String sql = "select ent_num ,ent_data from entrada_mp  where ent_num = ?;";
        try(Connection con = Conexao.abre())
        {
            if(con != null)
            {
                try(PreparedStatement ps = con.prepareStatement(sql)) 
                {
                    ps.setInt(1, numero);
                    try(ResultSet rs = ps.executeQuery())
                    {
                        if(rs.next())
                        {
                            return new Entrada(rs.getInt(1), rs.getDate(2).toLocalDate(), 0);
                        }
                    }    
                } 
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(Colaborador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
