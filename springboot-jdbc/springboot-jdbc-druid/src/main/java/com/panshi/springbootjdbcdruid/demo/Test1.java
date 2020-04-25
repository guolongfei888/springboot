package com.panshi.springbootjdbcdruid.demo;

import java.sql.*;

/**
 * @ClassName Test1
 * @Description
 * @Author guolongfei
 * @Date 2020/4/25  17:02
 * @Version
 */
public class Test1 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 加载MySQL驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=true";
            String username = "root";
            String password = "root";
            // 连接数据库
            conn = DriverManager.getConnection(url,username,password);
            // 创建一个Statement
            // 1、执行静态SQL语句。通常通过Statement实例实现。
            // 2、执行动态SQL语句。通常通过PreparedStatement实例实现。
            // 3、执行数据库存储过程。通常通过CallableStatement实例实现。
             stmt = conn.createStatement();
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            CallableStatement cstmt = conn.prepareCall("{CALL demoSp(?,?)}");
            // Statement 接口提供了三种执行sql语句的方法: executeQuery  executeUpdate  execute
            // 1. ResultSet executeQuery(String sqlString) : 执行查询数据库的sql语句,返回一个结果集(ResultSet) 对象
            // 2. int executeUpdate(String sqlString) : 用于执行insert update或delete语句以及sql DDL语句 如 create table 和 drop table
            // execute(sqlString) : 用于执行返回多个结果集  多个更新数或者二者组合的语句
            rs = stmt.executeQuery("select * from employee");
//            int rows = stmt.executeUpdate("insert into employee values(null,#{lastname},#{email},#{gender},#{dId})");
//            boolean flag = stmt.execute(String sql);
            // 处理结果:
            // 两张情况:
            // 1  执行更新返回的是本次操作影响到的记录数
            // 2 执行查询返回的结果是一个ResultSet对象
            // ResultSet 包含符合sql语句中条件的所有行,并且它通过一套get方法提供了对这些行中数据的访问
            // 使用结果集(ResultSet) 对象的访问方法获取数据:
            while (rs.next()) {
                String name = rs.getString("lastname");
//                String pass = rs.getString(1); // 此方法较高效(列是从左到右编号的,并且从列1开始)
                System.out.println(name);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
