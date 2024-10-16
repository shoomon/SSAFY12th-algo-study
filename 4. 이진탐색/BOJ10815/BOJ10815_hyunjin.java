package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ10815_숫자카드
// 메모리 : 141364KB
// 시간 : 804ms
public class BOJ10815_hyunjin {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(cards); // 이분탐색 진행할 배열 정렬

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());

			boolean hasCard = binarySearch(cards, tmp, N);

			int ans = hasCard ? 1 : 0;
			sb.append(ans).append(" ");
		}
		
		System.out.println(sb.toString());
	}

	static boolean binarySearch(int[] cards, int tmp, int N) {
		int low = 0;
		int high = N - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (cards[mid] > tmp) { // 찾고자 하는 값이 배열의 중간값보다 작은 경우
				high = mid - 1;

			} else if (cards[mid] < tmp) { // 찾고자 하는 값이 배열의 중간값보다 큰 경우
				low = mid + 1;
			} else {
				return true;
			}
		}

		return false;
	}
}
