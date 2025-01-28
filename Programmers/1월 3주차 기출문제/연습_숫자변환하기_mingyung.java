import java.util.*;
import java.io.*;

public class 연습_숫자변환하기_mingyung {
	public int solution(int x, int y, int n) {
        int answer = -1;
        
        // dp로 x부터 y까지 진행
        int[] dp = new int[y+1];
        // 기본 셋팅으로 -1로 채우기
        Arrays.fill(dp, -1);
        // x 시작점으로 0 만들기
        dp[x] = 0;
        
        for (int i=x; i<=y; i++) {
            // -1이면 
            if (dp[i] == -1) continue;
            // i+n이 y보다 작으면 갱신해주기
            if (i+n <= y) {
                // 아직 갱신 안 된 값이면 그냥 더해주고 아니면 지금값이랑 비교하기
                dp[i+n] = (dp[i+n] == -1) ? dp[i] + 1 : Math.min(dp[i+n], dp[i]+1);
            }
            // i*2 비교
            if (i*2 <= y) {
                dp[i*2] = (dp[i*2] == -1) ? dp[i] + 1 : Math.min(dp[i*2], dp[i]+1);
            }
            // i*3 비교
            if (i*3 <= y) {
                dp[i*3] = (dp[i*3] == -1) ? dp[i] + 1 : Math.min(dp[i*3], dp[i]+1);
            }
        }
        
        return dp[y];
    }
}
