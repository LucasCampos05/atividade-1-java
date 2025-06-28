
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2830482321024
 */
public class Nota_itensDAO {
    private conexao conexao;
    private Connection conn;
    public Nota_itensDAO(){
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    public void inserir(Nota_itens nota_itens){
        String sql = "INSERT INTO nota_itens (nit_id, nit_quantidade, nit_valorUnitario, pro_codigoBarra, not_id) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, nota_itens.getId());
            stmt.setInt(2, nota_itens.getQuantidade());
            stmt.setDouble(3, nota_itens.getValorUnitario());
            stmt.setString(4, nota_itens.getCodigoBarra());
            stmt.setInt(5, nota_itens.getFkId());
            stmt.execute();
        } catch(SQLException ex){
            System.out.println("Erro ao inserir nota_itens: " + ex.getMessage());
        }
    }  
    public Nota_itens getNota_itens(String id){
        String sql = "SELECT * FROM Nota_itens WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            Nota_itens n = new Nota_itens();
            rs.first();
            n.setId(rs.getInt("nit_id"));
            n.setQuantidade(rs.getInt("nit_quantidade"));
            n.setValorUnitario(rs.getDouble("nit_valorUnitario"));
            n.setCodigoBarra(rs.getString("pro_codigoBarra"));
            n.setFkId(rs.getInt("not_id"));
            
            return n;
        }
        catch (SQLException ex){
            System.out.println("Erro ao consultar Nota_itens: " + ex.getMessage());
            return null;
        }
    }
    public void editar(Nota_itens nota_itens){
        try{
            String sql = "UPDATE nota_itens set nit_quantidade=?, nit_valorUnitario=?,  pro_codigoBarra=?, not_id=? WHERE nit_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nota_itens.getQuantidade());
            stmt.setDouble(2, nota_itens.getValorUnitario());
            stmt.setString(3, nota_itens.getCodigoBarra());
            stmt.setInt(3, nota_itens.getFkId());
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar nota_itens: "+ex.getMessage());
        }
    }
    public void excluir(int id){
        try{
            String sql = "delete from nota_itens WHERE nit_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao excluir nota_itens: "+ ex.getMessage());
        }
    }
}
