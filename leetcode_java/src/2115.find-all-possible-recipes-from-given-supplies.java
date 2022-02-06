/*
 * @lc app=leetcode id=2115 lang=java
 *
 * [2115] Find All Possible Recipes from Given Supplies
 *
 * https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/
 *
 * algorithms
 * Medium (26.86%)
 * Likes:    178
 * Dislikes: 24
 * Total Accepted:    4.4K
 * Total Submissions: 16.3K
 * Testcase Example:  '["bread"]\n[["yeast","flour"]]\n["yeast","flour","corn"]'
 *
 * You have information about n different recipes. You are given a string array
 * recipes and a 2D string array ingredients. The i^th recipe has the name
 * recipes[i], and you can create it if you have all the needed ingredients
 * from ingredients[i]. Ingredients to a recipe may need to be created from
 * other recipes, i.e., ingredients[i] may contain a string that is in
 * recipes.
 * 
 * You are also given a string array supplies containing all the ingredients
 * that you initially have, and you have an infinite supply of all of them.
 * 
 * Return a list of all the recipes that you can create. You may return the
 * answer in any order.
 * 
 * Note that two recipes may contain each other in their ingredients.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies =
 * ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: recipes = ["bread","sandwich"], ingredients =
 * [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create
 * the ingredient "bread".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: recipes = ["bread","sandwich","burger"], ingredients =
 * [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies =
 * ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create
 * the ingredient "bread".
 * We can create "burger" since we have the ingredient "meat" and can create
 * the ingredients "bread" and "sandwich".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == recipes.length == ingredients.length
 * 1 <= n <= 100
 * 1 <= ingredients[i].length, supplies.length <= 100
 * 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <=
 * 10
 * recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase
 * English letters.
 * All the values of recipes and supplies combined are unique.
 * Each ingredients[i] does not contain any duplicate values.
 * 
 * 
 */

// @lc code=start
class Solution {
    Set<Integer> s;
    List<Integer>[] adj;
    int[] good;
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Integer> map = new HashMap<>();
        int id = 0;
        for(String r : recipes){
            if(!map.containsKey(r)){
                map.put(r, id++);
            }
        }
        for(List<String> ing : ingredients){
            for(String i : ing){
                if(!map.containsKey(i)){
                    map.put(i, id++);
                }    
            }
        }
        s = new HashSet<>();
        for(String sup : supplies){
            if(map.containsKey(sup)){
                s.add(map.get(sup));    
            }
        }
        adj = new List[map.size()];
        good = new int[map.size()];
        for(int i = 0; i < adj.length; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < recipes.length; i++){
            for(String ing : ingredients.get(i)){
                adj[i].add(map.get(ing));
            }
        }
        List<String> res = new ArrayList<>();
        for(String r : recipes){
            int i = map.get(r);
            dfs(i);
            if(good[i] == 1){
                res.add(r);
            }
        }
        return res;
    }

    void dfs(int u){
        if(good[u] == 0){
            if(adj[u].size() == 0){
                good[u] = s.contains(u) ? 1 : -1;
            }
            else{
                good[u] = 2;
                for(int v : adj[u]){
                    dfs(v);
                    if(good[v] == -1){
                        good[u] = -1;
                        break;
                    }
                }
                if(good[u] != -1){
                    good[u] = 1;
                }    
            }
            
        }
        else if(good[u] == 2){
            good[u] = -1;
        }
    }
}
// @lc code=end
