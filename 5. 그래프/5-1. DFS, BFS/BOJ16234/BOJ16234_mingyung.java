import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234_mingyung {
	// G4_인구 이동
	// 메모리 : 298,280kb
	// 시간 : 560ms
	
	/* N*N 땅에 나라는 각 한 칸 씩
	 * 국경선 공유하는 두 나라의 인구 차이가 L 이상 R 이하이면 국경선 OPEN
	 * 국경선 열리면 그 나라 모두 연합
	 * 연합 내 각 칸 인구수는 연합인구수/연합나라수, 소수점은 버림
	 * 연합 해제 후 국경선 닫음
	 * 각 나라 인구 수 주어졌을 때 인구 이동이 며칠 동한 발생하는가?
	 */
	
	static int N, L, R, union, sum, cnt, day;
	static int[][] peoples, unions;
	//                 상  하  좌  우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 땅 크기
		L = Integer.parseInt(st.nextToken()); // 인구 차이 최소 수
		R = Integer.parseInt(st.nextToken()); // 인구 차이 최대 수
		peoples = new int[N][N]; // 인구 수 배열
		for (int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				peoples[r][c] = Integer.parseInt(st.nextToken());
			}
		} // 입력받기 완료
		
		// 필요한 값 초기화
		day = 0;
		
		// 인구 이동 할 수 없을 때까지 반복
		while (true) {
			// while 돌 때 필요한 값 초기화
			union = 1;
			unions = new int[N][N];
			boolean isMoved = false;
			// Queue에 넣어서 갱신할 값 저장
//			Queue<Integer> q = new LinkedList<Integer>();
			
			// bfs 활용해 인구 이동
			for (int r=0; r<N; r++) {
				for (int c=0; c<N; c++) {
					if (unions[r][c] == 0) {
						if (bfs(r, c, union++)) isMoved = true;
//						sum = peoples[r][c]; // sum에 더해서 평균값 찾기
//						cnt = 1; // 연합에 속한 왕국 수 세기
//						unions[r][c] = union; // 방문체크
//						bfs(r, c); // bfs 돌리기
//						q.offer(sum/cnt); // 바뀐 인구수 저장
//						union++; // 다른 연합으로 만들기
					}
				}
			}

			// 아래는 시간초과 뜬 예전 코드
//			// 만약 인구이동이 불가능하다면 union은 N*N까지 증가했을 것
//			if (union == N*N+1) break;
//			
//			// 아니면 인구 이동 값 저장하기
//			int num = 1;
//			while (!q.isEmpty()) {
//				int tmp = q.poll();
//				for (int r=0; r<N; r++) {
//					for (int c=0; c<N; c++) {
//						if (unions[r][c] == num) {
//							peoples[r][c] = tmp;
//						}
//					}
//				}
//				num++;
//			}
			
			if (!isMoved) break;
			
			day++;
		}
		
		// 출력
		System.out.println(day);
	} // main
	
	// 주변을 다 확인해야 하므로 bfs가 더 적절하다 판단함
	static boolean bfs(int R, int C, int union) { // boolean으로 이동 체크
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {R, C});
		unions[R][C] = union;
		
		// 이동 후에 변경할 인구수와 위치 저장
		int sum = peoples[R][C];
		int cnt = 1;
		LinkedList<int[]> unionMems = new LinkedList<>();
		unionMems.add(new int[] {R, C});
		
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0];
			int c = tmp[1];
			
			for (int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 배열 벗어나지 않거나 방문하지 않았거나 인구차가 범위 내일 때
				if (nr>=0 && nr<N && nc>=0 && nc<N && unions[nr][nc]==0 && check(r, c, nr, nc)) {
					unions[nr][nc] = union; // 방문처리
					sum += peoples[nr][nc];
					cnt++;
					q.offer(new int[] {nr, nc});
					unionMems.add(new int[] {nr, nc});
				}
			}
		}
		
		if (cnt > 1) { // 연합 형성되면 인구 이동 수행
			int newPopulation = sum/cnt;
			for (int[] mem : unionMems) {
				peoples[mem[0]][mem[1]] = newPopulation;
			}
			return true;
		}
		
		return false;
	}
	
	// 범위 확인하는 메서드
	static boolean check(int r, int c, int nr, int nc) {
		if (Math.abs(peoples[r][c]-peoples[nr][nc]) >= L 
				&& Math.abs(peoples[r][c] - peoples[nr][nc]) <= R) return true;
		return false;
	}
}