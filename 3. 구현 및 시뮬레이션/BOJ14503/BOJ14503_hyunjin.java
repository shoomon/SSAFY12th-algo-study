package algoTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ14503_로봇 청소기
// 메모리 : 11852KB
// 시간 : 68ms
public class BOJ14503_hyunjin {
	static int N, M, startR, startC, cnt;
	static int dir;
	static int[][] board;
	static boolean[][] visited;

	// 북, 동, 남, 서
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로 길이
		M = Integer.parseInt(st.nextToken()); // 가로 길이

		st = new StringTokenizer(br.readLine());
		startR = Integer.parseInt(st.nextToken()); // 로봇 청소기의 시작 R 지점
		startC = Integer.parseInt(st.nextToken()); // 로봇 청소기의 시작 C 지점
		dir = Integer.parseInt(st.nextToken()); // 창소기 초기 방향

		board = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(startR, startC, dir); // 로봇 청소기 시작 지점에서 출발
		System.out.println(cnt);

	}

	static void dfs(int currR, int currC, int dir) {
		if(!visited[currR][currC]) {
			// 방문하지 않은 곳이라면 청소 진행
			visited[currR][currC] = true; // 시작 지점 청소 완료
			cnt++; // 청소 count++
		}

		for (int i = 0; i < 4; i++) {
			// 주변에 청소 가능한 칸이 있는 경우,
			dir = (dir + 3) % 4; // 반시계 방향으로 90도 회전하기

			int nr = currR + dr[dir];
			int nc = currC + dc[dir];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || board[nr][nc] == 1) {
				continue;
			}

			// 90도 회전한 칸이 청소 가능한 칸이면 재귀 함수 넣기
			dfs(nr, nc, dir);
			return; // 한 방향으로 탐색완료 했다면 다른 방향 탐색 
		}

		//	현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우,
		int back = (dir + 2) % 4; // 후진하기
		int br = currR + dr[back]; // 후진 했을 때 R
		int bc = currC + dc[back]; // 후진 했을 때 C

		if (br >= 0 && br < N && bc >= 0 && bc < M && board[br][bc] != 1) {
			dfs(br, bc, dir);
		}else {
			return; // 더이상 후진 할 수 없다면 return
		}

	}
}