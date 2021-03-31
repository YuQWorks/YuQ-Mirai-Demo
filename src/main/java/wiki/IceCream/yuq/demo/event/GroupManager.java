package wiki.IceCream.yuq.demo.event;

import com.IceCreamQAQ.Yu.annotation.Event;
import com.IceCreamQAQ.Yu.annotation.EventListener;
import com.icecreamqaq.yuq.event.GroupMemberJoinEvent;
import com.icecreamqaq.yuq.event.GroupMemberRequestEvent;
import com.icecreamqaq.yuq.message.Message;


@EventListener
public class GroupManager {


    //表明这是一个事件
    @Event(weight = Event.Weight.normal)
    /**
     * 需要处理的种种事件，直接寻找对应参数，进行处理即可。
     * @GroupMemberRequestEvent 是群申请入群事件
     */
    public void requestGroup(GroupMemberRequestEvent event){
        if("暗号".equals(event.getMessage())){
            //同意进群申请
            event.setAccept(true);
        }else {
            //拒绝进群事情
            event.setAccept(false);
            //设置拒绝原因
            event.setRejectMessage("暗号错误");
        }
        //取消事件（这样事件才会返回拒绝或者同意的请求，没有这一步事件是不会完成的。
        event.setCancel(true);
    }

    @Event
    public void welcome(GroupMemberJoinEvent event){
        event.getGroup().sendMessage(Message.Companion.toMessage("欢迎新朋友~"));
    }



}
