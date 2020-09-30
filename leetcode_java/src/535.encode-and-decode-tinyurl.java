/*
 * @lc app=leetcode id=535 lang=java
 *
 * [535] Encode and Decode TinyURL
 *
 * https://leetcode.com/problems/encode-and-decode-tinyurl/description/
 *
 * algorithms
 * Medium (80.14%)
 * Likes:    697
 * Dislikes: 1515
 * Total Accepted:    112.2K
 * Total Submissions: 140K
 * Testcase Example:  '"https://leetcode.com/problems/design-tinyurl"'
 *
 * Note: This is a companion problem to the System Design problem: Design
 * TinyURL.
 * 
 * TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL such
 * as http://tinyurl.com/4e9iAk.
 * 
 * Design the encode and decode methods for the TinyURL service. There is no
 * restriction on how your encode/decode algorithm should work. You just need
 * to ensure that a URL can be encoded to a tiny URL and the tiny URL can be
 * decoded to the original URL.
 * 
 */

// @lc code=start
public class Codec {

    private static final int SIZE = 6;
    private static final Random RANDOM = new Random();
    private Map<String, String> map = new HashMap<>();
    
    public String encode(String longUrl) {
        String generated;
        
        do {
            generated = generate(SIZE);
        } while (map.containsKey(generated));
        
        map.put(generated, longUrl);
        return generated;
    }
	
    public String decode(String shortUrl) {
        return map.getOrDefault(shortUrl, null);
    }
    
    private String generate(int count) {
        char[] url = new char[count];
        
        for (int i = 0; i < count; i++) {
            int num = RANDOM.nextInt(62);
            int cur;
            
            if (num < 26) {
                cur = num + 'a';
            } else if (num < 52) {
                cur = num + 'A' - 26;
            } else {
                cur = num - 52 + '0';
            }
            url[i] = (char) cur;
        }
        
        return new String(url);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
// @lc code=end
