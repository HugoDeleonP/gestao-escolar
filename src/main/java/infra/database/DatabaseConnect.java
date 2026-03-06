package infra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

    private final static String URL = "jdbc:mysql://127.0.0.1:3307/gestao_escolar?user=root";
    private final static String USER = "root";
    private final static String PASSWORD = "1234";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {

        try(Connection conn = DatabaseConnect.connection()){
            if(conn != null){
                System.out.println("Conexão realizada com sucesso!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
