import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列实现的生产者-消费者模型
 */

class MyResource{
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void MyProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while(FLAG){
            data = atomicInteger.incrementAndGet()+ "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue){

                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");

            }
            else{
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "线程叫停，生产动作结束");

    }

    public void MyConsumer() throws  Exception{
        String res = null;
        while(FLAG){
            res = blockingQueue.poll(2,TimeUnit.SECONDS);
            if(null == res || res.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "超时没有取到数据，消费退出" );
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列中的" + res + "成功");
        }
    }

    public void stop() throws  Exception{
        this.FLAG = false;
    }

}
public class ProdConstumer_BlockQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue(3));
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "\t生产线程启动");
            System.out.println();
            System.out.println();
            try{
                myResource.MyProd();
            }catch(Exception e){
                e.printStackTrace();
            }
        }, "prod").start();

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "\t消费线程启动");
            System.out.println();
            System.out.println();
            try{
                myResource.MyConsumer();
            }catch(Exception e){
                e.printStackTrace();
            }
        }, "consumer").start();
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("main线程叫停，活动结束");
        myResource.stop();

    }

}
