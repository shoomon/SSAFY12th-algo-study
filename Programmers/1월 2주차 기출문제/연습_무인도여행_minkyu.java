import java.util.*;

public class desert_island {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] maps = { "X591X", "X1X5X", "X231X", "1XXX1" };
		List<Integer> ans = sol.solution(maps);
		System.out.println(ans.toString());
	}

	static class Solution {
		char[][] map;
		boolean[][] visited;
		int[] dr = { 1, -1, 0, 0 };
		int[] dc = { 0, 0, 1, -1 };

		public List<Integer> solution(String[] maps) {
			List<Integer> answer = new ArrayList<>();
			map = new char[maps.length][maps[0].length()];
			visited = new boolean[map.length][map[0].length];
			for (int i = 0; i < maps.length; i++) {
				map[i] = maps[i].toCharArray();
			}
			// bfs 돌면서 새로운 덩어리 크기 확인
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++)
					if (!visited[i][j] && map[i][j] != 'X')
						answer.add(bfs(i, j));
			}
			// 오름차순으로 정렬
			Collections.sort(answer);
			if (answer.size() == 0)
				answer.add(-1);
			return answer;
		}

		public int bfs(int x, int y) {
			int sum = 0;
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] { x, y });
			visited[x][y] = true;
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				sum += map[cur[0]][cur[1]] - '0';
				for (int i = 0; i < 4; i++) {
					int r = cur[0] + dr[i];
					int c = cur[1] + dc[i];
					if (r < 0 || r >= map.length || c < 0 || c >= map[0].length || visited[r][c] || map[r][c] == 'X') continue;
					visited[r][c] = true;
					q.offer(new int[] { r, c });
				}
			}
			return sum;
		}
	}
}
