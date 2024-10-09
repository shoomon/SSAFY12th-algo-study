import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int boardSize;
	static int[][] board = {};
	static List<int[]> chips;
	static int maxCnt;
	static int minLength;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			boardSize = Integer.parseInt(br.readLine());
			board = new int[boardSize][boardSize];
			chips = new ArrayList<>();
			maxCnt = 0;
			minLength = Integer.MAX_VALUE;
			for (int i = 0; i < boardSize; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < boardSize; j++) {
					int curInfo = Integer.parseInt(st.nextToken());
					board[i][j] = curInfo;
					// 연결이 필요한 칩만 리스트로 생성
					if (i != 0 && i != boardSize - 1 && j != 0 && j != boardSize - 1 && curInfo == 1)
						chips.add(new int[] { i, j });
				}
			}

			connectProcessor(0, 0, 0);
			System.out.printf("#%d %d\n", tc, minLength);
		}
	}

	public static void checkCurState(int idx) {
		System.out.println(idx + "번째-------------------------------");
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void connectProcessor(int idx, int cnt, int length) {
		if (idx == chips.size()) {
			if (maxCnt < cnt) {
				maxCnt = cnt;
				minLength = length;
			} else if (maxCnt == cnt) {
				if (minLength > length)
					minLength = length;
			}
			return;
		}

//		checkCurState(idx);

		// 상하좌우 연결 가능성 탐색
		int[] tmp = chips.get(idx);
		// 현재 행보다 위에 장애물이 있는지 없는지 파악
		boolean isPossible = true;
		for (int i = 0; i < tmp[0]; i++) {
			if (board[i][tmp[1]] != 0) {
				isPossible = false;
				break;
			}
		}
		// 상 가능한 경우
		if (isPossible) {
			for (int i = 0; i < tmp[0]; i++)
				board[i][tmp[1]] = 1;
			connectProcessor(idx + 1, cnt + 1, length + tmp[0]);
			for (int i = 0; i < tmp[0]; i++)
				board[i][tmp[1]] = 0;
		}

		// 좌 가능한지 확인
		isPossible = true;
		for (int i = 0; i < tmp[1]; i++) {
			if (board[tmp[0]][i] != 0) {
				isPossible = false;
				break;
			}
		}
		// 좌 가능한 경우
		if (isPossible) {
			for (int i = 0; i < tmp[1]; i++)
				board[tmp[0]][i] = 1;
			connectProcessor(idx + 1, cnt + 1, length + tmp[1]);
			for (int i = 0; i < tmp[1]; i++)
				board[tmp[0]][i] = 0;
		}
		// 하 가능한지 확인
		isPossible = true;
		for (int i = tmp[0] + 1; i < boardSize; i++) {
			if (board[i][tmp[1]] != 0) {
				isPossible = false;
				break;
			}
		}
		// 하 가능한 경우
		if (isPossible) {
			for (int i = tmp[0] + 1; i < boardSize; i++)
				board[i][tmp[1]] = 1;
			connectProcessor(idx + 1, cnt + 1, length + boardSize - tmp[0] - 1);
			for (int i = tmp[0] + 1; i < boardSize; i++)
				board[i][tmp[1]] = 0;
		}
		// 우 가능한지 확인
		isPossible = true;
		for (int i = tmp[1] + 1; i < boardSize; i++) {
			if (board[tmp[0]][i] != 0) {
				isPossible = false;
				break;
			}
		}
		// 우 가능한 경우
		if (isPossible) {
			for (int i = tmp[1] + 1; i < boardSize; i++)
				board[tmp[0]][i] = 1;
			connectProcessor(idx + 1, cnt + 1, length + boardSize - tmp[1] - 1);
			for (int i = tmp[1] + 1; i < boardSize; i++)
				board[tmp[0]][i] = 0;
		}

		// 아무 곳도 연결할 수 없는 경우는 그냥 넘어간다.
		connectProcessor(idx + 1, cnt, length);
	}
}