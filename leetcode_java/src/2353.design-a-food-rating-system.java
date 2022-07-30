/*
 * @lc app=leetcode id=2353 lang=java
 *
 * [2353] Design a Food Rating System
 *
 * https://leetcode.com/problems/design-a-food-rating-system/description/
 *
 * algorithms
 * Medium (32.07%)
 * Likes:    242
 * Dislikes: 79
 * Total Accepted:    10.2K
 * Total Submissions: 32K
 * Testcase Example:  '["FoodRatings","highestRated","highestRated","changeRating","highestRated","changeRating","highestRated"]\n' +
  '[[["kimchi","miso","sushi","moussaka","ramen","bulgogi"],["korean","japanese","japanese","greek","japanese","korean"],[9,12,8,15,14,7]],["korean"],["japanese"],["sushi",16],["japanese"],["ramen",16],["japanese"]]'
 *
 * Design a food rating system that can do the following:
 * 
 * 
 * Modify the rating of a food item listed in the system.
 * Return the highest-rated food item for a type of cuisine in the system.
 * 
 * 
 * Implement the FoodRatings class:
 * 
 * 
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes
 * the system. The food items are described by foods, cuisines and ratings, all
 * of which have a length of n.
 * 
 * 
 * foods[i] is the name of the i^th food,
 * cuisines[i] is the type of cuisine of the i^th food, and
 * ratings[i] is the initial rating of the i^th food.
 * 
 * 
 * void changeRating(String food, int newRating) Changes the rating of the food
 * item with the name food.
 * String highestRated(String cuisine) Returns the name of the food item that
 * has the highest rating for the given type of cuisine. If there is a tie,
 * return the item with the lexicographically smaller name.
 * 
 * 
 * Note that a string x is lexicographically smaller than string y if x comes
 * before y in dictionary order, that is, either x is a prefix of y, or if i is
 * the first position such that x[i] != y[i], then x[i] comes before y[i] in
 * alphabetic order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["FoodRatings", "highestRated", "highestRated", "changeRating",
 * "highestRated", "changeRating", "highestRated"]
 * [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean",
 * "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14,
 * 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16],
 * ["japanese"]]
 * Output
 * [null, "kimchi", "ramen", null, "sushi", null, "ramen"]
 * 
 * Explanation
 * FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi",
 * "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek",
 * "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
 * foodRatings.highestRated("korean"); // return "kimchi"
 * ⁠                                   // "kimchi" is the highest rated korean
 * food with a rating of 9.
 * foodRatings.highestRated("japanese"); // return "ramen"
 * ⁠                                     // "ramen" is the highest rated
 * japanese food with a rating of 14.
 * foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
 * foodRatings.highestRated("japanese"); // return "sushi"
 * ⁠                                     // "sushi" is the highest rated
 * japanese food with a rating of 16.
 * foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
 * foodRatings.highestRated("japanese"); // return "ramen"
 * ⁠                                     // Both "sushi" and "ramen" have a
 * rating of 16.
 * ⁠                                     // However, "ramen" is
 * lexicographically smaller than "sushi".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 2 * 10^4
 * n == foods.length == cuisines.length == ratings.length
 * 1 <= foods[i].length, cuisines[i].length <= 10
 * foods[i], cuisines[i] consist of lowercase English letters.
 * 1 <= ratings[i] <= 10^8
 * All the strings in foods are distinct.
 * food will be the name of a food item in the system across all calls to
 * changeRating.
 * cuisine will be a type of cuisine of at least one food item in the system
 * across all calls to highestRated.
 * At most 2 * 10^4 calls in total will be made to changeRating and
 * highestRated.
 * 
 * 
 */

// @lc code=start
class FoodRatings {

    Map<String, String> food2Cuisine = new HashMap<>();
    Map<String, Integer> food2Rating = new HashMap<>();
    Map<String, TreeSet<String>> cuisine2Ratings = new HashMap<>();
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for(int i = 0; i < foods.length; i++){
            food2Cuisine.put(foods[i], cuisines[i]);
            if(!cuisine2Ratings.containsKey(cuisines[i])){
                cuisine2Ratings.put(cuisines[i], new TreeSet<>((a, b) -> 
                    Integer.compare(food2Rating.get(b), food2Rating.get(a)) != 0 ? 
                        Integer.compare(food2Rating.get(b), food2Rating.get(a)) :
                        a.compareTo(b))
                );
            }
            food2Rating.put(foods[i], ratings[i]);
            cuisine2Ratings.get(cuisines[i]).add(foods[i]);
        }

    }
    
    public void changeRating(String food, int newRating) {
        int rating = food2Rating.get(food);
        TreeSet<String> ts = cuisine2Ratings.get(food2Cuisine.get(food));
        ts.remove(food);
        food2Rating.put(food, newRating);
        ts.add(food);
    }
    
    public String highestRated(String cuisine) {
        return cuisine2Ratings.get(cuisine).first();
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
// @lc code=end
