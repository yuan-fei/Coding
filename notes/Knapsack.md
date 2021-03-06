# Notes

## Knapsack/Backpack
* Backpack: Given total volume of a backpack W, n kinds of items with each size<sub>i</sub> and value<sub>i</sub>, 
	* Categorory
		* item occurence:
			* 0-1 Backpack: each item can be chosen at most once
	 		* Complete Backpack: each item can be chosen infinite times
			* Multiple Backpack: the amount of each item is given, and each item i can be chosen at most amount<sub>i</sub> times.
			* Grouped Backpack: items are grouped into k groups with each group m<sub>i</sub> items, and at most 1 item an be chosen in each group
		* problem:
			1. Max size that the backpack can be filled
			2. Max total value of items that can be packed into the backpack
			3. How many ways that the backpack can be fullfilled (aka. Give n numbers, choose numbers that sum to a A)
				* item permutation as solution
				* item conbination as solution
			4. (All possible values) How many different sizes can be populated by the items 
	* Problem conversion 
		* 2 implementation of Complete Backpack: O(nW)
			1. state[i-1] based solution: inefficient O(n*W<sup>2</sup>)
			`state[i][j] = max(state[i - 1][j], state[i - 1][j - k * size[i]] + k * value[i]) while j >= k * size[i]`
			2. state[i] based solution: efficient O(nW)
			`state[i][j] = max(state[i - 1][j], state[i][j - size[i]] + value[i])`
		* Multiple Backpack: O(sum(amount<sub>i</sub>)*W)
			* Convert to 0-1 backpack problem by treating an item of size s<sub>i</sub> and amount a<sub>i</sub> as a<sub>i</sub> items each of size s<sub>i</sub>
			* Employ the 'state[i-1] based solution' of complete backpack problem: `state[i][j] = max(state[i-1][j - k * size[i]] + value[i]) while j >= k * size[i] and k <= amount[i]`
		* Grouped Backpack: O(sum(m<sub>i</sub>)*W)
			* Employ the 'state[i-1] based solution' of complete backpack problem: `state[i][j] = max(state[i - 1][j], state[i-1][j - size[i][k]] + value[i][k]) while j >= size[i][k]`
	* Basic code snippet

		~~~
		//0-1 backpack
		public static int zeroOneBackpack(int size_limit, int[] sizes, int[] values) {
			int[] state = new int[size_limit + 1];

			/* state initialization */
			
			for (int i = 1; i <= sizes.length; i++) {
				//backward update
				for (int j = size_limit; j >= sizes[i - 1]; j--) { 
					/* state update */
				}
			}
			return state[size_limit];
		}
		~~~
		
		~~~
		//complete backpack
		public static int completeBackpack(int size_limit, int[] sizes, int[] values) {
			int[] state = new int[size_limit + 1];
			
			/* state initialization */
			
			for (int i = 1; i <= sizes.length; i++) {
				//forward update
				for (int j = sizes[i - 1]; j <= size_limit; j++) { 
					/* state update */
				}
			}
			return state[size_limit];
		}
		~~~
	
		* The only difference is update direction:
			* backward for 0-1: state[j] update is only affected by state[t] where t is before j, backward update can avoid the case that state[j] is updated after state[t] is updated in the same item round, which means current item is packed twice
	* Problem solution with code snippet
		* Max size that the backpack can be filled
			* state[j]: max size that the backpack with volume j can be filled
			* state initialize: `state[0] = 0;`
			* state update: `state[j] = Math.max(state[j], state[j - sizes[i - 1]] + sizes[i - 1]);`
		* Max total value of items that can be packed into the backpack
			* state[j]: max total value of items that can be packed into the backpack with volume j
			* state initialize: `state[0] = 0;`
			* state update: `state[j] = Math.max(state[j], state[j - sizes[i - 1]] + values[i - 1]);`			
		* How many ways that the backpack can be fullfilled (aka. Give n numbers, choose numbers that sum to a A)
			* item permutation as solution: (1+2=3 and 2+1=3 are different solutions)
				* state[j]: # of permutations of first i places that sum to j with whatever item sizes
				
				~~~
				// swap the inner and outer loop
				state[0] = 1;
				for (int j = 1; j <= size_limit; j++) { 
					if(j >= sizes[i - 1]){
						for (int i = 1; i <= sizes.length; i++) {
							state[j] = state[j] + state[j - sizes[i - 1]];
						}
					}
				}
				~~~

			* item combination as solution: (1+2=3 and 2+1=3 are same solution)
				* state[j]: # of combinations that sum to j with only the first i kinds of item sizes
				* state initialize: `state[0] = 1;`
				* state update: `state[j] = state[j] + state[j - sizes[i - 1]];`
		* How many different sizes can be populated by the items
			* state[j]: whether there are items whose sizes sum to j
			* state initialize: `state[0] = true;`
			* state update: `state[j] = state[j] || state[j - sizes[i - 1]];` 
	* Details
		* '<=' or '=': although the code snippet are same for the 4 problems, the j in state[i][j] / state[j] stands for (i) the solutions of first i items whose sum of size <= j (problem 1, 2); or (ii) the solutions of first i items whose sum of size = j (problem 3, 4), what make the difference?
			* the answer relies in the state inializaiton step: when default value (i.e. 0, false) is not a valid solution (as in Proble 3, 4, the `state[0] = true or 1` must be specified), state[j] is being used under a strict '=' semantic.
			* the '=' semantic can be used in 0-1 backpack when volume limit is large and values are small. Populate state[i][j] which stands for the size of i items whose value sum exactly to j, and find the largest value j whose state[i][j] <= volume limit.
	* reference
		* 《挑战程序设计竞赛》
		* lintcode backpack ladder
		* http://blog.sina.com.cn/s/blog_8cf6e8d90100zldn.html

* Backpack optimization
	* Multiple backpack optimization for large amount<sub>i</sub>
		* DP original state transition: O(sum(amount<sub>i</sub>)*W)
		
			~~~
			i - ith item, 
			j - current volume, 
			m[i] - bound of item count
			
			state transition:
			dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i]]+v[i],..., dp[i-1][j-k*w[i]]+k*v[i]) where j>=k*w[i], k<m[i]`
			~~~
		* O(sum(log(amount<sub>i</sub>))*W) by binarization
			* Binarization: For amount<sub>i</sub> can be represented as a binary decomposition: <code>amount<sub>i</sub> = 1+2+..2<sup>n</sup> + a where 0<=a<2<sup>n</sup> </code>, any number in [0, amount<sub>i</sub>] can be represented by the sum of a subsets of {1,2,...2<sup>n</sup>, a}, 
			* Convert to 0-1 backpack problem: item i can be decomposed into log(amount<sub>i</sub>) sub 0-1 items: {[w[i], v[i]], [2\*w[i], 2\*v[i]], ..., [a\*w[i], a\*v[i]]}
		* O(Wn) by CHT
			* By observation, j, j-w[i], ..., j-k\*w[i] are in the same congruence of  j
			
			~~~
			if j = x*w[i] + r, l = max(x-m[i], 0)
			dp[i][j] = max(dp[i-1][x*w[i]+r], 
							dp[i-1][(x-1)*w[i]+r] 	+ v[i],
							..., 
							dp[i-1][l*r] 			+ (x-l)*v[i])
			         = x*v[i] + max(dp[i-1][x*w[i]+r] 		- x*v[i], 
					         		dp[i-1][(x-1)*w[i]+r] 	- (x-1)*v[i],
					         		..., 
					         		dp[i-1][l*r] 			- l*v[i])
			denote dp1[i-1][r][x] = dp[i-1][x*w[i]+r] - x*v[i]
			dp[i][j] = max(dp1[i-1][r][x], ..., dp1[i-1][r][l]) + x*v[i]
			dp1 can be soled by using CHT with monotonic deque implementation
			~~~
		* reference
			* 《挑战程序设计竞赛》p339
			* [淺談多重背包問題 (Multiple Knapsack Problem) 優化那些事](http://morris821028.github.io/2016/12/18/jg-20008/)
	* Mutiple backpack all possible values not more than V
		* O(n\*sum(maxItemCnt))->O(n\*count(maxItemCnt))
		* dp->newDp
			* `boolean dp[i][j]`: is value j possible composed with items chosen from first i items?
			* `int newDp[i][j]`: the amount left for ith item to fullfill total value j
		* transition:
	
		~~~
		newDp[i][j] = amount_i if newDp[i-1][j]>=0
		newDp[i][j] = -1 if j<value_i or newDp[i-1][j-value_i]<=0
		newDp[i][j] = newDp[i][j-value_i] - 1 otherwise
		~~~
	
		* reference
			* 《挑战程序设计竞赛》p62
	* 0-1 backpack variants
		* size is large, value is small
			* reference: 《挑战程序设计竞赛》p60
		* value and size are large, n is not large (n <= 40)
			* reference: 《挑战程序设计竞赛》p160