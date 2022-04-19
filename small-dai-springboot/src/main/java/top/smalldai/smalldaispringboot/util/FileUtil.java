package top.smalldai.smalldaispringboot.util;

import top.smalldai.smalldaispringboot.common.constant.GenerateFileConstant;
import top.smalldai.smalldaispringboot.common.result.ResultVO;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 文件处理工具
 * @Data:Created in 2022/3/28 12:25 下午
 */
public class FileUtil {

    /**
     * @Author: xingyuchen
     * @Discription: 存储
     * @param path
     * @param fileName
     * @param  bytes
     * @Date: 2022/3/28 12:35 下午
    */
    public static void saveFile(String path, String fileName, byte[] bytes) throws IOException {
        File file = new File(path, fileName);
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(bytes);
        stream.close();
    }

    /**
     * @Author: xingyuchen
     * @Discription: 创建文件夹
     * @param path
     * @Date: 2022/4/1 12:35 下午
    */
    public static void mkdir(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
    }

    /**
     * @Author: xingyuchen
     * @Discription: 解压zip
     * @param filePath
     * @Date: 2022/3/28 12:35 下午
    */
    public static void tarZip(String filePath) throws IOException {
        File srcFile = new File(filePath);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            return;
        }
        String destDirPath = filePath.replace(".zip", "");
        //创建压缩文件对象
        ZipFile zipFile = new ZipFile(srcFile);
        //开始解压
        Enumeration<?> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            // 如果是文件夹，就创建个文件夹
            if (entry.isDirectory()) {
                srcFile.mkdirs();
            } else {
                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                File targetFile = new File(destDirPath + "/" + entry.getName());
                // 保证这个文件的父文件夹必须要存在
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }
                targetFile.createNewFile();
                // 将压缩文件内容写入到这个文件中
                InputStream is = zipFile.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(targetFile);
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                fos.close();
                is.close();
            }
        }
    }
}
