import java.util.*;
import java.io.*;

public class DefenseGame {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] enemy = {3,3,3,3};
        int ans = sol.solution(2,4, enemy);
        System.out.println(ans);
    }

    public static class Solution {
        int[] originEnemy;
        long[] stackedSum;
        int unbeatableCnt, limit;
        public int solution(int n, int k, int[] enemy) {
            int answer = 0;
            originEnemy = enemy;
            stackedSum = new long[enemy.length];
            unbeatableCnt = k; limit = n;
            long before = 0;
            for (int i = 0; i < enemy.length; i++){
                before += enemy[i];
                stackedSum[i] = before;
            }
            int left = 0;
            int right = originEnemy.length - 1;
            while (left <= right){
                int mid = (left + right)/2;
                if (isPossible(mid)){
                    answer = mid + 1;
                    left = mid + 1;
                }else
                    right = mid - 1;
            }
            return answer;
        }

        public boolean isPossible(int round){
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = 0; i <= round; i++) pq.offer(originEnemy[i]);
            long curSum = stackedSum[round];
            for (int i = 0; i < unbeatableCnt; i++){
                if (pq.isEmpty()) break;
                curSum -= pq.poll();
            }
            return curSum <= limit;
        }
    }
}
