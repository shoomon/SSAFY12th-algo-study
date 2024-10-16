package codingTest;
import java.io.*;
import java.util.*;

// 84m
// 296420KB	852ms
public class BOJ14502 {
	static int N, M, map[][], maxCnt;
	static StringTokenizer st;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static int[][] nowMap;
	static ArrayList<loc> starts = new ArrayList<loc>();
	// 최대 횟수 64C3 = (64*65*63) / 6 = 21504
	// 바이러스가 최대로 퍼지는 횟수 21504 * 8
	
	static class loc{
		int r;
		int c;
		
		public loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = s.charAt(0) - '0';
		M = s.charAt(2) - '0';
		
		// 초기화
		map = new int[N][M];
		maxCnt = 0;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					starts.add(new loc(i,j));
				}
			}
		}
//		printMap(map);
		dfs(0);
		
		System.out.println(maxCnt);
		// 0 : 빈 칸, 1: 벽, 2: 바이러스
		// 바이러스가 다 퍼졌을 때 안전 영역(0)의 크기가 최댓값
		// 바이러스는 상하좌우로 퍼져나갈 수 있음
	}
	
	public static void dfs(int wall) {
		if(wall == 3) {
			// 벽 개수 무조건 3개
			bfs();
			return;
		}
		
		// 중복
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(wall+1);
					map[i][j] = 0;
				} 
			}
		}
	}
	
	public static void bfs() {
		boolean[][] visited = new boolean[N][M];
		nowMap = copyMap(map);
		
		// 초기값 q에 넣기
		Queue<loc> q = new ArrayDeque<loc>();
		for (loc s : starts) {
			q.add(s);
			visited[s.r][s.c] = true;
		}
		
		while(!q.isEmpty()) {
			loc now = q.poll();
			
			for (int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
//				System.out.println("M : "+M+", N : "+N);
//				System.out.println("nr : "+nr+", nc : "+nc);
				// 맵 범위를 넘거나, 벽이거나, 이미 방문했다면
				if (nr<0||nr>=N||nc<0||nc>=M||nowMap[nr][nc]==1||visited[nr][nc]) {
					continue;
				}
				
				// 바이러스 확장
				visited[nr][nc] = true;
				nowMap[nr][nc] = 2;
				q.add(new loc(nr,nc));
			}
		}
		// bfs q가 더이상 업데이트 되지 않을 때, 0의 개수
		int cnt = 0;
		for (int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if (nowMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		maxCnt = Math.max(cnt, maxCnt);
	}
	
	public static int[][] copyMap(int[][] orgMap){
		int[][] resMap = new int[N][M];
		for (int i=0; i<N; i++) {
			resMap[i] = orgMap[i].clone();		
			}
		return resMap;
	}
	
	public static void printMap(int[][] map) {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
	
}
