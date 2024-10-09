package BOJ;

import java.util.Scanner;

public class BOJ_1748 {
/*
12868kb, 88ms
time : 50m
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long ans = N;
		
		int pos = (N+"").length();
		
		// 자릿수별 변화 체크
		for (int i = 1; i < pos; i++) {
			ans+= (Math.pow(10, i) - Math.pow(10, i-1))*(i-1);
		}
		ans+= (N - Math.pow(10, pos-1) + 1) * (pos-1);
		
		System.out.println(ans);
	}
}
