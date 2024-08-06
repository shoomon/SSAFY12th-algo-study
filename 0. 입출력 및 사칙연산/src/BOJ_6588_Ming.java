

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_6588_Ming {
	// 틀렸습니다.
	public static void main(String[] args) throws IOException {
		// 배열 만들기
		boolean[] isPrime = new boolean[1000001];
		
		// default 값 true로 변경
		for (int i=0; i<isPrime.length; i++) {
			isPrime[i] = true;
		}
		
		// 0과 1은 소수가 아님
		isPrime[0] = isPrime[1] = false;
		
		// 소수의 배수 false 처리
		for (int i=2; i*i<=isPrime.length-1; i++) {
			if (isPrime[i]) {
				for (int j=i*i; j<isPrime.length; j+=i) {
					isPrime[j] = false;
				}
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while (true) {
			int num = Integer.parseInt(br.readLine());
			boolean ok = false;
			if (num == 0)
				break;
			for (int i=num; i>=0; i--) {
				if (isPrime[i] && isPrime[num-i]) {
					bw.write(num+" = "+i+" + "+(num-i) + "\n");
					ok = true;
					break;
				}
			}
			if (!ok) {
				bw.write("Goldbach's conjecture is wrong." + "\n");
			}
		}
		bw.close();
	}
}