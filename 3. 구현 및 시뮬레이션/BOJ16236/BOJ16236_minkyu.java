import java.util.*;
import java.io.*;

/*
아기 상어

크기가 같거나 큰 먹이를 먹을때, 아기 상어가 먹이를 먹지 못하게 되었을때,
아기 상어가 이동한 경로의 길이를 구하시오.

메모리 : 18556KB
시간 : 200ms
*/

public class Main {
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		// 상어 현재 위치 기록

		int[] curPos = new int[2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 상어 위치 받아놓기
				if (map[i][j] == 9) {
					curPos[0] = i;
					curPos[1] = j;
					map[i][j] = 0;
				}
			}
		}

		int sharkSize = 2;
		// 물고기 먹은 수
		int eatCnt = 0;
		// 움직인 총 거리
		int dist = 0;

		// 조건을 만족할때까지 계속 반복
		while (true) {
			// 조건을 적을 우선순위 큐
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->{
				if (o1[2] != o2[2])
					return Integer.compare(o1[2], o2[2]);
				else {
					if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
					else return Integer.compare(o1[1], o2[1]);
				}
			});

			// 현재 위치를 기준으로 BFS 탐색 진행하여 먹이를 먹으러 가야하는 위치 파악
			boolean[][] visited = new boolean[N][N];
			// 0 = r, 1 = c, 2 = 거리
			pq.add(new int[] { curPos[0], curPos[1], 0 });
			visited[curPos[0]][curPos[1]] = true;
			// 상어가 먹이를 먹었는지 체크
			boolean hasAte = false;
			while (!pq.isEmpty()) {
				// 먹이 위치로 상어 이동
				curPos = pq.poll();
				// 먹이가 있으면서 상어의 사이즈보다 작은 경우 먹이를 먹음
				if (map[curPos[0]][curPos[1]] != 0 && map[curPos[0]][curPos[1]] < sharkSize) {
					map[curPos[0]][curPos[1]] = 0;
					eatCnt++;
					dist += curPos[2]; // 움직인 거리를 총 움직인 거리에 추가
					hasAte = true; // 먹이 먹었다고 체크
					break;
				}

				for (int k = 0; k < 4; k++) {
					int r = curPos[0] + dr[k];
					int c = curPos[1] + dc[k];
					if (r < 0 || c < 0 || c >= N || r >= N || visited[r][c] || map[r][c] > sharkSize)
						continue;
					pq.add(new int[] { r, c, curPos[2] + 1 });
					visited[r][c] = true;
				}
			}

			// 물고기를 먹을 수 없어 엄마를 찾아야 한다.
			if (!hasAte)
				break;

			// 사이즈와 먹이를 먹은 수가 동일하다면 상어의 크기를 증가
			if (sharkSize == eatCnt) {
				sharkSize++;
				eatCnt = 0;
			}
		}

		System.out.println(dist);
	}

}