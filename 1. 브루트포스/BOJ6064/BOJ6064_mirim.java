package 아이고아이고;

import java.util.Scanner;

public class _6064_카잉달력 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			int M = sc.nextInt();
			int N = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();

			int result = -1;
			int year = 1;

			// <x:y> => <1,1> 1번째 해로 차감하면서 구하기
			while (year < N * M) {
				x--;
				if (x < 1) { // 1보다 작아지면 M으로 숫자 초기화
					x = M;
				}
				y--;
				if (y < 1) { // 1보다 작아지면 N으로 숫자 초기화
					y = N;
				}
				year++;

//				if (x == y) {
//					year += x;
//					break;
//				}

				if (x == 1 && y == 1) {
					break;
				}
				if (year == N * M) {
					year = -1;
					break;
				}

			} // end of while

			// 출력
			if (year > 0) {
				result = year;
			}
			System.out.println(result);

		} // end of T
	} // end of main
} // end of class
