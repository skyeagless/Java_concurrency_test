package jmm.singleton;

//懒汉式，线程安全，不推荐,效率太低
public class singleton3 {
    private static singleton3 INSTANCE;
    private singleton3(){}
    public synchronized static singleton3 getInstance(){
        if(INSTANCE == null){
            INSTANCE = new singleton3();
        }
        return INSTANCE;
    }
}
