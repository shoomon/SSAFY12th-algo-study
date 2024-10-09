package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
16124kb, 348ms
time : 70m
 */
public class BOJ_6064 {
	static int M,N,x,y;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			long idx = 0;
			long limit = Math.max(M, N);
			boolean signal = false;
			y = y==N ? 0 : y;
			
			// 나머지 기준잡고, 계속 수를 올려가며 맞춤. 최대 실행 횟수는 최소공배수가 될때까지
			
			while(idx < limit) {
				if((x+M*idx) % N == y && x+M*idx != 0) { // x값 기준으로 자릿수 맞춘 상태로, y의 자릿수 체크
					signal = true;
					break;
				}
				idx++;
			}
			if (signal) {
				System.out.println(x+M*idx);
			} else {
				System.out.println(-1);
			}
		}
		
	}
	
}
