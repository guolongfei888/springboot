package com.panshi.springbootasyncdemo2.controller;

import com.panshi.springbootasyncdemo2.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName TestController
 * @Description
 * @Author guolongfei
 * @Date 2020/4/28  14:55
 * @Version
 */
@RestController
public class TestController {
    @Autowired
    private MyService myService;

    @RequestMapping("/test")
    public String getIndex() throws InterruptedException, TimeoutException, ExecutionException {
        System.out.println("开始访问");
        long l1 = System.currentTimeMillis();
        Future<String> r1 = myService.JobOne();
        Future<String> r2 = myService.JobTwo();
        Future<String> r3 = myService.JobThree();
        String result2 = r2.get(1000, TimeUnit.MILLISECONDS);
        System.out.println("使用有参get()得到的返回值"+result2);
        long l2 = System.currentTimeMillis();
        while(true) {//死循环，每隔2000ms执行一次，判断一下这三个异步调用的方法是否全都执行完了。
            if(r1.isDone() && r2.isDone() && r3.isDone()) {//使用Future的isDone()方法返回该方法是否执行完成
                //如果异步方法全部执行完，跳出循环
                break;
            }
            Thread.sleep(2000);//每隔2000毫秒判断一次
        }
        String result = r1.get();
		/*
		 * String result2 = r2.get(50, TimeUnit.MILLISECONDS);
		   System.out.println("使用有参get()得到的返回值"+result2);
		   最开始在实验的时候把这两句话放在了这里，就一直测试不出来超时异常，
		   后来才发现把超时设置在while循环判断已经任务完成之后，那么超时设置当然就不起作用了，
		   所以放在这里也就不会出现超时异常，应该放在while循环之前即在任务开始执行之后就对其执行时长进行超时设置才会对时间真正起到限制作用。
		 */
        System.out.println("结束访问,用时"+(l2-l1));
        System.out.println("使用get方法获得的返回内容:"+result);

        return "finished";
    }
}
