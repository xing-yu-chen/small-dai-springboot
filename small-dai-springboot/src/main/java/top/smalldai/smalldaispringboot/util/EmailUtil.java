package top.smalldai.smalldaispringboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 验证码工具
 * @Data:Created in 2022/3/25 4:29 下午
 */
@Component
public class EmailUtil {

    private static final String EMAIL_TITLE = "欢迎使用小呆生成平台SpringBoot版";
    private static final String EMAIL_CENTER_PREFIX = "您的验证码为:<strong>";
    private static final String EMAIL_CENTER_SUFFIX = "</strong>,有效期为3分钟，请尽快完成操作。";

    @Value("${spring.mail.username}")
    private String emailUsername;

    @Resource
    private JavaMailSender javaMailSender;
    /**
     * @Author: xingyuchen
     * @Discription: 获取验证码
     * @param num 位数
     * @Date: 2022/3/25 5:25 下午
    */
    public String getRandomCode(Integer num){
        String str="";
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            str+=random.nextInt(10);
        }
        return str;
    }

    /**
     * @Author: xingyuchen
     * @Discription:
     * @param receiver 接收者
     * @param code 验证码
     * @Date: 2022/3/25 5:44 下午
    */
    public String sendSimpleMail(String receiver, String code){
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject(EMAIL_TITLE);
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom(emailUsername);
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo(receiver);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText(EMAIL_CENTER_PREFIX + code + EMAIL_CENTER_SUFFIX);
        // 发送邮件
        javaMailSender.send(message);
        return "发送成功，请于3分钟内注册";
    }

}
