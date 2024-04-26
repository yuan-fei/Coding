/*
 * @lc app=leetcode id=2818 lang=java
 *
 * [2818] Apply Operations to Maximize Score
 *
 * https://leetcode.com/problems/apply-operations-to-maximize-score/description/
 *
 * algorithms
 * Hard (31.76%)
 * Likes:    316
 * Dislikes: 10
 * Total Accepted:    6.4K
 * Total Submissions: 19.9K
 * Testcase Example:  '[8,3,9,3,8]\n2'
 *
 * You are given an array nums of n positive integers and an integer k.
 * 
 * Initially, you start with a score of 1. You have to maximize your score by
 * applying the following operation at most k times:
 * 
 * 
 * Choose any non-empty subarray nums[l, ..., r] that you haven't chosen
 * previously.
 * Choose an element x of nums[l, ..., r] with the highest prime score. If
 * multiple such elements exist, choose the one with the smallest index.
 * Multiply your score by x.
 * 
 * 
 * Here, nums[l, ..., r] denotes the subarray of nums starting at index l and
 * ending at the index r, both ends being inclusive.
 * 
 * The prime score of an integer x is equal to the number of distinct prime
 * factors of x. For example, the prime score of 300 is 3 since 300 = 2 * 2 * 3
 * * 5 * 5.
 * 
 * Return the maximum possible score after applying at most k operations.
 * 
 * Since the answer may be large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [8,3,9,3,8], k = 2
 * Output: 81
 * Explanation: To get a score of 81, we can apply the following operations:
 * - Choose subarray nums[2, ..., 2]. nums[2] is the only element in this
 * subarray. Hence, we multiply the score by nums[2]. The score becomes 1 * 9 =
 * 9.
 * - Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime
 * score of 1, but nums[2] has the smaller index. Hence, we multiply the score
 * by nums[2]. The score becomes 9 * 9 = 81.
 * It can be proven that 81 is the highest score one can obtain.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [19,12,14,6,10,18], k = 3
 * Output: 4788
 * Explanation: To get a score of 4788, we can apply the following operations: 
 * - Choose subarray nums[0, ..., 0]. nums[0] is the only element in this
 * subarray. Hence, we multiply the score by nums[0]. The score becomes 1 * 19
 * = 19.
 * - Choose subarray nums[5, ..., 5]. nums[5] is the only element in this
 * subarray. Hence, we multiply the score by nums[5]. The score becomes 19 * 18
 * = 342.
 * - Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime
 * score of 2, but nums[2] has the smaller index. Hence, we multipy the score
 * by nums[2]. The score becomes 342 * 14 = 4788.
 * It can be proven that 4788 is the highest score one can obtain.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length == n <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= min(n * (n + 1) / 2, 10^9)
 * 
 * 
 */

// @lc code=start
class Solution {
    long MOD = (long)1e9 + 7;
    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[] distinctPrimeFactorsCnt = new int[n];
        for(int i = 0; i < n; i++){
            distinctPrimeFactorsCnt[i] = countDistinctPrimeFactors(nums.get(i));
        }
        int[] lBound = getLeftBound(distinctPrimeFactorsCnt);
        int[] rBound = getRightBound(distinctPrimeFactorsCnt);
        Element[] elements = new Element[n];
        for(int i = 0 ; i < n; i++){
            elements[i] = new Element(nums.get(i), 1L * (i - lBound[i] + 1) * (rBound[i] - i + 1));
        }
        Arrays.sort(elements, Comparator.comparing((Element e) -> e.n()).reversed());
        // System.out.println(Arrays.toString(distinctPrimeFactorsCnt));
        // System.out.println(Arrays.toString(lBound));
        // System.out.println(Arrays.toString(rBound));
        // System.out.println(Arrays.toString(elements));
        long ans = 1;
        for(Element e : elements){
            long exp = Math.min(k, e.freq());
            ans *= pow(e.n, exp);
            ans %= MOD;
            // System.out.println(ans + ", " + exp);
            k -= exp;
            if(k == 0){
                return (int)ans;
            }
        }
        return 0;
    }

    int countDistinctPrimeFactors(int x){
        int cnt = 0;
        for(int f = 2; f * f <= x; f++){
            if(x % f == 0){
                cnt++;
            }
            while(x % f == 0){
                x /= f;
            }
        }
        if(x > 1){
            cnt++;
        }
        return cnt;
    }

    int[] getLeftBound(int[] distinctPrimeFactorsCnt){
        int n = distinctPrimeFactorsCnt.length;
        int[] lBound = new int[n + 1];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && distinctPrimeFactorsCnt[stack.peek()] < distinctPrimeFactorsCnt[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                lBound[i] = stack.peek() + 1;    
            }
            stack.push(i);
        }
        return lBound;
    }

    int[] getRightBound(int[] distinctPrimeFactorsCnt){
        int n = distinctPrimeFactorsCnt.length;
        int[] rBound = new int[n + 1];
        Arrays.fill(rBound, n - 1);
        Stack<Integer> stack = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(!stack.isEmpty() && distinctPrimeFactorsCnt[stack.peek()] <= distinctPrimeFactorsCnt[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                rBound[i] = stack.peek() - 1;
            }
            stack.push(i);
        }
        return rBound;
    }

    long pow(int base, long freq){
        if(freq == 0){
            return 1;
        }
        else if(freq % 2 == 1){
            return (base * pow(base, freq - 1)) % MOD;
        }
        else{
            long ret = pow(base, freq / 2);
            return (ret * ret) % MOD;
        }
    }

    record Element(int n, long freq){
        // @Override
        // public String toString(){return n + ", " + freq;}
    }
}
// @lc code=end
