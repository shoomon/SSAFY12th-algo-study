package AlgoStudy_12;

import java.util.Scanner;

public class BOJ1978_khj {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// test case
		int N = sc.nextInt();
		int ans = 0;

		for (int tc = 0; tc < N; tc++) {
			// 입력
			int num = sc.nextInt();
			int cnt = 0;

			for (int i = 1; i <= num; i++) {
				if (num % i == 0) {
					cnt++;
				}
			}

			if (cnt == 2) {
				ans++;
			}
			continue;

		} // tc
		System.out.println(ans);
	} // main
}
