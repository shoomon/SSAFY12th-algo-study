package codeplus_math;

import java.util.Scanner;

public class BOJ_17427_약수의합2 {
	
	// 틀렸다고 하는 원인 찾아야 함
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int res = 0;
		
		for (int i=1; i<=n;i++) {
			res += (n/i) * i;
		}
		
		System.out.println(res);
	}
}
