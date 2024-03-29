/*
 * @lc app=leetcode id=2102 lang=java
 *
 * [2102] Sequentially Ordinal Rank Tracker
 *
 * https://leetcode.com/problems/sequentially-ordinal-rank-tracker/description/
 *
 * algorithms
 * Hard (51.97%)
 * Likes:    25
 * Dislikes: 14
 * Total Accepted:    1.2K
 * Total Submissions: 2.3K
 * Testcase Example:  '["SORTracker","add","add","get","add","get","add","get","add","get","add","get","get"]\n' +
  '[[],["bradford",2],["branford",3],[],["alps",2],[],["orland",2],[],["orlando",3],[],["alpine",2],[],[]]'
 *
 * A scenic location is represented by its name and attractiveness score, where
 * name is a unique string among all locations and score is an integer.
 * Locations can be ranked from the best to the worst. The higher the score,
 * the better the location. If the scores of two locations are equal, then the
 * location with the lexicographically smaller name is better.
 * 
 * You are building a system that tracks the ranking of locations with the
 * system initially starting with no locations. It supports:
 * 
 * 
 * Adding scenic locations, one at a time.
 * Querying the i^th best location of all locations already added, where i is
 * the number of times the system has been queried (including the current
 * query).
 * 
 * For example, when the system is queried for the 4^th time, it returns the
 * 4^th best location of all locations already added.
 * 
 * 
 * 
 * 
 * Note that the test data are generated so that at any time, the number of
 * queries does not exceed the number of locations added to the system.
 * 
 * Implement the SORTracker class:
 * 
 * 
 * SORTracker() Initializes the tracker system.
 * void add(string name, int score) Adds a scenic location with name and score
 * to the system.
 * string get() Queries and returns the i^th best location, where i is the
 * number of times this method has been invoked (including this
 * invocation).
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["SORTracker", "add", "add", "get", "add", "get", "add", "get", "add",
 * "get", "add", "get", "get"]
 * [[], ["bradford", 2], ["branford", 3], [], ["alps", 2], [], ["orland", 2],
 * [], ["orlando", 3], [], ["alpine", 2], [], []]
 * Output
 * [null, null, null, "branford", null, "alps", null, "bradford", null,
 * "bradford", null, "bradford", "orland"]
 * 
 * Explanation
 * SORTracker tracker = new SORTracker(); // Initialize the tracker system.
 * tracker.add("bradford", 2); // Add location with name="bradford" and score=2
 * to the system.
 * tracker.add("branford", 3); // Add location with name="branford" and score=3
 * to the system.
 * tracker.get();              // The sorted locations, from best to worst,
 * are: branford, bradford.
 * ⁠                           // Note that branford precedes bradford due to
 * its higher score (3 > 2).
 * ⁠                           // This is the 1^st time get() is called, so
 * return the best location: "branford".
 * tracker.add("alps", 2);     // Add location with name="alps" and score=2 to
 * the system.
 * tracker.get();              // Sorted locations: branford, alps, bradford.
 * ⁠                           // Note that alps precedes bradford even though
 * they have the same score (2).
 * ⁠                           // This is because "alps" is lexicographically
 * smaller than "bradford".
 * ⁠                           // Return the 2^nd best location "alps", as it
 * is the 2^nd time get() is called.
 * tracker.add("orland", 2);   // Add location with name="orland" and score=2
 * to the system.
 * tracker.get();              // Sorted locations: branford, alps, bradford,
 * orland.
 * ⁠                           // Return "bradford", as it is the 3^rd time
 * get() is called.
 * tracker.add("orlando", 3);  // Add location with name="orlando" and score=3
 * to the system.
 * tracker.get();              // Sorted locations: branford, orlando, alps,
 * bradford, orland.
 * ⁠                           // Return "bradford".
 * tracker.add("alpine", 2);   // Add location with name="alpine" and score=2
 * to the system.
 * tracker.get();              // Sorted locations: branford, orlando, alpine,
 * alps, bradford, orland.
 * ⁠                           // Return "bradford".
 * tracker.get();              // Sorted locations: branford, orlando, alpine,
 * alps, bradford, orland.
 * ⁠                           // Return "orland".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * name consists of lowercase English letters, and is unique among all
 * locations.
 * 1 <= name.length <= 10
 * 1 <= score <= 10^5
 * At any time, the number of calls to get does not exceed the number of calls
 * to add.
 * At most 4 * 10^4 calls in total will be made to add and get.
 * 
 * 
 */

// @lc code=start
class SORTracker {
    PriorityQueue<Location> q1 = new PriorityQueue<>();
    PriorityQueue<Location> q2 = new PriorityQueue<>((b, a) -> a.compareTo(b));
    int rank = 1;
    public SORTracker() {
        
    }
    
    public void add(String name, int score) {
        Location l = new Location(name, score);
        if(q1.isEmpty() || l.compareTo(q1.peek()) >= 0){
            q1.offer(l);
        }
        else{
            q2.offer(l);
        }
        
    }
    
    public String get() {
        adjust();
        // System.out.println(q1);
        // System.out.println(q2);
        String ret = q1.peek().n;
        rank++;
        return ret;
    }

    void adjust(){
        while(q1.size() > rank){
            q2.offer(q1.poll());
        }
        while(q1.size() < rank){
            q1.offer(q2.poll());
        }
    }

    class Location implements Comparable<Location>{
        String n;
        int s;
        Location(String name, int score){
            n = name;
            s = score;
        }

        public int compareTo(Location b){
            Location a = this;
            return Integer.compare(a.s, b.s) == 0 ? b.n.compareTo(a.n) : Integer.compare(a.s, b.s);
        }

        public String toString(){
            return s + n;
        }
    }
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */
// @lc code=end
