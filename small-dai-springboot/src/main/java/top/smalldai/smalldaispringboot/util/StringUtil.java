package top.smalldai.smalldaispringboot.util;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/4/1 10:06 上午
 */
public class StringUtil {
    /**
     * @Author: xingyuchen
     * @Discription: 处理字符串
     * @param packageName
     * @Date: 2022/4/1 10:06 上午
    */
    public static String dealSpringPackageName(String packageName){
        String replace = packageName.replace(".", "/");
        return replace;
    }
}
