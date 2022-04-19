package top.smalldai.smalldaispringboot.util;

import java.util.regex.Pattern;

/**
 * @Author: xing-yu-chen
 * @Project: open-source
 * @Description: 数据库表名/字段名处理
 * @Data:Created in 2022/4/1 2:00 上午
 */
public class NameStrUtil {

    /**
     * @Author: xingyuchen
     * @Discription: 表名处理：使用正则表达式处理数据库表名/字段名处理,舍弃_/-左侧的第一组,如果没有特殊字符即不处理只让首字母大写
     * @param str
     * @Date: 2022/4/1 2:00 上午
     */
    public static String tableNameStringDealing(String str){
        //正则条件
        String pattern = "[-,_]";
        //使用Pattern对象
        Pattern r = Pattern.compile(pattern);
        //将匹配的到的字符串切割
        String[] split = r.split(str);
        //用StringBuilder接收数据
        StringBuilder stringBuilder=new StringBuilder();
        //如果该元素没有切割就说名不修改返回即可
        if(split.length == 1){
            return NameStrUtil.captureName(split[0]);
        }
        //遍历数组
        for (int i = 1; i < split.length ; i++) {
            //将获取的每组单词首字母大写后拼接
            stringBuilder.append(NameStrUtil.captureName(split[i]));
        }
        return stringBuilder.toString();
    }

    /**
     * @Author: xingyuchen
     * @Discription: 字段名处理:使用字符串切割的方法，如果长度是1，就直接进行大写判别，然后二次切割，否则只对下划线切割后的做省略第一部分
     * @param str
     * @Date: 2022/4/1 2:00 上午
     */
    public static String columnNameStringDealing(String str){
        //正则条件
        String pattern = "[_]";
        //使用Pattern对象
        Pattern r = Pattern.compile(pattern);
        //将匹配的到的字符串切割
        String[] split = r.split(str);
        //判断是否没有截取或者以下环线结尾,只需要处理第一个单词小写
        if(split.length == 1){
            //返回一个首字母小写的单词别名
            return NameStrUtil.lowerCaseName(split[0]);
        }
        //字段名中存在下划线，遍历处理首字母大写问题
        StringBuilder stringBuilder=new StringBuilder();
        //首字母小写追加
        stringBuilder.append(NameStrUtil.lowerCaseName(split[0]));
        //遍历后的首字母全部大写
        for (int i = 1; i < split.length; i++) {
            stringBuilder.append(NameStrUtil.captureName(split[i]));
        }
        //返回字符串类型
        return stringBuilder.toString();
    }

    /**
     * @Author: xingyuchen
     * @Discription: 处理备注信息，去除表这词
     * @param str
     * @Date: 2022/4/1 2:00 下午
     */
    public static String deleteBiaoName(String str){
        //正则条件
        String pattern = "[表]";
        //使用Pattern对象
        Pattern r = Pattern.compile(pattern);
        //将匹配的到的字符串切割
        String[] split = r.split(str);
        //如果未分割就说明在首尾或者不存在表这个字段
        if(split.length == 1){
            System.out.println(split[0]);
            //返回截取后的第一个字段
            return split[0];
        }
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            //在中间就拼接
            stringBuilder.append(split[i]);
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * @Author: xingyuchen
     * @Discription: 单词首字母大写
     * @param str
     * @Date: 2022/4/1 2:00 上午
     */
    public static String captureName(String str) {
        //将单词第一个截取出来变为大写，再组上截取第一个后的就是原来的单词首字母大写
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return  str;
    }

    /**
     * @Author: xingyuchen
     * @Discription: 单词首字母小写
     * @param str
     * @Date: 2022/4/1 2:00 下午
     */
    public static String lowerCaseName(String str) {
        //将单词一个截取出来变为小写，再组上截取第一个后的就是原来的单词首字母小写
        str = str.substring(0,1).toLowerCase() + str.substring(1);
        return str;
    }
}