
import java.util.*;
import java.io.*;

public class KAKAO_파괴되지않은건물_mingyung {
	public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        // 변화 누적합 구하기
        int n = board.length;
        int m = board[0].length;
        
        // 배열 벗어날 우려 제거하기 위해 +1로 생성
        int[][] change = new int[n+1][m+1];
        for (int i=0; i<skill.length; i++) {
            int[] cur = skill[i];
            int r1 = cur[1];
            int c1 = cur[2];
            int r2 = cur[3];
            int c2 = cur[4];
            int degree = cur[0] == 1 ? -cur[5] : cur[5];
            
            // 변화값 더해주기
            change[r1][c1] += degree;
            change[r1][c2+1] += -degree;
            change[r2+1][c1] += -degree;
            change[r2+1][c2+1] += degree;
        }
        
        // 누적합 구하기
        for (int r=0; r<n; r++) {
            for (int c=1; c<m; c++) {
                change[r][c] += change[r][c-1];
            }
        }
        
        for (int c=0; c<m; c++) {
            for (int r=1; r<n; r++) {
                change[r][c] += change[r-1][c];
            }
        }
        
        // board에 누적합 더해주기
        for (int r=0; r<n; r++) {
            for (int c=0; c<m; c++) {
                board[r][c] += change[r][c];
                if (board[r][c] > 0) answer++;
            }
        }
        
        return answer;
    }
}
