import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

2xn 타일링

2xn 크기의 직사각형을 1x2, 2x1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

메모리 : 14348 KB
시간 : 100 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] dp = new int[n + 1];
		if (dp.length > 1)
			dp[1] = 1;
		if (dp.length > 2)
			dp[2] = 2;
		for (int i = 3; i <= n; i++)
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;

		System.out.println(dp[n]);
	}
}