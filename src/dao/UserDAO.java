package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class UserDAO {

    public static void main(String[] args) {

        System.out.println(new UserDAO().getUser("tom", "123").getId());  //result对象.getId()

    }


    //通过用户给出的用户名和密码查询并返回数据库中的user对象
    public User getUser(String name, String password) {
        User result = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8",
                    "root",
                    "123456");

            String sql = "select * from user where name = ? and password = ?";

            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, password); //填写用于sql查找的参数
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                result = new User();
                result.setId(rs.getInt(1));
                result.setPassword(password);
                result.setName(name);
            }

            ps.close();

            c.close();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}