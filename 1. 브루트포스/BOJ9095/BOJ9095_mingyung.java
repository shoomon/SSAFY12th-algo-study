import java.util.Scanner;

public class BOJ9095_mingyung {
	// S3_123더하기 _맞았습니다!!
	// 1, 2, 3의 합으로 나타내는 방법의 수 구하기
	public static void main(String[] args) {
		// 입력받기_1 테스트케이스
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		// 주어지는 정수 N이 11보다 작으니 정답 배열 만들었음
		int[] dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i=4; i<11; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		for (int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			System.out.println(dp[N]);
		}
	}
}