
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucas campos paes
 * 16/06/2025
 */
public class ProdutosDAO {
    private conexao conexao;
    private Connection conn;
    public ProdutosDAO(){
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    public void inserir(Produtos produtos){
        String sql = "INSERT INTO produtos (pro_codigoBarra, pro_nome, pro_preco, Categorias_cat_idCategorias) VALUES (?,?,?,?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produtos.getCodigoBarra());
            stmt.setString(2, produtos.getNome());
            stmt.setFloat(3, produtos.getPreco());
            stmt.setInt(4, produtos.getIdCategoria());
            stmt.execute();
        } catch(SQLException ex){
            System.out.println("Erro ao inserir pessoa: " + ex.getMessage());
        }
    }  
    public Produtos getProdutos(String id){
        String sql = "SELECT * FROM produtos WHERE pro_codigoBarra = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            Produtos pro = new Produtos();
            rs.first();
            pro.setCodigoBarra(id);
            pro.setNome(rs.getString("nome"));
            pro.setPreco(rs.getFloat("Preço"));
            pro.setIdCategoria(rs.getInt("idCategoria"));
            return pro;
        }
        catch (SQLException ex){
            System.out.println("Erro ao consultar produtos: " + ex.getMessage());
            return null;
        }
    }
    public void editar(Produtos produtos){
        try{
            String sql = "UPDATE pessoa set nome=?, sexo=?, idioma=? WHERE pro_codigoBarra=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produtos.getCodigoBarra());
            stmt.setString(2, produtos.getNome());
            stmt.setFloat(3, produtos.getPreco());
            stmt.setInt(4, produtos.getIdCategoria());
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar pessoa: "+ex.getMessage());
        }
    }
    public void excluir(String id){
        try{
            String sql = "delete from produtos WHERE pro_codigoBarra=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao excluir produtos: "+ ex.getMessage());
        }
    }
}
