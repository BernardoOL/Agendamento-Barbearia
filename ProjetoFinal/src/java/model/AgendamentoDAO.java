package model;


import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.Time;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AgendamentoDAO extends  DataBaseDAO{
    
    public AgendamentoDAO() throws Exception{}
    
    
    public boolean registrar(Agendamento agendamento){
        
        try{
            this.conectar();
            String sql;
            if(agendamento.getIdAgendamento()==0){
                sql = "INSERT INTO agendamento (idCliente, idUsuario, idstatus, idServicos, dataHora, hora) "
                    + "VALUES(?,?,?,?,?,?)";
            }else{
                sql = "UPDATE agendamento SET idCliente=?, idUsuario=?, idstatus=?, idServicos=?, dataHora=?, hora=? WHERE idAgendamento=?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1,agendamento.getCliente().getIdCliente());
            pstm.setInt(2,agendamento.getVendedor().getIdUsuario());
            pstm.setInt(3,agendamento.getStatus().getIdStatus());
            pstm.setInt(4,agendamento.getServico().getIdServico());
            pstm.setDate(5, new Date(agendamento.getDataHora().getTime()));
            pstm.setString(6, agendamento.getHoraAgendamento());
            if(agendamento.getIdAgendamento()>0){
                pstm.setInt(7, agendamento.getIdAgendamento());
            }
            pstm.execute();
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next()){
                agendamento.setIdAgendamento(rs.getInt(1));
            }
            this.desconectar();
            return true;
        
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    
    
    public ArrayList<Agendamento> getLista()throws Exception{
        
        this.conectar();
        String sql = "SELECT * FROM agendamento";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        ArrayList<Agendamento> lista = new ArrayList<Agendamento>();
        
        while(rs.next()){
            Agendamento agendamento = new Agendamento();
            agendamento.setIdAgendamento(rs.getInt("idAgendamento"));
            agendamento.setDataHora(rs.getDate("dataHora"));
            agendamento.setHoraAgendamento(rs.getString("hora"));
            
            ClienteDAO cDAO = new ClienteDAO();
            agendamento.setCliente(cDAO.getCarregaPorID(rs.getInt("idCliente")));
            
            UsuarioDAO uDAO = new UsuarioDAO();
            agendamento.setVendedor(uDAO.getCarregaPorID(rs.getInt("idUsuario")));
            
            StatusDAO statusDAO = new StatusDAO();
            agendamento.setStatus(statusDAO.getCaregaPorId(rs.getInt("idstatus")));
            
            ServicoDAO servicoDAO = new ServicoDAO();
            agendamento.setServico(servicoDAO.getCarregaPorId(rs.getInt("idServicos")));
            
            lista.add(agendamento);
        }
        this.desconectar();
        return lista;
    
    }
    
    public boolean deletar(Agendamento agendamento){
        try{
            this.conectar();
            String sql = "DELETE FROM agendamento WHERE idAgendamento=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, agendamento.getIdAgendamento());
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public Agendamento getCarregaPorID(int idAgendamento) throws Exception{
        
        Agendamento agendamento= new Agendamento();
        String sql = "SELECT * FROM agendamento WHERE idAgendamento=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idAgendamento);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            agendamento.setIdAgendamento(rs.getInt("idAgendamento"));
            agendamento.setDataHora(rs.getDate("dataHora"));
            agendamento.setHoraAgendamento(rs.getString("hora"));
            
            ClienteDAO cDAO = new ClienteDAO();
            agendamento.setCliente(cDAO.getCarregaPorID(rs.getInt("idCliente")));
            
            UsuarioDAO uDAO = new UsuarioDAO();
            agendamento.setVendedor(uDAO.getCarregaPorID(rs.getInt("idUsuario")));
            
            StatusDAO statusDAO = new StatusDAO();
            agendamento.setStatus(statusDAO.getCaregaPorId(rs.getInt("idstatus")));
            
            ServicoDAO servicoDAO = new ServicoDAO();
            agendamento.setServico(servicoDAO.getCarregaPorId(rs.getInt("idServicos")));
        }
        this.desconectar();
        return agendamento;
    }
}