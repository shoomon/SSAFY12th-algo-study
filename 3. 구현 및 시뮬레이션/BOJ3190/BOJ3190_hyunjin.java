package 코테준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// BOJ3190_뱀

public class BOJ3190_hyunjin {
	static int N, K, r, c, L, X, C;
	static int[][] board;

	// 뱀의 시작 위치를 Deque에 넣기
	// Deque을 사용하는 이유 => 머리 : addFirst로 앞쪽에 추가, 꼬리 : removeLast로 뒤쪽 꼬리 제거 가능
	static Deque<int[]> snake = new ArrayDeque<>();

	// 오른쪽 아래 왼쪽 위
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static int direction = 0; // 처음에는 오른쪽 방향으로 이동 시작

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 보드의 크기 N
		K = Integer.parseInt(br.readLine()); // 사과의 개수

		board = new int[N][N];

		// 사과의 위치
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1; // 사과 위치 r좌표
			c = Integer.parseInt(st.nextToken()) - 1; // 사과 위치 c좌표

			board[r][c] = 1;
		}

		L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
		int[] timeArr = new int[L];
		char[] dirArr = new char[L];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			timeArr[i] = Integer.parseInt(st.nextToken()); // 게임이 시작한 시간 X초
			// 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전
			dirArr[i] = st.nextToken().charAt(0);

		}

		System.out.println(moveSnake(timeArr, dirArr)); // 게임 진행한 시간

	}

	static int moveSnake(int[] timeArr, char[] dirArr) {
		snake.addFirst(new int[] { 0, 0 }); // 시작 위치 (0,0)
		int time = 0; // 게임 시작한 시간 // timeArr랑 만나는 경우 방향 전환
		int dirIndex = 0; // 현재 처리할 방향 전환의 인덱스 == 몇 번 방향 전환을 했는지 확인

		while (true) {
			time++;

			int[] head = snake.peekFirst();

			int newR = head[0] + dr[direction];
			int newC = head[1] + dc[direction];

			// 벽에 부딪히거나 자기 몸에 부딪히면 게임 종료
			// 자기 몸통에 부딪힌 경우
			if (newR < 0 || newR >= N || newC < 0 || newC >= N || isSnake(newR, newC)) {
				return time; // 충돌하면 게임 종료 == 게임이 진행한 시간 return
			}

			snake.addFirst(new int[] { newR, newC });

			// 사과가 있으면 먹고, 사과가 없으면 꼬리 제거
			if (board[newR][newC] == 1) {
				board[newR][newC] = 0; // 사과 먹어서 없앰
			}
			// 사과가 없으면 꼬리 제거 == Deque의 마지막 제거
			else {
				snake.removeLast(); // 꼬리 제거
			}

			// 시간이 흘러서 방향 전환해야할 때
			// ex) dirIndex는 0,1,2
			if (dirIndex < L && time == timeArr[dirIndex]) {
				// 'L' 왼쪽으로 90도 회전
				if (dirArr[dirIndex] == 'L') {
					direction = (direction + 3) % 4;
				}
				// 'D' 오른쪽으로 90도 회전
				else if (dirArr[dirIndex] == 'D') {
					direction = (direction + 1) % 4;
				}
				dirIndex++; // 방향 전환에 완료했으면 index 값 +1
			}

		}

	}

	// 자기 몸통에 부딪힌 경우
	static boolean isSnake(int newR, int newC) {
		for (int[] snakePos : snake) {
			if (snakePos[0] == newR && snakePos[1] == newC)
				return true;
		}
		return false;
	}

}