# Notes

* K-sum problem
	* 2-sum, 3-sum, 4-sum
		* 2-sum
           * In unsorted array, hash table helps to find all non-duplicated solutions in O(n), with an extra O(n) hash table space.
           * In a sorted array, 2-pointer search can be used for find all non-duplicated solutions in O(n), with extra O(1) space.
	* [K-sum solution](https://cs.stackexchange.com/questions/2973/generalised-3sum-k-sum-problem)
       * K is even: generate a sorted list S of all sum of k/2, and solve 2 sum problem (check if S contains both x, -x). Time complexity O(n^(k/2))
       * K is odd: generate a sorted list S of all sum of (k-1)/2, solve 3 sum problem (for each input a, check if S contains both x, -a-x). Time complexity O(n^((k+1)/2))
       * The time complexity for K sum is O(n^ceiling(k/2))

* KMP: String matching with O(m+n)
	* Jump table generation: 
			
		0|1|2|3|4|5|6|7|8|9
		---|---|---|---|---|---|---|---|---|---|
		a|b|a|b|c|a|b|a|b|a
		-1|-1|0|1|-1|0|1|2|3|2
	
		* The longest common prefix: **T[0..k] == T[m-k..m] => next[m] = k**
		* idea
		
			```
			// element 6,7,8 illustrated above
			case T[m] == T[next[m-1]] => next[m] = next[m-1] + 1
			// element 9
			case T[m] != T[next[m-1]] => check
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
	
* Tricks
	* Skip duplicates in sorted array    
        
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

        
* References
	* [1]: [Leetcode solution](https://www.sigmainfy.com/blog/leetcode-handbook-all-problem-solution-index.html)
