package mysql;

import java.sql.*;

public class MySQLMain {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取数据库连接（里面内容依次是："jdbc:mysql://主机名:端口号/数据库名","用户名","登录密码"）
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rank?serverTimezone=UTC", "root", "2526");
        //PreparedStatement statement = connection.prepareCall(sql);

        StringBuilder stringBuilder = new StringBuilder("insert into scores(id,score) VALUES ") ;

        int from = 200_0001;
        int to = 500_0001;

        //4.获取预处理对象，并依次给参数赋值
        for (int i = from; i < to; i++) {
            stringBuilder.append("(").append(i).append(",").append(i).append(")");
//            statement.setInt(1, i); //数据库字段类型是int，就是setInt；1代表第一个参数
//            statement.setInt(2, i);    //数据库字段类型是String，就是setString；2代表第二个参数
            //5.执行sql语句（执行了几条记录，就返回几）
            //int count = statement.executeUpdate();
            if(i < to -1 ){
                stringBuilder.append(",");
            }
        }
        Statement statement = connection.createStatement();
        statement.execute(stringBuilder.toString());
        //6.关闭jdbc连接
        statement.close();
        connection.close();
    }
}
