// dp 구현하기 전...

package 아이고아이고;

import java.util.Scanner;

public class _9095_123더하기1 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			
			// 방법의 수 저장 배열
			int[] dp = new int[N+1];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			int cnt = 0;
			for (int i = 4; i < N+1; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
				
			}
			
			// 출력
			System.out.println(dp[N]);
		} // end of T
		
		
	} // end of main
}
