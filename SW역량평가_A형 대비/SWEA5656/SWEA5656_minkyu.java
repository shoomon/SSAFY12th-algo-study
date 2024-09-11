import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int throwCnt;
	static int minBlock;
	static int width;
	static int height;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			throwCnt = Integer.parseInt(st.nextToken());
			minBlock = Integer.MAX_VALUE;
			width = Integer.parseInt(st.nextToken());
			height = Integer.parseInt(st.nextToken());
			List<Integer>[] arr = new ArrayList[width];
			for (int i = 0; i < width; i++)
				arr[i] = new ArrayList<>();

			for (int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < width; j++) {
					int curVal = Integer.parseInt(st.nextToken());
					if (curVal != 0) {
						arr[j].add(curVal);
					}
				}
			}
			// 위 아래 순서 바꾸기
			for (int i = 0; i < width; i++) {
				List<Integer> originalList = new ArrayList<>();
				for (int j = arr[i].size() - 1; j >= 0; j--) {
					originalList.add(arr[i].get(j));
				}
				arr[i] = originalList;
			}

			proceedThrow(arr, 0);
			System.out.printf("#%d %d\n", tc, minBlock);
		}
	}

	public static void proceedThrow(List<Integer>[] arr, int curCnt) {
		if (curCnt == throwCnt) {
			int cnt = 0;
			for (int i = 0; i < arr.length; i++)
				cnt += arr[i].size();
			
//			checkArr(arr);

			if (minBlock > cnt)
				minBlock = cnt;

			return;
		}

		// 모든 너비 쪽에 한번씩 던져보기
		boolean hasThrown = false;
		for (int i = 0; i < arr.length; i++) {
			// 없는 곳에는 던지지 않기
			if (arr[i].size() == 0)
				continue;
			
			hasThrown = true;
			
			// 방문 여부 확인용 배열 생성
			boolean[][] hasVisited = new boolean[height][width];
			// 비어있는 곳은 방문처리
			for (int j = 0; j < width; j++) {
				for (int k = arr[j].size(); k < height; k++)
					hasVisited[k][j] = true;
			}
			// 복사 배열 선언
			List<Integer>[] copyArr = new ArrayList[width];
			for (int j = 0; j < width; j++)
				copyArr[j] = new ArrayList<>();
			
			// BFS 탐색으로 해당 회차에 부서져야할 블럭들 설정
			Queue<int[]> q = new ArrayDeque<>();
			// 던진 위치의 행, 열의 초기 값을 더한다.
			q.offer(new int[] { arr[i].size() - 1, i });
			hasVisited[arr[i].size() - 1][i] = true;
			while (!q.isEmpty()) {
				int[] tmp = q.poll();
				int curFinder = arr[tmp[1]].get(tmp[0]);
				for (int j = 1; j < curFinder; j++) {
					for (int k = 0; k < 4; k++) {
						int curR = tmp[0] + dr[k] * j;
						int curC = tmp[1] + dc[k] * j;
						if (0 <= curC && curC < width && 0 <= curR && curR < arr[curC].size()
								&& !hasVisited[curR][curC]) {
							q.offer(new int[] { curR, curC });
							hasVisited[curR][curC] = true;
						}
					}
				}
			}

			// 해당 리스트 그대로 복사
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < arr[j].size(); k++) {
					// 방문안된 곳만 남아있음
					if (!hasVisited[k][j])
						copyArr[j].add(arr[j].get(k));
				}
			}

			
//			checkArr(copyArr);
			proceedThrow(copyArr, curCnt + 1);
		}
		
		if (!hasThrown) {
			minBlock = 0;
			return;
		}
	}
	
	public static void checkArr(List<Integer>[] arr) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (j < arr[i].size()) {
					System.out.print(arr[i].get(j));
				}else {
					System.out.print(0);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}