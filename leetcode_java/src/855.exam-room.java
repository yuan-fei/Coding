/*
 * @lc app=leetcode id=855 lang=java
 *
 * [855] Exam Room
 *
 * https://leetcode.com/problems/exam-room/description/
 *
 * algorithms
 * Medium (43.44%)
 * Likes:    1175
 * Dislikes: 442
 * Total Accepted:    54.7K
 * Total Submissions: 126K
 * Testcase Example:  '["ExamRoom","seat","seat","seat","seat","leave","seat"]\n' +
  '[[10],[],[],[],[],[4],[]]'
 *
 * There is an exam room with n seats in a single row labeled from 0 to n - 1.
 * 
 * When a student enters the room, they must sit in the seat that maximizes the
 * distance to the closest person. If there are multiple such seats, they sit
 * in the seat with the lowest number. If no one is in the room, then the
 * student sits at seat number 0.
 * 
 * Design a class that simulates the mentioned exam room.
 * 
 * Implement the ExamRoom class:
 * 
 * 
 * ExamRoom(int n) Initializes the object of the exam room with the number of
 * the seats n.
 * int seat() Returns the label of the seat at which the next student will
 * set.
 * void leave(int p) Indicates that the student sitting at seat p will leave
 * the room. It is guaranteed that there will be a student sitting at seat
 * p.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
 * [[10], [], [], [], [], [4], []]
 * Output
 * [null, 0, 9, 4, 2, null, 5]
 * 
 * Explanation
 * ExamRoom examRoom = new ExamRoom(10);
 * examRoom.seat(); // return 0, no one is in the room, then the student sits
 * at seat number 0.
 * examRoom.seat(); // return 9, the student sits at the last seat number 9.
 * examRoom.seat(); // return 4, the student sits at the last seat number 4.
 * examRoom.seat(); // return 2, the student sits at the last seat number 2.
 * examRoom.leave(4);
 * examRoom.seat(); // return 5, the student sits at the last seat number
 * 5.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^9
 * It is guaranteed that there is a student sitting at seat p.
 * At most 10^4 calls will be made to seat and leave.
 * 
 * 
 */

// @lc code=start
class ExamRoom {
    int N;
    ArrayList<Integer> L = new ArrayList<>();
    public ExamRoom(int n) {
        N = n;
    }

    public int seat() {
        if (L.size() == 0) {
            L.add(0);
            return 0;
        }
        int d = Math.max(L.get(0), N - 1 - L.get(L.size() - 1));
        for (int i = 0; i < L.size() - 1; ++i) d = Math.max(d, (L.get(i + 1) - L.get(i)) / 2);
        if (L.get(0) == d) {
            L.add(0, 0);
            return 0;
        }
        for (int i = 0; i < L.size() - 1; ++i)
            if ((L.get(i + 1) - L.get(i)) / 2 == d) {
                L.add(i + 1, (L.get(i + 1) + L.get(i)) / 2);
                return L.get(i + 1);
            }
        L.add(N - 1);
        return N - 1;
    }

    public void leave(int p) {
        for (int i = 0; i < L.size(); ++i) if (L.get(i) == p) L.remove(i);
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
// @lc code=end
