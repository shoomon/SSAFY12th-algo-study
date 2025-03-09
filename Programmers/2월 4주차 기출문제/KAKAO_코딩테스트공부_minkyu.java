public class CodingTestStudy {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] problems = {{10,15,2,1,2}, {20,20,3,3,4}};
        int ans = sol.solution(10, 10, problems);
        System.out.println(ans);
    }

    public static class Solution {
        public int solution(int alp, int cop, int[][] problems) {
            // 목표 알고력과 코딩력 계산
            int maxAlp = 0;
            int maxCop = 0;
            for (int[] problem : problems) {
                maxAlp = Math.max(maxAlp, problem[0]); // alp_req
                maxCop = Math.max(maxCop, problem[1]); // cop_req
            }

            // 이미 목표치를 넘은 경우 조정
            alp = Math.min(alp, maxAlp);
            cop = Math.min(cop, maxCop);

            // dp 배열: [알고력][코딩력] 상태에 도달하는 최소 시간
            int[][] dp = new int[maxAlp + 1][maxCop + 1];

            // 초기값 설정 (무한대)
            for (int i = 0; i <= maxAlp; i++) {
                for (int j = 0; j <= maxCop; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }

            // 시작 위치 초기화
            dp[alp][cop] = 0;

            // DP 진행
            for (int i = alp; i <= maxAlp; i++) {
                for (int j = cop; j <= maxCop; j++) {
                    if (dp[i][j] == Integer.MAX_VALUE) continue;

                    // 1. 알고력 공부
                    if (i + 1 <= maxAlp) {
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                    }

                    // 2. 코딩력 공부
                    if (j + 1 <= maxCop) {
                        dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                    }

                    // 3. 문제 풀이
                    for (int[] p : problems) {
                        int alpReq = p[0];
                        int copReq = p[1];
                        int alpRwd = p[2];
                        int copRwd = p[3];
                        int cost = p[4];

                        // 현재 상태로 풀 수 있는 문제만 고려
                        if (i >= alpReq && j >= copReq) {
                            int nextAlp = Math.min(maxAlp, i + alpRwd);
                            int nextCop = Math.min(maxCop, j + copRwd);
                            dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                        }
                    }
                }
            }

            return dp[maxAlp][maxCop];
        }
    }
}
