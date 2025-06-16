/*
* lucas campos paes
* 16/06/2025
*/
 
import java.sql.Connection;
import java.sql.DriverManager;
 
/**
*
* @author 2830482321029
*/
public class conexao {
    public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/at1?useTimezone=true&serverTimezone=UTC", "root", "root");
            System.out.println("Conexão realizada com sucesso!");
            return conn;
        }
        catch(Exception e){
            System.out.println("Erro ao conectar no BD" + e.getMessage());
            return null;
        }
    }
}