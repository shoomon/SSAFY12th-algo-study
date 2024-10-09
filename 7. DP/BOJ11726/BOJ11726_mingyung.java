import java.util.Scanner;

public class BOJ11726_mingyung {
	// 11726_2xn타일링
	// 결과 : 맞았습니다!!    BufferedReader 사용 시
	// 메모리 : 17804 KB    14388 KB
	// 시간 : 164 ms       100 ms
	
	// 2xn 크기의 직사각형을 1x2, 2x1 타일로 채우는 방법의 수
	//                  구해서 10,007로 나눈 나머지 출력하기!!
	public static void main(String[] args) {
		// 입력받기 1<=n<=1,000
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N+1];
		
		dp[1]=1;
		// N=1일 때 2에 접근하면 ArrayIndexOutOfBounds뜸...
		if (N>1) {
			dp[2]=2;
			
			// 저장할 때 모듈러 사용하여 저장하기
			// (A+B)%C = ((A%C)+(B%C))%C
			for (int i=3; i<N+1; i++) {
				dp[i] = (dp[i-1]+dp[i-2])%10007;
			}
		}
		
		System.out.println(dp[N]);
	}
}