/*
 * @lc app=leetcode id=2671 lang=java
 *
 * [2671] Frequency Tracker
 *
 * https://leetcode.com/problems/frequency-tracker/description/
 *
 * algorithms
 * Medium (25.58%)
 * Likes:    103
 * Dislikes: 9
 * Total Accepted:    9.7K
 * Total Submissions: 38.1K
 * Testcase Example:  '["FrequencyTracker","add","add","hasFrequency"]\n[[],[3],[3],[2]]'
 *
 * Design a data structure that keeps track of the values in it and answers
 * some queries regarding their frequencies.
 * 
 * Implement the FrequencyTracker class.
 * 
 * 
 * FrequencyTracker(): Initializes the FrequencyTracker object with an empty
 * array initially.
 * void add(int number): Adds number to the data structure.
 * void deleteOne(int number): Deletes one occurence of number from the data
 * structure. The data structure may not contain number, and in this case
 * nothing is deleted.
 * bool hasFrequency(int frequency): Returns true if there is a number in the
 * data structure that occurs frequency number of times, otherwise, it returns
 * false.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["FrequencyTracker", "add", "add", "hasFrequency"]
 * [[], [3], [3], [2]]
 * Output
 * [null, null, null, true]
 * 
 * Explanation
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.add(3); // The data structure now contains [3]
 * frequencyTracker.add(3); // The data structure now contains [3, 3]
 * frequencyTracker.hasFrequency(2); // Returns true, because 3 occurs twice
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input
 * ["FrequencyTracker", "add", "deleteOne", "hasFrequency"]
 * [[], [1], [1], [1]]
 * Output
 * [null, null, null, false]
 * 
 * Explanation
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.add(1); // The data structure now contains [1]
 * frequencyTracker.deleteOne(1); // The data structure becomes empty []
 * frequencyTracker.hasFrequency(1); // Returns false, because the data
 * structure is empty
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input
 * ["FrequencyTracker", "hasFrequency", "add", "hasFrequency"]
 * [[], [2], [3], [1]]
 * Output
 * [null, false, null, true]
 * 
 * Explanation
 * FrequencyTracker frequencyTracker = new FrequencyTracker();
 * frequencyTracker.hasFrequency(2); // Returns false, because the data
 * structure is empty
 * frequencyTracker.add(3); // The data structure now contains [3]
 * frequencyTracker.hasFrequency(1); // Returns true, because 3 occurs
 * once
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= number <= 10^5
 * 1 <= frequency <= 10^5
 * At most, 2 * 10^5 calls will be made to add, deleteOne, and hasFrequency in
 * total.
 * 
 * 
 */

// @lc code=start
class FrequencyTracker {
    int[] freq = new int[100005];
    int[] freqCnt = new int[100005];

    public FrequencyTracker() {
        
    }
    
    public void add(int number) {
        freqCnt[freq[number]] = Math.max(0, freqCnt[freq[number]] - 1);
        freq[number]++;
        freqCnt[freq[number]]++;

    }
    
    public void deleteOne(int number) {
        freqCnt[freq[number]] = Math.max(0, freqCnt[freq[number]] - 1);
        freq[number] = Math.max(0, freq[number] - 1);
        freqCnt[freq[number]] = Math.max(0, freqCnt[freq[number]] + 1);
    }
    
    public boolean hasFrequency(int frequency) {
        return freqCnt[frequency] > 0;
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker obj = new FrequencyTracker();
 * obj.add(number);
 * obj.deleteOne(number);
 * boolean param_3 = obj.hasFrequency(frequency);
 */
// @lc code=end
