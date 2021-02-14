package wiki.IceCream.yuq.demo.event.myEvent;


import com.IceCreamQAQ.Yu.event.events.Event;
import com.icecreamqaq.yuq.event.MessageEvent;

/**
 * 自定义事件第一步，继承Event
 */
public class MessageStartWithTag1 extends Event {

    /**
     * 第二步 确定你的事件内容
     * 可以直接是其他的event方便你对事件进行二次包装
     * 也能是其他情况，这里只是简单举例
     */
    MessageEvent messageEvent;
    String startTag;

    /**
     * 最后一步，一个构造器
     * @param messageEvent
     * @param startTag
     */
    public MessageStartWithTag1(MessageEvent messageEvent, String startTag) {
        this.messageEvent = messageEvent;
        this.startTag = startTag;
    }
}
