import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16236_mingyung {
	// G3_아기 상어
	// 메모리 : 14,724 kb
	// 시간 : 116 ms
	
	// N*N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다.
	// 아기 상어의 크기는 2, 1초에 상하좌우로 인접한 한 칸씩 이동
	// 자신보다 큰 물고기가 있는 칸은 못 지나감
	// 크기가 같으면 먹을 순 없지만 지나갈 순 있음
	// 크기가 작으면 먹을 수 있음
	// 거리가 가장 가까운, 그 중에서 위쪽, 그 중에서 가장 왼쪽 물고기 먹음
	// 더 이성 먹을 수 있는 물고기 없으면 엄마 상어에게 도움 요청
	// 엄마 상어에게 도움 요청하지 않고 몇 초 동안 물고기를 잡아먹을 수 있는가?
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		int[] cur = new int[3]; // 아기상어 위치 저장, while문 돌 때 위치 변경 필요하므로 한 변수 사용
		for (int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					cur[0] = r;
					cur[1] = c;
					map[r][c] = 0; // 지나갈 수 있도록 처리!
				}
			}
		} // 입력받기 완
		
		// 필요한 변수 초기화
		int size = 2;
		int eat = 0;
		int move = 0;
		//          상  좌  우  하
		int[] dr = {-1, 0, 0, 1};
		int[] dc = {0, -1, 1, 0};
		
		// 이동하면서 물고기 먹기
		while (true) {
			// 먼저 갈 자리 선정 위해 PriorityQueue 사용
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
				o1[2] != o2[2] ? o1[2]-o2[2] : 
					(o1[0] != o2[0] ? o1[0]-o2[0] : o1[1]-o2[1])
			);
			
			boolean[][] vis = new boolean[N][N];
			vis[cur[0]][cur[1]] = true; // 방문처리
			pq.add(new int[] {cur[0], cur[1], 0});
			boolean ck = false; // 먹었는지 체크
			
			while (!pq.isEmpty()) {
				cur = pq.poll();
				int r = cur[0];
				int c = cur[1];
				int curr = cur[2];
				
				// 만약 먹을 수 있으면 먹고 다음 먹이 찾게 while 탈출
				if (map[r][c] != 0 && map[r][c] < size) {
					map[r][c] = 0;
					eat++;
					// 먹었는데, 크기만큼 먹었으면 size 업
					if (size == eat) {
						size++;
						eat = 0;
					}
					move += curr; // 움직인 거리 추가
					ck = true;
					break;
				}
				
				// 먹은 게 아니라면 계속 이동
				for (int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(nr>=0 && nr<N && nc>=0 && nc<N && !vis[nr][nc] && map[nr][nc] <= size) {
						vis[nr][nc] = true;
						pq.add(new int[] {nr, nc, curr+1});
					}
				}
			}
			
			// 큐가 비워졌는데 먹이를 먹지 않았다면 더이상 못먹음
			if (!ck) break;
		}
		
		// 출력하기
		System.out.println(move);
	}
}
