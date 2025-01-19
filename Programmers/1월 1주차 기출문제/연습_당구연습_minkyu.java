import java.util.*;
import java.io.*;

public class billiards {
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] balls = {{7,7},{2,7},{7,3}};
		int[] ans = sol.solution(10, 10, 3, 7, balls);
		System.out.println(Arrays.toString(ans));
	}
	
	static class Solution {
		int row, col;
	    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
	        int[] answer = new int[balls.length];
	        row = m; col = n;
	        int[] start = new int[] {startX, startY};

	        for (int i = 0; i < balls.length; i++) {
	            int[] ball = balls[i];
	            List<int[]> oppositePos = movePos(start, new int[] {ball[0], ball[1]});
	            int min = Integer.MAX_VALUE;
	            for (int[] point : oppositePos)
	                min = Math.min(min, getDist(start, point));
	            answer[i] = min;
	        }

	        return answer;
	    }

	    public List<int[]> movePos(int[] start, int[] ball) {
	        List<int[]> oppo = new ArrayList<>();
	        // 4 개의 방향으로 대칭이동
	        // 선 대칭일 때, 벽보다 공에 먼저 맞는 경우 제외
	        if(!(start[0] == ball[0] && start[1] > ball[1])) oppo.add(new int[] {ball[0], ball[1] * -1});
	        if(!(start[0] == ball[0] && start[1] < ball[1])) oppo.add(new int[] {ball[0], col + (col - ball[1])});
	        if(!(start[1] == ball[1] && start[0] < ball[0])) oppo.add(new int[] {row + (row - ball[0]), ball[1]});
	        if(!(start[1] == ball[1] && start[0] > ball[0])) oppo.add(new int[] {ball[0] * -1 , ball[1]});
	        return oppo;
	    }

	    public int getDist(int[] start, int[] ball){
	        return (int)(Math.pow(start[0] - ball[0], 2) + Math.pow(start[1] - ball[1], 2));
	    }
	}
}
