import java.util.*;
import java.io.*;

public class maze_escape {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
		int ans = sol.solution(maps);
		System.out.println(ans);
	}
	
	static class Solution{
		char[][] board;
		int[] dr = {1,-1,0,0};
		int[] dc = {0,0,1,-1};
		int[] start = new int[2], lever = new int[2], end = new int[2];
		public int solution(String[] maps) {
			int answer = 0;
			int row = maps.length;
			int col = maps[0].length();
			board = new char[row][col];
			// board에 현황 옮기기
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					char curChar = maps[i].charAt(j);
					if (curChar == 'S') {
						start[0] = i;
						start[1] = j;
					}else if (curChar == 'L') {
						lever[0] = i;
						lever[1] = j;
					}else if (curChar == 'E') {
						end[0] = i;
						end[1] = j;
					}
					board[i][j] = curChar;
				}
			}
			
			Queue<int[]> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[row][col];
			// BFS를 통한 레버로 먼저 이동하기
			int leverCnt = -1;
			q.offer(new int[] {start[0],start[1],0});
			visited[start[0]][start[1]] = true;
			main : while(!q.isEmpty()) {
				int[] tmp = q.poll();
				for (int i = 0; i < 4; i++) {
					int r = tmp[0] + dr[i];
					int c = tmp[1] + dc[i];
					if (0<= r && r < row && 0<=c&&c<col&&board[r][c]!='X'&&!visited[r][c]) {
						if (board[r][c] == 'L') {
							leverCnt = tmp[2] + 1;
							break main;
						}
						q.offer(new int[] {r,c,tmp[2] + 1});
						visited[r][c] = true;
					}
				}
			}
			// 레버에 도달하지 못하는 경우 취소
			if (leverCnt == -1) return leverCnt;
			
			q = new ArrayDeque<>();
			visited = new boolean[row][col];
			// BFS를 통한 레버에서 출구까지 거리 알아보기
			int exitCnt = -1;
			q.offer(new int[] {lever[0],lever[1],0});
			visited[lever[0]][lever[1]] = true;
			main : while(!q.isEmpty()) {
				int[] tmp = q.poll();
				for (int i = 0; i < 4; i++) {
					int r = tmp[0] + dr[i];
					int c = tmp[1] + dc[i];
					if (0<= r && r < row && 0<=c&&c<col&&board[r][c]!='X'&&!visited[r][c]) {
						if (board[r][c] == 'E') {
							exitCnt = tmp[2] + 1;
							break main;
						}
						q.offer(new int[] {r,c,tmp[2] + 1});
						visited[r][c] = true;
					}
				}
			}
			// 출구에 도달하지 못하는 경우 취소
			if (exitCnt == -1) return exitCnt;
			
			return leverCnt + exitCnt;
		}
	}
}
