package jmm.singleton;

//双重检查(线程安全，延迟加载，效率较高）
//不用volatile的原因（1.新建对象实际有三个步骤：建空对象，调用构造函数，赋值 2.重排序会导致空指针问题 3.volatile可以防止重排序
// 4. 可见性的一些问题）
public class singleton4 {
    private volatile static singleton4 INSTANCE;
    private singleton4(){}
    public static singleton4 getInstance(){
        if(INSTANCE == null){
            synchronized (singleton4.class){
                if(INSTANCE == null){
                    INSTANCE = new singleton4();
                }
            }
        }
        return INSTANCE;
    }
}
