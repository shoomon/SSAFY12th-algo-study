package Week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_6588_Ming {
	// 드디어 정답ㅠㅠ
	public static void main(String[] args) throws IOException {
		// 소수 찾는 배열 만들기
		boolean[] isPrime = new boolean[1000001];
		isPrime = findPrimeNumber(1000000);
		
		// 속도 위해 BufferedReader, BufferedWriter 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 무한루프
		while (true) {
			int num = Integer.parseInt(br.readLine());
			// 홀수의 합으로 나타낼 수 없는 경우 처리를 위한 배열
			// 값을 찾으면 배열에 넣기
			// 안 넣으면 0, 이를 이용해 if 처리
			// 이렇게 안 하면 "Goldbach's conjecture is wrong." 매번 출력
			int[] prime = new int[2];
			
			// 무한루프 끊기 위한 조건. 마지막엔 0을 입력 받음
			if (num == 0)
				break;
			// for문 돌며 i값 찾기
			for (int i=0; i<num; i++) {
				if (isPrime[i] && isPrime[num-i]) {
					prime[0]=i;
					prime[1]=num-i;
					bw.write(num+" = "+i+" + "+(num-i) + "\n");
					// 찾으면 바로 break 해줘야 가장 작은 i 찾을 수 있음
					break;
				}
			}
			// i 값을 찾지 못하면
			if (prime[0]==0) {
				bw.write("Goldbach's conjecture is wrong." + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
	
	// 소수 찾는 배열 만드는 함수 생성 (에라토스테네스의 해)
	static boolean[] findPrimeNumber(int N) {
		boolean[] isPrime = new boolean[N+1];
		
		for (int i=2; i<isPrime.length; i++) {
			isPrime[i] = true;
		}
		
		for (int i=2; i*i<=N; i++) {
			if (isPrime[i]) {
				for (int j=i*i; j<isPrime.length; j+=i) {
					isPrime[j] = false;
				}
			}
		}
		
		return isPrime;
	}
}
