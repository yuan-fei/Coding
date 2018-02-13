
public class LinkedListOps {
	public static class LinkNode{
		LinkNode next;
		int val;
		public LinkNode(int val) {
			this.val = val;
		}
	}
	
	public static LinkNode insertNodeBefore(LinkNode head, LinkNode node, int val) {
		if(head == null) {
			return null;
		}
		LinkNode dummy = new LinkNode(-1);
		dummy.next = head;
		LinkNode pre = dummy;
		while(pre != null) {
			if(pre.next == node) {
				pre.next = new LinkNode(val);
				pre.next.next = node;
				return dummy.next;
			}
			else {
				pre = pre.next;
			}
		}
		return dummy.next;
	}
	
	public static LinkNode reverse(LinkNode head) {
		LinkNode pre = null;
		while(head != null) {
			LinkNode tmp = head.next;
			head.next = pre;
			pre = head;
			head = tmp;
		}
		return pre;
	}
	
	public static LinkNode findMiddle(LinkNode head) {
		if(head == null) {
			return head;
		}
		LinkNode slow = head;
		LinkNode fast = head.next;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	private static LinkNode generateList() {
		LinkNode head = new LinkNode(1);
		LinkNode n = head;
		n.next = new LinkNode(2);
		n = n.next;
		n.next = new LinkNode(3);
		n = n.next;
		n.next = new LinkNode(4);
		n = n.next;
		n.next = new LinkNode(5);
		return head;
	}
	
	private static void printList(LinkNode head) {
		String s = "";
		while(head != null) {
			s+=head.val;
			head = head.next;
		}
		System.out.println(s);
	}
	
	public static void main(String[] args) {
		LinkNode head = generateList();
		printList(head);
		LinkNode middle = findMiddle(head);
		System.out.println(middle.val);
		head = insertNodeBefore(head, middle, 4);
		printList(head);
		head = reverse(head);
		printList(head);
	}
	
}
