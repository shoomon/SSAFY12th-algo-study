

import java.util.Scanner;

public class BOJ_1929_Ming {
	// 맞았습니다!!
	public static void main(String[] args) {
		// 입력 받기
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		// N까지 소수 구하기
		boolean[] isPrime = new boolean[N+1];
		
		for (int i=0; i<isPrime.length; i++) {
			isPrime[i] = true;
		}
		
		isPrime[0] = isPrime[1] = false;
		
		for (int i=2; i*i<=isPrime.length; i++) {
			if (isPrime[i]) {
				for (int j=i*i; j<isPrime.length; j+=i) {
					isPrime[j] = false;
				}
			}
		}
		
		// 출력하기
		for (int i=M; i<=N; i++) {
			if (isPrime[i]) {
				System.out.println(i);
			}
		}
	}
}
