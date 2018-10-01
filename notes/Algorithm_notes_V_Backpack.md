# Notes

## Backpack
* Backpack: Given total volume of a backpack, and value of each item, 
	* Categorory
		* item occurence:
			* 0-1 Backpack: each item can be chosen at most once
	 		* Complete Backpack: each item can be chosen infinite times
			* Multiple Backpack: the amount of each item is given, and each item i can be chosen at most amount<sub>i</sub> times.
		* problem:
			1. Max size that the backpack can be filled
			2. Max total value of items that can be packed into the backpack
			3. How many ways that the backpack can be fullfilled (aka. Give n numbers, choose numbers that sum to a A)
				* item permutation as solution
				* item conbination as solution
			4. How many different sizes can be populated by the items
	* Problem convertion
		* Complete Backpack: 2 way of recursions
			* inefficient: `state[i][j] = max(state[i-1][j - k * size[i]] + value[i]) while j >= k * size[i]`
			* efficient: `state[i][j] = max(state[i-1][j], state[i][j - size[i]] + value[i])`
		* Multiple Backpack
			* Convert to 0-1 backpack problem by treating an item of size s<sub>i</sub> and amount a<sub>i</sub> as a<sub>i</sub> items each of size s<sub>i</sub>
			* Employ the inefficient recursions of complete backpack problem: `state[i][j] = max(state[i-1][j - k * size[i]] + value[i]) while j >= k * size[i] and k <= amount[i]`
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
	
		*	The only difference is update direction: backward for 0-1; forward for complete
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
			* item permutation as solution

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
				* state[j]: # of permutations which the sizes of items sum to j
			* item conbination as solution
				* state[j]: # of combinations which the sizes of items sum to j
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