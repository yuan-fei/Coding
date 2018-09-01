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
* Implementation
	* rotation: left rotate and right rotate helps to reshape a subtree
	* insert: insert as BST, and make the node red, do insert fix up for red parent-child case (property 4)
	* delete: delete as BST, do insert fix up for bh imbalance case (property 5)
	
![RBT insert fix up](pics/RBT-insert-fixup.jpg)

![RBT delete fix up](pics/RBT-delete-fixup.jpg)