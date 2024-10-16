package oct_3th;

import java.io.*;
import java.util.*;

// 42m 오버플로우 고려하자 -- 누적합이면 그냥 확인할 것
// 175544KB	628ms

public class BOJ2805_dohee {
	static int N, M, trees[];
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(trees);

		cutTree();
	}

//	10 15 17 20
//	4 26 40 42 46
	public static void cutTree() {
		int lo = -1;
		int hi = N;
		while(lo+1<hi) {
			int mid = (lo + hi) / 2;
			long treeSum = check(mid);
			if (treeSum < M) { // 더 늘릴지 줄일지 결정
				hi = mid;
			} else {
				lo = mid;;
			}
		}
//		System.out.println(hi);
		// 최종 값은 lo와 hi 사이에 나타남.
		long res = check(hi);
		// hi 값에서 개수차이만큼 빼서 계산
		System.out.println(trees[hi] - (((M-res-1) / (N-hi)) + 1));
//	12/3=4
//	13/3=4.x -> 5	
		
	}

	private static long check(int mid) {
		long treeSum = 0;
		for (int i = mid; i < N; i++) {
			treeSum += trees[i] - trees[mid];
		}
		return treeSum;
	}
}