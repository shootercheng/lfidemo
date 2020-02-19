package com.example.db;

import org.junit.Test;

import java.sql.*;

/**
 * @author chengdu
 * @date 2020/1/23
 */
public class DerbyTest {

    @Test
    public void testDerbyJdbc() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby:ibderby;create=true");
        Statement statement = connection.createStatement();
        ResultSet resultSets = statement.executeQuery("select * from t_test");
        while (resultSets.next()) {
            int id = resultSets.getInt(0);
            String name = resultSets.getString(1);
            System.out.println(id + "_" + name);
        }
        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_test");
    }
}
