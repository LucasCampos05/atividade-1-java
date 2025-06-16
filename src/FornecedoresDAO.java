
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucas campos paes
 * 16/06/2025
 */
public class FornecedoresDAO {
    private conexao conexao;
    private Connection conn;
    public FornecedoresDAO(){
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    public void inserir(Fornecedores fornecedores){
        String sql = "INSERT INTO fornecedores (for_CNPJ, for_nome, for_nomeFantasia) VALUES (?,?,?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, fornecedores.getCNPJ());
            stmt.setString(2, fornecedores.getNome());
            stmt.setString(3, fornecedores.getNomeFantasia());
            stmt.execute();
        } catch(SQLException ex){
            System.out.println("Erro ao inserir fornecedores: " + ex.getMessage());
        }
    }  
    public Fornecedores getPessoa(String id){
        String sql = "SELECT * FROM fornecedores WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            Fornecedores f = new Fornecedores();
            rs.first();
            f.setCNPJ(id);
            f.setNome(rs.getString("nome"));
            f.setNomeFantasia(rs.getString("nome fantasia"));
            return f;
        }
        catch (SQLException ex){
            System.out.println("Erro ao consultar fornecedores: " + ex.getMessage());
            return null;
        }
    }
    public void editar(Fornecedores fornecedores){
        try{
            String sql = "UPDATE fornecedores set for_nome=?, for_nomeFantasia=? WHERE for_CNPJ=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fornecedores.getNome());
            stmt.setString(2, fornecedores.getNomeFantasia());
            stmt.setString(3, fornecedores.getCNPJ());
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar fornecedores: "+ex.getMessage());
        }
    }
    public void excluir(String id){
        try{
            String sql = "delete from fornecedores WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao excluir fornecedores: "+ ex.getMessage());
        }
    }
}
