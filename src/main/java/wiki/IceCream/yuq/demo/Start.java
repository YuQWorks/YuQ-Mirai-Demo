package wiki.IceCream.yuq.demo;

import com.IceCreamQAQ.Yu.hook.HookItem;
import com.IceCreamQAQ.Yu.hook.YuHook;
import com.icecreamqaq.yuq.mirai.YuQMiraiStart;

public class Start {

    /**
     * 请不要往本类里面添加任何项目代码，也不要在本类里面引用任何类，以防止增强失败。这个问题在之后会解决
     * 当前版本的命令行启动时，不会支持yu.config.runMode，如果想要使用runMode还请使用
     * 不要在命令行中添加参数，仍通过YuQ.properties启动，在YuQ.properties中配置使用runMode
     * @param args 启动参数
     */
    public static void main(String[] args) {
        if (args.length == 0){
            // 使用conf下的YuQ.properties启动项目
        } else {
            if (args.length != 2){
                System.err.println("命令行格式错误, 格式: qq号 qq密码");
                System.exit(-1);
            } else {
                System.setProperty("qqNumber", args[0]);
                System.setProperty("qqPassword", args[1]);

                YuHook.put(new HookItem("com.icecreamqaq.yuq.mirai.MiraiBot",
                        "init",
                        "wiki.IceCream.yuq.demo.aop.BotInitHook"));
            }
        }

        YuQMiraiStart.start();
    }

}
