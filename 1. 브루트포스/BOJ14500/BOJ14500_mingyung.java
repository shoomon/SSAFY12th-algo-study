import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_mingyung {
	// G4_테트로미노
	// 테트로미노 : 정사각형 4개를 이어 붙인 폴리오미노
	// N*M 크기의 종이, 각 칸에 정수 적혀있음
	// 테트로미노 하나를 놓고, 놓인 칸에 쓰여있는 수의 합 중 최대 구하기
	
	// 붙어있는 거 탐색하면 될 거 같아서 코드 짜봤는데
	// 일단 DFS로 풀어서 ㅗ자가 탐색이 안 돼서 답이 안나옵니다.
	// BFS 공부해서 다시 고쳐볼게요!!
	
	static int N, M, maxSum;
	static int[][] nums;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[] tmp = new int[4];
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력 첫 줄은 N, M
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st1.nextToken());
		M = Integer.parseInt(st1.nextToken());
		nums = new int[N][M];
		visited = new boolean[N][M];
		for (int r=0; r<N; r++) {
			// N개의 줄에 걸쳐 종이에 적힌 수 입력
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int c=0; c<M; c++) {
				nums[r][c] = Integer.parseInt(st2.nextToken());
			}
		} // 입력받기 완료
		
		maxSum = 0;
		for (int r=0; r<N; r++) {
			for (int c=0; c<M; c++) {
				visited[r][c] = true;
				sum(r, c, 0);
			}
		}
		
		System.out.println(maxSum);
	} // main
	
	static void sum(int r, int c, int idx) {
		if (idx==4) {
			int sum = 0;
			for (int i=0; i<4; i++) {
				sum += tmp[i];
			}
			if (maxSum < sum) {
				maxSum = sum;
			}
			return;
		}
		
		tmp[idx] = nums[r][c];
		for (int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if (nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc]) {
				visited[nr][nc] = true;
				sum(nr, nc, idx+1);
				visited[nr][nc] = false;
			}
		}
	}
}