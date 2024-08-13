package codeplus_math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1978_소수찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		int cnt = 0;
		String input = bf.readLine();
		int[] prime_arr = new int[1001];

		for (int i = 2; i<prime_arr.length; i++) {
			prime_arr[i] = 1;
		}
		int sqrt_n = (int) Math.sqrt(1000);
		for (int i = 2; i <= sqrt_n; i++) {
			int q = 1000 / i;
			for (int j=2; j <= q; j++) {
				prime_arr[i*j] = 0;
			}
		}
//		System.out.println(Arrays.toString(prime_arr));
		
		
		int[] check_arr = Arrays.stream(input.split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
		for (int ch : check_arr) {
			if (prime_arr[ch] == 1) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
}
}
