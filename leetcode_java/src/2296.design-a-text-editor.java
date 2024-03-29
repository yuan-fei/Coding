/*
 * @lc app=leetcode id=2296 lang=java
 *
 * [2296] Design a Text Editor
 *
 * https://leetcode.com/problems/design-a-text-editor/description/
 *
 * algorithms
 * Hard (33.82%)
 * Likes:    212
 * Dislikes: 182
 * Total Accepted:    7.8K
 * Total Submissions: 23K
 * Testcase Example:  '["TextEditor","addText","deleteText","addText","cursorRight","cursorLeft","deleteText","cursorLeft","cursorRight"]\n' +
  '[[],["leetcode"],[4],["practice"],[3],[8],[10],[2],[6]]'
 *
 * Design a text editor with a cursor that can do the following:
 * 
 * 
 * Add text to where the cursor is.
 * Delete text from where the cursor is (simulating the backspace key).
 * Move the cursor either left or right.
 * 
 * 
 * When deleting text, only characters to the left of the cursor will be
 * deleted. The cursor will also remain within the actual text and cannot be
 * moved beyond it. More formally, we have that 0 <= cursor.position <=
 * currentText.length always holds.
 * 
 * Implement the TextEditor class:
 * 
 * 
 * TextEditor() Initializes the object with empty text.
 * void addText(string text) Appends text to where the cursor is. The cursor
 * ends to the right of text.
 * int deleteText(int k) Deletes k characters to the left of the cursor.
 * Returns the number of characters actually deleted.
 * string cursorLeft(int k) Moves the cursor to the left k times. Returns the
 * last min(10, len) characters to the left of the cursor, where len is the
 * number of characters to the left of the cursor.
 * string cursorRight(int k) Moves the cursor to the right k times. Returns the
 * last min(10, len) characters to the left of the cursor, where len is the
 * number of characters to the left of the cursor.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["TextEditor", "addText", "deleteText", "addText", "cursorRight",
 * "cursorLeft", "deleteText", "cursorLeft", "cursorRight"]
 * [[], ["leetcode"], [4], ["practice"], [3], [8], [10], [2], [6]]
 * Output
 * [null, null, 4, null, "etpractice", "leet", 4, "", "practi"]
 * 
 * Explanation
 * TextEditor textEditor = new TextEditor(); // The current text is "|". (The
 * '|' character represents the cursor)
 * textEditor.addText("leetcode"); // The current text is "leetcode|".
 * textEditor.deleteText(4); // return 4
 * ⁠                         // The current text is "leet|". 
 * ⁠                         // 4 characters were deleted.
 * textEditor.addText("practice"); // The current text is "leetpractice|". 
 * textEditor.cursorRight(3); // return "etpractice"
 * ⁠                          // The current text is "leetpractice|". 
 * ⁠                          // The cursor cannot be moved beyond the actual
 * text and thus did not move.
 * ⁠                          // "etpractice" is the last 10 characters to the
 * left of the cursor.
 * textEditor.cursorLeft(8); // return "leet"
 * ⁠                         // The current text is "leet|practice".
 * ⁠                         // "leet" is the last min(10, 4) = 4 characters to
 * the left of the cursor.
 * textEditor.deleteText(10); // return 4
 * ⁠                          // The current text is "|practice".
 * ⁠                          // Only 4 characters were deleted.
 * textEditor.cursorLeft(2); // return ""
 * ⁠                         // The current text is "|practice".
 * ⁠                         // The cursor cannot be moved beyond the actual
 * text and thus did not move. 
 * ⁠                         // "" is the last min(10, 0) = 0 characters to the
 * left of the cursor.
 * textEditor.cursorRight(6); // return "practi"
 * ⁠                          // The current text is "practi|ce".
 * ⁠                          // "practi" is the last min(10, 6) = 6 characters
 * to the left of the cursor.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= text.length, k <= 40
 * text consists of lowercase English letters.
 * At most 2 * 10^4 calls in total will be made to addText, deleteText,
 * cursorLeft and cursorRight.
 * 
 * 
 * 
 * Follow-up: Could you find a solution with time complexity of O(k) per call?
 * 
 */

// @lc code=start
class TextEditor {

    class Node {
        char c;
        Node l, r;
        public Node(char cc){
            c = cc;
        }

        public Node(char cc, Node ll, Node rr){
            c = cc;
            l = ll;
            r = rr;
        }
    }

    Node head = new Node('a');
    Node tail = new Node('a');
    Node cur;

    public TextEditor() {
        head.r = tail;
        tail.l = head;
        cur = tail;
    }
    
    public void addText(String text) {
        for(char c : text.toCharArray()){
            cur.l.r = new Node(c, cur.l, cur);
            cur.l = cur.l.r; 
        }
    }
    
    public int deleteText(int k) {
        int cnt = 0;
        while(cur.l != head && k > cnt){
            cur.l.l.r = cur;
            cur.l = cur.l.l;
            cnt++;
        }
        return cnt;
    }
    
    public String cursorLeft(int k) {
        while(cur.l != head && k > 0){
            cur = cur.l;
            k--;
        }
        return getStr();
    }
    
    public String cursorRight(int k) {
        while(cur.r != null && k > 0){
            cur = cur.r;
            k--;
        }
        return getStr();   
    }

    private String getStr(){
        Node t = cur;
        int l = 10;
        while(t.l != head && l > 0){
            t = t.l;
            l--;
        }
        String s = "";
        while(t != head && t != cur){
            s += t.c;
            t = t.r;
        }
        return s;
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */
// @lc code=end
