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
* [insert-delete-getrandom-o1/](https://leetcode.com/problems/insert-delete-getrandom-o1): insert, delete, random in O(1)
	* For random, we need to keep an compact array and delete in O(1)
	* O(1) delete in array: move last element to location of deleted element
* [maximum-number-of-events-that-can-be-attended/](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended): interval
	* sort by begin, scan from left to right day by day while keeping active intervals, choose the active event with the earliest end.
* [shortest-path-in-a-grid-with-obstacles-elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination): BFS with unusual state (r,c,elimination)
* [minimum-cost-to-make-at-least-one-valid-path-in-a-grid](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/): 
	* BFS + DFS: DFS layer by DFS layer
* [pizza-with-3n-slices](https://leetcode.com/problems/pizza-with-3n-slices):
	* This problem is equivalent to ["choose n slices which are not adjacent to each other and the sum of slices is max"](https://leetcode.com/problems/pizza-with-3n-slices/discuss/546627/Question-about-proof-of-correctness)
	
	> 	The proof is by induction on N. For N=3, it is immediate. For N>3, let S be a set of N/3 slices no two of which are adjacent. Each slice has at least 1 slice not in S to its left and right, and the key point is that at least one slice in S has at least two slices not in S on one side. So, for example, if N=9 and S=[0,2,4], then 4 has more than 1 slice not in S on its right. Choose any slice with at least two slices buffer on one side, and eat this slice first. Then, once the two slices on the right and left are eaten, the remaining pizza is a circular array of size N-3, the remaining S has size (N-3)/3, and no two slices are adjacent. So by induction you can eat all of the slices in S.
	
	* similar to [house-robber-ii](https://leetcode.com/problems/house-robber-ii)
* [circle-and-rectangle-overlapping](https://leetcode.com/problems/circle-and-rectangle-overlapping/): 
	* find the closest point (x, y) in rectangle to center of circle
	* check if (x, y) within circle
* [valid-parenthesis-string](https://leetcode.com/problems/valid-parenthesis-string/):
	* keep [min # of ')' required, max # of ')' allowed]
* [find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows](https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/)
	* keep lowest k sum row by row
* [longest-substring-with-at-least-k-repeating-characters](https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters)
	* not binary search, sliding window
	* A llegal substring contains no illegal char (cnt < k)
	* illegal chars (cnt < k) split the string into parts, recursively check each part for max length
* [k-th-smallest-in-lexicographical-order/](https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/)
	* keywords: digits
	* 10-nary tree + count legal number count of a certain prefix
* [maximum-number-of-darts-inside-of-a-circular-dartboard](https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/)
	* angular sweep in O(n^2logn)
* [count-the-repetitions](https://leetcode.com/problems/count-the-repetitions/description/)
	* keywords: circular pattern
* [unique-substrings-in-wraparound-string](https://leetcode.com/problems/unique-substrings-in-wraparound-string/description/)
	* O(n^2) for input string is too slow
	* consider dp state about alphabet
* [count-submatrices-with-all-ones](https://leetcode.com/problems/count-submatrices-with-all-ones)
	* solution for sub-matrix problem:
		* O(n) histogram area problem
		* DP: relationship between M[i][j], M[i][j - 1], M[i - 1][j]
* [find-a-value-of-a-mysterious-function-closest-to-target](https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target/)
	* characteristic of 'and' operation: the more 'and', the smaller reult
	* at most 32 distinct values when adding a new number
* [remove-boxes](https://leetcode.com/problems/remove-boxes): [discussion](https://discuss.leetcode.com/topic/84687/java-top-down-and-bottom-up-dp-solutions)
	* similar to 
		* [string-compression-ii](https://leetcode.com/problems/string-compression-ii)
		* [strange-printer](https://leetcode.com/problems/strange-printer): [discussion](https://leetcode.com/problems/strange-printer/discuss/106813/Same-as-Remove-Boxes)
	* state[i, j] for subarray can't be used directly 
		* after a removal, the remaining boxes could be merged, which makes it hard to be devided into non-overlap sub problems.
	* we might want boxes with same color to be removed together, so we need to add information to state[i, j]
		* top-down solution: **state(i, j, streak)**: streak means the # of boxes with same color to boxes[i]
			* for case seq = [1...1...1...], there are 2 transitions
				1. [1]...1...1...: `state(i, j, streak) = cost(streak + 1) + state(i + 1, j, 0)`
				2. 1[...1...]1......: for each following m where seq[m] == seq[i], take seq[i + 1, m - 1] first, and merge seq[i] and seq[m, j]: `state(i, j, streak) = max(state(i, j, streak), state(i + 1, m - 1, 0) + state(m, j, streak + 1))`
* [string-compression-ii](https://leetcode.com/problems/string-compression-ii)
	* dp with multiple dimension
	* I don't know how to solve this...
* [super-washing-machines](https://leetcode.com/problems/super-washing-machines/)
* [random-flip-matrix](https://leetcode.com/problems/random-flip-matrix/)
	* keep [0, total] unpicked elements
* [count-substrings-that-differ-by-one-character](https://leetcode.com/problems/count-substrings-that-differ-by-one-character)
	1. count substrings with #diff = 1: the pattern is `{preSame}diff{postSame}`, and for each position of postSame, add #presame + 1 substrings to total count 
	2. for each aligment (s[i..], t[j..]), we need to count all substrings with #diff = 1
* [minimize-deviation-in-array](https://leetcode.com/problems/minimize-deviation-in-array/)
	* a set of set-of-numbers {s1, s2}, choose 1 number from each set and form a sequence with min deviation |max - min|
		* similar to merge sort: add smallest number in each set to priorityQueue, remove min in queue and fill in the 2nd smallest number in the same set
* [checking-existence-of-edge-length-limited-paths/](https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/)
	* queries can also be sorted
	* edges are given, so Union-Find can be used to find connectivity for efficiency
* [stone-game-vi](https://leetcode.com/problems/stone-game-vi)
	* A and B value differently: 1 and 2 are equivalent
		1. If A, B all start from 0, when A take 1, A get valueA, B will lose valueB
		2. If we assume B starts from all taken: sum(valueB), then when A takes 1 stone, A will get valueA + valueB
	* The question now becomes 'Take half of the stones and get the max sum of value'
		* We can sort by valueA + valueB descending, and greedily take stones alternatively
* [minimum-adjacent-swaps-for-k-consecutive-ones/](https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/)
  * trick 1: a[i] - i: [it is a common trick in competitive programming to use a[i] - i to convert a "min sum of moves to make numbers consecutive" problem into a "min sum of moves to a single point"](https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/discuss/987362/Python-The-trick-and-related-problems).
  * trick 2: distance to median in O(1) with prefix sum
    * `(pSum[i + k] - pSum[i + k / 2]) - (pSum[i + (k + 1) / 2] - pSum[i])`
* [distribute-repeating-integers](https://leetcode.com/problems/distribute-repeating-integers/)
	* Given buckts of free memory bucket[], and array of reuqests for amount of memory request[], **there is no greedy way** to satisfy all the requests.
* [count-ways-to-make-array-with-product](https://leetcode.com/problems/count-ways-to-make-array-with-product)
	* Count # of k number whose product equals to n
		* prime factorization
			* n = p<sub>1</sub><sup>n1</sup>\*p<sub>2</sub><sup>n2</sup>\*p<sub>i</sub><sup>ni</sup>
		* calculate # grouping ways: for each prime i
			* put i primes into k boxes with empty boxes allowed (stars and bars): C(ni + k - 1, k - 1)
* [maximum-building-height](https://leetcode.com/problems/maximum-building-height)
	* ref: [Using-stack](https://leetcode.com/problems/maximum-building-height/discuss/1175064/Python3.-O(N*log(N)).-Using-stack.-Explanation-added.)
	* pattern: adjacent diff <= 1, max height restriction on some position
		* max height is restricted by adjacent lower max heights
		* solution 1: 2 pass: from left, from right
		* solution 2: stack: remove redundant restrictions (see ref)
* [minimum-skips-to-arrive-at-meeting-on-time](https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time)
	* dp on time: open-close (fail due to precision)
	* dp on effective distance
* [partition-to-k-equal-sum-subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets)
	* [O(n*2^n) solution](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/335668/DP-with-Bit-Masking-Solution-%3A-Best-for-Interviews) : dp[mask] indicates the subset is legal: current subset can form several groups of 'target' (sum/k) and a partial group (whose sum is less than the target)
* [minimum-number-of-moves-to-make-palindrome](https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/)
	* greedy from left to right
* [count-different-palindromic-subsequences](https://leetcode.com/problems/count-different-palindromic-subsequences/)
	* state transition : dp[start][end][char]
		* when `s[start] == s[end] == char`, then dp[start][end][char] = 2 + sum(dp[start + 1][end - 1][c]). It means for all the palindromes in [start + 1, end - 1] map to `a...palindrome...a` with the addition of `a` and `aa`
* [cherry-pickup](https://leetcode.com/problems/cherry-pickup)
	* 	greedy 2 round dp is wrong

		~~~
		[1,1,1,1,0,0,0]
		[0,0,0,1,0,0,0]
		[0,0,0,1,0,0,1]
		[1,0,0,1,0,0,0]
		[0,0,0,1,0,0,0]
		[0,0,0,1,0,0,0]
		[0,0,0,1,1,1,1]
		~~~
	* convert to 2 inter-dependent trips from top-left to bottom-right
		* 	At time t, the 2 trips can stop at (r1, t - r1) and (r2, t - r2), thus the state is dp[t][r1][r2]
*  [bricks-falling-when-hit](https://leetcode.com/problems/bricks-falling-when-hit/description/)
	*  reverse the drop process and leverage DSU
	*  generalization: given a graph, when query connectivities between nodes while removing edges, we can reverse the process by adding edges one by one until orignal graph is recovered. DSU can be used after this problem conversion.
*  [tallest-billboard](https://leetcode.com/problems/tallest-billboard)
	*  meet in the middle: use state (left stand, right stand) so the problem can be divided into 2 halfs whose states are overlapped
*  [maximum-number-of-groups-with-increasing-length](https://leetcode.com/problems/maximum-number-of-groups-with-increasing-length)
	*  sort and greedily calculate the max layer: [solution](https://leetcode.com/problems/maximum-number-of-groups-with-increasing-length/solutions/3803904/java-c-python-math-o-n/)

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
	* [find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows](https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/)
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
* [bitwise-and-of-numbers-range](https://leetcode.com/problems/bitwise-and-of-numbers-range/): find leftmost different bit and clear all bits right to it
* [integer-break](https://leetcode.com/problems/integer-break): Math solution, use 3 as many as possible in decomposition
* [water-and-jug-problem](https://leetcode.com/problems/water-and-jug-problem): GCD
* [maximum-students-taking-exam/](https://leetcode.com/problems/maximum-students-taking-exam/): maximum independent set
* [minimum-number-of-taps-to-open-to-water-a-garden/](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/): Minimum segment covering
* [find-all-good-strings](https://leetcode.com/problems/find-all-good-strings): Digit DP + KMP
* [number-of-ways-to-wear-different-hats-to-each-other](https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/): bitmask dp, but use bitmask for person (1 << 10) instead of hat (1 << 40)

## Implementation heavy
* [all-oone-data-structure](https://leetcode.com/problems/all-oone-data-structure)