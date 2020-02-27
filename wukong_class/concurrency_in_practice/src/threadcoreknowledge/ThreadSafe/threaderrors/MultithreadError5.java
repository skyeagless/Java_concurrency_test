package threadcoreknowledge.ThreadSafe.threaderrors;

//观察者模式
public class MultithreadError5 {
    int count = 0;
    //注册监听器匿名内部类持有count这个外部类成员的引用，所以有可能发生count还没初始化，但已经注册完成的情况
    public MultithreadError5(MySource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("我得到的数字" + count);
            }
        });
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource= new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event(){});
        }).start();
        //监听器注册在初始化前
        MultithreadError5 multithreadError5 = new MultithreadError5(mySource);
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

}
