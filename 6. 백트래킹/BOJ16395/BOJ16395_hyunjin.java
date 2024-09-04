package 백트래킹;

import java.util.Arrays;
import java.util.Scanner;

// BOJ16395_파스칼삼각형
public class BOJ16395_hyunjin {
	static int N, K, ans;
	static int[] arr;
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt() - 1;
		K = sc.nextInt() - 1;

		arr = new int[N];
		result = new int[K];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}

		comb(0, 0);
		System.out.println(ans);
	}

	static void comb(int idx, int cnt) {
		if (cnt == K) {
			ans++;
			return;
		}

		for (int i = idx; i < N; i++) {
			if (visited[i])
				continue;
			result[cnt] = arr[i];
			visited[i] = true;
			comb(i, cnt + 1);
			visited[i] = false;
		}
	}
}
