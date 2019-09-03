# Notes
 
## upsolved

* [trapping-rain-water-ii](https://leetcode.com/problems/trapping-rain-water-ii/): heap

* [find-k-th-smallest-pair-distance](https://leetcode.com/problems/find-k-th-smallest-pair-distance): binary search for dist + sliding window for counting sub arrays

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