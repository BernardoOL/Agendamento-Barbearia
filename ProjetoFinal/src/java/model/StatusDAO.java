package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StatusDAO extends DataBaseDAO{
    
     public StatusDAO() throws Exception{}
    
    public ArrayList<Status> getLista() throws Exception{
        
        ArrayList<Status> lista = new ArrayList<Status>(); // criar uma lista
        String SQL = "SELECT * FROM status"; //seleciona todos os servicos do BD
        this.conectar(); //Abre a conexão com o bd
        Statement stm = conn.createStatement(); 
        ResultSet rs = stm.executeQuery(SQL) ; // recuperando as informações vindo do BD
        while (rs.next()){
            Status status = new Status();
            status.setIdStatus(rs.getInt("idStatus"));
            status.setDescricao(rs.getString("descricao"));
            lista.add(status); //adicionar na lista
        }
        this.desconectar();
        return lista;
    }
    
    public Status getCaregaPorId (int idStatus) throws Exception{
        
        Status status = new Status();
        String sql = "SELECT * FROM status WHERE idStatus=?";
        this.conectar();
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idStatus);
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            status.setIdStatus(rs.getInt("idStatus"));
            status.setDescricao(rs.getString("descricao"));
        }
        this.desconectar();
        return status;
        
    }
    
}
