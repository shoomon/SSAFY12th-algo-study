import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
탈주범 검거

지하 터널로 숨어든 탈주범을 잡기 위해
지하 터널로 숨었을때 탈주범의 예상 위치 파악

메모리 : 26960 KB
시간 : 184 ms

*/

public class Solution {
	static int rowSize;
	static int colSize;
	static int[] holePos = new int[2];
	static int time;
	static int[][] sewer = {};
	static int[][] getTime = {};
	static int[][] dr = {{},{-1,1,0,0},{-1,1},{0,0},{-1,0},{1,0},{1,0},{-1,0}};
	static int[][] dc = {{},{0,0,-1,1},{0,0},{-1,1},{0,1},{0,1},{0,-1},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			holePos[0] = Integer.parseInt(st.nextToken());
			holePos[1] = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			sewer = new int[rowSize][colSize];
			for (int r = 0; r < rowSize; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < colSize; c++) {
					sewer[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			getTime = new int[rowSize][colSize];
			bfs(holePos);
//			showTimeArr();
			int cnt = 0;
			for (int r = 0; r < rowSize; r++) {
				for (int c = 0; c < colSize; c++) {
					int curTime = getTime[r][c];
					if (0<curTime&&curTime<=time)
						cnt++;
				}
			}
			
			System.out.printf("#%d %d\n",tc,cnt);
		}
	}
	
	public static void showTimeArr() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				System.out.print(getTime[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void bfs(int[] st) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {st[0],st[1],1});
		getTime[st[0]][st[1]] = 1;
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int curType = sewer[tmp[0]][tmp[1]];
			for (int i = 0; i < dr[curType].length; i++) {
				int curR = tmp[0] + dr[curType][i];
				int curC = tmp[1] + dc[curType][i];
				if (0<=curR&&curR<rowSize&&0<=curC&&curC<colSize&&sewer[curR][curC]!=0&&getTime[curR][curC]==0) {
					if (!canConnect(curType, sewer[curR][curC], i))
						continue;
					q.offer(new int[] {curR, curC, tmp[2] + 1});
					getTime[curR][curC] = tmp[2] + 1;
				}
			}
		}
	}
	
	public static boolean canConnect(int type, int type2, int idx) {
		boolean isPossible = false;
		int curdr = -dr[type][idx];
		int curdc = -dc[type][idx];
		for (int i = 0; i < dr[type2].length; i++) {
			int tdr = dr[type2][i];
			int tdc = dc[type2][i];
			if (dr[type2][i] == curdr && dc[type2][i] == curdc)
				isPossible = true;
		}
		return isPossible;
	}
}