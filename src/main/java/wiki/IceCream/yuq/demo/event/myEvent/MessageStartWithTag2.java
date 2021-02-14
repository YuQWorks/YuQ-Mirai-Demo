package wiki.IceCream.yuq.demo.event.myEvent;

import com.IceCreamQAQ.Yu.event.events.Event;
import com.icecreamqaq.yuq.event.MessageEvent;

/**
 * 参考一即可。
 */
public class MessageStartWithTag2 extends Event {

    MessageEvent messageEvent;
    String startTag;

    public MessageStartWithTag2(MessageEvent messageEvent, String startTag) {
        this.messageEvent = messageEvent;
        this.startTag = startTag;
    }
}

