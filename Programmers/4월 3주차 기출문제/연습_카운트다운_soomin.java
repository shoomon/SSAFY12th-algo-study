import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2]; // [0]: 최소 다트 수, [1]: 싱글/불 개수

        // 초기값 설정
        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        // 가능한 점수 목록 만들기
        List<Integer> scores = new ArrayList<>();
        Set<Integer> singleOrBull = new HashSet<>();

        for (int i = 1; i <= 20; i++) {
            scores.add(i);       // 싱글
            scores.add(i * 2);   // 더블
            scores.add(i * 3);   // 트리플
            singleOrBull.add(i); // 싱글만 따로 저장
        }
        scores.add(50); // 불
        singleOrBull.add(50);

        // DP 점화식
        for (int i = 1; i <= target; i++) {
            for (int s : scores) {
                if (i - s < 0) continue;

                int total = dp[i - s][0] + 1;
                int singles = dp[i - s][1] + (singleOrBull.contains(s) ? 1 : 0);

                if (total < dp[i][0]) {
                    dp[i][0] = total;
                    dp[i][1] = singles;
                } else if (total == dp[i][0]) {
                    dp[i][1] = Math.max(dp[i][1], singles);
                }
            }
        }

        return dp[target];
    }
}