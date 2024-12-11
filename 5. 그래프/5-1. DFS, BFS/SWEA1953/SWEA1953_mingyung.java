import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953_mingyung {
	// SWEA1953_SW문제해결응용_탈주범검거
	// 결과 : Pass
	// 메모리 : 25,580 kb
	// 시간 : 136 ms
	
	// 탈주범 탈출한 지 한 시간 뒤,
	// 맨홀 뚜껑 통해 지하 터널의 한 지점으로 들어가 터널 어딘가에서 은신
	// 터널끼리 연결되어 있는 경우 이동 가능 => 있을 수 있는 위치 계산
	// 탈주범은 시간당 1의 거리 움직일 수 있음
	
	static int N, M, R, C, L, ans; // 세로, 가로 크기(N, M), 위치(R,C), 시간L, 답ans
	static int[][] map; // 지하터널 지도
	static boolean[][] vis; // 방문체크 배열
	//                 상  하  좌  우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] dir = {
			{-1},  // 0 터널 없음
			{0, 1, 2, 3},  // 1 상, 하, 좌, 우
			{0, 1},  // 2 상, 하
			{2, 3},  // 3 좌, 우
			{0, 3},  // 4 상, 우
			{1, 3},  // 5 하, 우
			{1, 2},  // 6 하, 좌
			{0, 2}   // 7 상, 좌
	};
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		// 첫 줄은 테스트케이스 개수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			// 세로 크기 N, 가로 크기 M, 맨홀 뚜껑 위치 R, C, 소요시간 L
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			for (int r=0; r<N; r++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				for (int c=0; c<M; c++) {
					map[r][c] = Integer.parseInt(st2.nextToken());
				}
			} // 입력받기 완
			
			// 필요한 변수 초기화
			vis = new boolean[N][M];
			ans = 1;
			check();
			
			// 출력하기
			System.out.println("#"+tc+" "+ans);
		} // tc
	} // main
	
	static void check() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {R, C, 1}); // r, c, time
		vis[R][C] = true;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0];
			int c = tmp[1];
			int t = tmp[2];
			// 소요시간이 다 됐으면 더 움직이지 않기
			if (t==L) continue;
			
			// 이동해야 할 델타배열 가져와서 이동
			int[] arr = dir[map[r][c]];
			for (int d=0; d<arr.length; d++) {
				int nr = r+dr[arr[d]];
				int nc = c+dc[arr[d]];
				
				// 방문하지 않았고, 다음 칸과 연결되어 있을 때 이동
				if (nr>=0 && nr<N && nc>=0 && nc<M && !vis[nr][nc] && map[nr][nc] != 0 && isCon(r, c, nr, nc)) {
					q.add(new int[] {nr, nc, t+1});
					vis[nr][nc] = true;
					ans++;
				}
			} // dir
		} // while
	} // check
	
	// 다음칸과 연결되어 있는지 확인
	static boolean isCon(int r, int c, int nr, int nc) {
		int p = map[nr][nc];
		// 새 파이프에서 이동 가능한 방향 체크
		for (int d=0; d<dir[p].length; d++) {
			int nd = dir[p][d];
			// 현재 방향으로 연결되어 있다면
			if (nr+dr[nd] == r && nc+dc[nd] == c) {
				return true;
			}
		}
		return false;
	} // isCon
}