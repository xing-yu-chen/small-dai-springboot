package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/3/31 12:59 下午
 */
public interface YmlConstant {
    // application.yml内容
    public static final String YML_APPLICATION_CONTENT = "server:\n" +
            "  port: 80\n" +
            "\n" +
            "spring:\n" +
            "  application:\n" +
            "    name: small-dai-springboot\n" +
            "  mail:\n" +
            "    default-encoding: UTF-8\n" +
            "    host: smtp.qq.com\n" +
            "    username: ${qqEmail}\n" +
            "    password: ${token}\n" +
            "    port: 587\n" +
            "    properties:\n" +
            "      mail:\n" +
            "        smtp:\n" +
            "          socketFactoryClass: javax.net.ssl.SSLSocketFactory" +
            "\n" +
            "  datasource:\n" +
            "    # 数据库驱动\n" +
            "    driver-class-name: com.mysql.cj.jdbc.Driver\n" +
            "    # 阿里巴巴的druid数据源\n" +
            "    type: com.alibaba.druid.pool.DruidDataSource\n" +
            "    # 数据库连接语句\n" +
            "    url: jdbc:mysql://${urlAddress}:${urlPort}/${urlDatabaseName}?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai\n" +
            "    # 用户名\n" +
            "    username: ${mysqlUsername}\n" +
            "    # 密码\n" +
            "    password: ${mysqlPassword}\n" +
            "    druid:\n" +
            "      initial-size: 10\n" +
            "      max-active: 150\n" +
            "      min-idle: 10\n" +
            "      max-wait: 5000\n" +
            "      pool-prepared-statements: false\n" +
            "      validation-query: SELECT 1\n" +
            "      validation-query-timeout: 500\n" +
            "      test-on-borrow: false\n" +
            "      test-on-return: false\n" +
            "      test-while-idle: true\n" +
            "      time-between-eviction-runs-millis: 60000\n" +
            "      min-evictable-idle-time-millis: 30000\n" +
            "      filters: stat,wall\n" +
            "      connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000\n" +
            "\n" +
            "mybatis-plus:\n" +
            "  mapper-locations: classpath:mappers/**/*GenMapper.xml\n" +
            "  # 返回实体类包\n" +
            "  type-aliases-package: ${springPackageName}.entity\n" +
            "  configuration:\n" +
            "    # 使用系统默认ibatis输出日志\n" +
            "    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n" +
            "\n";
}
