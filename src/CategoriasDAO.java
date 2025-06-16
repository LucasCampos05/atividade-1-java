
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 *
 * @author lucas campos paes
 * 16/06/2025
 */
public class CategoriasDAO {
    private conexao conexao;
    private Connection conn;
    public CategoriasDAO(){
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    public void inserir(Categorias categoria){
        String sql = "INSERT INTO categorias (cat_idCategorias, cat_nome, cat_descricao) VALUES (?,?,?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, categoria.getIdCategoria());
            stmt.setString(2, categoria.getNome());
            stmt.setString(3, categoria.getDescricao());
            stmt.execute();
        } catch(SQLException ex){
            System.out.println("Erro ao inserir pessoa: " + ex.getMessage());
        }
    }  
    public Categorias getidCategoria(int id){
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Categorias c = new Categorias();
            rs.first();
            c.setIdCategoria(id);
            c.setNome(rs.getString("nome"));
            c.setDescricao(rs.getString("descrição"));            
            return c;
        }
        catch (SQLException ex){
            System.out.println("Erro ao consultar categorias: " + ex.getMessage());
            return null;
        }
    }
    public void editar(Categorias categorias){
        try{
            String sql = "UPDATE categorias set cat_nome=?, cat_descricao=? WHERE cat_idCategorias=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categorias.getNome());
            stmt.setString(2, categorias.getDescricao());
            stmt.setInt(4, categorias.getIdCategoria());
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar categorias: "+ex.getMessage());
        }
    }
    public void excluir(int id){
        try{
            String sql = "delete from categorias WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao excluir categorias: "+ ex.getMessage());
        }
    }
}
