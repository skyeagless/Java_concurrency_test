

//可重入粒度测试:递归调用本方法
public class SynchroizedRecursion6 {
    int a = 0;

    public static void main(String[] args) {
        SynchroizedRecursion6 synchroizedRecursion6 = new SynchroizedRecursion6();
        synchroizedRecursion6.method1();
    }

    private synchronized void method1(){
        System.out.println("这是method1,a="+a);
        if(a == 0){
            a++;
            method1();
        }
    }
}
