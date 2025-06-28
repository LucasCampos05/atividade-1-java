
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
public class NotacabecalhoDAO {
    private conexao conexao;
    private Connection conn;
    public NotacabecalhoDAO(){
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    public void inserir(NotaCabecalho notaCabecalho){
        String sql = "INSERT INTO NotaCabecalho (not_id, not_data, not_valorTotal, cli_CPF, for_CNPJ) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, notaCabecalho.getId());
            stmt.setString(2, notaCabecalho.getData());
            stmt.setDouble(3, notaCabecalho.getValorTotal());
            stmt.setString(4, notaCabecalho.getCPF());
            stmt.setString(5, notaCabecalho.getCNPJ());
            stmt.execute();
        } catch(SQLException ex){
            System.out.println("Erro ao inserir notaCabecalho: " + ex.getMessage());
        }
    }  
    public NotaCabecalho getNotaCabecalho(String id){
        String sql = "SELECT * FROM NotaCabecalho WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            NotaCabecalho n = new NotaCabecalho();
            rs.first();
            n.setId(rs.getInt("not_id"));
            n.setData(rs.getString("not_data"));
            n.setValorTotal(rs.getDouble("not_valorTotal"));
            n.setValorTotal(rs.getInt("cli_CPF"));
            n.setValorTotal(rs.getInt("for_CNPJ"));
            
            return n;
        }
        catch (SQLException ex){
            System.out.println("Erro ao consultar notaCabecalho: " + ex.getMessage());
            return null;
        }
    }
    public void editar(NotaCabecalho notaCabecalho){
        try{
            String sql = "UPDATE notaCabecalho set not_data=?, not_valorTotal=?,  cli_CPF=?, for_CNPJ=? WHERE not_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, notaCabecalho.getData());
            stmt.setDouble(2, notaCabecalho.getValorTotal());
            stmt.setString(3, notaCabecalho.getCPF());
            stmt.setString(3, notaCabecalho.getCNPJ());
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao atualizar notaCabecalho: "+ex.getMessage());
        }
    }
    public void excluir(int id){
        try{
            String sql = "delete from notaCabecalho WHERE not_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex){
            System.out.println("Erro ao excluir notaCabecalho: "+ ex.getMessage());
        }
    }
}
