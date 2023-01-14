/*
 * @lc app=leetcode id=2532 lang=java
 *
 * [2532] Time to Cross a Bridge
 *
 * https://leetcode.com/problems/time-to-cross-a-bridge/description/
 *
 * algorithms
 * Hard (43.78%)
 * Likes:    23
 * Dislikes: 89
 * Total Accepted:    1K
 * Total Submissions: 2.4K
 * Testcase Example:  '1\n3\n[[1,1,2,1],[1,1,3,1],[1,1,4,1]]'
 *
 * There are k workers who want to move n boxes from an old warehouse to a new
 * one. You are given the two integers n and k, and a 2D integer array time of
 * size k x 4 where time[i] = [leftToRighti, pickOldi, rightToLefti, putNewi].
 * 
 * The warehouses are separated by a river and connected by a bridge. The old
 * warehouse is on the right bank of the river, and the new warehouse is on the
 * left bank of the river. Initially, all k workers are waiting on the left
 * side of the bridge. To move the boxes, the i^th worker (0-indexed) can
 * :
 * 
 * 
 * Cross the bridge from the left bank (new warehouse) to the right bank (old
 * warehouse) in leftToRighti minutes.
 * Pick a box from the old warehouse and return to the bridge in pickOldi
 * minutes. Different workers can pick up their boxes simultaneously.
 * Cross the bridge from the right bank (old warehouse) to the left bank (new
 * warehouse) in rightToLefti minutes.
 * Put the box in the new warehouse and return to the bridge in putNewi
 * minutes. Different workers can put their boxes simultaneously.
 * 
 * 
 * A worker i is less efficient than a worker j if either condition is
 * met:
 * 
 * 
 * leftToRighti + rightToLefti > leftToRightj + rightToLeftj
 * leftToRighti + rightToLefti == leftToRightj + rightToLeftj and i > j
 * 
 * 
 * The following rules regulate the movement of the workers through the bridge
 * :
 * 
 * 
 * If a worker x reaches the bridge while another worker y is crossing the
 * bridge, x waits at their side of the bridge.
 * If the bridge is free, the worker waiting on the right side of the bridge
 * gets to cross the bridge. If more than one worker is waiting on the right
 * side, the one with the lowest efficiency crosses first.
 * If the bridge is free and no worker is waiting on the right side, and at
 * least one box remains at the old warehouse, the worker on the left side of
 * the river gets to cross the bridge. If more than one worker is waiting on
 * the left side, the one with the lowest efficiency crosses first.
 * 
 * 
 * Return the instance of time at which the last worker reaches the left bank
 * of the river after all n boxes have been put in the new warehouse.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
 * Output: 6
 * Explanation: 
 * From 0 to 1: worker 2 crosses the bridge from the left bank to the right
 * bank.
 * From 1 to 2: worker 2 picks up a box from the old warehouse.
 * From 2 to 6: worker 2 crosses the bridge from the right bank to the left
 * bank.
 * From 6 to 7: worker 2 puts a box at the new warehouse.
 * The whole process ends after 7 minutes. We return 6 because the problem asks
 * for the instance of time at which the last worker reaches the left bank.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
 * Output: 50
 * Explanation: 
 * From 0  to 10: worker 1 crosses the bridge from the left bank to the right
 * bank.
 * From 10 to 20: worker 1 picks up a box from the old warehouse.
 * From 10 to 11: worker 0 crosses the bridge from the left bank to the right
 * bank.
 * From 11 to 20: worker 0 picks up a box from the old warehouse.
 * From 20 to 30: worker 1 crosses the bridge from the right bank to the left
 * bank.
 * From 30 to 40: worker 1 puts a box at the new warehouse.
 * From 30 to 31: worker 0 crosses the bridge from the right bank to the left
 * bank.
 * From 31 to 39: worker 0 puts a box at the new warehouse.
 * From 39 to 40: worker 0 crosses the bridge from the left bank to the right
 * bank.
 * From 40 to 49: worker 0 picks up a box from the old warehouse.
 * From 49 to 50: worker 0 crosses the bridge from the right bank to the left
 * bank.
 * From 50 to 58: worker 0 puts a box at the new warehouse.
 * The whole process ends after 58 minutes. We return 50 because the problem
 * asks for the instance of time at which the last worker reaches the left
 * bank.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n, k <= 10^4
 * time.length == k
 * time[i].length == 4
 * 1 <= leftToRighti, pickOldi, rightToLefti, putNewi <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {

    public static int findCrossingTime(int n, int k, int[][] time) {
        // [id, direction, arrivedTime]
        PriorityQueue<int[]> arrivedQueue = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        // [id, direction, arrivedTime]

        // time consuming
        // PriorityQueue<int[]> waitingQueueL = new PriorityQueue<>(Comparator.comparing((int[] a) -> -a[1])
        //         .thenComparing(a -> -time[a[0]][0] - time[a[0]][2]).thenComparing(a -> -a[0]));
        // PriorityQueue<int[]> waitingQueueR = new PriorityQueue<>(Comparator.comparing((int[] a) -> -a[1])
        //         .thenComparing(a -> -time[a[0]][0] - time[a[0]][2]).thenComparing(a -> -a[0]));
        
        // time efficient
        PriorityQueue<int[]> waitingQueueL = new PriorityQueue<>(Comparator.comparing((int[] a) -> -time[a[0]][0] - time[a[0]][2]).thenComparing(a -> -a[0]));
        PriorityQueue<int[]> waitingQueueR = new PriorityQueue<>(Comparator.comparing((int[] a) -> -time[a[0]][0] - time[a[0]][2]).thenComparing(a -> -a[0]));
        var waitingQueue = new WaitingQueueArray(waitingQueueL, waitingQueueR);
        int lastCrossBridgeTime = 0;
        for (int i = 0; i < k; i++) {
            arrivedQueue.offer(new int[] { i, 0, 0 });
        }

        while (!arrivedQueue.isEmpty() || !waitingQueue.isEmpty()) {
            if (waitingQueue.isEmpty()) {
                int[] cur = arrivedQueue.poll();
                waitingQueue.offer(cur);
                while (!arrivedQueue.isEmpty() && arrivedQueue.peek()[2] == cur[2]) {
                    waitingQueue.offer(arrivedQueue.poll());
                }
            }
            int c = 0;
            while (!waitingQueue.isEmpty()) {
                int[] cur = waitingQueue.poll();

                if (n != 0 || cur[1] == 1) {
                    lastCrossBridgeTime = Math.max(lastCrossBridgeTime, cur[2]);
                    lastCrossBridgeTime += time[cur[0]][cur[1] * 2];
                    if (cur[1] == 0) {
                        n--;
                    }

                    while (!arrivedQueue.isEmpty() && arrivedQueue.peek()[2] <= lastCrossBridgeTime) {
                        waitingQueue.offer(arrivedQueue.poll());
                    }
                    arrivedQueue.offer(
                            new int[] { cur[0], 1 - cur[1], lastCrossBridgeTime + time[cur[0]][cur[1] * 2 + 1] });
                }
            }
        }
        return lastCrossBridgeTime;


    }

    static class WaitingQueueArray{
        PriorityQueue<int[]>[] q = new PriorityQueue[2];
        public WaitingQueueArray(PriorityQueue<int[]> waitingQueueL, PriorityQueue<int[]> waitingQueueR){
            q[0] = waitingQueueL;
            q[1] = waitingQueueR;
        }

        boolean isEmpty(){
            return q[0].isEmpty() && q[1].isEmpty();
        }

        void offer(int[] e){
            q[e[1]].offer(e);
        }

        int[] poll(){
            if(!q[1].isEmpty()){
                return q[1].poll();
            }
            return q[0].poll();
        }
    }

    
}
// @lc code=end
