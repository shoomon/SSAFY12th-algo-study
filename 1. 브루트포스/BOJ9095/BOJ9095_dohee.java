package bruteforce;
import java.io.*;
import java.util.*;
/*
12832KB	96ms
41m
 */
public class BOJ_9095 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc=1;tc<=T;tc++) {
			int N = sc.nextInt();
			int[] dp = new int[N+1];
			if (N==1) {
				System.out.println(1);
				continue;
			} else if (N==2){
				System.out.println(2);
				continue;
			} else if(N==3) {
				System.out.println(4);
				continue;
			}
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			for(int i=4; i<N+1; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			System.out.println(dp[N]);
		}
	}
	  
	  
	
}