import java.util.*;
import java.io.*;

/*
탈옥

탈옥시 가장 문을 적게 열고 탈출할 수 있는 문의 수를 구하시오.

메모리 : 184724 KB
시간 : 736ms
*/

public class Main {
	static int h, w;
	static char[][] map;
	static int[][] prisonerPos;
	static boolean[][] visitMap;
	static int[][] costMap;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		StringBuilder result = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			// 외부 시작점에서도 bfs를 돌려 해당 지점까지의 최솟값 구하기
			map = new char[h + 2][w + 2];
			costMap = new int[h + 2][w + 2];
			visitMap = new boolean[h+2][w+2];
			prisonerPos = new int[2][2];
			int prisonCnt = 0;
			for (int i = 1; i <= h; i++) {
				String curLine = br.readLine();
				for (int j = 1; j <= w; j++) {
					char curChar = curLine.charAt(j - 1);
					map[i][j] = curChar;
					if (curChar == '$') {
						prisonerPos[prisonCnt][0] = i;
						prisonerPos[prisonCnt][1] = j;
						prisonCnt++;
					}
				}
			}

			// 밖에서 문들까지의 거리 비용 추가
			bfs(new int[] { 0, 0 });
			bfs(prisonerPos[0]);
			bfs(prisonerPos[1]);

			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (!visitMap[i][j]) continue;
					if (map[i][j] == '#')
						ans = Math.min(ans, costMap[i][j] - 2);
					else
						ans = Math.min(ans, costMap[i][j]);
				}
			}

			result.append(ans).append("\n");
		}
		System.out.println(result);
	}

	public static void bfs(int[] startPos) {
		int[][] minCosts = new int[h + 2][w + 2];
		for (int i = 0; i < (h + 2); i++) {
			for (int j = 0; j < (w + 2); j++)
				minCosts[i][j] = Integer.MAX_VALUE;
		}
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { startPos[0], startPos[1], 0 });
		minCosts[startPos[0]][startPos[1]] = 0;
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			for (int i = 0; i < 4; i++) {
				int r = tmp[0] + dr[i];
				int c = tmp[1] + dc[i];
				if (0 <= r && r < (h + 2) && 0 <= c && c < (w + 2) && map[r][c] != '*') {
					int curCost = tmp[2];
					if (map[r][c] == '#') curCost++;
					if (minCosts[r][c] <= curCost) continue;
					visitMap[r][c] = true;
					minCosts[r][c] = curCost;
					q.offer(new int[] { r, c, curCost });
				}
			}
		}
		
		for (int i = 0; i < (h + 2); i++) {
			for (int j = 0; j < (w + 2); j++) {
				if (minCosts[i][j] == Integer.MAX_VALUE) continue;
				costMap[i][j] += minCosts[i][j];
			}
		}
	}
}