package wiki.IceCream.yuq.demo.aop;

import com.IceCreamQAQ.Yu.hook.HookMethod;
import com.IceCreamQAQ.Yu.hook.HookRunnable;
import com.icecreamqaq.yuq.mirai.MiraiBot;

import javax.swing.*;
import java.lang.reflect.Field;

/**
 * @author Ethereal
 * @date 2020/8/12 9:30
 */
public class BotInitHook implements HookRunnable {

    @Override
    public boolean preRun(HookMethod method) {
        Object obj = method.paras[0];
        if (obj instanceof MiraiBot) {
            String qqStr = System.getProperty("qqNumber");
            String password = System.getProperty("qqPassword");
            try {
                MiraiBot bot = (MiraiBot) obj;
                for (Field field : bot.getClass().getDeclaredFields()) {
                    if (field.getName().equals("qq")) {
                        field.setAccessible(true);
                        field.set(bot, qqStr);
                    } else if (field.getName().equals("pwd")) {
                        field.setAccessible(true);
                        field.set(bot, password);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();

            }
        }
        return false;
    }

    @Override
    public void postRun(HookMethod method) {

    }

    /**
     * 目前版本的Yuq-Mirai在登录失败时，会抛出错误但程序不会结束运行
     * 该方法是登录发生错误时的回调
     * @param method HookMethod对象
     * @return
     */
    @Override
    public boolean onError(HookMethod method) {
        JOptionPane
                .showMessageDialog(null,
                        "账号或密码错误",
                        "登录失败",
                        JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
        return false;
    }
}
