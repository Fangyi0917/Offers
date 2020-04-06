import java.util.concurrent.*;
public class ThreadPool{

	public static void main (String args[]) {
		//获取cpu核数
		System.out.println(Runtime.getRuntime().availableProcessors());
		//如何配置线程数
		ExecutorService threadPool = new ThreadPoolExecutor(2,5,1L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
			
			
	}
	
}