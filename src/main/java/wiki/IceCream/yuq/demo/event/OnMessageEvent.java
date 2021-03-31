package wiki.IceCream.yuq.demo.event;

import com.IceCreamQAQ.Yu.annotation.Event;
import com.IceCreamQAQ.Yu.annotation.EventListener;
import com.icecreamqaq.yuq.event.GroupMessageEvent;
import com.icecreamqaq.yuq.event.MessageEvent;
import com.icecreamqaq.yuq.event.PrivateMessageEvent;
import com.icecreamqaq.yuq.message.Message;

@EventListener
public class OnMessageEvent {


    /***
     * 整体
     *
     * 当收到群聊消息时，本方法会被调用。
     * 事件会优先于控制器收到响应。
     * 事件可以被取消，当事件被取消之后，控制器将不会再响应。
     * @param event 事件
     */
    @Event(weight = Event.Weight.normal)
    public void onGroupMessage(GroupMessageEvent event) {
        System.out.printf("消息来自群：%s(%d)%n", event.getGroup().getName(), event.getGroup().getId());
        System.out.printf("消息来自群成员：%s(%d)%n" , event.getSender().getNameCard(),event.getSender().getId());
    }

    /***
     * 一个事件可以被重复注册多次，并可以通过指定优先级来让他们保持一定的先后顺序。
     * 本事件则简单地介绍了一个取消事件的方式。
     *
     * 当消息事件被取消后，后续将不会再进行控制器部分的响应了。
     * 也就是说，群号为 111 的群，永远不会响应到 Controller
     */
    @Event(weight = Event.Weight.low)
    public void onGroupMessageLow(GroupMessageEvent event) {
        if (111 == event.getGroup().getId()) {
            event.setCancel(true);
        }
    }

    /***
     * 当收到私聊消息时，本方法会被调用。
     * 事件会优先于控制器收到响应。
     * 事件可以被取消，当事件被取消之后，控制器将不会再响应。
     * @param event 事件
     */
    @Event
    public void onPrivateMessage(PrivateMessageEvent event) {
        System.out.printf("消息来自群成员：%s(%d)%n" , event.getSender().getName(),event.getSender().getId());
    }

    /***
     * 当你注册了某个事件的父事件的时候，则这个父事件的所有子事件都会被响应。
     * 如 MessageEvent 是 PrivateMessageEvent 和 GroupMessageEvent 的父事件，
     * 则 PrivateMessageEvent 和 GroupMessageEvent 事件触发的时候，监听了 MessageEvent 事件的监听器都会受到响应。
     */
    @Event
    public void onMessage(MessageEvent event) {

    }

}
