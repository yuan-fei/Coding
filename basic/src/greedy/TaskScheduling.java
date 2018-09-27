package greedy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TaskScheduling {

	public static void main(String[] args) {
		testActivitySelection();
		scheduleUnitTaskWithPenalty();
	}

	static void testActivitySelection() {
		int[] start = { 1, 2, 7, 6, 8 };
		int[] end = { 3, 5, 7, 9, 10 };
		System.out.println(Arrays.toString(activitySelection(start, end)));
	}

	static void scheduleUnitTaskWithPenalty() {
		int[] start = { 2, 1, 2, 0 };
		int[] end = { 9, 7, 7, 2 };
		System.out.println(Arrays.toString(scheduleUnitTaskWithPenalty(start, end)));
	}

	/**
	 * Problem: Given n tasks with start and end time, no 2 tasks can run at the
	 * same time. Pick the most tasks which can be scheduled sequentially
	 * 
	 * Solution: Greedy
	 */
	public static int[] activitySelection(int[] start, int[] end) {
		Task[] t = new Task[start.length];
		for (int i = 0; i < start.length; i++) {
			t[i] = new Task(start[i], end[i]);
		}
		Arrays.sort(t, new Comparator<Task>() {
			@Override
			public int compare(Task o1, Task o2) {
				return Integer.compare(o1.end, o2.end);
			}

		});
		int lastEnd = -1;
		List<Integer> selectedTask = new ArrayList<Integer>();
		for (int i = 0; i < t.length; i++) {
			if (lastEnd < t[i].start) {
				selectedTask.add(i);
				lastEnd = t[i].end;
			}
		}
		return selectedTask.stream().mapToInt(i -> i.intValue()).toArray();
	}

	/**
	 * Problem: given unit task with deadline and penalty, find the set of most
	 * tasks to schedule while having minimum total penalty
	 * 
	 * Solution: greedy. Refer to CLRS problem 16.4
	 */
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

class Task {
	int start;
	int end;

	public Task(int start, int end) {
		this.start = start;
		this.end = end;
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
