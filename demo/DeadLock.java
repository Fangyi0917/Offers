
class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run(){
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 持有锁" + lockA + "\t尝试获得" + lockB);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 持有锁" + lockB + "\t尝试获得" + lockA);
            }
        }
    }
}
public class DeadLock{
    public static void main(String[] args){
        String lockA = "A";
        String lockB = "B";
        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();

    }
}