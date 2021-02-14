package wiki.IceCream.yuq.demo.event;

import com.IceCreamQAQ.Yu.annotation.Event;
import com.IceCreamQAQ.Yu.annotation.EventListener;
import com.icecreamqaq.yuq.event.NewFriendRequestEvent;

@EventListener
public class FriendListEvent {

    /***
     * 事件的注册，并不会限制你在某个类去注册，只要你的类标记了 EventListener_ 注解。
     * 有利于对事件进行分类
     * 具体的事件列表请查看github文档
     * https://yuqworks.github.io/YuQ-Doc/#/
     *
     * NewFriendRequestEvent 事件
     * 当有新的好友申请的时候，会触发本事件。
     * 如果您将事件的 accept 属性设置为 true，并同时取消了事件，那么将同意好友请求。
     * 否则将忽略（不进行任何处理）这个好友请求。
     */
    @Event
    public void newFriendRequestEvent(NewFriendRequestEvent event) {
        event.setAccept(true);
        event.setCancel(true);
    }


}
