import java.util.*;
import java.io.*;

/*
치킨 배달

정해진 수의 치킨집을 선택하여 치킨 거리의 최소값을 구하시오.

메모리 : 294952 KB
시간 : 668 ms

*/

public class Main {
	static int N;
	static int M;
	static List<int[]> chick = new ArrayList<>();
	static Set<Integer> selected = new HashSet<>();
	static int[][] map = {};
	
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int curVal =Integer.parseInt(st.nextToken());
				map[i][j] = curVal;
				// 치킨 집 위치 저장
				if (curVal == 2)
					chick.add(new int[] {i,j});
			}
		}
		
		// 치킨 집 고르기
		recur(0);
		System.out.println(min);
	}
	
	public static void recur(int idx) {
		// M개의 치킨 집을 전부 고른 경우
		if (selected.size() == M) {
			Queue<int[]> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];
			// 선택된 치킨집으로 BFS 돌리기
			for (int i : selected) {
				int r = chick.get(i)[0];
				int c = chick.get(i)[1];
				q.offer(new int[] {r,c, 0});
				visited[r][c] = true;
			}
			
			int sum = 0;
			while(!q.isEmpty()) {
				int[] tmp = q.poll();
				for (int i = 0; i < 4; i++) {
					int r = tmp[0] + dr[i];
					int c = tmp[1] + dc[i];
					if (0<=r&&r<N&&0<=c&&c<N&&!visited[r][c]) {
						if (map[r][c] == 1) sum += tmp[2] + 1;
						visited[r][c] = true;
						q.offer(new int[] {r,c,tmp[2]+1});
					}
				}
			}
			
			min = Math.min(min, sum);
		}
		
		// 선택을 아직 못했지만 인덱스가 넘어간 경우
		if (idx == chick.size())
			return;
		
		// 선택 안하고 넘어가는 경우
		recur(idx + 1);
		// 선택 하고 넘어가는 경우
		selected.add(idx);
		recur(idx + 1);
		selected.remove(idx);
	}
}