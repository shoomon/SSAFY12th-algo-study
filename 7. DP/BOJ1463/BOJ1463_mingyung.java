import java.util.Scanner;

public class BOJ1463_mingyung {
	// 1463_ 1로 만들기
	// 결과 : 맞았습니다!!
	// 메모리 : 21632 KB
	// 시간 : 184 ms
	
	// 세 가지 연산을 최소로 사용하여 1로 만들기
	// 3으로 나누어 떨어지면, 3으로 나눔
	// 2로 나누어 떨어지면, 2로 나눔
	// 1을 뻄
	
	public static void main(String[] args) {
		// 입력받기 1<=N<=1,000,000
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N+1];
		
		// 초기값 설정
		dp[1] = 0;
		
		// 2부터 돌기
		for (int i=2; i<N+1; i++) {
			// 6으로 나누어 떨어지면
			if (i%6==0) {
				dp[i] = Math.min(1+dp[i/3], 1+dp[i-1]);
				dp[i] = Math.min(dp[i], 1+dp[i/2]);
			}
			
			// 3으로 나누어 떨어지면
			else if (i%3==0) {
				dp[i] = Math.min(1+dp[i/3], 1+dp[i-1]);
			}
			
			// 2로 나누어 떨어지면
			else if (i%2==0) {
				dp[i] = Math.min(1+dp[i/2], 1+dp[i-1]);
			}
			
			// 그 외
			else {
				dp[i] = 1+dp[i-1];
			}
		}
		
		System.out.println(dp[N]);
	} // main
}