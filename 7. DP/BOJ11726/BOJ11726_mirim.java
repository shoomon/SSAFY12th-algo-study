package DP;

import java.util.Scanner;

public class BOJ11726_mirim {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		long[] list = new long[1001]; // 인덱스 맞춤
		// 값 0 초기화( 중복되는 결과값 없음 )

		list[1] = 1;
		list[2] = 2;

		for (int i = 3; i <= n; i++) { // n까지 반복하면서
			list[i] = (list[i - 2] + list[i - 1]) % 10007;
		}

		System.out.println(list[n]);
	} // end of main

} // end of class
