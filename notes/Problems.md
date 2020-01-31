# Notes
 
## upsolved

* [peak-index-in-a-mountain-array](https://leetcode.com/problems/peak-index-in-a-mountain-array): must check a[mid] and a[mid + 1] to determine the movement
* [trapping-rain-water-ii](https://leetcode.com/problems/trapping-rain-water-ii/): heap

* [find-k-th-smallest-pair-distance](https://leetcode.com/problems/find-k-th-smallest-pair-distance): binary search for dist + sliding window for counting sub arrays
* [shortest-path-visiting-all-nodes](https://leetcode.com/problems/shortest-path-visiting-all-nodes): bit operation + BFS
	* State: the last visited node and a bitmap for tracking all visited nodes
	* DP on bitmasks: allowing a node visited multiple times makes DP on bitmasks harder (see the DP solution in LC's problem solution)
* [maximum-gap](https://leetcode.com/problems/maximum-gap): bucket + pigeon hole
	* n numbers in interval [a, b], the lower bound of max gap of adjacent numbers is `(b-a)/(n-1)`
	* use bucket size of `(b-a)/(n-1)`, the max gap can only exist in between-buckets 
* [dungeon-game](https://leetcode.com/problems/dungeon-game): DP on matrix, or binary search
	* Direction matters: 
		* For check a 'feasible' value in binary search, DP from top-left to bottom-right is OK
		* For direct DP solution, bottom-right to top-left is much more easier because you don't need to remember a current HP value which is not only related to adjacent dp states.
* [expression-add-operators](https://leetcode.com/problems/expression-add-operators/description/): 
	* update multiplication by remember mulAcc state
	* difference between first digit and following digits
* [remove-invalid-parentheses](https://leetcode.com/problems/remove-invalid-parentheses): 
	* find # of redundant left parenthesis and right parenthesis
	* recursion on (pair, left, right)
* [convert-integer-to-the-sum-of-two-no-zero-integers](https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers): 
	* recursion solution
	* borrow 1 from higher bit
* [burst-balloons](https://leetcode.com/problems/burst-balloons)
	* instead of iterate the 1st busrst baloon during DP, we can iterate the last burst baloon during DP which means the sections devided by the baloon is fixed 2 subproblems
* [create-maximum-number](https://leetcode.com/problems/create-maximum-number)
	* get the largest k numbers when keeping the relative order in 1d array
	* merge numbers in 2 arrays to largest number
* [count-of-range-sum](https://leetcode.com/problems/count-of-range-sum)
	* segment tree range is large, compression required
		* sort array of distinct values

	~~~
	private SegmentTreeNode build(long[] arr, int start, int end) {
		SegmentTreeNode r = new SegmentTreeNode(start, end);
		r.left = arr[start];
		r.right = arr[end];
		if (start < end) {
			r.left = build(arr, start, start + (end - start) / 2);
			r.right = build(arr, start + (end - start) / 2 + 1, end);
		}
		return r;
	}
	~~~
* [pour-water](https://www.lintcode.com/problem/pour-water)
	* simulation, two pointer(index and i)
* [reverse-subarray-to-maximize-array-value/](https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/)
	* abs, analyze all possible cases and find the only case increases
* [wiggle-sort-ii](https://leetcode.com/problems/wiggle-sort-ii/)
	* only 1 arrangement is correct

	~~~
	# correct: Ms are placed far away
	even: M-S
	odd:  -L-M
	
	# incorrect
	even: S-M
	odd:  -M-L
	~~~

## interesting problems and tricks

* [IPO](https://leetcode.com/problems/ipo): 
	* keywords: max/min, 2 sorted set, scan set 1 in order and for each element search best fit in another set 2
	* implementation: prefix max query + update can be implemented with heap + scan from left to right (besides segment tree). 
	* [minimum-cost-to-hire-k-workers](https://leetcode.com/problems/minimum-cost-to-hire-k-workers/)
	* [minimum-number-of-refueling-stops](https://leetcode.com/problems/minimum-number-of-refueling-stops)
* [ugly-number-ii](https://leetcode.com/problems/ugly-number-ii): 
	* keywords: find min, 2nd min, ..., on demand, sorted arrays
	* implementation: heap, sorted array, for each element keep track of pointer to the elements in the other array
	* [find-k-pairs-with-smallest-sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/)
* LIS
	* [russian-doll-envelopes](https://leetcode.com/problems/russian-doll-envelopes)
	* [largest-divisible-subset](https://leetcode.com/problems/largest-divisible-subset/)
* [shortest-path-to-get-all-keys](https://leetcode.com/problems/shortest-path-to-get-all-keys): BFS with state combination: position + which keys are holding
* [distant-barcodes](https://leetcode.com/problems/distant-barcodes/): watch out for element with the largest occurance (N+1)/2.
* [candy](https://leetcode.com/problems/candy/): see my solution O(nlogn). sort and iterate from smallest to largest, update with max(left, right) + 1
* [maximum-equal-frequency](https://leetcode.com/problems/maximum-equal-frequency): 
	* 2 indices: 
		* occured number -> freq
		* freq -> number count
* [number-of-submatrices-that-sum-to-target](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/description/): 
	* keywords: submatrix
	* fix 2 columns, convert the problem to 'find subarray sum to target'
		* column prefix sum for each row, elements in subarray is the diff of prefix sum between 2 columns
		* 'find subarray sum to target': calculate prefix sum for subarray and convert it to 2-sum to target problem
* [maximal-square](https://leetcode.com/problems/maximal-square/): square area dp with adjacent cells: `dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1`
	* [count-square-submatrices-with-all-ones](https://leetcode.com/problems/count-square-submatrices-with-all-ones)
* [elimination-game](https://leetcode.com/problems/elimination-game/description/): recursion with odd/even number
* [shortest-path-in-a-grid-with-obstacles-elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination): BFS with unusual state (r,c,elimination)
* [bitwise-and-of-numbers-range](https://leetcode.com/problems/bitwise-and-of-numbers-range/): find leftmost different bit and clear all bits right to it
* [integer-break](https://leetcode.com/problems/integer-break): Math solution, use 3 as many as possible in decomposition
* [water-and-jug-problem](https://leetcode.com/problems/water-and-jug-problem): GCD