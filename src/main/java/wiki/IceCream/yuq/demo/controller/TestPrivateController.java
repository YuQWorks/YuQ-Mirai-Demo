package wiki.IceCream.yuq.demo.controller;

import com.IceCreamQAQ.Yu.annotation.Action;
import com.icecreamqaq.yuq.annotation.PrivateController;


/**
 * 该触发器响应私聊消息，其他与群聊路由一致
 */
@PrivateController
public class TestPrivateController {


    @Action("hello")
    public String helloYuQ(){
        return "hello,welcome to yuq.";
    }
}
