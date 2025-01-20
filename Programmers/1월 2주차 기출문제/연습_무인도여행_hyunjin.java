package PRO_1월_2주차;

import java.util.*;

public class 연습_무인도여행_hyunjin {
	static int n, m;
	static char[][] map;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public List<Integer> solution(String[] maps) {
		this.n = maps.length;
		this.m = maps[0].length();

		map = new char[n][m];
		visited = new boolean[n][m];

		List<Integer> answer = new ArrayList<>();

		// map 지도 만들기
		for (int i = 0; i < n; i++) {
			String str = maps[i];
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		// bfs 탐색
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 'X' && !visited[i][j]) {
					int date = search(i, j);
					answer.add(date);
				}
			}
		}

		if (answer.isEmpty()) {
			answer.add(-1);
		} else {
			answer.sort((o1, o2) -> o1 - o2);
		}
		return answer;
	}

	public static int search(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		visited[r][c] = true;

		int date = map[r][c] - '0';
		queue.add(new int[] { r, c });

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currR = curr[0];
			int currC = curr[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = currR + dr[dir];
				int nc = currC + dc[dir];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || map[nr][nc] == 'X')
					continue;

				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc });
				date += (map[nr][nc] - '0');
			}
		}
		return date;
	}
}
