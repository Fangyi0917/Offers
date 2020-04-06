import java.util.concurrent.*;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("this is callable");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> futureTask = new FutureTask(new MyThread());
        FutureTask<Integer> futureTask1 = new FutureTask(new MyThread());
        new Thread(futureTask, "AAA").start();
        new Thread(futureTask1, "BBB").start();

        int res1 = 100;
        while(!futureTask.isDone()){

        }
        int res2 = futureTask.get();
        System.out.println(res1+res2);
    }
}
