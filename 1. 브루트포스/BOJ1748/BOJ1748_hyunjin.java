package 브루트포스;

import java.util.Scanner;

// BOJ1748_수 이어 쓰기1
public class BOJ1748_hyunjin {
	static int N;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		String strNum = Integer.toString(N);
		int length = strNum.length();

		System.out.println((long) func(length));
	}

	static double func(int length) {

		double ans = length * (N - Math.pow(10, length - 1) + 1) + repeat(length - 1);
		return ans;

	}

	static double repeat(int M) {
		// 기저 조건
		if (M == 0)
			return 0;

		// 몇 자리 수인지에 따라, 앞 수들의 길이 구하기
		// ex) 10~99 => 9*(10^1)*2 = 180 자리
		// ex) 1~9 => 9*(10^0)*1 = 9 자리
		double cnt = 9 * Math.pow(10, (M - 1)) * M;
		return cnt += repeat(M - 1);

	}
}
