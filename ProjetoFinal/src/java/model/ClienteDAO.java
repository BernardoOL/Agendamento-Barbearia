package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ClienteDAO extends DataBaseDAO{
    
    public ClienteDAO() throws Exception{}
    
    public ArrayList<Cliente> getLista() throws Exception{
        
        ArrayList<Cliente> lista = new ArrayList<Cliente>(); // criar uma lista
        String SQL = "SELECT * FROM cliente"; //seleciona todos os usuários do BD
        this.conectar(); //Abre a conexão com o bd
        Statement stm = conn.createStatement(); 
        ResultSet rs = stm.executeQuery(SQL) ; // recuperando as informações vindo do BD
        while (rs.next()){
            Cliente c = new Cliente();
            c.setIdCliente(rs.getInt("idCliente"));
            c.setNome(rs.getString("nome"));
            c.setTelefone(rs.getString("telefone"));
            c.setCpf(rs.getString("cpf"));
            c.setRua(rs.getString("rua"));
            c.setQuadra(rs.getString("quadra"));
            c.setNumero(rs.getString("numero"));
            c.setCep(rs.getString("cep"));
            
            lista.add(c); //adicionar na lista
        }
        this.desconectar();
        return lista;
        
    }
    
    
    public boolean gravar (Cliente c) {
    
        try{
            String sql;
            this.conectar();
            if(c.getIdCliente()==0){
                sql = "INSERT INTO cliente (nome, cpf, telefone, rua, quadra, numero, cep) "
                        + "VALUES (?,?,?,?,?,?,?)";
            }else{
                sql = "UPDATE cliente SET nome=? ,cpf=? ,telefone=? ,rua=? ,quadra=? ,numero=? ,cep=? WHERE idCliente=?";
            }
        
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, c.getNome());
            pstm.setString(2,c.getCpf());
            pstm.setString(3, c.getTelefone());
            pstm.setString(4, c.getRua());
            pstm.setString(5, c.getQuadra());
            pstm.setString(6, c.getNumero());
            pstm.setString(7, c.getCep());
            if(c.getIdCliente()>0){
                pstm.setInt(8, c.getIdCliente());
            }
            pstm.execute();
            this.desconectar();
            return true;
        
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
            
    }
    
    
    public Cliente getCarregaPorID (int idCliente) throws Exception{
    
        Cliente c = new Cliente();
        String sql = "SELECT * FROM cliente WHERE idCliente=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idCliente);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            c.setIdCliente(rs.getInt("idCliente"));
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setTelefone(rs.getString("telefone"));
            c.setRua(rs.getString("rua"));
            c.setQuadra(rs.getString("quadra"));
            c.setNumero(rs.getString("numero"));
            c.setCep(rs.getString("cep"));
        }
        this.desconectar();
        return c;
        
    }
    
    public boolean deletar (Cliente c){
        
        try {
            this.conectar();
            String sql = "DELETE FROM cliente WHERE idCliente=?";
           PreparedStatement pstm = conn.prepareStatement(sql);
           pstm.setInt(1, c.getIdCliente());
           pstm.execute();
           this.desconectar();
           return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}

