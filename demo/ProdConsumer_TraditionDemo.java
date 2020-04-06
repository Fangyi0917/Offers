import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个初始值为0的变量，两个线程交替操作，一个+1， 一个-1
 * 判断、操作、通知
 * 防止虚假唤醒机制
 */

class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() throws Exception{
        lock.lock();
        try {
            //判断
            while (number != 0) {
                // 等待
                condition.await();
            }
            //操作
            number++;
            System.out.println(Thread.currentThread().getName() + "\t生产后,SD为\t" + number);
            //通知唤醒
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
    public void decrement() throws Exception{
        lock.lock();
        try {
            //判断
            while (number == 0) {
                // 等待
                condition.await();
            }
            //操作
            number--;
            System.out.println(Thread.currentThread().getName() + "\t消费后,SD为\t" + number);
            //通知唤醒
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
    }
}
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args){
        ShareData shareData = new ShareData();
        new Thread(() ->{
            for (int i = 0; i < 5; i++){
                try{
                    shareData.increment();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }, "AAA").start();
        new Thread(() ->{
            for (int i = 0; i < 5; i++){
                try{
                    shareData.decrement();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }, "BBB").start();
    }
}
