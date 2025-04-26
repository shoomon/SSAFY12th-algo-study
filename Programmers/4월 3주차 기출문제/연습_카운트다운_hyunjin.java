package PRO_4월_3주차;

import java.util.*;

class 연습_카운트다운_hyunjin {
    public int[] solution(int target) {
        List<int[]> scores = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            scores.add(new int[]{i, 1});       // 싱글
            scores.add(new int[]{i * 2, 0});   // 더블
            scores.add(new int[]{i * 3, 0});   // 트리플
        }
        scores.add(new int[]{50, 1}); // 불

        int[][] dp = new int[target + 1][2];
        dp[0][0] = 0;
        dp[0][1] = 0;

        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;

            for (int[] s : scores) {
                int score = s[0];
                int isSingleOrBull = s[1];

                if (i - score >= 0 && dp[i - score][0] != Integer.MAX_VALUE) {
                    int darts = dp[i - score][0] + 1;
                    int singleBull = dp[i - score][1] + isSingleOrBull;

                    if (darts < dp[i][0]) {
                        dp[i][0] = darts;
                        dp[i][1] = singleBull;
                    } else if (darts == dp[i][0] && singleBull > dp[i][1]) {
                        dp[i][1] = singleBull;
                    }
                }
            }
        }

        return dp[target];
    }
}
