package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ2776_암기왕
// 메모리 : 334460KB
// 시간 :1360ms

public class BOJ2776_hyunjin {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine()); // 하루동안 본 수 : 수첩1에 적힌 수의 개수
			int[] book1 = new int[N]; // 수첩 1

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				book1[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(book1); // 이분탐색 진행할 배열 정렬하기

			int M = Integer.parseInt(br.readLine()); // 동규가 한 질문 : 수첩2 에 적힌 수 개수

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int tmp = Integer.parseInt(st.nextToken());

				// 각 수에 대해서 이분탐색 시작
				int low = 0;
				int high = N - 1;
				boolean flag = false;

				while (low <= high) {
					int mid = (low + high) / 2;

					if (book1[mid] > tmp) {
						high = mid - 1;
					} else if (book1[mid] < tmp) {
						low = mid + 1;
					} else {
						sb.append(1).append("\n");
						flag = true;
						break;
					}
				} // while
				if (!flag) {
					sb.append(0).append("\n");
				}
			}

		} // tc
		System.out.println(sb.toString());
	}

//	static int binarySearch(int[] book1, int tmp, int N) {
//		int low = 0;
//		int high = N - 1;
//
//		while (low <= high) {
//			int mid = (low + high) / 2;
//
//			if (book1[mid] > tmp) {
//				high = mid - 1;
//			} else if (book1[mid] < tmp) {
//				low = mid + 1;
//			} else {
//				return 1;
//			}
//		}
//
//		return 0;
//	}
}
