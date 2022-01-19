package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ServicoDAO extends DataBaseDAO{
    
    public ServicoDAO() throws Exception{}
    
    public ArrayList<Servico> getLista() throws Exception{
        
        ArrayList<Servico> lista = new ArrayList<Servico>(); // criar uma lista
        String SQL = "SELECT * FROM servicos"; //seleciona todos os servicos do BD
        this.conectar(); //Abre a conexão com o bd
        Statement stm = conn.createStatement(); 
        ResultSet rs = stm.executeQuery(SQL) ; // recuperando as informações vindo do BD
        while (rs.next()){
            Servico s = new Servico();
            s.setIdServico(rs.getInt("idServicos"));
            s.setNome(rs.getString("nome"));
            s.setValor(rs.getDouble("valor"));
            lista.add(s); //adicionar na lista
        }
        this.desconectar();
        return lista;
    }
    
    
    public boolean gravar (Servico s){
    
        try{
            
            String sql;
            this.conectar();
            if(s.getIdServico()==0){
                sql = "INSERT INTO servicos(nome, valor) VALUES (?,?)";
            }else{
                sql = "UPDATE servicos SET nome=?, valor=? WHERE idServicos=?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setString(1,s.getNome());
            pstm.setDouble(2,s.getValor());
            
            if(s.getIdServico()>0){
                pstm.setInt(3,s.getIdServico());
            }
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    
    public Servico getCarregaPorId (int idServico) throws Exception{
        
        Servico s = new Servico();
        String sql = "SELECT * FROM servicos WHERE idServicos=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idServico);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            s.setIdServico(rs.getInt("idServicos"));
            s.setNome(rs.getString("nome"));
            s.setValor(rs.getDouble("valor"));
        }
        this.desconectar();
        return s;
    }
    
    public boolean deletar (Servico s){
        
        try {
            this.conectar();
            String sql = "DELETE FROM servicos WHERE idServicos=?";
           PreparedStatement pstm = conn.prepareStatement(sql);
           pstm.setInt(1, s.getIdServico());
           pstm.execute();
           this.desconectar();
           return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}

