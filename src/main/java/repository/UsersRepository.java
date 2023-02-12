package repository;

import config.MysqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {

    public int countUsersByEmailAndPassword(String email, String password){
        int count = 0;
        //mo ket noi database
        Connection connection = MysqlConfig.getConnection();
//        String query = "SELECT count(*) FROM users u\n" +
//                "WHERE u.email = '" + email + "' and password = '" + password + "'";

        // ky tu ? dai dien cho tham so jdbc se truyen vao khi thuc thi query
        String query = "SELECT count(*) AS count FROM users u WHERE u.email = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return count;
    }
}
