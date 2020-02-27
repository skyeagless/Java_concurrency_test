package jmm.singleton;

//静态内部类，可用(延迟加载）
public class singleton5 {
    private singleton5(){}
    private static class SingletonInstance{
        private static final singleton5 INSTANCE = new singleton5();
    }

    public static singleton5 getInstance(){
        return SingletonInstance.INSTANCE;
    }

//    public static void main(String[] args) {
//        singleton6.INSTANCE.some_method();
//    }
}
