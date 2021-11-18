/*
 * @lc app=leetcode id=2071 lang=java
 *
 * [2071] Maximum Number of Tasks You Can Assign
 *
 * https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/description/
 *
 * algorithms
 * Hard (32.57%)
 * Likes:    51
 * Dislikes: 6
 * Total Accepted:    830
 * Total Submissions: 2.5K
 * Testcase Example:  '[3,2,1]\n[0,3,3]\n1\n1'
 *
 * You have n tasks and m workers. Each task has a strength requirement stored
 * in a 0-indexed integer array tasks, with the i^th task requiring tasks[i]
 * strength to complete. The strength of each worker is stored in a 0-indexed
 * integer array workers, with the j^th worker having workers[j] strength. Each
 * worker can only be assigned to a single task and must have a strength
 * greater than or equal to the task's strength requirement (i.e., workers[j]
 * >= tasks[i]).
 * 
 * Additionally, you have pills magical pills that will increase a worker's
 * strength by strength. You can decide which workers receive the magical
 * pills, however, you may only give each worker at most one magical pill.
 * 
 * Given the 0-indexed integer arrays tasks and workers and the integers pills
 * and strength, return the maximum number of tasks that can be completed.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
 * Output: 3
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 0.
 * - Assign worker 0 to task 2 (0 + 1 >= 1)
 * - Assign worker 1 to task 1 (3 >= 2)
 * - Assign worker 2 to task 0 (3 >= 3)
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
 * Output: 1
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 0.
 * - Assign worker 0 to task 0 (0 + 5 >= 5)
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength =
 * 10
 * Output: 2
 * Explanation:
 * We can assign the magical pills and tasks as follows:
 * - Give the magical pill to worker 0 and worker 1.
 * - Assign worker 0 to task 0 (0 + 10 >= 10)
 * - Assign worker 1 to task 1 (10 + 10 >= 15)
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: tasks = [5,9,8,5,9], workers = [1,6,4,2,6], pills = 1, strength = 5
 * Output: 3
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 2.
 * - Assign worker 1 to task 0 (6 >= 5)
 * - Assign worker 2 to task 2 (4 + 5 >= 8)
 * - Assign worker 4 to task 3 (6 >= 5)
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == tasks.length
 * m == workers.length
 * 1 <= n, m <= 5 * 10^4
 * 0 <= pills <= m
 * 0 <= tasks[i], workers[j], strength <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxTaskAssign(int[] A, int[] B, int cnt, int add) {
        Arrays.sort(A);
        Arrays.sort(B);
        int l = 0, r = A.length-1;
        int res = -1;
        while(l<=r){
            int mid = l +(r-l)/2;
            if(check(A,B,cnt,add,mid)){
                res=mid;
                l=mid+1;
            }else{
                r=mid-1;
            }
        }
        
        return res+1;
    }
    
    public boolean check(int A[],int B[],int cnt,int add,int mid){
        TreeMap<Integer,Integer>tree=new TreeMap<>();
        int j = B.length-1;
        
        for(int i = mid; i >=0; i--){
            while(j>=0&&B[j]>=A[i]){
                add(tree,B[j]);
                j--;
            }
            
            Integer ceil = tree.ceilingKey(A[i]);
            if(ceil!=null){
                del(tree,ceil);
            }
            else{
                if(cnt>0){
                    while(j>=0&&B[j]+add>=A[i]){
                        add(tree,B[j]);
                        j--;
                    }
                    
                    ceil = tree.ceilingKey(A[i]-add);
                    if(ceil!=null){
                        cnt--;
                        del(tree,ceil);
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
                
            }
        }
        return true;
    }
    
    public void add(TreeMap<Integer,Integer>tree,int k){
        if(!tree.containsKey(k))tree.put(k,0);
        tree.put(k,tree.get(k)+1);
    }
    
    public void del(TreeMap<Integer,Integer>tree,int k){
        tree.put(k,tree.get(k)-1);
        if(tree.get(k)==0)tree.remove(k);
    }
}
// @lc code=end
