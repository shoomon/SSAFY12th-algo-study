import java.util.*;

public class CountDown {
    class Solution {
        public int[] solution(int target) {
            Map<Integer, Boolean> scores = new HashMap<>();
            // 싱글
            for (int i = 1; i <= 20; i++)
                scores.put(i,true);

            // 불
            scores.put(50, true);

            // 더블
            for (int i = 1; i <= 20; i++){
                if (scores.containsKey(i * 2)) continue;
                scores.put(i * 2, false);
            }

            // 트리플
            for (int i = 1; i <= 20; i++){
                if (scores.containsKey(i * 3)) continue;
                scores.put(i * 3, false);
            }

            int[][] dp = new int[target + 1][2];
            for (int i = 0; i <= target; i++) {
                dp[i][0] = Integer.MAX_VALUE;
                dp[i][1] = 0;
            }

            dp[0][0] = 0;
            for (int i = 1; i <= target; i++) {
                for (int score : scores.keySet()){
                    if (i >= score) {
                        int prev = i - score;
                        if (dp[prev][0] != Integer.MAX_VALUE) {
                            int newDarts = dp[prev][0] + 1;
                            int newSingleBull = dp[prev][1] + (scores.get(score) ? 1 : 0);

                            // 더 적은 라운드가 가능한 경우
                            if (newDarts < dp[i][0]) {
                                dp[i][0] = newDarts;
                                dp[i][1] = newSingleBull;
                            }
                            // 같은 다트 수지만 싱글,불 횟수가 더 많은 경우
                            else if (newDarts == dp[i][0] && newSingleBull > dp[i][1]) {
                                dp[i][1] = newSingleBull;
                            }
                        }
                    }
                }
            }

            return dp[target];
        }
    }
}
