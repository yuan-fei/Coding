package design;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TaskQueue {

	public static void main(String[] args) throws IOException {
		TaskQueue q = new TaskQueue();
		Producer[] p = new Producer[2];
		Consumer[] c = new Consumer[3];

		for (int i = 0; i < c.length; i++) {
			c[i] = q.new Consumer(q, i);
			new Thread(c[i]).start();
		}
		for (int i = 0; i < p.length; i++) {
			p[i] = q.new Producer(q, i);
			new Thread(p[i]).start();
		}
		// System.in.read();
	}

	Queue<Integer> queue = new LinkedList<Integer>();

	public void enQueue(int i) {
		synchronized (this) {
			queue.offer(i);
			this.notify();
		}
	}

	public int deQueue() throws InterruptedException {
		synchronized (this) {
			while (queue.isEmpty()) {
				this.wait();
			}
			int task = queue.poll();
			this.notify();
			return task;
		}
	}

	class Producer implements Runnable {
		int id;
		TaskQueue tq;

		public Producer(TaskQueue q, int id) {
			tq = q;
			this.id = id;
		}

		@Override
		public void run() {
			Random r = new Random();
			for (int i = 0; i < 10; i++) {
				int task = r.nextInt();
				System.out.println("Producer " + id + " adds task " + task);
				tq.enQueue(task);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	class Consumer implements Runnable {
		int id;
		TaskQueue tq;

		public Consumer(TaskQueue q, int id) {
			tq = q;
			this.id = id;
		}

		@Override
		public void run() {
			while (true) {
				try {
					int task = tq.deQueue();
					System.out.println("Consumer " + id + " processes task " + task);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}
}
