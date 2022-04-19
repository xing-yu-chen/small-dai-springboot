package top.smalldai.smalldaispringboot.util;

import cn.hutool.db.DbUtil;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.smalldai.smalldaispringboot.common.result.ResultVO;
import top.smalldai.smalldaispringboot.entity.generation.GenDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/3/28 11:19 上午
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "数据库JDBC工具")
public class DBUtil {
    /**
     * 获取连接方法
     * @return
     */
    public static Connection getConnection(String db) throws IOException {
        Connection conn = null;

        String url = "jdbc:mysql://localhost:3306/" + db + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "123456";
        String driverClass = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }



    /**
     * 释放资源方法
     * @param conn
     * @param pstmt
     * @param rs
     */
    public static void release(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author: xingyuchen
     * @Discription: 执行修改保存等操作
     * @param genDatabase
     * @param sql
     * @Date: 2022/3/30 12:32 下午
    */
    public static Boolean saveOrUpdate(GenDatabase genDatabase, String sql, Boolean createDatabase) throws IOException, SQLException {
        Connection connection = null;
        PreparedStatement updateColumnCommitStatement = null;
        String empty = "";
        connection = getConnection(createDatabase? empty : genDatabase.getName());
        updateColumnCommitStatement = connection.prepareStatement(sql);
        int execute = updateColumnCommitStatement.executeUpdate(sql);
        release(connection, updateColumnCommitStatement, null);
        return true;
    }

    /**
     * @Author: xingyuchen
     * @Discription: 查询所有表字段
     * @param tableName
     * @Date: 2022/4/14 2:04 下午
    */
    public static List<Object> queryAllColumn(String tableName) throws IOException, SQLException {
        Connection connection = null;
        PreparedStatement selectCommitStatement = null;
        ResultSet rs = null;
        List<Object> columnList = new ArrayList<>();
        connection = getConnection("small_dai_springboot");
        String sql = "select column_name,column_comment from information_schema.columns where table_name = '" + tableName + "' and table_schema = 'small_dai_springboot';";
        selectCommitStatement = connection.prepareStatement(sql);
        rs = selectCommitStatement.executeQuery(sql);
        while (rs.next()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", NameStrUtil.columnNameStringDealing(rs.getString("column_name")));
            map.put("comment", rs.getString("column_comment"));
            columnList.add(map);
        }
        release(connection, selectCommitStatement, rs);
        return columnList;
    }

    /**
     * @Author: xingyuchen
     * @Discription: 查询表备注信息
     * @param tableName
     * @Date: 2022/4/14 2:29 下午
    */
    public static String queryTableComment(String tableName) throws IOException, SQLException {
        Connection connection = null;
        PreparedStatement selectCommitStatement = null;
        ResultSet rs = null;
        String tableComment = "";
        connection = getConnection("small_dai_springboot");
        String sql = "select table_comment from information_schema.tables where table_name = '" + tableName + "' and table_schema = 'small_dai_springboot';";
        selectCommitStatement = connection.prepareStatement(sql);
        rs = selectCommitStatement.executeQuery(sql);
        while (rs.next()) {
            tableComment = rs.getString("table_comment");
        }
        release(connection, selectCommitStatement, rs);
        return tableComment;
    }
}
