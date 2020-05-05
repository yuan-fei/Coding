/*
 * @lc app=leetcode id=381 lang=java
 *
 * [381] Insert Delete GetRandom O(1) - Duplicates allowed
 *
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/description/
 *
 * algorithms
 * Hard (33.58%)
 * Likes:    683
 * Dislikes: 66
 * Total Accepted:    60K
 * Total Submissions: 178.6K
 * Testcase Example:  '["RandomizedCollection","insert","insert","insert","getRandom","remove","getRandom"]\n[[],[1],[1],[2],[],[1],[]]'
 *
 * Design a data structure that supports all following operations in average
 * O(1) time.
 * Note: Duplicate elements are allowed.
 * 
 * 
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The
 * probability of each element being returned is linearly related to the number
 * of same value the collection contains.
 * 
 * 
 * 
 * Example:
 * 
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 * 
 * // Inserts 1 to the collection. Returns true as the collection did not
 * contain 1.
 * collection.insert(1);
 * 
 * // Inserts another 1 to the collection. Returns false as the collection
 * contained 1. Collection now contains [1,1].
 * collection.insert(1);
 * 
 * // Inserts 2 to the collection, returns true. Collection now contains
 * [1,1,2].
 * collection.insert(2);
 * 
 * // getRandom should return 1 with the probability 2/3, and returns 2 with
 * the probability 1/3.
 * collection.getRandom();
 * 
 * // Removes 1 from the collection, returns true. Collection now contains
 * [1,2].
 * collection.remove(1);
 * 
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 * 
 * 
 */

// @lc code=start
class RandomizedCollection {

    /** Initialize your data structure here. */
    Map<Integer, Set<Integer>> v2i = new HashMap<>();
    List<Integer> values = new ArrayList<>();
    Random rand = new Random();
    public RandomizedCollection() {
        
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Set<Integer> idxList = v2i.getOrDefault(val, new HashSet<>());
        idxList.add(values.size());
        values.add(val);
        v2i.put(val, idxList);
        return v2i.get(val).size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!v2i.containsKey(val)){
            return false;
        }
        Set<Integer> idxList = v2i.get(val);
        int id = idxList.iterator().next();
        idxList.remove(id);
        if(idxList.isEmpty()){
            v2i.remove(val);
        }
        if(id != values.size() - 1){
            int v = values.get(values.size() - 1);
            values.set(id, v);
            v2i.get(v).remove(values.size() - 1);
            v2i.get(v).add(id);
        }
        values.remove(values.size() - 1);
        // System.out.println(v2i);
        // System.out.println(values);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int id = rand.nextInt(values.size());
        return values.get(id);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
// @lc code=end
