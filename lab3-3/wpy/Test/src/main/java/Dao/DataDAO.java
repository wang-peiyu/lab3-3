package Dao;

import java.sql.*;

public class DataDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    public String getContacts() {
        StringBuilder contacts = new StringBuilder();

        try {
            // 连接到数据库
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 创建Statement对象
            Statement statement = connection.createStatement();

            // 执行查询语句
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
            contacts.append("查询结果为：").append("\n");
            // 处理查询结果
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                contacts.append("联系人信息").append("id: ").append(id).append("name: ").append(name).append(", address: ").append(address).append(", phone: ").append(phone).append("\n");
            }

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contacts.toString();
    }

    public boolean addContact(String name, String address, String phone) {
        try {
            // 连接到数据库
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 创建PreparedStatement对象
            PreparedStatement statement = connection.prepareStatement("INSERT INTO person (name, address, phone) VALUES (?, ?, ?)");

            // 设置参数
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone);

            // 执行插入语句
            int rowsAffected = statement.executeUpdate();

            // 关闭连接
            statement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateContact(String name, String address, String phone) {
        try {
            // 连接到数据库
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 创建PreparedStatement对象
            PreparedStatement statement = connection.prepareStatement("UPDATE person SET address = ?, phone = ? WHERE name = ?");

            // 设置参数
            statement.setString(1, address);
            statement.setString(2, phone);
            statement.setString(3, name);

            // 执行更新语句
            int rowsAffected = statement.executeUpdate();

            // 关闭连接
            statement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteContact(String name) {
        try {
            // 连接到数据库
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 创建PreparedStatement对象
            PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE name = ?");

            // 设置参数
            statement.setString(1, name);

            // 执行删除语句
            int rowsAffected = statement.executeUpdate();

            // 关闭连接
            statement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
