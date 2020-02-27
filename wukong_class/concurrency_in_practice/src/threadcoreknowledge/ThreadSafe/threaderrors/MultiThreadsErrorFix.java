package threadcoreknowledge.ThreadSafe.threaderrors;
//用工厂模式修复初始化问题
public class MultiThreadsErrorFix {
    int count;
    private EventListener listener;

    //构造方法变成private
    private MultiThreadsErrorFix(MySource source) {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我得到的数字"+count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    //工厂方法,先初始化完，然后再注册
    public static MultiThreadsErrorFix getInstance(MySource source){
        MultiThreadsErrorFix safeListener = new MultiThreadsErrorFix(source);
        source.registerListener(safeListener.listener);
        return safeListener;
    }

    static class MySource{
        private EventListener listener;
        void registerListener(EventListener eventListener){
            this.listener = eventListener;
        }
        void eventCome(Event e){
            if(listener!=null){
                listener.onEvent(e);
            }else{
                System.out.println("还未初始化完毕");
            }
        }
    }
    interface Event{}
    interface EventListener{
        void onEvent(Event e);
    }

    public static void main(String[] args) {
        MySource mySource= new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event(){});
        }).start();
        MultiThreadsErrorFix m = getInstance(mySource);
    }

}
