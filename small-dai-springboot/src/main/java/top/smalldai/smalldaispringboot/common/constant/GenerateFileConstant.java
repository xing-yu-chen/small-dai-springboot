package top.smalldai.smalldaispringboot.common.constant;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 创建项目的文件相关常量池
 * @Data:Created in 2022/3/28 10:12 上午
 */
public interface GenerateFileConstant {

    /* 下载SpringBoot文件的路径 */
    public static final String PROJECT_FILE_PATH_DOWNLOAD = "/Users/xingyuchen/Desktop";

    /* absolutePath/项目名/spring_file_name/ */
    // maven的pom文件地址
    public static final String PROJECT_FILE_PATH_MAVEN_POM = "pom.xml";

    // src目录下的主目录 /src/main
    public static final String PROJECT_FILE_PATH_MAIN_PACKAGE = "/src/main";

    // java代码包主目录 /src/main/java
    public static final String PROJECT_FILE_PATH_JAVA_PACKAGE = PROJECT_FILE_PATH_MAIN_PACKAGE + "/java";

    // resource代码包主目录 /src/main/resource
    public static final String PROJECT_FILE_PATH_RESOURCE_PACKAGE = PROJECT_FILE_PATH_MAIN_PACKAGE +"/resources";

    // 项目banner.txt /src/main/resource/banner.txt
    public static final String PROJECT_FILE_PATH_BANNER = PROJECT_FILE_PATH_RESOURCE_PACKAGE + "/banner.txt";

    // 本机banner文字 /src/main/resource/banner.txt
    public static final String PROJECT_FILE_PATH_BANNER_CONTENT = PROJECT_FILE_PATH_BANNER;

    // controller
    public static final String PROJECT_FILE_PATH_CONTROLLER_PACKAGE = "/controller";

    // service
    public static final String PROJECT_FILE_PATH_SERVICE_PACKAGE = "/service";

    // service impl
    public static final String PROJECT_FILE_PATH_SERVICE_IMPL_PACKAGE = PROJECT_FILE_PATH_SERVICE_PACKAGE + "/impl";

    // mapper
    public static final String PROJECT_FILE_PATH_MAPPER_PACKAGE = "/mapper";

    // entity
    public static final String PROJECT_FILE_PATH_ENTITY_PACKAGE = "/entity";

    // util
    public static final String PROJECT_FILE_PATH_UTIL_PACKAGE = "/util";
    public static final String PROJECT_FILE_PATH_UTIL_EMAIL_UTIL_JAVA = "EmailUtil.java";


    // to
    public static final String PROJECT_FILE_PATH_TO_PACKAGE = "/to";

    // 自定义注解
    public static final String PROJECT_FILE_PATH_ANNOTATION_PACKAGE = "/annotation";

    // aop
    public static final String PROJECT_FILE_PATH_AOP_PACKAGE = "/aop";

    // common
    public static final String PROJECT_FILE_PATH_COMMON_PACKAGE = "/common";

    // exception
    public static final String PROJECT_FILE_PATH_EXCEPTION_PACKAGE ="/exception";

    // response
    public static final String PROJECT_FILE_PATH_RESPONSE_PACKAGE = "/response";
    public static final String PROJECT_FILE_PATH_RESPONSE_JAVA = "ResponseVO.java";
    public static final String PROJECT_FILE_PATH_RUNNING_EXCEPTION_HANDLER_JAVA = "RunningExceptionHandler.java";


    // config
    public static final String PROJECT_FILE_PATH_CONFIG_PACKAGE = "/config";
    public static final String PROJECT_FILE_PATH_CONFIG_REALM_PACKAGE = "/realm";

    // filter
    public static final String PROJECT_FILE_PATH_FILTER_PACKAGE = "/filter";

    // mappers
    public static final String PROJECT_FILE_PATH_MAPPERS_PACKAGE = PROJECT_FILE_PATH_RESOURCE_PACKAGE + "/mappers";

    // *Mappers.xml
    public static final String PROJECT_FILE_PATH_MAPPER_XML = "Mapper.xml";

    // application.yml /src/main/resource/application.yml
    public static final String PROJECT_FILE_PATH_YML = PROJECT_FILE_PATH_RESOURCE_PACKAGE + "/application.yml";

    // ftl basePath
    public static final String PROJECT_FILE_FREEMARKER_BASE_PATH = "src/main/resources/templates";
    public static final String PROJECT_FILE_PATH_RESPONSE_INPUT_PATH = "ResponseVO.ftl";
    public static final String PROJECT_FILE_PATH_RUNNING_EXCEPTION_HANDLER_PATH = "RunningExceptionHandler.ftl";

}
