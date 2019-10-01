/*
 * @lc app=leetcode id=187 lang=java
 *
 * [187] Repeated DNA Sequences
 *
 * https://leetcode.com/problems/repeated-dna-sequences/description/
 *
 * algorithms
 * Medium (36.93%)
 * Total Accepted:    136.2K
 * Total Submissions: 368.4K
 * Testcase Example:  '"AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"'
 *
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and
 * T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA.
 * 
 * Write a function to find all the 10-letter-long sequences (substrings) that
 * occur more than once in a DNA molecule.
 * 
 * Example:
 * 
 * 
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 * 
 * 
 */
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
    	List<String> res = new ArrayList<>();
    	int N = s.length();
        Map<String, Integer> cntMap = new HashMap<>();
        for (int i = 0; i <= N - 10; i++) {
        	String ss = s.substring(i, i + 10);
        	cntMap.put(ss, cntMap.getOrDefault(ss, 0) + 1);
        }
        for (String k : cntMap.keySet()) {
        	if(cntMap.get(k) > 1){
        		res.add(k);	
        	}
        }
        return res;
    }
}
