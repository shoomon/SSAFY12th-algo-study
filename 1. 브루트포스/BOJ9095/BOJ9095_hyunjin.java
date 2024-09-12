package 브루트포스;

import java.util.Arrays;
import java.util.Scanner;

//BOJ9095_1,2,3 더하기

public class BOJ9095_hyunjin {
	static int n, cnt;
	static int[] arr;
	static int[] result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		arr = new int[] { 1, 2, 3 };

		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			result = new int[n];
//			visited = new boolean[n];

			cnt = 0;
			func(0, 0);
			System.out.println(cnt);

		} // tc
	} // main

	static void func(int sum, int idx) {
		if (sum == n) {
//			for (int a : result) {
//				System.out.print(a + " ");
//			}
//			System.out.println();
			cnt++;
			return;
		}

		if (sum > n) {
			return;
		}

//		int tmp = -1;
		for (int i = 1; i <= 3; i++) {
			result[idx] = i;
			func(sum + i, idx + 1);
			result[idx] = 0;
		}
	}
}
