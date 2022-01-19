package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseDAO {
    public final String URL="jdbc:mysql://localhost:3306/barbearia"; // nome do banco
    public final String USER="root"; // nome do usuario do banco
    public final String SENHA= ""; // senha do usuario do banco
    public Connection conn; // variável do tipo Connetion(sql)
    
    //Método Construtor DataBaseDAO
    public DataBaseDAO() throws Exception{
        Class.forName("com.mysql.jdbc.Driver"); // Chama o driver do mysql
    }
    
    //Método para conectar ao Banco de Dados
    public void conectar() throws Exception {
        conn = DriverManager.getConnection(URL, USER, SENHA); // Abri uma conexão e retorna um valor (TRUE ou FALSE)
    }
    
    //Método para desconectar do banco
    public void desconectar() throws Exception{
        conn.close();
    }
}
