package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Problem: Given n tasks with start and end time, no 2 tasks can run at the
 * same time. Pick the most tasks which can be scheduled sequentially
 * 
 * Solution: Greedy
 */
public class ActivitySelection {

	public static void main(String[] args) {
		testActivitySelection();
	}

	static void testActivitySelection() {
		int[] start = { 1, 2, 7, 6, 8 };
		int[] end = { 3, 5, 7, 9, 10 };
		System.out.println(Arrays.toString(activitySelection(start, end)));
	}

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

	static class Task {
		int start;
		int end;

		public Task(int start, int end) {
			this.start = start;
			this.end = end;
		}

	}
}
