package 백트래킹;

import java.util.Arrays;
import java.util.Scanner;

// BOJ11050_이항계수1
// 이항 계수 (N K) => nCk => 조합 구하기
public class BOJ11050_hyunjin {
	static int N, K, ans;
	static int[] arr;
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();

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
