package threadcoreknowledge.ThreadSafe.threaderrors;

import java.util.HashMap;
import java.util.Map;

//构造函数里新建线程
public class MultithreadError6 {
    private Map<String,String> states;

    public MultithreadError6() {
        //构造函数里不能新开线程
        new Thread(()->{
            states = new HashMap<>();
            states.put("1","周一");
            states.put("2","周二");
            states.put("3","周三");
            states.put("4","周四");
            states.put("5","周五");
        }).start();
    }

    public Map<String,String> getStates(){
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        MultithreadError6 obj = new MultithreadError6();
        Thread.sleep(1000);
        Map<String,String> states = obj.getStates();
        System.out.println(states.get("1"));
    }
}
