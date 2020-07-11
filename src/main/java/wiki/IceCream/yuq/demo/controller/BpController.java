package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.Action;
import com.icecreamqaq.yuq.FunKt;
import com.icecreamqaq.yuq.annotation.GroupController;
import com.icecreamqaq.yuq.controller.ContextSession;
import com.icecreamqaq.yuq.controller.QQController;

@GroupController
public class BpController extends QQController {

    /***
     * 可以通过声明一个参数为 ContextSession 类型，名叫 session 的参数。
     * 调用 session 的 waitNextMessage 方法，可以挂起当前线程，并且等待一条新消息。
     *
     * 注意：消息在被传递到这里之前，会先经过 MessageEvent，则代表当取消事件时，消息并不会被传递到本处。
     *
     * com.com.icecreamqaq.yuq.FunKt 是一个简单的工具类，封装了一定方法可供快捷使用。
     * FunKt.firstString 方法，获取 MessageBody 的第一个元素，并且转换为 String。
     */
    @Action("绑手机")
    public String bp(ContextSession session) {
        reply("请输入手机号码");
        String phone = FunKt.firstString(session.waitNextMessage());
        reply("请输入手机验证码");
        String key = FunKt.firstString(session.waitNextMessage());
        return String.format("您输入的手机号码为：%s，手机验证码为：%s。", phone, key);
    }

}
