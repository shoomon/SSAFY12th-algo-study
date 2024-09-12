package 브루트포스;

import java.util.Scanner;

//BOJ9095_1,2,3 더하기
// 안 풀려용,,,
public class BOJ9095_hyunjin {
	static int n, cnt;
	static int[] arr;
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		arr = new int[] { 1, 2, 3 };

		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			cnt = 0;
			result = new int[n];
			visited = new boolean[arr.length];

			func(0, 0);

		} // tc
	} // main

	static void func(int idx, int sum) {

		for (int i = 0; i < result.length; i++) {
			sum += result[i];

			if (sum == n) {
				for (int a : result) {
					System.out.print(a + " ");
				}
				System.out.println();
				return;
			}
		}

		for (int i = 0; i < 3; i++) {
			result[cnt] = arr[i];
			func(i, sum + 1);
			func(i + 1, cnt + 1);
		}
	}
}
