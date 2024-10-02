package DP;

import java.util.Scanner;

public class BOJ1463_mirim {
	public static void main(String[] args) {
		// X가 3으로 나누어 떨어지면, 3으로 나눈다.
		// X가 2로 나누어 떨어지면, 2로 나눈다.
		// 1을 뺀다.
		
		// 최소한의 연산으로 1 만드는 횟수 출력
		Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        // 최소 연산 횟수를 저장할 배열
        int[] dp = new int[N + 1];
        
        // 1은 이미 1이므로 연산 횟수 0
        dp[1] = 0;
        
        // 2부터 N까지 최소 연산 횟수 계산
        for (int i = 2; i <= N; i++) {
            // 1을 빼는 연산을 먼저 고려
            dp[i] = dp[i - 1] + 1;
            
            // 2로 나누어 떨어지면, 2로 나누는 연산도 고려
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            
            // 3으로 나누어 떨어지면, 3으로 나누는 연산도 고려
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
        
        // 최소 연산 횟수 출력
        System.out.println(dp[N]);
        
	} // end of main
} // end of class
