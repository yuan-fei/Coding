
public class K_Sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(kSum(new int[] {1,2,3,4}, 2, 5));	//2
	}

	/*
	 * count of soluotion to K-sum*/
    public static int kSum(int[] nums, int k, int target) {
        int[][][] state = new int[nums.length + 1][k + 1][target + 1];
        state[0][0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
			state[i][0][0] = 1;
		}
        for (int i = 1; i <= k; i++) {
			state[0][i][0] = 0;
		}
        for (int i = 1; i <= target; i++) {
			state[0][0][i] = 0;
		}
        
        for (int i = 1; i <= nums.length; i++) {
        		for (int j = 1; j <= k; j++) {
        			for (int t = 1; t <= target; t++) {
        				state[i][j][t] = state[i - 1][j][t];
        				if(nums[i - 1] <= t) {
        					state[i][j][t] += state[i][j - 1][t - nums[i - 1]];
        				}
        			}
        		}
        }
        return state[nums.length][k][target];
    }
}
