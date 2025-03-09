
import java.util.*;
import java.io.*;

public class KAKAO_코딩테스트공부_mingyung {
	public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        
        // 문제 중 가장 높은 알고력, 코딩력 찾기
        int maxAlp = 0;
        int maxCop = 0;
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        // dp[alp][cop] = (alp, cop) 위치까지 도달하는 데 걸리는 최단 시간
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        
        // 기존 알고력, 코딩력이 문제 요구 최대보다 큰 경우 처리
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0; // 시작 위치
        
        for (int i=alp; i<=maxAlp; i++) {
            for (int j=cop; j<=maxCop; j++) {
                // 기본 문제를 풀 경우
                if (i+1 <= maxAlp) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                }
                if (j+1 <= maxCop) {
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                }
                // 선택 문제를 풀 경우
                for (int[] problem : problems) {
                    // 문제를 풀 수 있는 경우
                    if (problem[0] <= i && problem[1] <= j) {
                        // maxAlp, maxCop 범위 처리
                        int nextAlp = Math.min(maxAlp, i+problem[2]);
                        int nextCop = Math.min(maxCop, j+problem[3]);
                        // 비교 후 갱신
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j]+problem[4]);
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}
