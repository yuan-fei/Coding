# Notes

* K-sum problem
    * 2-sum, 3-sum, 4-sum
        * 2-sum
            * In unsorted array, hash table helps to find all non-duplicated solutions in O(n), with an extra O(n) hash table space.
            * In a sorted array, 2-pointer search can be used for find all non-duplicated solutions in O(n), with extra O(1) space.
    * [K-sum solution](https://cs.stackexchange.com/questions/2973/generalised-3sum-k-sum-problem)
        * even: generate a sorted list S of all sum of k/2, and solve 2 sum problem (check if S contains both x, -x). Time complexity O(n^(k/2))
        * odd: generate a sorted list S of all sum of (k-1)/2, solve 3 sum problem (for each input a, check if S contains both x, -a-x). Time complexity O(n^((k+1)/2))
        * The time complexity for K sum is O(n^ceiling(k/2))


* Tricks
    * Sorting can be used for duplication prevention
        * skip duplicate in sorted array
        
        ~~~
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
        for(int low =start; low < high; low++){
        	if(low != start && a[low] == a[low - 1]]){
        		continue;
        	}
        	...
        }
        ~~~

        
* References
	* [1]: [Leetcode solution](https://www.sigmainfy.com/blog/leetcode-handbook-all-problem-solution-index.html)