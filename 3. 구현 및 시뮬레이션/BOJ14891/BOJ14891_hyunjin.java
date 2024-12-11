package algoTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ14891_톱니바퀴
// 메모리 :11804KB
// 시간 :68ms

public class BOJ14891_hyunjind {

	static int[][] wheel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 초기 톱니바퀴의 상태를 저장하는 배열
		wheel = new int[4][8]; // 총 4개의 톱니바퀴, 8 방향의 상태

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = str.charAt(j) - '0';
			}
		}
//		System.out.println();
//		System.out.println(Arrays.deepToString(wheel));

		int K = Integer.parseInt(br.readLine()); // 회전 수

		// 톱니 회전 시키기
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int wheelNum = Integer.parseInt(st.nextToken()) - 1; // 회전하는 톱니바퀴 번호
			int rotationDir = Integer.parseInt(st.nextToken()); // 회전 방향 => 시계 방향 : 1, 반시계 : -1

			// 각 톱니바퀴가 회전을 하는지 안 하는지 체크
			int[] rotationWheel = new int[4]; // 각 톱니바퀴가 어느 방향으로 회전하는지 저장
			rotationWheel[wheelNum] = rotationDir;

			// 왼쪽에 있는 톱니바퀴들의 회전 여부 확인
			for (int j = wheelNum - 1; j >= 0; j--) {
				if (wheel[j][2] != wheel[j + 1][6]) {
					rotationWheel[j] = -rotationWheel[j + 1]; // 물려 있는 톱니바퀴의 방향과 반대로 회전
				} else {
					break;
				}
			}

			// 오른쪽에 있는 톱니바퀴들의 회전 여부 확인
			for (int j = wheelNum + 1; j < 4; j++) {
				if (wheel[j - 1][2] != wheel[j][6]) {
					rotationWheel[j] = -rotationWheel[j - 1]; // 물려 있는 톱니바퀴의 방향과 반대로 회전
				} else {
					break;
				}
			}

//			System.out.println(Arrays.toString(rotationWheel));

			// 각 톱니바퀴를 돌리기
			// 어느 방향으로 돌릴지 넘겨주기
			for (int num = 0; num < 4; num++) {
				if (rotationWheel[num] != 0) {
					rotation(rotationWheel[num], num);
//					System.out.println(Arrays.deepToString(wheel));
				}
			}

		} // 톱니 회전

//		System.out.println("최종 톱니 바퀴 상태");
//		System.out.println(Arrays.deepToString(wheel));

		// 정답 구하기
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			if (wheel[i][0] == 0) {
				ans += 0;
			} else {
				ans += Math.pow(2, i);
			}
		}
		System.out.println(ans);

	} // main

	// 톱니바퀴 회전 방향
	static void rotation(int dir, int wheelNum) {
		// 회전 방향 1 : 시계 방향
		if (dir == 1) {
			int tmp = wheel[wheelNum][7];
			for (int i = 7; i > 0; i--) {
				// 값을 한 칸씩 뒤로 밀기
				wheel[wheelNum][i] = wheel[wheelNum][i - 1];
			}
			wheel[wheelNum][0] = tmp;

		} // 회전 방향 -1 : 반시계 방향
		else if (dir == -1) {
			int tmp = wheel[wheelNum][0];
			for (int i = 0; i < 7; i++) {
				// 다음 바퀴의 값을 앞으로 한 칸 옮기기
				wheel[wheelNum][i] = wheel[wheelNum][i + 1];
			}
			wheel[wheelNum][7] = tmp; // 마지막 값은 다시 0번째 인덱스 값 넣어주기
		}
	} // rotation

}