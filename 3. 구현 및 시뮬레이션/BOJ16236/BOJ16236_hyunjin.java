package 코테준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 시간 : 152 ms
 * 메모리 : 21896 KB
 */

// BOJ16236_아기 상어 (골3)
public class BOJ16236_hyunjin {
	static int N;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int sharkSize = 2; // 초기 아기 상어 사이즈

	static class Pos implements Comparable<Pos> {
		int r, c, dist;

		Pos(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.dist == o.dist) { // 거리가 같다면
				if (this.r == o.r) { // 같은 행에 위치 한다면,
					return this.c - o.c; // 열 번호 비교
				}
				return this.r - o.r; // 행 번호 비교

			}
			return this.dist - o.dist; // 거리 비교
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int sharkR = 0;
		int sharkC = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 9) {
					// 상어의 시작 위치 잡기
					sharkR = i;
					sharkC = j;
					board[i][j] = 0; // 상어 시작 위치를 0으로 변경
				}
			}
		}

		int totalTime = 0; // 이동한 거리 == 이동 시간 
		int eatenFishSize = 0; // 먹은 물고기 크기
		
		// 더이상 먹을 물고기가 없을 때까지, 반복 
		while (true) {
			Pos target = bfs(sharkR, sharkC); // 먹을 물고기의 위치 탐색 
			if (target == null)
				break; // 더 이상 먹을 물고기가 없다

			// 상어가 이동한 위치
			sharkR = target.r;
			sharkC = target.c;
			totalTime += target.dist;
			board[sharkR][sharkC] = 0; // 상어가 이동한 위치 다시 0으로

			eatenFishSize++;
			if (eatenFishSize == sharkSize) {
				sharkSize++;
				// 사이즈가 커졌다면, 먹은 물고기 양 다시 0 초기화 
				eatenFishSize = 0;
			}
		}

		System.out.println(totalTime);

	}

	// 아기 상어가 먹을 수 있는 먹이 위치 찾기
	static Pos bfs(int r, int c) {
		Queue<Pos> queue = new LinkedList<>(); // 이동 가능한 물고기의 모든 위치 확인
		List<Pos> fishList = new ArrayList<>(); // 물고기 정렬 가능

		boolean[][] visited = new boolean[N][N];
		queue.add(new Pos(r, c, 0));

		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Pos curr = queue.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nr = curr.r + dr[dir];
				int nc = curr.c + dc[dir];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
					continue;

				if (board[nr][nc] > sharkSize)
					continue; // 물고기가 상어보다 크다면 continue

				visited[nr][nc] = true;
				// 물고기가 있으면서 && 상어보다 작은 물고기 == 상어가 먹을 수 있는 물고기 => list에 넣기
				if (board[nr][nc] > 0 && board[nr][nc] < sharkSize) {
					fishList.add(new Pos(nr, nc, curr.dist + 1));

				}
				queue.add(new Pos(nr, nc, curr.dist + 1));
			}

		} // while

		// 먹을 물고기가 없다면, null 반환
		if (fishList.isEmpty())
			return null;

		// 먹을 물고기가 있다면, 물고기 중 가장 가까운 물고기 거리 순으로 정렬
		Collections.sort(fishList);
		return fishList.get(0); // 가장 가까운 물고기부터 반환
	}
}
