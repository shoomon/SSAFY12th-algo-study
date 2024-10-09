package DP;

import java.util.Scanner;

// BOJ2579_계단 오르기 
public class BOJ2579_hyunjin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int[] dp = new int[N + 1];
		int[] score = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			score[i] = sc.nextInt();
		}

		// 초기값 설정
		dp[0] = 0;
		if (N == 1) {
			System.out.println(score[1]);
			return;
		}

		dp[1] = score[1];

		if (N > 1) {
			dp[2] = score[1] + score[2];
		}

		for (int i = 3; i <= N; i++) {
			// 두 단계전에 오는 경우 v.s. 세 단계전 + 한 단계전에서 오는 경우
			dp[i] = Math.max(dp[i - 2] + score[i], dp[i - 3] + score[i - 1] + score[i]);
		}

		System.out.println(dp[N]);
	}
}