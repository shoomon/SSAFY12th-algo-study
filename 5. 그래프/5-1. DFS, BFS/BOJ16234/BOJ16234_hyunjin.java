package 코테준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* BOJ16234_인구이동
시간 : 524 ms
메모리 : 296204 KB
*
*/

public class BOJ16234_hyunjin {
	static int N, L, R;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0; // 정답

		// 더이상 인구 이동이 일어나지 않을때까지 반복
		while (true) {
			boolean isMove = false;
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						if (bfs(i, j)) {
							isMove = true;
						}
					}
				}
			}

			// 더이상 인구 이동을 진행하지 않으면,
			if (!isMove) {
				System.out.println(ans); // 답 출력
				break; // 무한루프 빠져나가기
			}

			// 인구이동이 계속 가능하다면, ans ++ 해주기
			ans++;
		}

	}

	static boolean bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		List<int[]> list = new ArrayList<>();

		queue.add(new int[] { r, c }); // bfs 돌면서 이동 가능한 Node queue에 넣기
		list.add(new int[] { r, c }); // 인구 이동할 Node 몇 개인지 -> list에 추가
		visited[r][c] = true;
		int sum = board[r][c];

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currR = curr[0];
			int currC = curr[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = currR + dr[dir];
				int nc = currC + dc[dir];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
					continue;

				int diff = Math.abs(board[nr][nc] - board[currR][currC]);
				if (L <= diff && diff <= R) {
					queue.add(new int[] { nr, nc });
					list.add(new int[] { nr, nc });
					sum += board[nr][nc];
					visited[nr][nc] = true;
				}
			}

		}

		int avg = sum / list.size(); // 인구 이동 후, 각 노드별 인구 수

		// list 내의 모든 Node를 돌면서, 새로운 인구로 바꾸기
		if (list.size() > 1) {
			for (int[] arr : list) {
				board[arr[0]][arr[1]] = avg;
			}
			return true; // 인구 이동이 발생
		}
		return false; // 인구 이동이 발생하지 않음 
	}

}
