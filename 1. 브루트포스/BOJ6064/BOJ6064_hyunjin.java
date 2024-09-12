package 브루트포스;

import java.util.Scanner;

// BOJ6064_카잉 달력
// 시간 초과..
public class BOJ6064_hyunjin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();

			int gcd = GCD(M, N); // 최대 공약수
			int lcm = M * N / gcd; // 최소 공배수
//			System.out.println(lcm);

			int m = 1;
			int n = 1;
			int year = 1;

			int ans = 0;

			while (!(m == x && n == y)) {
				m++;
				n++;
				year++;

				if (year > lcm) {
					ans = -1;
					break;
				}

				if (m > M)
					m = 1;

				if (n > N)
					n = 1;
			}

			int result = (ans == -1) ? -1 : year;

			System.out.println(result);

		} // tc

	} // main

	// 유클리드 호제법(최대 공약수 구하는 법)
	static int GCD(int m, int n) {
		if (n == 0) {
			return m;
		} else {
			return GCD(n, m % n);
		}
	}
}
