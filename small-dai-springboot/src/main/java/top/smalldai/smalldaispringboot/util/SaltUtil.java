package top.smalldai.smalldaispringboot.util;

import java.util.Random;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 随机盐生成器
 * @Data:Created in 2022/3/26 2:24 下午
 */
public class SaltUtil {
    public static  String getSalt(int n){
        char[] chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890~!@#$%^&*()[]:,./".toCharArray();
        //根据传入的参数n从数组中随机取n次数据，每次取一个
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

}
