
import java.util.*;
import java.io.*;

public class 연습_2차원동전뒤집기_mingyung {
	public int solution(int[][] beginning, int[][] target) {
        int answer = Integer.MAX_VALUE;
        int n = beginning.length;
        int m = beginning[0].length;
        
        // 행 뒤집을 조합 비트마스크로 표현
        for (int rowMask=0; rowMask<(1<<n); rowMask++) {
            // 행 뒤집기 위해 깊은 복사
            int[][] flipped = new int[n][m];
            for (int r=0; r<n; r++) {
                flipped[r] = Arrays.copyOf(beginning[r], m);
            }
            
            // 행 뒤집기
            for (int r=0; r<n; r++) {
                if ( (rowMask&(1<<r)) != 0) {
                    flipRow(flipped, r);
                }
            }
            
            // 열 뒤집기 판단
            int colMask = 0;
            for (int c=0; c<m; c++) {
                boolean needFlip = false;
                for (int r=0; r<n; r++) {
                    if (flipped[r][c] != target[r][c]) {
                        needFlip = true;
                        break;
                    }
                }
                if (needFlip) {
                    colMask |= (1<<c);
                    flipCol(flipped, c);
                }
            }
            
            // 결과 확인
            boolean isSame = true;
            here:
            for (int r=0; r<n; r++) {
                for (int c=0; c<m; c++) {
                    if (flipped[r][c] != target[r][c]) {
                        isSame = false;
                        break here;
                    }
                }
            }
            
            if (isSame) {
                int count = Integer.bitCount(rowMask) + Integer.bitCount(colMask);
                answer = Math.min(answer, count);
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    // 행 전체 뒤집기
    void flipRow(int[][] arr, int row) {
        for (int c=0; c<arr[0].length; c++) {
            arr[row][c] ^= 1;
        }
    }
    
    // 열 전체 뒤집기
    void flipCol(int[][] arr, int col) {
        for (int r=0; r<arr.length; r++) {
            arr[r][col] ^= 1;
        }
    }
}
