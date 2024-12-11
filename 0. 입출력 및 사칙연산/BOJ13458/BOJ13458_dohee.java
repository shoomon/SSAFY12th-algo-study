package codingTest;

import java.io.*;
import java.util.*;

public class BOJ13458 {
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		String[] s = br.readLine().split(" ");
		int B = Integer.parseInt(s[0]);
		int C = Integer.parseInt(s[1]);
		
		long res =0;
		int A =0;
		// 값 분리 및 계산 바로하기
		for (int i=0; i<N; i++) {
			// 메인감독관 수
			A = Integer.parseInt(st.nextToken());
			
			A -= B;
			res++;
			
			// 서브감독관 수 계산
			// 나머지 남아있으면 한명 더 배치
			if (A>0) {
				res += (A+C-1)/C;
			}
		}
		System.out.println(res);
	}
}
