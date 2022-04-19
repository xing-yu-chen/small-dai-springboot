package top.smalldai.smalldaispringboot.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: Freemarker工具类
 * @Data:Created in 2022/3/30 5:59 下午
 */
public class FreemarkerUtil {
    /**
     * @Author: xingyuchen
     * @Discription: 字符串模版
     * @param templateName
     * @param templateString
     * @param hashMap
     * @param path
     * @Date: 2022/4/1 1:24 下午
    */
    public static void generateTemplate(String templateString, String templateName, HashMap<String, Object> hashMap, String path) throws IOException, TemplateException {
        //创建配置对象
        Configuration configuration = new Configuration();
        //制定加载器
        Template template =null;
        //创建字符串模版
        configuration.setTemplateLoader(new StringTemplateLoader());
        //通过字符串创建模版 参数:templateString
        template = new Template(templateName, new StringReader(templateString), configuration);
        File file = new File(path);
        //构造数据 参数：hashMap
        FileWriter fileWriter = new FileWriter(file);
        //处理模版,在文件输出
        template.process(hashMap, fileWriter);
        fileWriter.close();
    }

    /**
     * @Author: xingyuchen
     * @Discription: 文件模版freemarker
     * @param map
     * @param basePath
     * @param inputPath
     * @param outputPath
     * @Date: 2022/4/1 1:18 下午
    */
    public static void fileTemplate(HashMap<String, Object> map,String basePath,String inputPath,String outputPath){
        //创建FreeMarker配置类
        Configuration configuration = new Configuration();
        //初始化模版
        Template template =null;
        try {
            //指定模版加载器
            configuration.setDirectoryForTemplateLoading(new File(basePath));
            configuration.setDefaultEncoding("UTF-8");   //设置编码
            //获取模板文件
            template=configuration.getTemplate(inputPath);
            /*构造数据模型留给传入*/
            //模型处理，输出为文件
            template.process(map,new FileWriter(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件传输出错");
        } catch (TemplateException e) {
            e.printStackTrace();
            System.out.println("模版语法出错");
        }
    }
}
