package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.Action;
import com.icecreamqaq.yuq.FunKt;
import com.icecreamqaq.yuq.annotation.GroupController;
import com.icecreamqaq.yuq.controller.ContextSession;
import com.icecreamqaq.yuq.controller.QQController;
import com.icecreamqaq.yuq.message.JsonEx;

@GroupController
public class JsonController extends QQController {

    /***
     * 可以通过声明一个参数为 ContextSession 类型，名叫 session 的参数。
     * 调用 session 的 waitNextMessage 方法，可以挂起当前线程，并且等待一条新消息。
     *
     * 注意：消息在被传递到这里之前，会先经过 MessageEvent，则代表当取消事件时，消息并不会被传递到本处。
     *
     * com.com.icecreamqaq.yuq.FunKt 是一个简单的工具类，封装了一定方法可供快捷使用。
     * FunKt.firstString 方法，获取 MessageBody 的第一个元素，并且转换为 String。
     */
    @Action("准备json")
    public JsonEx json(ContextSession session) {
        reply("您请说");
        return mif.jsonEx(FunKt.firstString(session.waitNextMessage()));
    }

}
