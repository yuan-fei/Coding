/*
 * [18] 4Sum
 *
 * https://leetcode.com/problems/4sum/description/
 *
 * algorithms
 * Medium (27.79%)
 * Total Accepted:    222.2K
 * Total Submissions: 738.5K
 * Testcase Example:  '[1,0,-1,0,-2,2]\n0'
 *
 * Given an array nums of n integers and an integer target, are there elements
 * a, b, c, and d in nums such that a + b + c + d = target? Find all unique
 * quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * 
 * The solution set must not contain duplicate quadruplets.
 * 
 * Example:
 * 
 * 
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * 
 * A solution set is:
 * [
 * ⁠ [-1,  0, 0, 1],
 * ⁠ [-2, -1, 1, 2],
 * ⁠ [-2,  0, 0, 2]
 * ]
 * 
 * 
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Map<Integer, List<Tuple>> map = new HashMap<>();
        Set<Quadruplet> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
        	for(int j = i + 1; j< nums.length; j++){
        		Tuple t = new Tuple(i, j);
        		int sum = nums[i] + nums[j];
        		int complement = target - sum;
        		if(map.containsKey(complement)){
        			List<Tuple> compTuple = map.get(complement);
        			for(Tuple ct : compTuple){
        				if(!ct.isIntersected(t)){
        					List<Integer> elements = Arrays.asList(nums[t.idx1], nums[t.idx2], nums[ct.idx1], nums[ct.idx2]);
        					set.add(new Quadruplet(elements));
        				}
        			}
        		}
        		if(!map.containsKey(sum)){
        			map.put(sum, new ArrayList<>());
    			}
    			map.get(sum).add(t);
        	}
        }
        List<List<Integer>> res = new ArrayList<>();
        for(Quadruplet q: set){
        	res.add(q.elements);
        }
        return res;
    }

    static class Tuple {
    	public int idx1;
    	public int idx2;

    	Tuple(int idx1, int idx2){
    		this.idx1 = idx1;
    		this.idx2 = idx2;
    	}

    	boolean isIntersected(Tuple t){
    		return (idx1==t.idx1 || idx2 == t.idx2 || idx1==t.idx2 || idx2 == t.idx1);
    	}
    }

    static class Quadruplet {

    	public List<Integer> elements;
    	Quadruplet(List<Integer> elements){
    		this.elements = new ArrayList(elements);
    		Collections.sort(this.elements);
    	}

    	@Override
    	public boolean equals(Object q){
    		Quadruplet that = (Quadruplet)q;
    		for(int i = 0; i < 4; i++){
    			if(!elements.get(i).equals(that.elements.get(i))){
    				return false;
    			}
    		}
    		return true;
    	}

    	@Override
    	public int hashCode(){
    		int code = 0;
    		for(int i = 0; i < 4; i++){
    			code += code * 37 + elements.get(i);
    		}
    		return code;
    	}

    }
}
