import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
1로 만들기

X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.

연산을 사용하는 횟수의 최솟값을 출력하시오.


메모리 : 18176 KB
시간 : 116 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tar = Integer.parseInt(br.readLine());
		
		int[] dp = new int[tar + 1];
		
		for (int i = 2; i <= tar; i++) {
			int min = Integer.MAX_VALUE;
			
			if (i - 1 >= 0 && min > dp[i - 1] + 1)
				min = dp[i - 1] + 1;
			if (i % 2 == 0 && i / 2 > 0 && min > dp[i / 2] + 1)
				min = dp[i / 2] + 1;
			if (i % 3 == 0 && i / 3 > 0 && min > dp[i / 3] + 1)
				min = dp[i / 3] + 1;
			
			dp[i] = min;
		}
		
		System.out.println(dp[tar]);
	}
}