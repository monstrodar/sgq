/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
