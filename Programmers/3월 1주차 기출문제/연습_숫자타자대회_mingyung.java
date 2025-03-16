import java.util.*;
import java.io.*;

public class 연습_숫자타자대회_mingyung {
	// 가중치 구하는 함수
    int[][] pad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}};
    int cal(int s, int e) {
        // 각 패드의 위치 좌표 구하기
        int[] sp = null, ep = null;
        for (int r=0; r<4; r++) {
            for (int c=0; c<3; c++) {
                if (pad[r][c] == s) sp = new int[]{r, c};
                if (pad[r][c] == e) ep = new int[]{r, c};
            }
        }
        
        // 가로와 세로 거리 차이 구하기
        int rd = Math.abs(sp[0] - ep[0]), cd = Math.abs(sp[1] - ep[1]);
        
        // 가로와 세로 거리 차이가 없으면 같은 위치이므로 1 반환
        if (rd == 0 && cd == 0) return 1;
        
        // 가로와 세로의 거리차 중 작은 값만큼 대각선 이동(3) -> 이후 큰 값과 작은 값 차이만큼 평행 이동(2)
        return Math.min(rd, cd)*3 + (Math.max(rd, cd) - Math.min(rd, cd)) * 2;
    }
    
    public int solution(String numbers) {
        int answer = 0;
        
        // 일단 char 배열로 변환
        char[] nums = numbers.toCharArray();
        
        // 가중치 저장을 위한 DP 배열 생성
        int[][][] dp = new int[nums.length][10][10];
        
        // 배열 내부의 값 초기화
        for (int i=0; i<nums.length; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        
        // 시작점 초기화
        dp[0][nums[0]-'0'][6] = cal(nums[0]-'0', 4);
        dp[0][4][nums[0]-'0'] = cal(nums[0]-'0', 6);
        
        // DP를 통해 탐색 최적화
        for (int i=1; i<nums.length; i++) {
            int n = nums[i]-'0';
            
            // 좌우 손가락을 움직이면서 최솟값만 저장
            for (int l=0; l<10; l++) {
                for (int r=0; r<10; r++) {
                    // 왼손과 오른손이 같은 위치에 올 수 없음
                    if (l==r || dp[i-1][l][r] == Integer.MAX_VALUE) continue;
                    dp[i][n][r] = Math.min(dp[i][n][r], dp[i-1][l][r] + cal(n, l));
                    dp[i][l][n] = Math.min(dp[i][l][n], dp[i-1][l][r] + cal(n, r));
                }
            }
        }
        
        // 마지막 배열에서 최솟값 반환
        answer = Integer.MAX_VALUE;
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                answer = Math.min(answer, dp[nums.length-1][i][j]);
            }
        }
        
        return (answer == Integer.MAX_VALUE) ? 0 : answer;
    }
}
