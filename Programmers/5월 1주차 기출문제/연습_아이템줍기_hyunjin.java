package PRO_5월_1주차;

import java.util.*;

class Solution {

	static int[][] map = new int[101][101]; // 0 : 빈공간, 1 : 내부, 2 : 테두리 => 2를 따라서 bfs 탐색
	static boolean[][] visited = new boolean[101][101];
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		int answer = 0;

		// 사각형 테두리 그리기
		for (int[] r : rectangle) {
			int x1 = r[0] * 2;
			int y1 = r[1] * 2;
			int x2 = r[2] * 2;
			int y2 = r[3] * 2;
			draw(x1, y1, x2, y2);
		}

		answer = bfs(characterY * 2, characterX * 2, itemY * 2, itemX * 2);
		return answer;
	}

	// 사각형 그리기
	// y축 => row / x축 => col
	private void draw(int x1, int y1, int x2, int y2) {
		for (int i = y1; i <= y2; i++) {
			for (int j = x1; j <= x2; j++) {
				if (map[i][j] == 1)
					continue; // 내부는 그대로 두기
				map[i][j] = 1; // 내부로 채우기

				// 테두리일 경우, 다시 덮어쓰기
				if (i == y1 || i == y2 || j == x1 || j == x2) {
					map[i][j] = 2;
				}
			}
		}
	}

	// bfs 테두리 따라 이동하기
	private int bfs(int startY, int startX, int endY, int endX) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { startY, startX, 0 }); // 위치 x, y, 거리 정보
		visited[startY][startX] = true;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currY = curr[0];
			int currX = curr[1];
			int dist = curr[2];

			if (currY == endY && currX == endX) {
				return dist / 2; // 2배로 크기를 늘렸으므로
			}

			for (int dir = 0; dir < 4; dir++) {
				int ny = currY + dy[dir];
				int nx = currX + dx[dir];

				if (ny < 0 || ny >= 101 || nx < 0 || nx >= 101 || visited[ny][nx])
					continue;

				if (map[ny][nx] != 2)
					continue; // 테두리로만 이동가능

				visited[ny][nx] = true;
				queue.add(new int[] { ny, nx, dist + 1 });
			}
		}
		return 0;
	}
}