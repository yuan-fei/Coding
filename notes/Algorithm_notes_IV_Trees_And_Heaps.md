# Notes

## <a name='BTree'></a>B Tree
* B Tree: worst case O(tlog<sub>t</sub>n)
	* Minimum degree constraint: 
		* for non-root node: t-1 <= #keys <= 2t-1 
		* for non-root internal node: t <= #children <= 2t 
	* Balanced: all leaves have the same depth, which is the height of tree: h <= log<sub>t</sub>(n+1)/2
	* Hierarchical: good for cache/index implementation - architeccture leverage of different kinds of storage
	* Fast node insertion or deletion: O(tlog<sub>t</sub>n)
	* Grow upwards: Only when root is full and a split is performed at root will a B tree increase its height; other split does not affect the height

* Implementation: 
	* One pass downward without backtrack: adjust node on the path during insertion and deletion before the constraint breaks.
	* Node type needs to be adjust:
		* full node: node with #keys = 2t-1
		* 'empty' node: node with #keys = t-1 (it's not empty actually, but I use empty in contrast with full node)
	* Basic node adjust operation
		* Decrease #key of a full node for a safe insertion
			* split a intermediate node: turn a full node into 2 'empty' node, and elevate 1 key into parent
			* split root: intrduce new root, and height + 1
		* Increase #key of a 'empty' node for a safe deletion
			* borrow key from sibling: push down a key from its parent to the 'empty' node, at the same time move a key of one of its sibling who is not 'empty' to parent
			* merge with sibling: merge the 'empty' node with one of its sibling who is also 'empty', and the merged node has 2t-1 keys (the key in the parent between the 2 node is pushed down to the merged node also)
	* Code complexity: 
		* insertion: remember to split any full node on search path
		* deletion: much more complicated, but the basic rule is to adjust the 'empty' node before make any delete, and recursively do the deletion

## <a name='Red_Black_Tree'></a>Red Black Tree
* RBT: O(logn)
	* Properties
		1. Every node is either red or black.
		2. The root is black.
		3. Every leaf (NIL) is black.
		4. If a node is red, then both its children are black.
		5. For each node, all simple paths from the node to descendant leaves contain the same number of black nodes. 
	* Balanced: no path is twice as long as others. h <= 2log(n+1)
* Left Lean RBT: Only left child can be red
	* LLRBT is equavalent to 2-3/2-3-4 tree (B tree with minimum degree = 2)
	* It has simpler implementation
* Implementation
	* rotation: left rotate and right rotate helps to reshape a subtree
	* insert: insert as BST, and make the node red, do insert fix up for red parent-child case (property 4)
	* delete: delete as BST, do insert fix up for bh imbalance case (property 5)
	
![RBT insert fix up](../pics/RBT-insert-fixup.jpg)

![RBT delete fix up](../pics/RBT-delete-fixup.jpg)

## <a name='OS_Tree'></a>Order Statistics Tree
* OS Tree: dynamic order statistic with O(logn)
	* operations supported: select the ith element, rank of a element
	* augment of RBT
			* size: # of nodes in subtree

		
## <a name='Interval_Tree'></a>Interval Tree
* Interval Tree: find overlap of intervals with O(logn)
	* operations supported: find an overlap interval of the given interval
 	* augment of RBT
	 	* left end of interval as key
		* max right: max right end in the subtree

## <a name='vEB_Tree'></a>van Emde Boas Tree
* van Emde Boas Tree: priority queue operations in O(loglogu)
	* Optimized bitmap: NO sort needed
	* Keys are integers in range [0, u) with no duplicate
	* SEARCH, INSERT, DELETE, MINIMUM, MAXIMUM, SUCCESSOR, and PREDECESSOR in O(loglogu)
	* Data structure
		* size: range
		* min: minimum element in subtree
		* max: maximum element in subtree
		* cluster: each vEB node of size u has a cluster of size u<sup>1/2</sup> in which each cluster node is a vEB node of size u<sup>1/2</sup>.
		* summary: each vEB node has a summary for its cluster, indicating which cluster has elements.
	* The cluster hierarchical structure decrease the height
	* The summary helps to locate the non-null cluster fast so that we can jump around


![RBT delete fix up](../pics/vEB.jpg)
vEB tree with elements [2, 3, 4, 5, 7, 14, 15]

## <a name='Union_Find_Set'></a>Union-Find Set
* union-find set: Build MCC in O(V+E)
	* Construct MCC(max connected component) dynamically
	* Operations
		* union
		* find

## <a name='Mergeable_Heap'></a>Mergeable Heap
* Mergeable heap:
	* support oprtations: insert, minimum, extractMin, union, deceaseKey, delete
	* usally bad performance for search operation
* Binomial heap: worst case O(logn)
	* Binomial tree B<sub>k</sub>: 2 B<sub>k-1</sub> binomial subtrees for which take 1 subtree's root as the other subtree's left child
		* height k = logn
		* The root of B<sub>k</sub> has degree k which corresponding to k children from left to right: B<sub>k-1</sub>, B<sub>k-2</sub>, ..., B<sub>0</sub>
	* Binomial heap H:
		* each binomial tree is a minimu heap whose key is larger than its parent
		* binomial trees are ordered by the degree of their root and no duplicate degree is allowed in the root list
	* Implementation
		* union 2 heaps is a base operation of others
* Fibnacci Heap: amortized O(1) for most opertaion except O(logn) for extractMin and delete
	* Implementation
		* An unordered list of heaps, and a 'min' point to minimum heap top element
		* Only extractMin will reorganize the list
		* A 'mark' indicates whether a node has lost a child since it became a child of its parent. The 'mark' enforces the sub tree be cut and re-added as a new heap, which garantees the 'extractMin' runs in O(logn)