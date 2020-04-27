package com.panshi.springbootjdbccrud.demo;

import com.panshi.springbootjdbccrud.bean.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EmployeeDao
 * @Description
 * @Author guolongfei
 * @Date 2020/4/27  10:32
 * @Version
 */
public class EmployeeDemo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 获取连接, url username  password
            String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=true";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,username,password);
//            insert(conn, pstmt);
//            delete(conn,pstmt);
//            update(conn,pstmt);
            findAll(conn,pstmt,rs);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean insert(Connection conn ,
            PreparedStatement pstmt){

//        String sql = "update employee set lastName=?,email=?,gender=?,d_id=? where id=?";
        try {
            String sql = "insert into employee(id,lastName,email,gender,d_id) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,3);
            pstmt.setString(2,"zhangsan");
            pstmt.setString(3,"zhangsan@163.com");
            pstmt.setInt(4,12);
            pstmt.setInt(5,1);

            int count = pstmt.executeUpdate();
            return count>0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt);
        }
        return false;
    }

    private static boolean delete(Connection conn,PreparedStatement pstmt) {
        String sql = "delete from employee where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,3);
            int count = pstmt.executeUpdate();
            return count>0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt);
        }
        return false;
    }

    private static boolean update(Connection conn,PreparedStatement pstmt) {
        String sql = "update employee set lastName=?,email=?,gender=?,d_id=? where id=?";
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,"lisi");
            pstmt.setString(2,"lisi@163.com");
            pstmt.setInt(3,13);
            pstmt.setInt(4,2);
            pstmt.setInt(5,2);
            int count = pstmt.executeUpdate();
            return count>0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt);
        }
        return false;

    }
    
    private static List<Employee> findAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
        String sql = "select * from employee";
        List<Employee> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setLastName(rs.getString("lastName"));
                employee.setEmail(rs.getString("email"));
                employee.setGender(rs.getInt("gender"));
                employee.setdId(rs.getInt("d_id"));
                list.add(employee);
            }
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt,rs);
        }
        return list;
    }

    // 先打开，后关闭
    public static void closeAll(Connection connection, PreparedStatement statement) {
        try {
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 先打开，后关闭
    public static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(connection, statement);
        }
    }
}
