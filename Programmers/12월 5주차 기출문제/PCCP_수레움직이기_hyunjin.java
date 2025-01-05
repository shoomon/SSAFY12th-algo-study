package PRO_12월_5주차;

import java.util.*;

public class PCCP_수레움직이기_hyunjin {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int n, m;
	static boolean[][] visited;

	// 수레의 시작 위치와 도착 위치를 저장
	static int[] redStart = null, blueStart = null, redEnd = null, blueEnd = null;

	public int solution(int[][] maze) {
		this.n = maze.length;
		this.m = maze[0].length;

		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (maze[i][j] == 1)
					redStart = new int[] { i, j };
				else if (maze[i][j] == 2)
					blueStart = new int[] { i, j };
				else if (maze[i][j] == 3)
					redEnd = new int[] { i, j };
				else if (maze[i][j] == 4)
					blueEnd = new int[] { i, j };
			}
		}

		int answer = bfs(maze);

		return answer;
	}

	private int bfs(int[][] maze) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { redStart[0], redStart[1], blueStart[0], blueStart[1], 0 }); // red, blue R, C 위치 저장, 이동 횟수
		visited[redStart[0]][redStart[1]] = true;
		visited[blueStart[0]][blueStart[1]] = true;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int redR = curr[0];
			int redC = curr[1];
			int blueR = curr[2];
			int blueC = curr[3];
			int moveCnt = curr[4];

			// 도착 지점에 도달한 경우,
			if (redR == redEnd[0] && redC == redEnd[1] && blueR == blueEnd[0] && blueC == blueEnd[1]) {
				return moveCnt;
			}

			for (int dir = 0; dir < 4; dir++) {
				int newRedR = redR + dr[dir];
				int newRedC = redC + dc[dir];
				int newBlueR = blueR + dr[dir];
				int newBlueC = blueC + dc[dir];

				// 경계밖 조건 제외
				if (newRedR < 0 || newRedR >= n || newRedC < 0 || newRedC >= m || maze[newRedR][newRedC] == 5
						|| visited[newRedR][newRedC])
					continue;
				if (newBlueR < 0 || newBlueR >= n || newBlueC < 0 || newBlueC >= m || maze[newBlueR][newBlueC] == 5
						|| visited[newBlueR][newBlueC])
					continue;

				// 둘이 동시에 같은 위치에 있는 경우 제외
				if (newRedR == newBlueR && newRedC == newBlueC)
					continue;

				// 한 수레가 이미 도착 지점에 도착했다면,
				if (redR == redEnd[0] && redC == redEnd[1]) {
					newRedR = redR;
					newRedC = redC;
				}

				if (blueR == blueEnd[0] && blueC == blueEnd[1]) {
					newBlueR = blueR;
					newBlueC = blueC;
				}

				// 이동가능하다면,
				visited[newRedR][newRedC] = true;
				visited[newBlueR][newBlueC] = true;
				queue.add(new int[] { newRedR, newRedC, newBlueR, newBlueC, moveCnt + 1 });

			}

		}

		return 0;

	}

}
