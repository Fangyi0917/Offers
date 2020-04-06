import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程顺序打印，循环10次
 * AA5次，BB10次，CC15次
 * AA5次，BB10次，CC15次
 * 。。。
 */
class ShareResource{
    private int number = 1;
    Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5(){
        lock.lock();
        try{
            //判断
            while(number != 1){
                //等待
                c1.await();
            }
            //操作

            System.out.println(Thread.currentThread().getName()+"\t打印了5次");
            //通知
            number  = 2;
            c2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //判断
            while(number != 2){
                //等待
                c2.await();
            }
            //操作

            System.out.println(Thread.currentThread().getName()+"\t打印了10次");
            //通知
            number = 3;
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //判断
            while(number != 3){
                //等待
                c3.await();
            }
            //操作

            System.out.println(Thread.currentThread().getName()+"\t打印了15次");
            //通知
            number = 1;
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}

public class ThreadPrintDemo {

    public static void main(String[] args){
        ShareResource shareResource = new ShareResource();
        new Thread(() ->{
            for(int i = 0; i < 5; i++){

                shareResource.print5();
                System.out.println();
            }
        }).start();
        new Thread(() ->{
            for(int i = 0; i < 5; i++){

                shareResource.print10();
                System.out.println();
            }
        }).start();
        new Thread(() ->{
            for(int i = 0; i < 5; i++){
                shareResource.print15();
                System.out.println();
            }
        }).start();

    }
}
