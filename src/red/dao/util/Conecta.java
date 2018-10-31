/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Daniel
 */
public class Conecta {
     public static Connection abreConexaoBanco(){
        
         Connection conn; 
        try {
            
            Class.forName("org.postgresql.Driver");
            String url ="jdbc:postgresql://localhost/sgq";
            conn = DriverManager.getConnection(url,"postgres","postgres123");
            
        } catch (Exception e) {
            conn=null;
        }
        return conn;
    }
      private Connection connect;
    private String erro="";
     public ResultSet consultar(String sql)
    {   ResultSet rs=null;
        try {
           Statement statement = connect.createStatement();
             //ResultSet.TYPE_SCROLL_INSENSITIVE,
             //ResultSet.CONCUR_READ_ONLY);
           rs = statement.executeQuery( sql );
           //statement.close();
        }
        catch ( SQLException sqlex )
        { erro="Erro: "+sqlex.toString();
          return null;
        }
        return rs;
    }
}
