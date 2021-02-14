package wiki.IceCream.yuq.demo.job;

import com.IceCreamQAQ.Yu.annotation.Cron;
import com.IceCreamQAQ.Yu.annotation.JobCenter;
import com.IceCreamQAQ.Yu.util.DateUtil;
import com.icecreamqaq.yuq.YuQ;
import com.icecreamqaq.yuq.message.Message;

import javax.inject.Inject;

@JobCenter
public class JobMain {

    /***
     * 时钟任务。
     * value 参数可接受 1d 1h 1m 1s 1S（天，小时，分钟，秒，毫秒）的参数。
     * 同时也支持类似于 5m5s 的组合参数。
     *
     * 当然如果你愿意，1d2S111m56h7998s333m 这样的参数也能顺利解析并正确使用。
     * 但是代码是写给自己看的，为什么要跟自己过不去呢？
     *
     * 时钟任务方法不接受任何参数，也不接受任何返回值。
     */
    @Cron("10s")
    public void ten() {
        System.out.println("到十秒钟啦！");
    }

    @Inject
    private DateUtil dateUtil;

    @Inject
    private YuQ yuq;

    @Cron("At::h::00")
    public void printTime() {
//        Message message = new Message().plus("内容");

//        yuq.getGroups().get(123456789L).sendMessage(message);
    }

    /***
     * 定时任务。
     * value 必须按规范写成组合
     * 以 At:: 开头。
     * 如果匹配每天的第几个小时的第几分钟，则接下来写 d。
     * 如果匹配某小时的第几分钟则写 h。
     * 接下来写分隔符 ::
     * 如果上一步写的是 d，则写 小时:分钟（这里只有一个 : ）（二十四小时制）。例如: 12:00
     * 如果上一步写的是 h，则直接写第几分钟。例如: 00
     * 所有的冒号均是英文半角。
     *
     * 本实例在每个小时刚开始触发。
     *
     * 定时任务方法不接受任何参数，也不接受任何返回值。
     *
     * At::h::8:15 (每天的八点十五
     *
     * At::d::30 (每个小时的第30分钟
     */
    @Cron("At::h::00")
    public void at00() {
        System.out.println("现在是每个小时开始的第一分钟！" + dateUtil.formatDate());
    }

}
