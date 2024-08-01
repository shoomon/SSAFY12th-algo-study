package BOJ_0_입출력및사칙연산;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1037_khj {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] measureArray = new int[N];

		for (int i = 0; i < N; i++) {
			measureArray[i] = sc.nextInt();
		}

		Arrays.sort(measureArray);

//		System.out.println(Arrays.toString(measureArray));
		long ans = (long) measureArray[0] * measureArray[N - 1];
		System.out.println(ans);

	}
}
