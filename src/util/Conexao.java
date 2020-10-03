package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public Connection con;

    public Conexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/desafioproway?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "1234";
            con = DriverManager.getConnection(url, user, password);
            
        } catch (ClassNotFoundException erro) {
            System.out.println("Driver não encontrado!" + erro.getMessage());
        } catch (SQLException erro) {
            System.out.println("Erro na conexão com BD! " + erro.getMessage());
        }
    }
    public void desconectar(){
        try{
            con.close();
        }catch(SQLException erro){
            System.out.println("Erro ao desconectar! "+ erro.getMessage());
        }
    }
}
