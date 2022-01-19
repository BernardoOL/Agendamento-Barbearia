package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class UsuarioDAO extends DataBaseDAO{
    
    public UsuarioDAO() throws Exception{}
    
    public ArrayList<Usuario> getLista() throws Exception{
        
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT u.*, p.nome FROM usuario u "
                + "INNER JOIN perfil p ON p.idPerfil = u.idPerfil ";
        
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("u.idUsuario"));
            usuario.setNome(rs.getString("u.nome"));
            usuario.setLogin(rs.getString("u.login"));
            usuario.setSenha(rs.getString("u.senha"));
            usuario.setStatus(rs.getInt("u.status"));
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(rs.getInt("u.idPerfil"));
            perfil.setNome(rs.getString("p.nome"));
            usuario.setPerfil(perfil);
            lista.add(usuario);
        
        }
        this.desconectar();
        return lista;
    
    }
    
    public boolean gravar(Usuario usuario){
        
        try{
            String sql;
            this.conectar();
            if(usuario.getIdUsuario()==0){
                sql = "INSERT INTO usuario (nome, login, senha, status, idPerfil) "
                        + " VALUES (?,?,?,?,?)";
            }else{
                sql = "UPDATE usuario SET nome=?, login=?, senha=?, status=?, idPerfil=? "
                        + "WHERE idUsuario=?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2,usuario.getLogin());
            pstm.setString(3, usuario.getSenha());
            pstm.setInt(4,usuario.getStatus());
            pstm.setInt(5, usuario.getPerfil().getIdPerfil());
            if(usuario.getIdUsuario()>0)
                pstm.setInt(6,usuario.getIdUsuario());
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    
    public Usuario getCarregaPorID(int idUsuario) throws Exception{
        
        Usuario usuario = new Usuario();
        String sql = "SELECT u.* , p.nome FROM usuario u "
                + "INNER JOIN perfil p ON p.idPerfil = u.idPerfil "
                + "WHERE u.idUsuario=? ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idUsuario);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            usuario.setIdUsuario(rs.getInt("u.idUsuario"));
            usuario.setNome(rs.getString("u.nome"));
            usuario.setLogin(rs.getString("u.login"));
            usuario.setSenha(rs.getString("u.senha"));
            usuario.setStatus(rs.getInt("u.status"));
            Perfil perfil = new Perfil();
            perfil.setIdPerfil(rs.getInt("u.idPerfil"));
            perfil.setNome(rs.getString("p.nome"));
            usuario.setPerfil(perfil);
        }
        this.desconectar();
        return usuario;
    
    }
    
    public boolean deletar(Usuario u){
        
        try{
        
            String sql = "UPDATE usuario SET status=2 WHERE idUsuario=?";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,u.getIdUsuario());
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
            
        }
        
    
    }
    
    public Usuario getRecuperarUsuario(String login){
    
        Usuario u = new Usuario();
        String sql = "SELECT u.* FROM usuario u "
                + "WHERE u.login=?";
        try{
            
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,login);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                u.setIdUsuario(rs.getInt("u.idUsuario"));
                u.setNome(rs.getString("u.nome"));
                u.setLogin(rs.getString("u.login"));
                u.setSenha(rs.getString("senha"));
                u.setStatus(rs.getInt("u.status"));
                PerfilDAO pDAO = new PerfilDAO();
                u.setPerfil(pDAO.getCarregaPorID(rs.getInt("u.idPerfil")));
            
            }
            this.desconectar();
            return u;
        
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    
    
    }
    
    
    
}