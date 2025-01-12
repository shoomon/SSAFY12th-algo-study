import java.util.*;
import java.io.*;

public class 연습_당구연습_mingyung {
	public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        // balls 배열 돌기
        for (int i=0; i<balls.length; i++) {
            answer[i] = Integer.MAX_VALUE;
            // 왼쪽 당구대 부딪히기
            // 직선상일 때 startX보다 balls[i][0]이 작으면 못때리니 제외하기
            // (int)로 형변환 해주기!
            if (!(startY==balls[i][1] && startX>balls[i][0])) {
                answer[i] = (int) Math.min(answer[i], Math.pow(startX + balls[i][0], 2) + Math.pow(balls[i][1]-startY, 2));
            }
            
            // 위쪽 당구대 가로m 세로n
            if (!(startX==balls[i][0] && startY<balls[i][1])) {
                answer[i] = (int) Math.min(answer[i], Math.pow(startX - balls[i][0], 2) + Math.pow(2*n - balls[i][1] - startY, 2));
            }
            
            // 오른쪽
            if (!(startY==balls[i][1] && startX<balls[i][0])) {
                answer[i] = (int) Math.min(answer[i], Math.pow(2*m - startX - balls[i][0], 2) + Math.pow(balls[i][1]-startY, 2));
            }
            
            // 아래쪽
            if (!(startX==balls[i][0] && startY>balls[i][1])) {
                answer[i] = (int) Math.min(answer[i], Math.pow(startX - balls[i][0], 2) + Math.pow(balls[i][1] + startY, 2));
            }
        }
        
        return answer;
    }
}
