package red.dao.util;

//Singleton
public class Banco 
{
   private static Conexao conexao;
   private Banco(){}
   static public boolean conectar()
   {
      conexao=new Conexao("", 
                       "jdbc:postgresql://localhost/", "sgq",
                      "postgres","postgres123");
      return conexao.getEstadoConexao();
   }
   public static Conexao getCon()
   {
       return conexao;
   }    
}
