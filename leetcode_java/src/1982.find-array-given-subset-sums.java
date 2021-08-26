/*
 * @lc app=leetcode id=1982 lang=java
 *
 * [1982] Find Array Given Subset Sums
 *
 * https://leetcode.com/problems/find-array-given-subset-sums/description/
 *
 * algorithms
 * Hard (43.66%)
 * Likes:    67
 * Dislikes: 9
 * Total Accepted:    1.2K
 * Total Submissions: 2.8K
 * Testcase Example:  '3\n[-3,-2,-1,0,0,1,2,3]'
 *
 * You are given an integer n representing the length of an unknown array that
 * you are trying to recover. You are also given an array sums containing the
 * values of all 2^n subset sums of the unknown array (in no particular
 * order).
 * 
 * Return the array ans of length n representing the unknown array. If multiple
 * answers exist, return any of them.
 * 
 * An array sub is a subset of an array arr if sub can be obtained from arr by
 * deleting some (possibly zero or all) elements of arr. The sum of the
 * elements in sub is one possible subset sum of arr. The sum of an empty array
 * is considered to be 0.
 * 
 * Note: Test cases are generated such that there will always be at least one
 * correct answer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, sums = [-3,-2,-1,0,0,1,2,3]
 * Output: [1,2,-3]
 * Explanation: [1,2,-3] is able to achieve the given subset sums:
 * - []: sum is 0
 * - [1]: sum is 1
 * - [2]: sum is 2
 * - [1,2]: sum is 3
 * - [-3]: sum is -3
 * - [1,-3]: sum is -2
 * - [2,-3]: sum is -1
 * - [1,2,-3]: sum is 0
 * Note that any permutation of [1,2,-3] and also any permutation of [-1,-2,3]
 * will also be accepted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, sums = [0,0,0,0]
 * Output: [0,0]
 * Explanation: The only correct answer is [0,0].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 4, sums = [0,0,5,5,4,-1,4,9,9,-1,4,3,4,8,3,8]
 * Output: [0,-1,4,5]
 * Explanation: [0,-1,4,5] is able to achieve the given subset sums.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 15
 * sums.length == 2^n
 * -10^4 <= sums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] recoverArray(int n, int[] sums) {
        List<Integer> sumList = new ArrayList();
        for(int sum :sums){
            sumList.add(sum);
        }
        Collections.sort(sumList, (a, b) -> Integer.compare(b,a ));
        int res[] =new int[n];
        int i = 0;
        while(sumList.size() > 2){
            List<Integer> array1 = new ArrayList(), array2 = new ArrayList();
            int num = sumList.get(0) - sumList.get(1);
            Map<Integer, Integer> map =  getCountMap(sumList);
            for(int elm : sumList){
                if(map.containsKey(elm)){
                    array2.add(elm);
                    array1.add(elm - num);
                    remove(map, elm);
                    remove(map, elm - num);
                }
            }
            int index = array2.indexOf(num);
            if(index != -1){
                if(array1.get(index) == 0){
                    res[i++] = num;
                    sumList = array1;
                    continue;
                }
            }
            res[i++] = -num;
            sumList = array2;
        }
        if(sumList.get(0)==0){
            res[i++] = sumList.get(1);
        }else{
            res[i++] = sumList.get(0);
        }
        return res;
    }
    private Map<Integer, Integer> getCountMap(List<Integer> list){
        Map<Integer, Integer> map = new HashMap();
        for(Integer num : list){
            map.put(num, map.getOrDefault(num, 0) +1);
        }
        return map;
    }
    private void remove(Map<Integer, Integer> map, int elm){
        if(map.containsKey(elm)){
            if(map.get(elm) > 1){
                map.put(elm, map.get(elm)-1);
            }else{
                map.remove(elm);
            }
        }
    }
}
// @lc code=end
