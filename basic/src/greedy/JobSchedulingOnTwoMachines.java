package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Johnson's Rule
 * (https://cp-algorithms.com/schedules/schedule_two_machines.html)
 * 
 * Given a set of tasks of 2 steps, for each task, step A must be finished
 * before B, what is the min time?
 */
public class JobSchedulingOnTwoMachines {

	public static void main(String[] args) {
		int[][] tasks = new int[][] { new int[] { 1, 2 }, new int[] { 2, 1 }, new int[] { 3, 3 } };
		int[][] schedule = scheduleTasks(tasks);
		System.out.println(Arrays.deepToString(schedule));
		System.out.println(getFinishTime(schedule));
	}

	static int[][] scheduleTasks(int[][] tasks) {
		Arrays.sort(tasks, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(Math.min(o1[0], o1[1]), Math.min(o2[0], o2[1]));
			}
		});
		int[][] schedule = new int[tasks.length][];
		int left = 0;
		int right = schedule.length - 1;
		for (int i = 0; i < tasks.length; i++) {
			if (tasks[i][0] < tasks[i][1]) {
				schedule[left++] = tasks[i];
			} else {
				schedule[right--] = tasks[i];
			}
		}
		return schedule;
	}

	static int getFinishTime(int[][] tasks) {
		int t1 = 0;
		int t2 = 0;
		for (int i = 0; i < tasks.length; i++) {
			t1 += tasks[i][0];
			t2 = Math.max(t1, t2) + tasks[i][1];
		}
		return t2;
	}
}
