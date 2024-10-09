import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 얼음판 크기
	static int boardSize;
	// 얼음판
	static int[][] board = {};
	static int[][] tmpArr = {};
	// 마법 횟수
	static int magicCnt;
	// 마법으로 회전하는 방향 설정
	static int[] magicDr = { 0, 1, 0, -1 };
	static int[] magicDc = { 1, 0, -1, 0 };
	// 상하좌우 얼음 존재 여부 파악 방향 설정
	static int[] iceDr = { -1, 1, 0, 0 };
	static int[] iceDc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		magicCnt = Integer.parseInt(st.nextToken());
		boardSize = 1 << N;
		board = new int[boardSize][boardSize];
		tmpArr = new int[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < magicCnt; i++) {
			// 현재 마법 단계
			int curMagicLevel = Integer.parseInt(st.nextToken());

			int magicSize = 1 << curMagicLevel;

			// 마법 구현
			spellMagic(boardSize, magicSize, 0, 0);
			// 얼음 값 줄이기 구현
			reduceIce();
		}

		int iceCnt = 0;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				iceCnt += board[i][j];
			}
		}
		System.out.println(iceCnt);
		System.out.println(findPackage());
	}

	public static void spellMagic(int curLevel, int magicSize, int startRow, int startCol) {
		// 마법 크기 1인 경우 회전 의미 없음
		if (magicSize == 1)
			return;
		int curSize = curLevel / 2;
		// 마법의 크기가 현재 잘려진 크기와 동일한 경우
		if (curLevel == magicSize) {
			// 마법에 따라 90도 회전을 실시한다.
			for (int i = 0; i < curLevel; i++) {
				for (int j = 0; j < curLevel; j++)
					tmpArr[j+startRow][curLevel - 1 - i+startCol] = board[i+startRow][j+startCol];
			}

			// 임시 객체 갈아끼우기
			for (int i = startRow; i < startRow + curLevel; i++) {
				for (int j = startCol; j < startCol + curLevel; j++) {
					board[i][j] = tmpArr[i][j];
				}
			}

			return;
		}

		// 해당 마법레벨이 아닌 경우 4등분해서 다시 마법 시전 레벨까지 내려감
		spellMagic(curSize, magicSize, startRow, startCol);
		spellMagic(curSize, magicSize, startRow, startCol + curSize);
		spellMagic(curSize, magicSize, startRow + curSize, startCol);
		spellMagic(curSize, magicSize, startRow + curSize, startCol + curSize);
	}

	// 마법 발생시킨 이후 얼음의 양을 조절함
	public static void reduceIce() {
		// 감소시키는 곳의 위치를 저장해놓음
		boolean[][] tmp = new boolean[boardSize][boardSize];

		// 해당 위치에 얼음을 감소시켜야하는지 여부
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				// 인접한 얼음의 개수
				int cnt = 0;
				// 4방 탐색
				for (int k = 0; k < 4; k++) {
					// 탐색 범위가 보드 안인 경우
					if (0 <= i + iceDr[k] && i + iceDr[k] < boardSize && 0 <= j + iceDc[k]
							&& j + iceDc[k] < boardSize) {
						// 탐색 범위에 얼음이 존재하는 경우
						if (board[i + iceDr[k]][j + iceDc[k]] != 0) {
							cnt++;
						}
					}
				}

				if (cnt < 3)
					tmp[i][j] = true;
			}
		}

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (tmp[i][j]) {
					// 0보다 큰 경우 얼음을 감소시킨다.
					if (board[i][j] > 0)
						board[i][j]--;
				}
			}
		}
	}

	public static int findPackage() {
		// BFS 방식으로 탐색

		// 탐색 여부 확인
		boolean[][] hasFound = new boolean[boardSize][boardSize];

		// 얼음이 없는 경우 탐색할 필요가 없음
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == 0)
					hasFound[i][j] = true;
			}
		}

		boolean isNotFound = false;
		int maxCnt = 0;
		while (!isNotFound) {
			// 못 찾은 그룹이 없을때까지 반복
			isNotFound = true;
			int curCnt = 0;
			main: for (int i = 0; i < boardSize; i++) {
				for (int j = 0; j < boardSize; j++) {
					// 얼음이 있는데 탐색이 안된 곳 찾아서 BFS 진행
					if (!hasFound[i][j]) {
						Queue<int[]> q = new ArrayDeque<>();
						q.offer(new int[] { i, j });
						hasFound[i][j] = true;
						curCnt = 1;
						while (!q.isEmpty()) {
							int[] tmp = q.poll();
							// 4방 탐색
							for (int k = 0; k < 4; k++) {
								// 범위 안에 들어오는 경우만 탐색 진행
								if (0 <= tmp[0] + iceDr[k] && tmp[0] + iceDr[k] < boardSize && 0 <= tmp[1] + iceDc[k]
										&& tmp[1] + iceDc[k] < boardSize
										&& !hasFound[tmp[0] + iceDr[k]][tmp[1] + iceDc[k]]) {
									q.offer(new int[] { tmp[0] + iceDr[k], tmp[1] + iceDc[k] });
									hasFound[tmp[0] + iceDr[k]][tmp[1] + iceDc[k]] = true;
									curCnt++;
								}
							}
						}
						if (maxCnt < curCnt)
							maxCnt = curCnt;
						break main;
					}
				}
			}
		}

		return maxCnt;
	}
}