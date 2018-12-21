package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Problem: given unit task with deadline and penalty, find the set of most
 * tasks to schedule while having minimum total penalty
 * 
 * Solution: greedy. Refer to CLRS problem 16.4
 */
public class UnitTaskScheduling {

	public static void main(String[] args) {
		scheduleUnitTaskWithPenalty();
	}

	static void scheduleUnitTaskWithPenalty() {
		int[] start = { 2, 1, 2, 0 };
		int[] end = { 9, 7, 7, 2 };
		System.out.println(Arrays.toString(scheduleUnitTaskWithPenalty(start, end)));
	}

	public static int[] scheduleUnitTaskWithPenalty(int[] deadline, int[] penalty) {
		UnitTask[] t = new UnitTask[deadline.length];
		int maxDeadline = -1;
		for (int i = 0; i < deadline.length; i++) {
			t[i] = new UnitTask(deadline[i], penalty[i]);
			maxDeadline = Math.max(maxDeadline, t[i].deadline);
		}
		Arrays.sort(t, new Comparator<UnitTask>() {
			@Override
			public int compare(UnitTask o1, UnitTask o2) {
				return -Integer.compare(o1.penalty, o2.penalty);
			}
		});

		int[] scheduling = new int[maxDeadline + 1];
		Arrays.fill(scheduling, -1);
		for (int i = 0; i < t.length; i++) {
			if (scheduling[t[i].deadline] == -1) {
				scheduling[t[i].deadline] = i;
			} else {
				for (int j = t[i].deadline - 1; j >= 0; j--) {
					if (scheduling[j] == -1) {
						scheduling[j] = i;
						break;
					}
				}
			}
		}
		return scheduling;
	}
}

class UnitTask {
	int deadline;
	int penalty;

	public UnitTask(int deadline, int penalty) {
		this.deadline = deadline;
		this.penalty = penalty;
	}

}
