package dao;

import JdbcUtils.JDBCUtils;
import domain.User;
import main.SignIn;
import main.SignIn2;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QianDao2 {
    public static void run(User user) throws IOException {
        int t = 3; // 最多尝试3次终止
        String uid =  user.getUid();
        String gh = user.getGh();
        while(t > 0 && !SignIn2.start(new User(uid, gh, "匿名用户"))){
            t--;
        }
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDatasource());
        if(t == 0){ // 此用户自动签到失败了 打出到日志
            template.update("insert into logs values(?, ?, ?)", gh, "failed", new Date());
        } else {
            template.update("insert into logs values(?, ?, ?)", gh, "success", new Date());
        }
        FileWriter fileWriter = new FileWriter(new File(File.separator + "logs2.txt"), true);
        fileWriter.write("学号:" + gh + " 在时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "打卡" + (t == 0 ? "失败" : "成功"));
        fileWriter.write("\n");
        fileWriter.close();
    }
}