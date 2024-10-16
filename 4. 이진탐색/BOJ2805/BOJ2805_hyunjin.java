package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ2805_나무 자르기 
// 메모리 : 
// 시간 : 

public class BOJ2805_hyunjin {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 나무의 수
		int M = Integer.parseInt(st.nextToken()); // 집에 가져가려는 나무의 길이

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int ans = 0;

		int left = 0; // 처음 left : 절단기의 최소 높이가 0
		int right = arr[N - 1]; // 처음 right : 절단기의 최대 높이 

		while (left <= right) {
			int mid = (left + right) / 2;

			long goHomeHeight = 0;
			for (int i = 0; i < N; i++) {
				if (arr[i] - mid <= 0)
					continue;
				goHomeHeight += (arr[i] - mid);
			}

			if (goHomeHeight >= M) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		} // while

		System.out.println(ans);

	}
}
