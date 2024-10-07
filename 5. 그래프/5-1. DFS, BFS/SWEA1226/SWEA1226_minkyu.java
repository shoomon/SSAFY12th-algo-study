import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
미로 1

2로 표기된 시작점으로 부터 3으로 표기된 도착점까지 도착 가능 여부를 출력

메모리 : 19432 KB
시간 : 111 ms

*/

public class Solution {
	static int N = 16;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[] startPos = new int[2];
			for (int i = 0; i < N; i++) {
				String curLine = br.readLine();
				for (int j = 0; j < N; j++) {
					int curVal = curLine.charAt(j) - '0'; 
					map[i][j] = curVal;
					if (curVal == 2) {
						startPos[0] = i;
						startPos[1] = j;
					}
				}
			}
			
			boolean isPossible = false;
			Queue<int[]> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];
			q.offer(startPos);
			visited[startPos[0]][startPos[1]] = true;
			main : while(!q.isEmpty()) {
				int[] tmp = q.poll();
				for (int i = 0; i < 4; i++) {
					int r = tmp[0] + dr[i];
					int c = tmp[1] + dc[i];
					if (map[r][c] != 1 && !visited[r][c]) {
						if (map[r][c] == 3) {
							isPossible = true;
							break main;
						}
						q.offer(new int[] {r,c});
						visited[r][c] = true;
					}
				}
			}
			
			if (isPossible)
				System.out.printf("#%d %d\n",T,1);
			else
				System.out.printf("#%d %d\n",T,0);
		}
	}
}