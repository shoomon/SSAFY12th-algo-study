package Programmers.PRO_2월_4주차;

import java.util.*;

public class KAKAO_코딩테스트공부_hyunjin {
        public int solution(int alp, int cop, int[][] problems) {
            int INF = Integer.MAX_VALUE;

            // 1. 문제 푸는데 필요한 최대 알고력, 최대 코딩력
            int max_alp = 0;
            int max_cop = 0;

            for (int[] p : problems) {
                max_alp = Math.max(max_alp, p[0]); // 최대 알고력
                max_cop = Math.max(max_cop, p[1]); // 최대 코딩력
            }

            // 현재 알고력, 코딩력이 목표보다 크다면, 목표값으로 갱신 -> 목표값까지 드는 시간 : 0
            alp = Math.min(alp, max_alp);
            cop = Math.min(cop, max_cop);

            // 2. 이차원 DP 배열 초기화
            int[][] dp = new int[151][151];
            for (int[] row : dp) {
                Arrays.fill(row, INF);
            }

            // 3. 현재 alp, cop에서 시작
            dp[alp][cop] = 0;

            // 4. DP 탐색
            for (int i = alp; i <= max_alp; i++) {
                for (int j = cop; j <= max_cop; j++) {

                    // 4-1) 문제를 풀지 않고, 알고력+1, 코딩력+1 하는 경우
                    if (i < max_alp) {
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1); // 알고력 +1 => 시간 +1
                    }

                    if (j < max_cop) {
                        dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1); // 코딩력 +1 => 시간 +1
                    }

                    // 4-2) 문제를 풀어서 알고력, 코딩력 높이는 경우
                    for (int[] p : problems) {
                        if (p[0] <= i && p[1] <= j) { // 문제를 풀 수 있는 경우
                            int ni = Math.min(i + p[2], max_alp);
                            int nj = Math.min(j + p[3], max_cop);

                            dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + p[4]); // 문제를 풀어서 드는 시간(p[4] : cost)
                        }
                    }
                }
            }

            // 5. 가장 어려운 문제를 푸는데 드는 최단 시간
            return dp[max_alp][max_cop];
        }
}
