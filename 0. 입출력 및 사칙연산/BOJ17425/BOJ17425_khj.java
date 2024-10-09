package AlgoStudy_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17425_khj {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());

			// g(x) 값 구하기
			long ans = 0;
			for (int i = 1; i <= N; i++) {
				ans += sumFunc(i);
			}
			sb.append(ans).append("\n");
		} // tc
		System.out.println(sb.toString());
	} // main

	
	// f(N) 구하는 메서드
	static long sumFunc(int A) {
		long sum = 0;
		for (int i = 1; i <= A; i++) {
			if (A % i == 0) {
				sum += i;
			}
		}
		return sum;
	}
}
