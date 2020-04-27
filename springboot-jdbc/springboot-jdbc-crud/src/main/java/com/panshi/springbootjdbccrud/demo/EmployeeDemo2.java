package com.panshi.springbootjdbccrud.demo;

import com.panshi.springbootjdbccrud.bean.Employee;

import java.sql.*;

/**
 * @ClassName EmployeeDemo2
 * @Description
 * @Author guolongfei
 * @Date 2020/4/27  11:35
 * @Version
 */
public class EmployeeDemo2 {
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
//            exec(conn, pstmt);
            exec2(conn, pstmt);
//            delete(conn,pstmt);
//            update(conn,pstmt);
//            findAll(conn,pstmt,rs);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    // 使用PreparedStatement对象
    private static void exec(Connection conn,PreparedStatement pstmt) {
        long startTime = System.currentTimeMillis();
        String sql = "insert into employee(id,lastName,email,gender,d_id) values(?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i=1;i<100000;i++) {
                pstmt.setInt(1,i);
                pstmt.setString(2,"zhangsan"+i);
                pstmt.setString(3,"zhangsan@qq.com"+i);
                pstmt.setInt(4,i);
                pstmt.setInt(5,i);
                pstmt.executeUpdate();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt);
        }
    }

    // 使用PreparedStatement + 批处理
    public static void exec2(Connection conn,PreparedStatement pstmt) {
        long startTime = System.currentTimeMillis();
        String sql = "insert into employee(id,lastName,email,gender,d_id) values(?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            for (int i=1;i<=100000;i++) {
                pstmt.setInt(1,i);
                pstmt.setString(2,"zhangsan"+i);
                pstmt.setString(3,"zhangsan@qq.com"+i);
                pstmt.setInt(4,i);
                pstmt.setInt(5,i);
                pstmt.addBatch();
                // 每10000 次提交一次
                if (i%10000==0) { // 可以设置不同的大小；如50，100，500，1000 10000等等
                    pstmt.executeBatch();
                    conn.commit();
                    pstmt.clearBatch();
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pstmt);
        }
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
