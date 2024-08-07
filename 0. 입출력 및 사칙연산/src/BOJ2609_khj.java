package AlgoStudy_12;

import java.util.Scanner;

public class BOJ2609_khj {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int a = Math.max(N, M); // 두 수 중 큰 값
		int b = Math.min(N, M); // 두 수 중 작은 값

		// greatest common divisor (최대공약수)
		int gcd = GCD(a, b);
		System.out.println(gcd);

		// least common multiple(최소공배수)
		int lcm = N * M / gcd;
		System.out.println(lcm);
	} // main

	// 유클리드 호제법 (재귀함수)
	// GCD(a,b) = GCD(b,r)
	// a와 b의 최대 공약수는 b와 r의 최대 공약수와 같다.
	private static int GCD(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return GCD(b, a % b); // 재귀함수 호출
		}
	} // GCD

}
