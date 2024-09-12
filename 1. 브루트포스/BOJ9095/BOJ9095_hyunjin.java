package 브루트포스;

import java.util.Scanner;

//BOJ9095_1,2,3 더하기
// 안 풀려용,,,
public class BOJ9095_hyunjin {
	static int n,  sum;
	static int[] arr;
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		arr = new int[] { 1, 2, 3 };

		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			result = new int[n];
			visited = new boolean[arr.length];

			sum = 0;
			func(0, 0);

		} // tc
	} // main

	static void func(int idx, int cnt) {

		if (sum == n) {
			for (int a : result) {
				System.out.print(a + " ");
			}
			System.out.println();
			return;
		}

		if (sum > n) {
			return;
		}

		for (int i = 0; i < 3; i++) {
			result[cnt] = arr[i];
			func(i, cnt + 1);

		}
	}
}
