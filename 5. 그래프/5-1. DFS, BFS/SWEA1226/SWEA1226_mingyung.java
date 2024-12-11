import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA1226_mingyung {
	// SWEA1226_SW문제해결기본_미로1_Pass
	// 결과 : Pass
	// 메모리 : 17,424 kb
	// 시간 : 138 ms
	
	// 16*16 행렬 형태로 만들어진 미로
	// 미로의 출발점부터 도착지점까지 갈 수 있는 길이 있는지 판단하는 프로그램
	
	static int[][] map = new int[16][16]; // 미로
	static int sR, sC, fR, fC; // 출발점 r, c, 도착점 r, c
	static boolean[][] vis; // 방문체크 배열
	static boolean ans; // 도달할 수 있는지 체크
	// 돌기 위한 델타배열  상  하  좌  우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		// 첫 번째 줄에 테스트케이스 번호
		// 다음 줄에 테스트 케이스 주어짐
		// 10개의 테스트케이스
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc=1; tc<=10; tc++) {
			br.readLine(); // 테스트케이스 번호
			// 미로 입력받기
			for(int r=0; r<16; r++) {
				String str = br.readLine();
				for (int c=0; c<16; c++) {
					map[r][c] = str.charAt(c)-'0';
					// 입력받으면서 출발점, 도착점 r, c 갱신
					if(map[r][c]==2) {
						sR = r;
						sC = c;
					}
				}
			} // 입력받기 완
			
			// 필요한 변수 초기화
			vis = new boolean[16][16];
			ans = false;
			bfs(sR, sC);
			
			// 출력하기
			if (ans) {
				System.out.println("#"+tc+" "+1);				
			} else {
				System.out.println("#"+tc+" "+0);
			}
		} // tc
	} // main
	
	static void bfs(int sR, int sC) {		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {sR, sC});
		vis[sR][sC] = true;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0];
			int c = tmp[1];
			
			for (int d=0; d<4; d++) {
				int nr = tmp[0] + dr[d];
				int nc = tmp[1] + dc[d];
				
				// 배열 벗어나지 않고 다음 칸으로 이동 가능한지 확인하여 q에 넣기
				if (nr>=0 && nr<16 && nc>=0 && nc<16 && map[nr][nc]==0 && !vis[nr][nc]) {
					q.add(new int[] {nr, nc});
					vis[nr][nc] = true;
				}
				
				// 목적지에 도착했으면 ans true 만들고 return
				if (map[nr][nc]==3) {
					ans = true;
					return;
				}
			}
		}
	}
}