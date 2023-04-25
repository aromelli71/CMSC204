import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
	Queue<Integer> q;

	public CarQueue() {
		q = new LinkedList<>();
		q.add(0);
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(0);
		q.add(1);
		q.add(2);
		q.add(3);
	}

	public void addToQueue() {
		
		class Runner implements Runnable {
			@Override
			public void run() {
				try {
					for (int i=0;i<999;i++) {
						int output = new java.util.Random().nextInt(4);
						q.add(output);
						Thread.sleep(0);
					}
				} catch (InterruptedException exception) {
				}
			}
		}
		
		Runnable r = new Runner();
		Thread t = new Thread(r);
		t.start();
	}
	
	public Integer deleteQueue() {
		return q.remove();
	}
}