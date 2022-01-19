/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mat
 */
public class PerfilDAO extends DataBaseDAO{
    
    public PerfilDAO()throws Exception{}
    
    public ArrayList<Perfil> getLista() throws Exception{
        
        ArrayList<Perfil> lista = new ArrayList<Perfil>();
        String SQL = "SELECT * FROM perfil";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        while(rs.next()){
            Perfil p = new Perfil();
            p.setIdPerfil(rs.getInt("idPerfil"));
            p.setNome(rs.getString("nome"));
            lista.add(p);
        }
        this.desconectar();
        return lista;
    
    }
    
    public boolean gravar (Perfil p){
        
        try{
            String sql;
            this.conectar();
            if(p.getIdPerfil()==0){
                sql = "INSERT INTO perfil (nome) VALUES(?)";
            }else{
                sql = "UPDATE perfil SET nome=? WHERE idPerfil=?";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,p.getNome());
            
            if(p.getIdPerfil()>0){
                pstm.setInt(2,p.getIdPerfil());
            }
            pstm.execute();
            this.desconectar();
            return true;
            
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    
    
    }
    
    public Perfil getCarregaPorID(int idPerfil) throws Exception{
        
        Perfil p = new Perfil();
        String sql = "SELECT * FROM perfil WHERE idPerfil=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idPerfil);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            p.setIdPerfil(rs.getInt("idPerfil"));
            p.setNome(rs.getString("nome"));
            p.setMenus(menusVinculadosPorPerfil(idPerfil));
            p.setNaoMenus(menusNaoVinculadosPorPerfil(idPerfil));
        }
        this.desconectar();
        return p;
    
    }
    
    public boolean deletar (Perfil p){
        
        try{
            this.conectar();
            String sql = "DELETE FROM perfil WHERE idPerfil=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,p.getIdPerfil());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public ArrayList<Menu> menusVinculadosPorPerfil(int idPerfil) throws Exception{
        
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String sql = "SELECT m.* FROM menu_perfil as mp, menu as m "
                + "WHERE mp.idMenu = m.idMenu AND mp.idPerfil = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idPerfil);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("m.idMenu"));
            m.setNome(rs.getString("m.nome"));
            m.setLink(rs.getString("m.link"));
            m.setExibir(rs.getInt("m.exibir"));
            lista.add(m);
            
        }
        this.desconectar();
        return lista;
    
    
    }
    
    public ArrayList<Menu> menusNaoVinculadosPorPerfil(int idPerfil) throws Exception{
        
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String sql = "SELECT m.* FROM menu as m "
                + "WHERE m.idMenu NOT IN (SELECT mp.idMenu FROM menu_perfil as mp WHERE mp.idPerfil=?)";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idPerfil);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("m.idMenu"));
            m.setNome(rs.getString("m.nome"));
            m.setLink(rs.getString("m.link"));
            m.setExibir(rs.getInt("m.exibir"));
            lista.add(m);
            
        }
        this.desconectar();
        return lista;
    
    
    }
    
    public boolean vincular(int idMenu, int idPerfil){
        
        try{
            
            String sql = "INSERT INTO menu_perfil (idMenu, idPerfil) "
                    + "VALUES(?,?)";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,idMenu);
            pstm.setInt(2,idPerfil);
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    
    public boolean desvincular(int idMenu, int idPerfil){
        
        try{
            String sql = "DELETE FROM menu_perfil WHERE idMenu=? AND idPerfil=?";
            this.conectar();
            PreparedStatement pstm =conn.prepareStatement(sql);
            pstm.setInt(1, idMenu);
            pstm.setInt(2, idPerfil);
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    
    
    }
    
}
