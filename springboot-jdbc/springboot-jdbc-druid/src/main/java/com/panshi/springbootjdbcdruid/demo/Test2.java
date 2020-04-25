package com.panshi.springbootjdbcdruid.demo;

import com.panshi.springbootjdbcdruid.bean.Employee;

import java.sql.*;

/**
 * @ClassName Test2
 * @Description
 * @Author guolongfei
 * @Date 2020/4/25  18:25
 * @Version
 */
public class Test2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // 加载MySQL驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=true";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,username,password);
            insert1(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    // 1、使用Statement插入100000条记录
    private static void insert1(Connection conn) throws SQLException {
        Statement stmt;//开始时间
        long beginTime = System.currentTimeMillis();
        // 设置手动提交
        conn.setAutoCommit(false);
        stmt = conn.createStatement();
        Employee employee = new Employee();
        for (int i = 0;i<2;i++) {
            String sql = "insert into employee values(1,zhangsan,zhangsan@qq.com,12,12)";

            stmt.executeUpdate(sql);
        }
        conn.commit();
        // 结束时间
        long endTime = System.currentTimeMillis();
        stmt.close();
        conn.close();
    }
    // 2、使用PreparedStatement对象
    public void exec2(Connection conn){
        try {
            Long beginTime = System.currentTimeMillis();
            conn.setAutoCommit(false);//手动提交
            PreparedStatement pst = conn.prepareStatement("insert into t1(id) values (?)");
            for(int i=0;i<100000;i++){
                pst.setInt(1, i);
                pst.execute();
            }
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("pst:"+(endTime-beginTime)/1000+"秒");//计算时间
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // 3、使用PreparedStatement + 批处理
    public void exec3(Connection conn){
        try {
            conn.setAutoCommit(false);
            Long beginTime = System.currentTimeMillis();
            //构造预处理statement
            PreparedStatement pst = conn.prepareStatement("insert into t1(id) values (?)");
            //1万次循环
            for(int i=1;i<=100000;i++){
                pst.setInt(1, i);
                pst.addBatch();
                //每1000次提交一次
                if(i%1000==0){//可以设置不同的大小；如50，100，500，1000等等
                    pst.executeBatch();
                    conn.commit();
                    pst.clearBatch();
                }
            }
            Long endTime = System.currentTimeMillis();
            System.out.println("pst+batch："+(endTime-beginTime)/1000+"秒");
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
