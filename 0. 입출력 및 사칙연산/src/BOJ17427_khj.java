package AlgoStudy_12;

import java.util.Scanner;

public class BOJ17427_khj {
	// 자연수 A와 B 존재
	// A = BC 만족하는 자연수 C를 A의 약수
	// g(N) = f(1) + ... + f(N)

	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		// f(N)의 값
//		sumFunc(N);

		// g(x) 값 구하기
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			ans += sumFunc(i);
		}
		System.out.println(ans);

	}

	static int sumFunc(int A) {
		// f(N) 먼저 구하기
		// f(N) = sum
		int sum = 0;
		for (int i = 1; i <= A; i++) {
			if (A % i == 0) {
				sum += i;
			}
		}
		return sum;
	}

}
