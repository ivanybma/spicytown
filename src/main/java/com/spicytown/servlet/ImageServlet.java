package com.spicytown.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by cheyikung on 5/4/16.
 */

public class ImageServlet extends HttpServlet {

    private static final String SQL_FIND = "SELECT * FROM menu WHERE menu_id = ?";
    private static final String JDBC_ADDR = "jdbc:mysql://localhost:3306/";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = ""; // on ec2 the password is [password]
    private static final String DATABASE_NAME = "spicytown";
    private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menuId = request.getPathInfo().substring(1);

        try {
            Class.forName(JDBC_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver Not Found");
            e.printStackTrace();
            return;
        }
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(JDBC_ADDR + DATABASE_NAME, JDBC_USERNAME, JDBC_PASSWORD);

        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return;
        }
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_FIND);
            statement.setString(1, menuId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("image");
                int blobLength = (int) blob.length();
                byte[] content = blob.getBytes(1, blobLength);
                response.setContentType(getServletContext().getMimeType(resultSet.getString("content_type")));
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
            } else {
                System.out.println("No Image Found for id" + menuId);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
