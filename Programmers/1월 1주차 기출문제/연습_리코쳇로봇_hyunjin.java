package PRO_1월_1주차;

import java.util.*;

public class 연습_리코쳇로봇_hyunjin {
	public static void main(String[] args) {
		String[] board = { "...D..R", ".D.G...", "....D.D", "D....D.", "..D...." };
		연습_리코쳇로봇_hyunjin sol = new 연습_리코쳇로봇_hyunjin();
		System.out.println(sol.solution(board));
	}

	static int n, m;
	static int startR = 0, startC = 0;
	static int endR = 0, endC = 0;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public int solution(String[] board) {
		n = board.length;
		m = board[0].length();

		map = new char[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {
				map[i][j] = board[i].charAt(j);
				// 시작 지점 
				if (map[i][j] == 'R') {
					startR = i;
					startC = j;
				// 도착 지점 
				} else if (map[i][j] == 'G') {
					endR = i;
					endC = j;
				}
			}
		}

		int cnt = move(startR, startC);
		return cnt;
	}

	private int move(int startR, int startC) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { startR, startC, 0 });
		visited[startR][startC] = true;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currCnt = curr[2];

			if (curr[0] == endR && curr[1] == endC)
				return currCnt;

			for (int i = 0; i < 4; i++) {
				int dir = i;
				int currR = curr[0];
				int currC = curr[1];

				// 한 방향이 끝날때까지 이동
				while (true) {
					int nr = currR + dr[dir];
					int nc = currC + dc[dir];

					// 벽을 만나면, 방향 이동
					if (nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 'D') {
						break;
					}
					currR = nr;
					currC = nc;
				}
				// 원래 위치랑 같으면 -> 방향 변경
				if (currR == curr[0] && currC == curr[1])
					continue;
				if (visited[currR][currC])
					continue;

				queue.add(new int[] { currR, currC, currCnt + 1 });
				visited[currR][currC] = true;
			}

		}
		return -1;
	}

}
