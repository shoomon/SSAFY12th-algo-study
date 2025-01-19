package PRO_1월_2주차;

import java.util.*;

public class 연습_미로탈출_hyunjin {
	static int n, m;
	static char[][] map;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public int solution(String[] maps) {
		int answer = 0;
		n = maps.length;
		m = maps[0].length();
		map = new char[n][m];

		int startX = 0, startY = 0;
		int leverX = 0, leverY = 0;

		for (int i = 0; i < n; i++) {
			String str = maps[i];
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					startX = i;
					startY = j;
				} else if (map[i][j] == 'L') {
					leverX = i;
					leverY = j;
				}
			}
		}

		// 1. 시작점 -> 레버까지의 최단거리
		int startToLever = bfs(startX, startY, 'L');
		if (startToLever == -1)
			return -1; // 레버까지 도착 못 함

		// 2. 레버 -> 도착점까지의 최단거리
		int leverToEnd = bfs(leverX, leverY, 'E');
		if (leverToEnd == -1)
			return -1;

		answer = startToLever + leverToEnd;
		return answer;
	}

	private int bfs(int r, int c, char target) {
		boolean[][] visited = new boolean[n][m]; // boolean 배열 초기화
		Queue<int[]> queue = new LinkedList<>();

		visited[r][c] = true;
		queue.add(new int[] { r, c, 0 });

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currR = curr[0];
			int currC = curr[1];
			int time = curr[2];

			if (map[currR][currC] == target)
				return time;

			for (int dir = 0; dir < 4; dir++) {
				int nr = currR + dr[dir];
				int nc = currC + dc[dir];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || map[nr][nc] == 'X')
					continue;

				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc, time + 1 });
			}
		}
		return -1;
	}
}
