package code;
import java.util.*;
import java.io.*;
//24.09.10
//설계 시간: 1분
//구현 시간: 5분
//메모리: 14128 kb
//시간: 104 ms
public class BOJ9095 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		int[] dp = new int[12];
		
		// 2 = 1+1 2, 3 = dp[2]+1 dp[1]+2
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = dp[1]+dp[2] + 1;
		for(int i = 4; i < 12; i++) {
			dp[i] = dp[i-3]+dp[i-2]+dp[i-1];
		}
//		for(int i : dp) System.out.print(i+" ");
//		System.out.println();
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			
			System.out.println(dp[N]);
		}
		
	}

}
