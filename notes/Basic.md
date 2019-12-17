# Notes

## <a name='Binary_search'></a>Binary search
	
* Binary search template
	* `if (nums.length == 0)`: handle empty array
	* `start + 1 < end`: 
		* skip array with only 1 or 2 elements
		* jump out the loop when only less then 3 elements left (2 or 1 element(s))
		* make sure **mid never overlap with start or end**, which avoid the infinite loop
	* `mid = start + (end - start)/2`: avoid numeric overflow
	* `end = mid or start = mid`: no need to mid + 1 or mid - 1
	
	```java
	/** binary search and return the index of the first occurrence of target */
	public static int binarySerach(int[] nums, int target) {
		if (nums.length == 0) {
			return -1;
		}
		int start = 0;
		int end = nums.length - 1;
		/** 1. Jump out of the loop when there are less than 3 elements left */
		while (start + 1 < end) {
			/** 2. avoid numeric overflow */
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				/**
				 * 3. move end to mid in order to find the first target; if last
				 * target is expected, move start to mid
				 */
				end = mid;
			} else if (nums[mid] > target) {
				/**
				 * 4. No need to be 'end = mid - 1' because we make sure mid will
				 * never equals start or end! (because there are at least 3
				 * elements according to the while loop condition 'start + 1 <
				 * end')
				 */
				end = mid;
			} else if (nums[mid] < target) {
				start = mid;
			}
		}
	
		/** 5. Only 2 or 1 elements left between start and end */
		if (nums[start] == target) {
			return start;
		} else if (nums[end] == target) {
			return end;
		}
		return -1;
	}
	```	
* Kinds of Binary search spaces
	* array index: when array is sorted
	* value range: when array is unsorted
		* [find-the-duplicate-number](https://leetcode.com/problems/find-the-duplicate-number)
		* [kth-smallest-element-in-a-sorted-matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix)
	* Binary search for real values:
		* Given array of values and weights, find a subset that maximize the ratio: sum(v<sub>i</sub>) / sum(w<sub>i</sub>) (see 'MaximizeRatio')
		* binary search on ratio:
			* termination condition: 
				* `high - low < eps` or 
				* for-loop 100 times for normal 'high'
		* ratio check: convert ratio to sum
			* <code>sum(v<sub>i</sub>) / sum(w<sub>i</sub>) >= r</code> equivalent to <code>sum(v<sub>i</sub> - r * w<sub>i</sub>) >= 0</code>
			* sort by <code>v<sub>i</sub> - r * w<sub>i</sub></code> and sum k largest
		* Reference: 《挑战程序设计竞赛》p143
* Related Problems
	* [Search for a range](https://leetcode.com/problems/search-for-a-range/)
	* [Search insert position](https://leetcode.com/problems/search-insert-position): index of first element greater or equal to target
	* [Find first bad version](https://leetcode.com/problems/first-bad-version)
	* [Find peak element](https://leetcode.com/problems/find-peak-element)
	* [find-the-duplicate-number](https://leetcode.com/problems/find-the-duplicate-number)
	* [kth-smallest-element-in-a-sorted-matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix)

## Rotated sorted array
* Rotated sorted array without duplicated element
	* Left sorted part A[0..k] increases monotonically, and right sorted part A[k+1..n] increases monotonically
	* Binary search with O(logn) applies: **recursively**. 
		1. decide where is mid: left part or the right part
		2. A[mid] falls in either
			* i. left sorted part: A[low] <= A[mid], then A[mid..high] is a rotated sorted subarray
			* ii. right sorted part: A[mid] <= A[high], then A[low..mid] is a rotated sorted subarray

		~~~
		if(nums[low] < nums[mid]){
			// left part
    		if(nums[low] <= target && target < nums[mid] ){
    			high = mid;
    		}
    		else{
    			low  = mid;
    		}
    	}
    	else{
			// right part    	
    		if(nums[mid] < target && target <= nums[high] ){
    			low = mid;
    		}
    		else{
    			high  = mid;
    		}	
    	}
		~~~
* Rotated sorted array with duplicated element must be processed in at least O(n): consider [2,2,2,2,2,1,2] and [2,1,2,2,2,2,2]
* Related Problems
	* [Search in rotated sorted array](https://leetcode.com/problems/search-in-rotated-sorted-array)
	* [Find minimum in rotated sorted array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array)

## <a name='Step_wise_linear_search'></a>Step wise linear search ([leetcode demonstration](https://discuss.leetcode.com/topic/3462/yet-another-way-to-see-what-happens-in-the-o-n-algorithm?page=1))
	
```
  1 2 3 4 5 6
1 x ------- o
2 x x - o o o
3 x x x o | |
4 x x x x | |
5 x x x x x |
	
A[1, 6] -> A[2, 6] -> A[2, 5] -> A[2, 4] -> A[3, 4]
```

* Solutions: Total time complexity O(m+n)
	* m*n candidates in total, with constraint
		*  for A[i, j], either the row A[i, \*] is ordered, or the column A[\*, j] is ordered (or both).
	* Start from the top right A[1, n] or bottom left A[m, 1]
	* Each time according to the comparison with the target, we can either
		* skip row A[i, \*] and go down to A[i+1, j], or 
		* skip column A[\*, j] and go left to A[i, j+1], or
		* both and go to A[i+1, j+1] when A[i, j] == target and we want to find all targets.
		
* Related problems:
	* [Search 2D matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii)
	* [2-sum in sorted array](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted)
	* [3-sum](https://leetcode.com/problems/3sum), [3-sum closest](https://leetcode.com/problems/3sum-closest)
	* [Container with most water](https://leetcode.com/problems/container-with-most-water): if height[i] is smaller, height[i, j] = min(height[i], height[j]) = height[i], then we can skip height[i, \*] for the order **height[i, j] >= height[i, j']**, and go down to check height[i+1, j] 

## Combination and permutation
* The key of **Backtracking** is to generate all subsets of a given set which is the same as combination problems
* Combination template (Backtrack)

```java
public static List<List<Integer>> getAllSubsets(int[] source) {
	List<List<Integer>> result = new ArrayList<List<Integer>>();
	_getAllSubsets(source, 0, result, new ArrayList<Integer>());
	return result;
}

private static void _getAllSubsets(int[] source, int pos, List<List<Integer>> result, List<Integer> currentSet) {
	//Check if it's a solution
	result.add(new ArrayList<Integer>(currentSet));
	
	for (int i = pos; i < source.length; i++) {
		currentSet.add(source[i]);
		_getAllSubsets(result, currentSet, source, i + 1);
		currentSet.remove(currentSet.size() - 1);
	}
}
```
* Permutation template

```java
public static List<int[]> getFullPermutations(int[] source) {
	List<int[]> result = new ArrayList<int[]>();
	_getFullPermutations(result, source, 0);
	return result;
}

private static void _getFullPermutations(List<int[]> result, int[] source, int pos) {
	if (pos == source.length) {
		result.add(Arrays.copyOf(source, source.length));
	}

	for (int i = pos; i < source.length; i++) {
		// in place swap is used instead of the alternative storage
		// (currentSet)
		swap(source, pos, i);
		_getFullPermutations(result, source, pos + 1);
		swap(source, i, pos);
	}
}
```
* Unique solution set
	* In some cases, duplicates are allowed in given set such as "1" in [1, 2, 1, 5], but as the 2 "1"s are not distinguished, no duplicate combinations should appear in the solution set, which is 'Unique solution set' constraint
	* Sort the given set first, and use the trick [Skip duplicates in **sorted** array](#skip_duplicates_in_sorted_array) to avoid duplicate solution set
* Related Problems
	* [combination-sum-i](https://leetcode.com/problems/combination-sum-i)
	* [combination-sum-ii](https://leetcode.com/problems/combination-sum-ii)
	* [combination-sum-iii](https://leetcode.com/problems/combination-sum-iii)
	
## K-sum problem
* 2-sum, 3-sum, 4-sum
	* 2-sum
		* [2-sum](https://leetcode.com/problems/two-sum): In unsorted array with duplicated elements, hash table helps to find all unique solutions in O(n), with an extra O(n) hash table space. 
		* [2-sum in sorted array](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted): In a sorted array with duplicated elements, [Step wise linear search](#Step_wise_linear_search) can be used for find all unique solutions in O(n), with extra O(1) space. 
		* [2-sum less or equal to target](https://www.lintcode.com/problem/two-sum-less-than-or-equal-to-target/description): two pointer meet-in-the-middle
	* 3-sum, 3-sum closest
		* Sort the array, for each element do [2-sum in sorted array](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted). Time complexity O(n^2).
   	
* [K-sum solution](https://cs.stackexchange.com/questions/2973/generalised-3sum-k-sum-problem)
   * K is even: generate a sorted list S of all sum of k/2, and solve 2 sum problem (check if S contains both x, -x). Time complexity O(n^(k/2))
   * K is odd: generate a sorted list S of all sum of (k-1)/2, solve 3 sum problem (for each input a, check if S contains both x, -a-x). Time complexity O(n^((k+1)/2))
   * The time complexity for K sum is O(n^ceiling(k/2))
* Related problems
	* [plane maximum rectangle](https://www.lintcode.com/problem/plane-maximum-rectangle/description): fix diagonal and find the other 2 points

## DFS and BFS
* Related Problems
	* [word-ladder](https://leetcode.com/problems/word-ladder)
	* [word-ladder-ii](https://leetcode.com/problems/word-ladder-ii)
	* [n-queens](https://leetcode.com/problems/n-queens)
	* [sudoku-solver](https://leetcode.com/problems/sudoku-solver)
	* [palindrome-partitioning](https://leetcode.com/problems/palindrome-partitioning)
	* Topological sort: [course-schedule](https://leetcode.com/problems/course-schedule)

## Backtracking
* backtracking can be implemented by DFS
* backtrack state: track available candidates for each step
	* matrix input: use a matrxi with same dimension
	 	* [n-queens](https://leetcode.com/problems/n-queens)
		* [sudoku-solver](https://leetcode.com/problems/sudoku-solver)

## Greedy
* Activity Selection
	* Given tasks each with start and end, find the max # of tasks that can be scheduled sequentially
	* Given intervals each with start and end, find the min # of shoots that can eliminate all intervals (1 shoot at x can eliminate all intervals that intersect at x) ([Shooting baloons](https://mp.weixin.qq.com/s/iSHP4MJq-EkwjitMlPkT8g))
* Task scheduling on 2 machines
	* This task is about finding an optimal schedule for n jobs on two machines. Every item must first be processed on the first machine, and afterwards on the second one. The i-th job takes ai time on the first machine, and bi time on the second machine. Each machine can only process one job at a time.
	* Johnson's rule
		* sort: min(a<sub>i</sub>,b<sub>i</sub>) < min(a<sub>j</sub>,b<sub>j</sub>)
		* schedule: for each task if a<sub>i</sub> < b<sub>i</sub> then put it front, otherwise put it end, until meet in the middle.
	* Reference
		* [Johnson's rule on cp-algorithms](https://cp-algorithms.com/schedules/schedule_two_machines.html)
		* [paper](http://www.rspq.org/pubs/j2.pdf)
* Longest increasing sequence (LIS)
	* O(n^2) for DP, O(nlogn) for non-DP
	* non-DP
		1. An increasing sequence of length L would like its last element to be as small as possible
		2. Invariant: denote le(L) the last element of current increasing sequence with length L (might not be the LIS's prefix) , if L<sub>a</sub> < L<sub>b</sub> then  le(L<sub>a</sub>) < le(L<sub>b</sub>)
		3. The idea is to keep le(L) - the last element of length L - and update it when there is a smaller one, or more strictly, if a new element is greater than the le(L - 1) and smaller than le(L), then update le(L) to make it better.
		4. For a new element, it can be simply a binary search to find the le(L) to update since the invariant in 2
	* [Patience sorting and duality](http://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence-2x2.pdf)
		* LIS's non-DP solution is the first stage of patient sorting
		* The strong duality: the length of longest increasing subsequence = the min # of piles of non-increasing subsequence
		* Related problems: [Least non-increasing subsequence](https://www.lintcode.com/problem/least-subsequences/description)
	* [remove duplicate letters](https://leetcode.com/problems/remove-duplicate-letters/)
		* greedy + Stack, or greedy + 2 pointers

## Prefix Sum
* Problems about consecutive subarray sum can be solved by prefix sum: 

```
Let prefixSum[i] = sum(a[0], a[1], ..., a[i])
thus, subarray[i..j] - prefixSum[j] - prefixSum[i-1]
```
* Problems about total gap to the max in a sorted sub array: (i.e. for [1,2,4,6,8], the total gap to max in subarray [2,4,6] is 4+2+0=6)

```
//Let prefixSum[i] = sum(a[0], a[1], ..., a[i])
long gap(int start, in endInclusive) {
	long vEnd = prefixSum[endInclusive] - prefixSum[endInclusive - 1];
	long rangeSum = (prefixSum[endInclusive] - prefixSum[start - 1]);
	return vEnd * (endInclusive - start + 1) - rangeSum;
}

```
* Event based offset
	* Given a interval of length n, and m increments in range: ([l<sub>i</sub>, r<sub>i</sub>], inc), how to calculate sum in any range [l, r]?
	* brute-force: O(n+sum(r<sub>i</sub>-l<sub>i</sub>))
		1. inc val for each point in interval
		2. calculate prefix sum
	* event based offset: O(n+m)
		1. event based offset: (l<sub>i</sub>, +inc), (r<sub>i</sub>, -inc) ...
		2. calculate each point value by prefix sum
		3. calculate prefix sum
	
	~~~
	Interval[] intervals;	//0-based: [0,1]
	long[] val = new long[n+1];
	for(int i = 0; i < intervals.length; i++){
		val[intervals[i].left] += intervals[i].value;
		val[intervals[i].right + 1] -= intervals[i].value;
	}
	long[] prefSum = new long[n+1];	
	for(int i = 1; i <= n; i++){
		val[i] += val[i-1];
		prefSum[i] = prefSum[i-1] + val[i];
	}
	~~~

* Related Problems
	* [maximum-subarray](https://leetcode.com/problems/maximum-subarray)
	* [minimum-size-subarray-sum](https://leetcode.com/problems/minimum-size-subarray-sum)

## Sliding Window
* Two-Pointer: like maintaining a 'Queue'

	~~~
	//template
	int findMaxLengthWithNoMoreThanKDistinctChars(int[] a, int K){
		int left = 0;
		Map<Integer,Integer> window = new HashMap<>();
		int maxLen = 0;
		for(int i = 0; i<n; i++){
			window.put(a[i], window.getOrDefault(a[i], 0) + 1);
			while(window.size() > K){
				int cnt = window.get(a[left]) - 1;
				if(cnt == 0){
					window.remove(a[left]);
				}
				else{
					window.put(a[left], cnt);
				}
				left++;
			}
			maxLen = Math.max(maxLen, i-left+1);
		}
		return maxLen;
	}
	~~~
	* related problems
		* [longest-substring-without-repeating-characters](https://leetcode.com/problems/longest-substring-without-repeating-characters)
		* [minimum-window-substring](https://leetcode.com/problems/minimum-window-substring/)
		* [longest-repeating-character-replacement](https://leetcode.com/problems/longest-repeating-character-replacement)
		* [find-k-th-smallest-pair-distance](https://leetcode.com/problems/find-k-th-smallest-pair-distance)
		
* Monotonic deque
	* [Sliding window maximum](https://leetcode.com/problems/sliding-window-maximum/)
		* O(nlogk): use a self-balancing tree to keep the oder in the window with O(logk) for insert, max, delete
		* O(n): Use a Deque to keep indexes of a decreasing sequence in window
			* head of queue is the idx of the max in the window
			* each subsequent element indicated by the idx in deque should be a potential max in following windows (for an element in a window, any element from left smaller than it will not be a max candidate) 
	* 	Problems:
		*  [shortest-subarray-with-sum-at-least-k](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k)
* Two-pointer window sufficient requirement
	* 'max' problem: 
		* Mononicity: for a starting index i, [i, j] is legal as the ending index j increases, until some k, [i, k] becomes illegal and keep illegal for [i, j] with j>k forever. 
		* The mononicity should hold for ending index j as well (legal -> illegal as i decreases)
* Queue (Two pointer) or Deque?
	* The difference between Queue nad Deque is whether you need to manage the end of the queue to maintain the monotonicity of the queue/deque
		*  Queue
			*  [longest-substring-without-repeating-characters](https://leetcode.com/problems/longest-substring-without-repeating-characters): for [i, j] which is not legal with repeating chars, increase i until it's legal (no repeating chars), then any [k, j] where k>i will not be better.
			*  [minimum-window-substring](https://leetcode.com/problems/minimum-window-substring/): for [i, j] which is legal (window just contains all chars), increase i to get better solutions until it's not legal
		*  Deque
			*  [shortest-subarray-with-sum-at-least-k](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k):  for [i, j] which is good (sum to k), we don't know if we shall proceed to increase i to get a better solution if the prefix sum in queue is not monotonic, so we need to maintain the monotonicity of the queue by removing any obsolete elements (for j which `j<i && ps[j] >= ps[i]`) from the end of the queue.
* Min or Max?
	* Max: 
		* for each start i, find max j that [i, j] is legal
		* fast pointer moves first greedily until not legal, then slow pointer moves until legal
	* Min: 
		* for each end j, find max i that [i, j] is legal
		* fast pointer moves first until legal, then slow pointer moves greedily until not legal



## Subarray counting
* Count subarrays that satisfy a certain condition
	1. for each element A[i] in array, find the max interval [left ,right] contains it and satisfies the condition, and solve a sub problem:
		* count how many subarrays in the interval contains A[i]: `cnt = (i-left+1) * (right-i+1);`
		* [unique-letter-string](https://leetcode.com/problems/unique-letter-string)
	2. 	count substrings satisfes a condition, we want all substrings if [left, right] is a good interval: sliding window
	   * [find-k-th-smallest-pair-distance](https://leetcode.com/problems/find-k-th-smallest-pair-distance)
	3. if the condition is like subarray sum which can be represented as prefix sum, then the problem is converted to 
		* find how many prfixSum[j] - prefixSum[i] = limit, where i<j. 
		* This is *two-sum* problem

## Two Pointer (to be summarized)
* meet in the middle
	* 2-Sum
	* Count pair of sum less than K (see CountPairsOfTwoSumLessThanK)
* slow-fast
	* Sliding window
	* DeleteASubarray


## Bit operaton

* Bit operation:
	* Shift operator: i << k left shift k%32 times (**do not truncate for k > 32**); >> signed right shift; >>> unsigned right shift
	* Bit mask for lowest k bits: (k>=32)? -1 : (1 << k) - 1
	* Bit mask for bits i to j: (j-i+1>=32)? -1 : ((1 << (j-i+1)) - 1) << i
	* Clear lowest 1: n & (n-1)
		* How many bits are set in a given integer: repeatedly clear lowest 1
		* Check if n is power-of-2: only 1 bit is set `n > 0 && (n & (n-1)) == 0`
		* Check if n is power-of-4: `((num & (num - 1)) == 0) && ((num & 0x55555555) == num)`
	* Get bitmask: n's lowest 1: `n & (-n)`
		* Clear lowest 1: `n - (n & (-n))`
		* Check if n is power-of-2: only 1 bit is set `n == (n & (n-1))`
	* Bitmask all subsets enumeration: `int x = mask; while(x!=0){x=(x-1)&mask;}`

## JAVA Data Structure
* LinkedList: remove element while iterating in O(1)

~~~
LinkedList<Integer> l = new LinkedList<>;
Iterator<Integer> it = l.iterator();
while(it.hasNext()){
	int val = it.next()
	//...
	it.remove()
}
~~~

* Stack: check `StackTrick` class
	* given an array of ints, for each element, find the first higher(lower) element to its left and right
		* [largest-rectangle-in-histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
		* [maximal-rectangle](https://leetcode.com/problems/maximal-rectangle/)
	* Expression parsing
		* nested structure + single operator: 
			* currentResult + resultStack
				* currentResult keep current result in scope (current nesting level)
				* when new level begins (see `(`), push result to resultStack and init a new currentResult for this new level
				* when current level finishes (see `)`), combine last level result (`resultStack.pop()`) to the currentResult

			~~~
			Stack resultStack;
			String result;
			while(hasNExtToken()){
				String t = nextToken();
				switch(t){
				case "(": //new level start
					resultStack.push(result);
					result="";
				break;
				case ")": //level end
					String preResult = resultStack.pop();
					result=preResult + result;
				break;
				...
				}
			}
			return result;
			~~~
		
			* Problems:
				* [decode-string](https://leetcode.com/problems/decode-string/)
				* [number-of-atoms](https://leetcode.com/problems/number-of-atoms)
		* [Shunting yard](https://en.wikipedia.org/wiki/Shunting-yard_algorithm): op stack and oprand stack
			* [basic calculator ii](https://leetcode.com/problems/basic-calculator-ii/)
			* [brace expansion ii](https://leetcode.com/problems/brace-expansion-ii/)
	* [Maximum Frequency Stack](https://leetcode.com/problems/maximum-frequency-stack/)
		* pop most frequent number in stack
		* use frequency as index
* Queue: use `offer(e)` and `poll()` instead of `add(e)` and `remove()` because they don't throw exceptions when failed.
	* min Queue: 2 min stacks
* Deque (ArrayDeque, LinkedList): enqueue and dequeue from both end. Used as sliding window
	* pollFisrt(), pollLast(), offerFisrt(), offerFisrt()
* PriorityQueue: Heap implementation in java
* Map: 
	* HashMap: unordered, unsorted
	* TreeMap: sorted by key; red-black tree
		* pollFisrtEntry(), firstEntry(), firstKey()
	* LinkedHashMap: ordered by the order of `add` operation; LRU cache implementation
* Trie (re**trie**val): 
	* Character by character search (state machine)
	* Multiple strings as input pattern
* Related Probles
	* PriorityQueue: [find-median-from-data-stream](https://leetcode.com/problems/find-median-from-data-stream)
	* Trie
		* [implement-trie-prefix-tree](https://leetcode.com/problems/implement-trie-prefix-tree)
		* [word-search-ii](https://leetcode.com/problems/word-search-ii)

## Miscellaneous
* [Next Permutation](https://leetcode.com/problems/next-permutation): O(n). 
	1. Find the longest suffix that is in decreasing order. 
	2. Swap the preceding element x with the smallest one from the decreasing sequence larger than x, let's say y
	3. Sort the suffix after y in increasing order by reverse it
* [Median of 2 sorted arrays](https://leetcode.com/problems/median-of-two-sorted-arrays)
	* TC = O(log(min(m, n))). Keep the count of numbers in left set and right set equal with order constraints. Binary search.
* Single number: 
	* [single-number-ii](https://leetcode.com/problems/single-number-ii): A general solution provided by felixhao28 in his post in [this](https://leetcode.com/problems/single-number-ii/discuss/43296/An-General-Way-to-Handle-All-this-sort-of-questions.) thread
		* For each bit of INT (0 ~ 31), count the bit of all the n numbers and mod by k (the count of repeats). If the result bit > 0, the except number's bit = 1; otherwise, the except number's bit = 0.
	* [single-number-iii](https://leetcode.com/problems/single-number-iii): group by the distiguished bit of the result of xor.
* Boyer-Moore majority vote: [1/2 Majority votes](https://leetcode.com/problems/majority-element), [1/3 Majority votes](https://leetcode.com/problems/majority-element-ii)
	* Intuition for 1/k majority votes: eliminate at most 1 majority vote in each k votes
	* Implementation: 1/k majority requires k-1 candidate space for there are at most k-1 competence for the majority
	* Distributable: slice the array and DC will return correct result
* Rotate array
	* swap by reverse
	* juggling rotate: [Why use GCD](https://stackoverflow.com/questions/23321216/rotating-an-array-using-juggling-algorithm)
* Sampling and Shuffling: Check SamplingAndShuffling class
* [Reservoir sampling](https://en.wikipedia.org/wiki/Reservoir_sampling)
	* Randomly sample n numbers in unknown sized stream with equal probability (n/total for which total is changing all the time)
* In place swap: check missing number
	
	```
	// Invariant: A[i] = i
	if(A[i] != i){
		swap(A[i], A[A[i]]);
	}
	```
	* [first-missing-positive](https://leetcode.com/problems/first-missing-positive)
* [maximum-frequency-stack](https://leetcode.com/problems/maximum-frequency-stack): frequent is continuous, so index stack by frequent
* [longest-subarray-count-1s-one-count-0s](https://www.geeksforgeeks.org/longest-subarray-count-1s-one-count-0s/)
	* calculate accumulate difference sequence `diff[n]`, it has the following property
		1. `differ by 1`: the sequence starts at 0, and two adjacent elements in sequence differs by 1
		2. `monotonicity`: denote `first[d]` the position first time we see diff d. if a and b are 2 elements in sequence
			* a,b>0, a>b, then `first[b] < first[a]`
			* a,b<0, a>b, then `first[a] < first[b]`
	* if `diff[i]<=0`, we should try to find the j with smallest `first[j]` and `diff[i]-diff[i]>0`. According to 'monotonicity' before, such `j = first[diff[i-1]]`, and the span is `i-j`
	* A similar problem: [longest-well-performing-interval](https://leetcode.com/problems/longest-well-performing-interval)

## Tricks
* <a name='skip_duplicates_in_sorted_array'></a>Skip duplicates in **sorted** array: move pointer only once in each loop
    
```java
/* while loop */
int low = start;
int high = end;
while (low < high){
	if(low != start && a[low] == a[low - 1]]){
		low++;
		continue;
	}
	...
}
    
/* for loop 1 */
for(int low = start; low < end; low++){
	if(low != start && a[low] == a[low - 1]]){
		continue;
	}
	...
}

/* for loop 2 */
int left = -1;
for(int i = start; i < end; i++){
	if(i == start || a[left] != a[i]){
		left = i;
	}
}
```
* Dummy node for linked list: when the head is not determinated. 
	* Head might be eliminated or dynamically generated.
	* Complex 'if' could be used to distinguish the processing between head and other nodes.

```java
...
LinkNode dummy = new LinkNode(-1);
dummy.next = head;
...
return dummy.next;
```

* Slow and fast pointer for linked list: 
	* Key implementation: pointer fast starts with head.next
		* fast steps n first, and slow.next will point to the nth node to the last which is handy for node operation (i.e. removal)

```java
LinkNode slow = head;
LinkNode fast = head.next;

// move fast n steps first

while(fast != null && fast.next != null){
	slow = slow.next;
	fast = fast.next.next;
}
...

```

* Merge intervals

```
if (intervals.size() <= 1) {
    return intervals;
}
List<Interval> res = new ArrayList<Interval>();
Interval last = intervals.get(0);
for (int i = 1; i < intervals.size(); i++) {
	Interval cur = intervals.get(i);
	if (cur.start > last.end) {
		res.add(last);
		last = cur;
	} else {
		last.end = Math.max(last.end, cur.end);
	}
}
res.add(last);
```

* Floyd's Tortoise and Hare: cycle detection and find cycle entrance 
	* Key implementation: slow and fast point to same node initially
		* cycle detection: fast and slow meet at Len(cycle) - Len(Non-Cycle)
		* cycle entrance: after met, rewind slow to start node and move slow and fast with same speed until the 2nd meet, the node where they meet again is the cycle entrance.
	* Related Problems: 
		* [linked-list-cycle-ii](https://leetcode.com/problems/linked-list-cycle-ii/)
		* [find-the-duplicate-number](https://leetcode.com/problems/find-the-duplicate-number)

```
LinkNode slow = head;
LinkNode fast = head;

// cycle detection
while(fast != null && fast.next != null){
	slow = slow.next;
	fast = fast.next.next;
	if(slow == fast){
		break;
	}
}
if(fast == null || fast.next == null){
	return null;
}
// find the cycle entrance
slow = head;
while(slow != fast){
	slow = slow.next;
	fast = fast.next;
}
return slow;
```

* Combination implementation

```java
private int combination(int n, int m){
	long r = 1;
	m = Math.min(m, n - m);
	for (int i = 1; i <= m; i++) {
		r *= (n - m + i);
		//avoid overflow
		r /= i;
	}
	return (int)r;
}
```

* N-Queen diagonal check with array

<pre><code>
public int totalNQueens(int n) {
	<strong>
	boolean[] c = new boolean[n]; // direction '/'
	boolean[] d1 = new boolean[2*n]; // diagonal '/'
	boolean[] d2 = new boolean[2*n]; // diagonal '\'
	</strong>
	return totalNQueensHelper(n, c, d1, d2, 0);
}

int totalNQueensHelper(int n, boolean[] c, boolean[] d1, boolean[] d2, int r){
	if(r == n){
		return 1;
	}
	else{
		int total = 0;
		for(int i = 0; i < n; i++){
			<strong>
			//map -n~n to 0~2n
			int id1 = n+i-r;
			</strong>
			int id2 = i+r;
			<strong>
			if(!c[i] && !d1[id1] && !d2[id2])</strong>{
				c[i] = d1[id1] = d2[id2] = true;
				total += totalNQueensHelper(n, c, d1, d2, r+1);
				c[i] = d1[id1] = d2[id2] = false;
			}
		}
		return total;
	}  	
}
</code></pre>

## Tips
* Increase Java stack size: -Xss256M

## References
* [1]: [Leetcode solution](https://www.sigmainfy.com/blog/leetcode-handbook-all-problem-solution-index.html)

