/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        int status;
        int erro;
        String valor = String.valueOf(produto.getValor());
        try{
        conn = new conectaDAO().connectDB();
        
        prep = conn.prepareStatement("insert into produtos(nome, valor, status) values (?, ?, ?)");
        prep.setString(1, produto.getNome());
        prep.setString(2, valor);
        prep.setString(3, produto.getStatus());
        
        status = prep.executeUpdate();
        return status;
                } catch (SQLException sqle) {
            erro = sqle.getErrorCode();
            System.out.println("Erro "+erro);
            return erro;
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

