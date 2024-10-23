package greedy;

import java.util.Scanner;

// BOJ11047_동전 0
// 메모리 : 13044KB
// 시간 : 92ms
public class BOJ11047_hyunjin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();
		int index = 0;

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();

			if (arr[i] <= K) {
				index = Math.max(index, i); // K 보다 작은 수 중 가장 값이 큰 동전 index
			}
		}

		int ans = 0;

		while (true) {
			// cnt는 while문을 돌 때마다 갱신
			int cnt = K / arr[index];

			// 정답 ans에 동전 개수 더하기
			ans += cnt;

			K = K - (arr[index] * cnt);

			// while 문 빠져나오는 조건
			if (K == 0) {
				System.out.println(ans);
				break;
			}

			// ex) 1000 > 200 이면 index 하나씩 줄면서 index 줄여나가기
			if (arr[index] > K) {
				index--;
			}

		}

	}
}
