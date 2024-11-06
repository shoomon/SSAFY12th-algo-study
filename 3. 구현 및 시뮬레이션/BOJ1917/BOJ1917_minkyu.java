import java.util.*;
import java.io.*;

/*
정육면체 전개도

해당 전개도가 정육면체가 될 수 있는지 파악하라

메모리 : 11840 KB
시간 : 72 ms

*/

public class Main {
	// 주사위 면에 따른 상 우 하 좌 방향 설정
	static int[][] diceFace = {{5,2,4,3},{5,3,4,2},{5,1,4,0},{5,0,4,1},{0,2,1,3},{1,2,0,3}};
	// 상우하좌 (시계방향 순으로 정렬)
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 0; tc < 3; tc++) {
			// 주사위 보드 정리
			int[][] board = new int[6][6];
			for (int i = 0; i < 6; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 6; j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}
			
			Queue<int[]> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[6][6];
			
			int[] faceCnt = new int[6];
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					if (board[i][j] == 1 && !visited[i][j]) {
						// 0, 1 : 현재 위치 행렬, 2 : 현재 어디 면인지, 3 : 어떤 방향에서 왔는지, 4 : 어떤 면에서 왔는지
						q.offer(new int[] {i,j,0,0,5});
						visited[i][j] = true;
						faceCnt[0]++;
						while(!q.isEmpty()) {
							int[] tmp = q.poll();	
							
							int originDir = 0;
							// 해당 면이 원래 어떤 방향에 해당하는지 구하기
							for (int k = 0; k < 4; k++) {
								if (diceFace[tmp[2]][k] == tmp[4]) {
									originDir = k;
									break;
								}
							}
							int adjustDir = (tmp[3] - originDir + 4) % 4;
							for (int k = 0; k < 4; k++) {
								int curDir = (k + adjustDir) % 4;
								int r = tmp[0] + dr[curDir];
								int c = tmp[1] + dc[curDir];
								if (0<=r&&r<6&&0<=c&&c<6&&!visited[r][c]&&board[r][c]==1) {
									// 상하 좌우 반대 방향이 다음 
									int dir = (curDir + 2) % 4;
									q.offer(new int[] {r,c,diceFace[tmp[2]][k], dir, tmp[2]});
									visited[r][c] = true;
									faceCnt[diceFace[tmp[2]][k]]++;
								}
							}
						}
					}
				}
			}
			
			boolean isPossible = true;
			for (int i = 0; i < 6; i++) {
				if (faceCnt[i] == 0) {
					isPossible = false;
					break;
				}
			}
			
			if (isPossible)
				System.out.println("yes");
			else
				System.out.println("no");
			
		}
	}
}