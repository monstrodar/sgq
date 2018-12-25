
package red.conexcao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexao {
            //"jdbc:postgresql://localhost/"+banco;
   // private static final String URL =  "jdbc:postgresql://localhost/sgq";
      private static final String URL = 
            "jdbc:postgresql://localhost/controle3";      
    private static final String usuario = "postgres";
    private static final String senha = "postgres123";
    
     public static Connection abre() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL,
                    usuario, senha);
            
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
