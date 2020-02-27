package jmm.singleton;

//懒汉式，线程不安全
public class singleton2 {
    private static singleton2 INSTANCE;
    private singleton2(){}
    public static singleton2 getInstance(){
        //线程不安全
        if(INSTANCE == null){
            INSTANCE = new singleton2();
        }
        return INSTANCE;
    }
}
