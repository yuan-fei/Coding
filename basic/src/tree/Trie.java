package tree;

public class Trie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] dic = new String[] {"car", "cat", "card", "cart", "art", "bar", "dart", "heart"};
		Trie trie = new Trie();
		for (String s : dic) {
			trie.insert(s);
		}
		
		System.out.println(trie.search("card"));
		System.out.println(trie.search("cart"));
		System.out.println(trie.search("cars"));
		System.out.println(trie.startsWith("car"));
		System.out.println(trie.startsWith("ba"));
	}

	static class TrieNode{
        TrieNode[] surffixes;
        boolean isWord;
        TrieNode(){
            surffixes = new TrieNode[26];
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode tn = root;
        for (char c: word.toCharArray()) {
            if(tn.surffixes[c - 'a'] == null){
                tn.surffixes[c - 'a'] = new TrieNode();    
            }
            tn = tn.surffixes[c - 'a'];
        }
        tn.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode tn = root;
        for (char c: word.toCharArray()) {
            tn = tn.surffixes[c - 'a'];
            if(tn == null){
                return false;
            }
        }
        return tn.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode tn = root;
        for (char c: prefix.toCharArray()) {
            tn = tn.surffixes[c - 'a'];
            if(tn == null){
                return false;
            }
        }
        return true;
    }
}
