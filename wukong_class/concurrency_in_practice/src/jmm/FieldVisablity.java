package jmm;

//演示可见性带来的问题
public class FieldVisablity {
    int a = 1;
    //b加了volatile，后面想读取b，可以看到写入b之前的所有操作，包括a =3
    volatile int b = 2;

    public static void main(String[] args) {
        FieldVisablity test = new FieldVisablity();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.change();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.print();
            }
        }).start();
        
        
    }

    private void print() {
        System.out.println("b="+ b + " a="+a);
    }

    private void change() {
        a = 3;
        b = a;
    }
}
