package math;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ReservoirSampling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] counter = new int[4];
		Arrays.fill(counter, 0);
		for (int i = 0; i < 1000; i++) {
			Queue<Integer> stream = new LinkedList<Integer>();
			stream.add(0);
			stream.add(1);
			stream.add(2);
			stream.add(3);
			int[] result = sampling(stream, 2);
			for(int j = 0; j < result.length; j++) {
				counter[result[j]]++;
			}
		}
		for (int i = 0; i < counter.length; i++) {
			System.out.println(counter[i]);
		}
		
	}

	/*
	 * sample n numbers randomly from unknown sized stream with equal probability
	 * each number in stream will have n/total chance to be chosen**/
	public static int[] sampling(Queue<Integer> stream, int n) {
		Random r =  new Random();
		int[] result = new int[n];
		int total = 0;
		while(total < n) {
			result[total] = stream.poll();
			total++;
		}
		while(!stream.isEmpty()) {
			int candidate = stream.poll();
			total++;
			// With probability n/total keep current candidate and roll out 1/n number in result
			if(r.nextInt(total) < n) {
				int i = r.nextInt(n);
				result[i] = candidate;
			}
		}
		return result;
	}
}
