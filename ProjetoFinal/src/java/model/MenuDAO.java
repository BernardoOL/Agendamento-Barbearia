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
 * @author User
 */
public class MenuDAO extends DataBaseDAO {
    
        public MenuDAO() throws Exception{}
    
    public ArrayList<Menu> getLista() throws Exception{
        
        ArrayList<Menu> lista = new ArrayList<Menu>(); // criar uma lista
        String SQL = "SELECT * FROM menu"; //seleciona todos os menus do BD
        this.conectar(); //Abre a conexão com o bd
        Statement stm = conn.createStatement(); 
        ResultSet rs = stm.executeQuery(SQL) ; // recuperando as informações vindo do BD
        while (rs.next()){
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("idMenu"));
            m.setNome(rs.getString("nome"));
            m.setLink(rs.getString("link"));
            m.setExibir(rs.getInt("exibir"));
            lista.add(m); //adicionar na lista
        }
        this.desconectar();
        return lista;
        
    }
    
    public boolean gravar (Menu m){
    
        try{
            
            String sql;
            this.conectar();
            if(m.getIdMenu()==0){
                sql = "INSERT INTO menu (nome, link, exibir) VALUES (?,?,?)";
            }else{
                sql = "UPDATE menu SET nome=?,link=?, exibir=? WHERE idMenu=? ";
            }
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,m.getNome());
            pstm.setString(2,m.getLink());
            pstm.setInt(3,m.getExibir());
            if(m.getIdMenu()>0){
                pstm.setInt(4,m.getIdMenu());
            }
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    
    public Menu getCarregaPorId (int idMenu) throws Exception{
    
    
        Menu m = new Menu();
        String sql = "SELECT * FROM menu WHERE idMenu=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idMenu);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            m.setIdMenu(rs.getInt("idMenu"));
            m.setNome(rs.getString("nome"));
            m.setLink(rs.getString("link"));
            m.setExibir(rs.getInt("exibir"));
        }
        this.desconectar();
        return m;
    }
    
    public boolean deletar (Menu m){
        
        try {
            this.conectar();
            String sql = "DELETE FROM menu WHERE idMenu=?";
           PreparedStatement pstm = conn.prepareStatement(sql);
           pstm.setInt(1, m.getIdMenu());
           pstm.execute();
           this.desconectar();
           return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
