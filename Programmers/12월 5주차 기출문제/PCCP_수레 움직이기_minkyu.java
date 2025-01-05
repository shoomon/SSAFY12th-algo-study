import java.util.*;
import java.io.*;

public class PCCP_moving_cart {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] maze = {{1, 0, 2}, {0, 0, 0}, {5, 0 ,5}, {4, 0, 3}};
		int ans = sol.solution(maze);
		System.out.println(ans);
	}
	
	static class Solution {
		int dist;
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,1,-1};
		int row, col;
		int[][] map;
		int[] redEnd = new int[2];
		int[] blueEnd = new int[2];
		boolean[][] visitedRed, visitedBlue;
		
	    public int solution(int[][] maze) {
	        dist = Integer.MAX_VALUE;
	        
	        int[] redStart = new int[2];
	        int[] blueStart = new int[2];
	        row = maze.length;
	        col = maze[0].length;
	        map = new int[row][col];
	    	visitedRed = new boolean[row][col];
	    	visitedBlue = new boolean[row][col];
	    	
	    	//map 에서 시작, 종료 위치 받아오기
	    	for (int i = 0; i < row; i++) {
	    		for (int j = 0; j < col; j++) {
	    			switch(maze[i][j]) {
	    			// 빨강 시작
	    			case 1:
	    				redStart[0] = i;
	    				redStart[1] = j;
	    				visitedRed[i][j] = true;
	    				break;
	    			// 파랑 시작
	    			case 2:
	    				blueStart[0] = i;
	    				blueStart[1] = j;
	    				visitedBlue[i][j] = true;
	    				break;
	    			// 빨강 도착
	    			case 3:
	    				redEnd[0] = i;
	    				redEnd[1] = j;
	    				break;
	    			// 파랑 도착
	    			case 4:
	    				blueEnd[0] = i;
	    				blueEnd[1] = j;
	    				break;
	    			// 벽 정보 추가
	    			case 5:
	    				map[i][j] = 5;
	    				break;
	    			}
	    		}
	    	}
	    	recur(redStart, blueStart, 0, false, false);
	    	
	        return dist == Integer.MAX_VALUE ? 0 : dist;
	    }
	    
	    public void recur(int[] redPos, int[] bluePos, int curDist, boolean isRedEnd, boolean isBlueEnd) {
	    	if (!isRedEnd && redPos[0] == redEnd[0] && redPos[1] == redEnd[1]) isRedEnd = true;
	    	if (!isBlueEnd && bluePos[0] == blueEnd[0] && bluePos[1] == blueEnd[1]) isBlueEnd = true;
	    	
	    	// 수레 둘 다 마지막에 도착한 경우 끝
	    	if (isRedEnd && isBlueEnd) {
	    		dist = Math.min(dist, curDist);
	    		return;
	    	}
	    	
	    	// 16가지 경우의 수 중 가능한 경우만 진행할 수 있게 할 것.
	    	for (int i = 0; i < 4; i++) {
	    		int rr = redPos[0] + dr[i];
	    		int rc = redPos[1] + dc[i];
	    		if (0 > rr || rr >= row || 0 > rc || rc >= col || map[rr][rc] == 5 || visitedRed[rr][rc]) continue;
	    		for (int j = 0; j < 4; j++) {
	    			int br = bluePos[0] + dr[j];
	    			int bc = bluePos[1] + dc[j];
	    			if (0 > br || br >= row || 0 > bc || bc >= col || map[br][bc] == 5 || visitedBlue[br][bc]) continue;
	    			// 같은 위치 겹치기 방지
	    			if (rr == br && rc == bc) continue;
	    			// 서로 자리 바꾸기 X
	    			if (redPos[0] == br && redPos[1] == bc && bluePos[0] == rr && bluePos[1] == rc) continue;
	    			
	    			visitedRed[rr][rc] = true;
	    			visitedBlue[br][bc] = true;
	    			recur(new int[] {rr,rc},new int[] {br,bc}, curDist + 1, isRedEnd, isBlueEnd);
	    			visitedRed[rr][rc] = false;
	    			visitedBlue[br][bc] = false;
	    		}
	    	}
	    }
	}
}
