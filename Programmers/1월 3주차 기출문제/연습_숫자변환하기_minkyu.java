import java.util.*;
import java.io.*;

public class ChangeNumber {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int ans = sol.solution(10,40,30);
        System.out.println(ans);
    }

    static class Solution{
        public int solution(int x, int y, int n) {
            int answer = 0;
            int[] dp = new int[1000001];
            Arrays.fill(dp, -1);

            dp[x] = 0;
            for (int i = x + 1; i <= y; i++){
                // 2로 나눈 전 값이 존재하면, dp로 소환
                if (i % 2 == 0){
                    if (dp[i/2] != -1)
                        dp[i] = dp[i/2] + 1;
                }
                // 3로 나눈 전 값이 존재하면, dp로 소환
                if (i % 3 == 0){
                    if (dp[i/3] != -1)
                        if (dp[i] != -1) dp[i] = Math.min(dp[i], dp[i/3] + 1);
                        else dp[i] = dp[i/3] + 1;
                }
                if (i - n > 0){
                    if (dp[i-n] != -1){
                        if (dp[i] != -1) dp[i] = Math.min(dp[i], dp[i-n] + 1);
                        else dp[i] = dp[i-n] + 1;
                    }
                }
            }
            answer = dp[y];

            return answer;
        }
    }
}
