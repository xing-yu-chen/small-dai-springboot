package ${springPackageName}.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@Component
public class EmailUtil {

    private static final String EMAIL_TITLE = "欢迎使用${pNameZh}}";
    private static final String EMAIL_CENTER_PREFIX = "您的验证码为:<strong>";
    private static final String EMAIL_CENTER_SUFFIX = "</strong>,有效期为3分钟，请尽快完成操作。";

    @Value("<#noparse>${</#noparse>spring.mail.username}")
    private String emailUsername;

    @Resource
    private JavaMailSender javaMailSender;

    public String getRandomCode(Integer num){
        String str="";
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            str+=random.nextInt(10);
        }
        return str;
    }

    public String sendSimpleMail(String receiver, String code){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(EMAIL_TITLE);
        message.setFrom(emailUsername);
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo(receiver);
        message.setSentDate(new Date());
        message.setText(EMAIL_CENTER_PREFIX + code + EMAIL_CENTER_SUFFIX);
        javaMailSender.send(message);
        return "发送成功，请于3分钟内注册";
    }

    public String getSalt(int n){
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
