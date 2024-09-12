package 브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// BOJ6064_카잉 달력
// 시간 초과.. buffered로 바꿔도 시간 초과.. 
public class BOJ6064_hyunjin {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		Scanner sc = new Scanner(System.in);

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);

			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int gcd = GCD(M, N); // 최대 공약수
			int lcm = M * N / gcd; // 최소 공배수
//			System.out.println(lcm);

			int ans = -1;

			// 얘가 시간 초과 범인.. 
			// 모든 값을 다 돌 필요는 없다!!
//			while (!(m == x && n == y)) {
//				m++;
//				n++;
//				year++;
//
//				if (year > lcm) {
//					ans = -1;
//					break;
//				}
//
//				if (m > M)
//					m = 1;
//
//				if (n > N)
//					n = 1;
//			}
			
			/////////////////////////////////
			// 새로운 방법 

			// i(x-1) = 2 -> 12 -> 22 -> 32 이렇게 돌면서
			// i % N 한 값이 y-1가 나올때 stop
			// x-1, y-1을 해주는 이유는 %연산을 쓰면 나머지가 N이 12인 경우, 0~11까지 나오므로@!
			// 10 12 10 12 가 들어올 경우, 60%12 == 0이 되어 버려서 y(12)랑 같지 않음
			
			for (int i = x - 1; i <= lcm; i += M) {
				if (i % N == y - 1) {
					ans = i + 1;
					break;
				}
			}

			int result = (ans == -1) ? -1 : ans;

			System.out.println(result);

		} // tc

	} // main

	// 유클리드 호제법(최대 공약수 구하는 법)
	static int GCD(int m, int n) {
		if (n == 0) {
			return m;
		} else {
			return GCD(n, m % n);
		}
	}
}
