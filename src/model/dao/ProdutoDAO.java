/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author luisf
 */
public class ProdutoDAO {
    
    public void create (Produto produto){
        
            Connection con = ConnectionFactory.getConnection(); //metodo responsavel por inserir dados no BD
            PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO produto(descricao,qtd,preco) VALUES (?,?,?)");
            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, produto.getQtd());
            stmt.setDouble(3, produto.getPreco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    
    }
    public ArrayList<Produto> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        ArrayList<Produto> listaProdutos = new ArrayList<>();
    
        try{
            stmt = con.prepareStatement("SELECT * from produto order by idproduto");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Produto prod = new Produto();
                prod.setIdProduto(rs.getInt("idproduto"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setQtd(rs.getInt("qtd"));
                prod.setPreco(rs.getDouble("preco"));
                listaProdutos.add(prod);
                
                
            }
            
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }
    
        return listaProdutos;
    }
     public void update (Produto produto){
        
            Connection con = ConnectionFactory.getConnection(); 
            PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE produto set descricao = ?, qtd = ?, preco = ? WHERE idproduto = ?");
            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, produto.getQtd());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4,produto.getIdProduto());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    
    }
     public void delete (Produto produto){
        
            Connection con = ConnectionFactory.getConnection(); 
            PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE idproduto = ?");
            stmt.setInt(1,produto.getIdProduto());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
     
}
     
     public ArrayList<Produto> getListaProdutoPorDescricao(String descricao){
            
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
        ArrayList<Produto> listaProdutos = new ArrayList<>();
    
        try{
            stmt = con.prepareStatement("SELECT * from produto WHERE descricao LIKE ? ORDER BY idproduto");
            stmt.setString(1,"%" + descricao + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Produto prod = new Produto();
                prod.setIdProduto(rs.getInt("idproduto"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setQtd(rs.getInt("qtd"));
                prod.setPreco(rs.getDouble("preco"));
                listaProdutos.add(prod);
                
            }
            
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }
    
        return listaProdutos;
     
     }
}