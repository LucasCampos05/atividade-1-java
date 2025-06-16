
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucas campos paes
 * 16/06/2025
 */
public class ClientesDAO {
    private conexao conexao;
    private Connection conn;
    public ClientesDAO(){
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    public void inserir(Clientes clientes){
        String sql = "INSERT INTO cliente (cli_CPF, cli_nome, cli_email) VALUES (?,?,?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, clientes.getCPF());
            stmt.setString(2, clientes.getNome());
            stmt.setString(3, clientes.getEmail());
            stmt.execute();
        } catch(SQLException ex){
            System.out.println("Erro ao inserir clientes: " + ex.getMessage());
        }
    }  
    public Clientes getCliente(String id){
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            Clientes c = new Clientes();
            rs.first();
            c.setCPF(id);
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            return c;
        }
        catch (SQLException ex){
            System.out.println("Erro ao consultar clientes: " + ex.getMessage());
            return null;
        }
    }
    public void editar(Clientes clientes){
        try{
            String sql = "UPDATE pessoa set cli_nome=?, cli_email=? WHERE cli_CPF=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, clientes.getNome());
            stmt.setString(2, clientes.getEmail());
            stmt.setString(3, clientes.getCPF());
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar clientes: "+ex.getMessage());
        }
    }
    public void excluir(int id){
        try{
            String sql = "delete from clientes WHERE cli_CPF=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao excluir clientes: "+ ex.getMessage());
        }
    }
}
