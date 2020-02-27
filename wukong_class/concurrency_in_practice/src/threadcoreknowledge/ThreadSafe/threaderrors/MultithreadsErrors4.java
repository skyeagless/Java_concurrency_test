package threadcoreknowledge.ThreadSafe.threaderrors;

//初始化未完毕，就this复制
public class MultithreadsErrors4 {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new PointMaker()).start();
        //sleep时间太短会直接把还没完全执行完毕的构造函数值返回（1,0），时间较长才回返回(1,1)
        Thread.sleep(1000);
        if (point != null) {
            System.out.println(point);
        }
    }

    static class Point {
        private final int x, y;

        public Point(int x, int y) throws InterruptedException {
            this.x = x;
            MultithreadsErrors4.point = this;
            Thread.sleep(100);
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }

    static class PointMaker implements Runnable {
        @Override
        public void run() {
            try {
                new Point(1, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

