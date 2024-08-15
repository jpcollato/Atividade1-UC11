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
    ArrayList<ProdutosDTO> listagemVendidos = new ArrayList<>();

    public int cadastrarProduto(ProdutosDTO produto) {
        int status;
        int erro;
        String valor = String.valueOf(produto.getValor());
        try {
            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement("insert into produtos(nome, valor, status) values (?, ?, ?)");
            prep.setString(1, produto.getNome());
            prep.setString(2, valor);
            prep.setString(3, produto.getStatus());

            status = prep.executeUpdate();
            return status;
        } catch (SQLException sqle) {
            erro = sqle.getErrorCode();
            System.out.println("Erro " + erro);
            return erro;
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {

            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("select * from produtos");
            resultset = prep.executeQuery();
            

            while (resultset.next()) {
                ProdutosDTO p = new ProdutosDTO();
                int id = Integer.parseInt(resultset.getString("id"));
                p.setId(id);
                p.setNome(resultset.getString("nome"));
                int valor = Integer.parseInt(resultset.getString("valor"));
                p.setValor(valor);
                p.setStatus(resultset.getString("status"));
                listagem.add(p);
            }
            return listagem;
        } catch (SQLException sqle) {
            System.out.println("Erro ao consultar!");
            return null;
        }
    }
    
    public int venderProduto(int id){
        int status;
        int erro;
        
        try{
            String idStr = String.valueOf(id);
            
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("update produtos set status = 'Vendido' where id = ?");
            prep.setString(1, idStr);
            status = prep.executeUpdate();
            
            return status;
        } catch(SQLException sqle){
            erro = sqle.getErrorCode();
            System.out.println("Erro " + erro);
            return erro;
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        try {

            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("select * from produtos where status = 'Vendido'");
            resultset = prep.executeQuery();
            

            while (resultset.next()) {
                ProdutosDTO p = new ProdutosDTO();
                int id = Integer.parseInt(resultset.getString("id"));
                p.setId(id);
                p.setNome(resultset.getString("nome"));
                int valor = Integer.parseInt(resultset.getString("valor"));
                p.setValor(valor);
                p.setStatus(resultset.getString("status"));
                listagemVendidos.add(p);
            }
            return listagemVendidos;
        } catch (SQLException sqle) {
            System.out.println("Erro ao consultar!");
            return null;
        }
    }

}
