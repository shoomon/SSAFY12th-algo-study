package PRO_12월_4주차;

import java.util.*;

/*
 * PCCP_2_석유 시추 
 * 
 */
public class PCCP_석유시추_hyunjin {
	public static void main(String[] args) {
		int[][] land = { { 0, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 0, 0, 0, 1, 1, 0 },
				{ 1, 1, 1, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 1, 1 } };
		PCCP_석유시추_hyunjin sol = new PCCP_석유시추_hyunjin();
		System.out.println(sol.solution(land));
	}

	static int n, m;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[] oil; // 시추관 위치별 석유량 저장

	public int solution(int[][] land) {
		int answer = 0;

		n = land.length; // 세로 길이
		m = land[0].length; // 가로 길이

		oil = new int[m]; // 석유가 있는 열 => 석유량 더해가는 배열 
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (land[i][j] == 1 && !visited[i][j]) {
					bfs(i, j, land);

				}
			}
		}

//		System.out.println(Arrays.toString(oil));
		// 전체 열 중에서 석유 가장 많이 시추할 수 있는 위치 뽑기 
		for (int i = 0; i < oil.length; i++) {
			answer = Math.max(answer, oil[i]);
		}
		return answer;
	}

	private static void bfs(int r, int c, int[][] land) {
		Queue<int[]> queue = new LinkedList<>();
		visited[r][c] = true;
		queue.add(new int[] { r, c });

		int size = 1;
		// 석유가 있는 덩어리의 열 위치를 저장
		// 중복을 피하기 위해서 set 자료 구조 사용
		// ex) 3,4,5,4,5,5,6 => 3,4,5,6 저장
		Set<Integer> oilSet = new HashSet<>();

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currR = curr[0];
			int currC = curr[1];

			// 현재 열 번호 set에 추가하기
			oilSet.add(currC);

			for (int dir = 0; dir < 4; dir++) {
				int nr = currR + dr[dir];
				int nc = currC + dc[dir];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || land[nr][nc] != 1)
					continue;

				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc });
				size++;

			}
		}

		// ex) 3,4,5,6 인덱스 자리에 size 추가해주기
		for (int idx : oilSet) {
			oil[idx] += size;
		}

	}
}
