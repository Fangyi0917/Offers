import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

class BlockingQueueDemo{
    public static void main(String[] args){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

        //三组插入、移除、检查方法调用
        System.out.println(blockingQueue.add("a"));
        //System.out.println(blockingQueue.remove("a"));
        //System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.offer("b"));
        //System.out.println(blockingQueue.poll());
        //System.out.println(blockingQueue.peek());

//        try{
//            blockingQueue.put("1");
//        }
//        catch (InterruptedException e){
//            System.out.println(e);
//        }

        BlockingQueue<String> blockingQueue1 = new SynchronousQueue<>();




    }
}