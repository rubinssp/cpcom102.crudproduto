/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

/**
 *
 * @author ASUS
 */
public class ProdutoDAO1 {
    public void create(){
        
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = null;
        
        try{
            String sql1 = "INSERT INTO produto (descricao, qtd, preco) VALUES ('teste1',15,5.55)";
            String sql2 = "INSERT INTO produto (descricao, qtd, preco) VALUES ('teste1',25,5.55)";
            String sql3 = "INSERT INTO produto (descricao, qtd, preco) VALUES ('teste1',35,5.55)";
            
            con.setAutoCommit(false);//interrompe o autocomit que vem true por defaul
            stmt = con.createStatement();
            
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            if(true){
                throw new SQLException();
            }
            stmt.executeUpdate(sql3);
            
            con.commit(); // executa a atualização do bd
            
        }catch(SQLException ex){
           Logger.getLogger(ProdutoDAO1.class.getName()).log(Level.SEVERE, null, ex);
           try{
               con.rollback();
           }catch(SQLException ex1){
               System.out.println("Erro ao salvar!!");
           }
        }
    }
}
