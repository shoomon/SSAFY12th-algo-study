package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ10816_숫자카드2
// 메모리 : 178804KB
// 시간 : 1148ms
public class BOJ10816_hyunjin {
	static int N;
	static int[] arr;
	static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {

			int tmp = Integer.parseInt(st.nextToken()); // 찾을 수
			// 초기화
			flag = false;
			// tmp가 arr 에 몇 개 있는지 이분 탐색 시작
			int startIndex = low_index(tmp);
			int endIndex = high_index(tmp);
			int ans = (flag) ? endIndex - startIndex + 1 : 0;
			sb.append(ans).append(" ");
		}

		System.out.println(sb.toString());

	}

	static int low_index(int tmp) {

		int low = 0;
		int high = N - 1; // 마지막 인덱스 다음 자리에도 값이 들어갈 수 있으므로

		while (low <= high) {
			int mid = (low + high) / 2;

			if (arr[mid] > tmp) {
				high = mid - 1;
			} else if (arr[mid] < tmp) {
				low = mid + 1;
			} else { // arr[mid] == tmp 값이 같을 때,
				// 1. startIndex 찾기
				high = mid - 1;
				flag = true;
			}
		} // while
		return low;
	}

	static int high_index(int tmp) {

		int low = 0;
		int high = N - 1; // 마지막 인덱스 다음 자리에도 값이 들어갈 수 있으므로

		while (low <= high) {
			int mid = (low + high) / 2;

			if (arr[mid] > tmp) {
				high = mid - 1;
			} else if (arr[mid] < tmp) {
				low = mid + 1;
			} else { // arr[mid] == tmp 값이 같을 때,
				// 2. endIndex 찾기
				low = mid + 1;
				flag = true;
			}
		} // while
		return high;
	}

}
