package io;

import java.util.Scanner;

public class BOJ_17427 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long res = 0; //자료형
		
		for (int i=1; i<=n;i++) {
			res +=  (n/i) * i;
		}
		
		System.out.println(res);
	}
}
