package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
29096kb, 232ms
time : 70m
 */

public class BOJ_14500 {
	static int[] dr = {0,0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static int N, M, map[][], maxtot, maxval;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		maxtot = Integer.MIN_VALUE;
		maxval = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxval = Math.max(map[i][j], maxval);
			}
		}
		boolean[][] visited = new boolean[N][M]; // visited 배열도 여러번 만들면 시간초과 뜰 수 있음
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i,j,1,map[i][j], visited);
				visited[i][j] = false;
			}
		}
		System.out.println(maxtot);
		
	}
	private static void dfs(int r, int c, int cnt, int tot, boolean[][] visited) {
		if(maxtot >= tot + maxval * (4-cnt)) return;
		
		if(cnt==4) {
			maxtot = Math.max(tot, maxtot);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr<0||nr>=N||nc<0||nc>=M||visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			dfs(nr,nc,cnt+1, tot+map[nr][nc], visited);
			
			if (cnt==2) {
				for (int j = 0; j < 4; j++) {
					int nr2 = r + dr[j];
					int nc2 = c + dc[j];
					
					if(nr2<0||nr2>=N||nc2<0||nc2>=M||visited[nr2][nc2]) continue;
					
					visited[nr2][nc2] = true;
					dfs(nr,nc,cnt+2, tot+map[nr][nc]+map[nr2][nc2], visited);
					visited[nr2][nc2] = false;
				}
			}
			
			visited[nr][nc] = false;
		}
	}
}
