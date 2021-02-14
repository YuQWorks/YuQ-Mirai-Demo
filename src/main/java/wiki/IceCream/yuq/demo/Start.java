package wiki.IceCream.yuq.demo;

import com.icecreamqaq.yuq.mirai.YuQMiraiStart;

public class Start {

    /***
     * 请不要往本类里面添加任何项目代码，也不要在本类里面引用任何类，以防止增强失败。
     * 这个问题在之后会解决。
     * 消息处理顺序 接受---->事件处理（参看event包）---->路由（参看controller包）
     * 特殊功能 job -->定时器 具体查看Job包
     * @param args 启动参数
     */
    public static void main(String[] args) {
        YuQMiraiStart.start(args);
    }

}
