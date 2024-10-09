import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
나무 베기

RC카를 이용해 나무를 베어나가며 목적지까지 가기 위한 최소한의 명령 수를 출력

메모리 : 21180 KB
시간 : 145 ms

*/

public class Solution {
	static int N;
	static int K;
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static char[][] map = {};
	static boolean[][] visited = {};
	
	static int[] curPos = {};
	static int[] lastPos = {};
	
	static int minMove;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new char[N][N];
			curPos = new int[2];
			lastPos = new int[2];
			for (int i = 0; i < N; i++) {
				String curLine = br.readLine();
				for (int j = 0; j < N; j++) {
					char curChar = curLine.charAt(j);
					map[i][j] = curChar;
					if (curChar == 'X') {
						curPos[0] = i;
						curPos[1] = j;
					}else if (curChar == 'Y') {
						lastPos[0] = i;
						lastPos[1] = j;
					}
				}
			}
			minMove = -1;
			visited = new boolean[N][N];
			visited[curPos[0]][curPos[1]] = true;
			dfs(0, 0, K);
			
			// 마지막 위치 출력하기
			System.out.printf("#%d %d\n",tc,minMove);
		}
	}
	
	public static void dfs(int actCnt, int curDir, int cuttableCnt) {
		// 만약 최종위치와 같다면 해당 위치로부터의 최소 이동값을 파악할 수 있다.
		if (curPos[0] == lastPos[0] && curPos[1] == lastPos[1]) {
			if (minMove == -1)
				minMove = actCnt;
			else if (minMove > actCnt)
				minMove = actCnt;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int r = curPos[0] + dr[i];
			int c = curPos[1] + dc[i];
			if (0<=r&&r<N&&0<=c&&c<N&&!visited[r][c]) {
				int rotateCnt = getDirection(curDir, i);
				if (map[r][c] == 'G' || map[r][c] == 'Y') {
					curPos[0] += dr[i];
					curPos[1] += dc[i];
					visited[r][c] = true;
					dfs(actCnt + rotateCnt + 1, i, cuttableCnt);
					curPos[0] -= dr[i];
					curPos[1] -= dc[i];
					visited[r][c] = false;
				}else if (map[r][c] == 'T' && cuttableCnt > 0) {
					curPos[0] += dr[i];
					curPos[1] += dc[i];
					visited[r][c] = true;
					dfs(actCnt + rotateCnt + 1, i, cuttableCnt - 1);
					curPos[0] -= dr[i];
					curPos[1] -= dc[i];
					visited[r][c] = false;
				}
			}
		}
	}
	
	public static int getDirection(int dir, int tar) {
		if (dir == tar) return 0;
		if ((dir + tar) % 2 == 0) return 2;
		else return 1;
	}
}