import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
구슬 탈출 2

중력이 작용하는 보드에서 파란 구슬을 빠뜨리지 않으면서 빨간 구슬을 구멍으로 넣는 가장 빠른 횟수를 입력하세요.

메모리 : 81820 KB
시간 : 244 ms

 */

public class Main {
	static int rSize;
	static int cSize;
	static char[][] board = {};
	static int[] holePos = {};

	static int minCnt;

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rSize = Integer.parseInt(st.nextToken());
		cSize = Integer.parseInt(st.nextToken());

		int[] rPos = new int[2];
		int[] bPos = new int[2];
		holePos = new int[2];

		board = new char[rSize][cSize];
		for (int i = 0; i < rSize; i++) {
			String curLine = br.readLine();
			for (int j = 0; j < cSize; j++) {
				char curChar = curLine.charAt(j);
				if (curChar == 'R') {
					rPos[0] = i;
					rPos[1] = j;
					board[i][j] = '.';
				} else if (curChar == 'B') {
					bPos[0] = i;
					bPos[1] = j;
					board[i][j] = '.';
				} else if (curChar == 'O') {
					holePos[0] = i;
					holePos[1] = j;
					board[i][j] = curChar;
				} else
					board[i][j] = curChar;
			}
		}

		// 기본을 마이너스로 하고 변경되는 경우에만 변경하도록 진행
		minCnt = -1;
		tilt(0, rPos, bPos);
		System.out.println(minCnt);
	}

	public static void tilt(int cnt, int[] rPos, int[] bPos) {
		if (cnt == 10)
			return;

		cnt++;
		// 기울임 방향 설정
		for (int i = 0; i < 4; i++) {

			// 여기서 움직이고 결정
			boolean hasConnected = false;
			boolean redFallen = false;
			boolean blueFallen = false;

			// 빨간 공 먼저 움직여보기
			int rR = rPos[0];
			int rC = rPos[1];
			// 벽에 부딪히거나 공이 구멍에 빠지거나 파란색을 만나기 전까지 움직이기
			while (true) {
				int nextrR = rR + dr[i];
				int nextrC = rC + dc[i];

				if (board[nextrR][nextrC] == '#')
					break;
				// 공이 구멍에 빠진경우
				else if (board[nextrR][nextrC] == 'O') {
					redFallen = true;
					rR = -1;
					rC = -1;
					break;
					// 다음 움직일 위치가 파란색과 동일한 경우
				} else if (nextrR == bPos[0] && nextrC == bPos[1]) {
					hasConnected = true;
					break;
				}

				rR = nextrR;
				rC = nextrC;
			}

			// 파란 공 마저 움직여보기
			int bR = bPos[0];
			int bC = bPos[1];
			// 벽에 부딪히거나 공이 구멍에 빠지기 전까지 움직이기
			while (true) {
				int nextbR = bR + dr[i];
				int nextbC = bC + dc[i];

				if (board[nextbR][nextbC] == '#')
					break;
				// 빨간색이 움직인 위치와 동일하다면 뒤로 한칸 이동 후 고정
				else if (nextbR == rR && nextbC == rC)
					break;
				// 파란색 공이 구멍에 빠진 경우 잘못된 경우이므로 리턴
				else if (board[nextbR][nextbC] == 'O') {
					blueFallen = true;
					break;
				}

				// 새롭게 옮기기
				bR = nextbR;
				bC = nextbC;
				// 빨간색이 파란색 뒤에 붙은 경우 따라 붙기
				if (hasConnected) {
					rR = rR + dr[i];
					rC = rC + dc[i];
				}
			}

			if (redFallen && !blueFallen) {
				if (minCnt == -1)
					minCnt = cnt;
				else if (minCnt > cnt)
					minCnt = cnt;
				return;
			}

			// 구멍에 빠지지 않은 경우 새로운 위치를 가지고 다시 움직이기
			if (!redFallen && !blueFallen)
				tilt(cnt, new int[] { rR, rC }, new int[] { bR, bC });
		}
	}
}