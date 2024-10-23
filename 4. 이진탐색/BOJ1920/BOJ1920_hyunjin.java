package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ1920_수 찾기
// 메모리 : 65228KB
// 시간 : 1304ms

public class BOJ1920_hyunjin {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N]; // 이분 탐색할 숫자 배열

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A); // 이분 탐색할 배열 정렬하기

		int M = Integer.parseInt(br.readLine());

		// M개의 수 하나씩 돌면서 A 배열에 있는지 확인하기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			int ans = binarySearch(A, tmp, N);
			System.out.println(ans);
		}
	}

	static int binarySearch(int[] A, int tmp, int N) {
		int low = 0; // 시작 지점
		int high = N - 1; // 마지막 지점

		while (low <= high) { // low == high == mid가 만날때까지 진행
			int mid = (low + high) / 2;

			if (A[mid] > tmp) {
				high = mid - 1;
			} else if (A[mid] < tmp) {
				low = mid + 1;
			} else {
				return 1;
			}
		}
		return 0;

	}
}
