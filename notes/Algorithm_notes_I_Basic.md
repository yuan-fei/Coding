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
	* Binary search with O(logn) applies: **recursively**. A[mid] falls in either
		1. left sorted part: A[low] <= A[mid], then A[mid..high] is a rotated sorted subarray
		2. right sorted part: A[mid] <= A[high], then A[low..mid] is a rotated sorted subarray

		choose to stay in sorted part or rotated sorted part
* Rotated sorted array with duplicated element must be processed in at least O(n)
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
	* 3-sum, 3-sum closest
		* Sort the array, for each element do [2-sum in sorted array](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted). Time complexity O(n^2).
   	
* [K-sum solution](https://cs.stackexchange.com/questions/2973/generalised-3sum-k-sum-problem)
   * K is even: generate a sorted list S of all sum of k/2, and solve 2 sum problem (check if S contains both x, -x). Time complexity O(n^(k/2))
   * K is odd: generate a sorted list S of all sum of (k-1)/2, solve 3 sum problem (for each input a, check if S contains both x, -a-x). Time complexity O(n^((k+1)/2))
   * The time complexity for K sum is O(n^ceiling(k/2))

## KMP: String matching with O(m+n)
* Jump table generation: 
		
	0|1|2|3|4|5|6|7|8|9
	---|---|---|---|---|---|---|---|---|---|
	a|b|a|b|c|a|b|a|b|a
	-1|-1|0|1|-1|0|1|2|3|2
	
	* The longest common prefix: **T[0..k] == T[m-k..m] => next[m] = k**
	* idea
	
		```
		// element 6,7,8 illustrated above
		case T[m] == T[next[m-1] + 1] => next[m] = next[m-1] + 1
		// element 9
		case T[m] != T[next[m-1] + 1] => check
						T[m] == T[next[next[m-1]] + 1], 
						T[m] == T[next[next[next[m-1]]] + 1]
						... 
						until find it or reach the end
		```
	* Time complexity: Why O(m)
		* Nested loops
			* outer loop: all elements from 1 to m
			* inner loop: +1, or iteratively apply next, next(next), ... next^n
			* **through all the loop, 'next' can be applied at most m times in total! (no repeated element access before next jump to -1)**
			* So 2m cost at most => O(m)
* Match with help of jump table

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

## Dynamic programing
* When to use DP
	* Maximum/Minimum
	* Yes/No 
	* Solution count
	* Input can not be sorted/swapped (like sequence, string, but not set)
* When not to use DP
	* Find all solutions 
* DP category
	* Matrix DP: Triangle, Unique paths
	* Sequence: Climb stairs, Word break
	* 2 sequence: Longest common subsequence/substring, Edit distance
	* Backpack: K-sum
* DP implementation
	* Memorize search: cache solution of subproblems
	* Iteration: build up
		* State
			* Single state
				* Longest Common Subsequence: `f[i][j]`: length of the longest common subsequence till s1[i], s2[j]
				* Longest Common Substring: `f[i][j]`: length of the common substring ended with s1[i]=s2[j]
				* Backpack I: `f[i][j]`: the feasibility of any goods in first i goods whose volume can be added up to j.
				* Backpack II: `f[i][j]`: the max price of any goods in first i goods whose volume can be added up to j.
				* K-sum: `f[i][j][sum]`: the feasibility/solution # of j numbers in first i numbers that can be added up to 'sum'.
			* State Machine
				* best-time-to-buy-and-sell-stock-with-cooldown ([analysis](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75928/Share-my-DP-solution-(By-State-Machine-Thinking))) 
					* state:
					
					```
					state machine: 
					* bought[i]: hold->bought[i+1]; sell->sold[i+1]. 
					* sold[i]: hold->cooldown[i+1]. 
					* cooldown[i]: hold->cooldown[i+1]; buy->bought[i+1]
					```
					
					* function
				
					```
bought[i] = max(bought[i - 1], cooldown[i - 1] - prices[i - 1]);
sold[i] = bought[i - 1] + prices[i - 1];
cooldown[i] = max(cooldown[i - 1], sold[i - 1]);
					```
				* best-time-to-buy-and-sell-stock-iv ([analysis](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/54150/State-machine-is-the-easiest-way-to-understand-stock-problem-could-solve-all-the-stock-problem-in-leetcode))
					* State: `Bought[i][j]`: max profit till day i - 1 with j - 1 transaction, and the last state is in 'Bought'; `Sold[i][j]`: max profit till day i - 1 with j - 1 transaction, and the last state is in 'Sold'
					
					```
					Bought[i][j] = max(Bought[i - 1][j], Sold[i - 1][j - 1] - price[i - 1])
					Sold[i][j] = max(Sold[i - 1][j], Bought[i - 1][j] + price[i - 1])
					```

		* Function
		* Initialization
			* Starting point and Boundary: state[0][0], state[0][x], state[x][0]...
			* **Sometimes**, initialize state array with `length = problem scope + 1` to avoid boudary processing with `if-else`
		
			```java
			public boolean wordBreak(String s, List<String> wordDict) {
			    boolean[] isBreakable = new boolean[s.length() + 1];
			    //sentinel value for initial state
			    isBreakable[0] = true;
			    for(int i = 1; i <= s.length(); i++){
			    	...
			    }
			    ...
			    return isBreakable[s.length()];
			}
			```
		* Answer
* Related Problems
	* [longest-palindromic-substring](https://leetcode.com/problems/longest-palindromic-substring): 
		* O(n^2) with DP: DP on all lengths substring 
		* O(n) with [Manacher's algo](https://www.felix021.com/blog/read.php?2040): max LPS length symetry around center
	* [word-break](https://leetcode.com/problems/word-break)
	* [unique-paths-ii](https://leetcode.com/problems/unique-paths-ii)
	* [climbing-stairs](https://leetcode.com/problems/climbing-stairs)
	* [palindrome-partitioning-ii](https://leetcode.com/problems/palindrome-partitioning-ii)
	* [longest-increasing-subsequence](https://leetcode.com/problems/longest-increasing-subsequence)
	* [best-time-to-buy-and-sell-stock-with-cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown)
	* [best-time-to-buy-and-sell-stock-iv](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv)
	* [best-time-to-buy-and-sell-stock-with-transaction-fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee)
	* [maximum-product-subarray](https://leetcode.com/problems/maximum-product-subarray)
	* [regular-expression-matching](https://leetcode.com/problems/regular-expression-matching)
	* [wildcard-matching](https://leetcode.com/problems/wildcard-matching/)

## Prefix Sum
* Problems about consecutive subarray sum can be solved by prefix sum: 

```
Let prefixSum[i] = sum(num[0], sum[1], ..., sum[i])
thus, subarray[i..j] - prefixSum[j] - prefixSum[i-1]
```
* Related Problems
	* [maximum-subarray](https://leetcode.com/problems/maximum-subarray)
	* [minimum-size-subarray-sum](https://leetcode.com/problems/minimum-size-subarray-sum)

## Math

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
* Factorial factors: 
	* how many p in n! (the largest k which makes p^k devides n!)
		*  f(n, p) = ⌊n/p⌋ + ⌊n/(p^2)⌋ +...
	* [Trailing zeros of n!] (https://leetcode.com/problems/factorial-trailing-zeroes): how many 5s in n!

## JAVA Data Structure
* Stack: check `StackTrick` class
* Queue: use `offer(e)` and `poll()` instead of `add(e)` and `remove()` because they don't throw exceptions when failed.
* PriorityQueue: Heap implementation in java
* Map: 
	* HashMap: unordered, unsorted
	* TreeMap: sorted by key; red-black tree
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
* [Next Permutation](https://leetcode.com/problems/next-permutation)
	* TC = O(n). observation the pattern and combine the reversing array and binary search
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
    
/* for loop */
int low = start;
int high = end;
for(int low = start; low < high; low++){
	if(low != start && a[low] == a[low - 1]]){
		continue;
	}
	...
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
    
## References
* [1]: [Leetcode solution](https://www.sigmainfy.com/blog/leetcode-handbook-all-problem-solution-index.html)

