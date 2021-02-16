package wiki.IceCream.yuq.demo.event.myEvent;


import com.IceCreamQAQ.Yu.annotation.Event;
import com.IceCreamQAQ.Yu.annotation.EventListener;
import com.IceCreamQAQ.Yu.event.EventBus;
import com.icecreamqaq.yuq.event.MessageEvent;
import com.icecreamqaq.yuq.message.Message;

import javax.inject.Inject;

/**
 * 此处展示如何进行自定义事件
 * 通过自定义事件可以更好，更简单的实现自己想要的功能
 */
@EventListener
public class MyNewEvent {

    @Inject
    private EventBus eventBus;

    /**
     * 该事件将实现以@和！开头的信息导入不同的事件中
     */
    @Event
    public void isStartWithSpecialTag(MessageEvent event){
        String messageString = Message.Companion.firstString(event.getMessage());
        if(messageString.startsWith("!")){
            event.getSender();
            /**
             * 使用new一下你的事件，然后把必要的属性放上去
             * 然后post一下，就会到你的事件里面了
             */
            MessageStartWithTag1 e = new MessageStartWithTag1(event,"!");
            eventBus.post(e);
        }
        if(messageString.startsWith("@")){
            MessageStartWithTag2 e = new MessageStartWithTag2(event,"@");
            eventBus.post(e);
        }



    }

    @Event
    public void forTheTag1(MessageStartWithTag1 event){
        System.out.println("------tag1------");
        //获取消息
        event.messageEvent.getMessage();
        System.out.println(event.messageEvent.getMessage());
        //获取自定义事件中的内容
        String s =event.startTag;
        System.out.println(s);
        //发送信息
        event.messageEvent.getSender().sendMessage(Message.Companion.toMessage("收到了以!开头的信息，").plus(event.messageEvent.getMessage()));
        //取消事件
        event.setCancel(true);

    }
    @Event
    public void forTheTag2(MessageStartWithTag2 event){
        System.out.println("------tag2------");
        //获取消息
        event.messageEvent.getMessage();
        System.out.println(event.messageEvent.getMessage());
        //获取自定义事件中的内容
        String s =event.startTag;
        System.out.println(s);
        //发送信息
        event.messageEvent.getSender().sendMessage(Message.Companion.toMessage("收到了以@开头的信息，").plus(event.messageEvent.getMessage()));
        //取消事件
        event.setCancel(true);

    }

}
