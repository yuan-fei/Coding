# Notes
 
## upsolved

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
* [shortest-path-to-get-all-keys](https://leetcode.com/problems/shortest-path-to-get-all-keys): BFS with state combination: position + which keys are holding
* [distant-barcodes](https://leetcode.com/problems/distant-barcodes/): watch out for element with the largest occurance (N+1)/2.
* [candy](https://leetcode.com/problems/candy/): see my solution O(nlogn). sort and iterate from smallest to largest, update with max(left, right) + 1