package cn.stt.test;

/**
 * Created by Administrator on 2016-11-24.
 */
public class TestVolatile {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final TestVolatile test = new TestVolatile();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
        System.out.println("Thread.activeCount()11111::"+Thread.activeCount());
        while(Thread.activeCount()>2){  //保证前面的线程都执行完
            System.out.println("Thread.activeCount()::"+Thread.activeCount());

            Thread.yield();
        }
        System.out.println(test.inc);
    }
}
