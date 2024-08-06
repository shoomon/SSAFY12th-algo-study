

import java.util.Scanner;

public class BOJ_1978_Ming {
	// 맞았습니다!!
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 1000 이하의 자연수 중 소수 찾기
		boolean[] isPrime = new boolean[1001];
		// default false -> true
		for (int i=0; i<isPrime.length; i++) {
			isPrime[i] = true;
		}
		// 0, 1은 소수 아님
		isPrime[0] = isPrime[1] = false;
		// 앞에서부터 소수의 배수 차근차근 제거
		for (int i=2; i*i<=isPrime.length; i++) {
			if (isPrime[i]) {
				for (int j=i*i; j<isPrime.length; j+=i) {
					isPrime[j] = false;
				}
			}
		}
		
		// 입력 받을 수의 숫자
		int N = sc.nextInt();
		
		// 입력 받는 숫자가 소수인지 확인
		int cnt = 0;
		for (int i=0; i<N; i++) {
			int num = sc.nextInt();
			if (isPrime[num]) {
				cnt++;
			}
		}
		
		// 출력
		System.out.println(cnt);
	}
}
