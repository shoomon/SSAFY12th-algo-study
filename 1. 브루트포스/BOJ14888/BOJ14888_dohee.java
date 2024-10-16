package oct_3th;

import java.io.*;
import java.util.*;

// 43m
// 12628KB	76ms

// 연산자 우선순위 무시, 앞에서부터 진행
// 나눗셈 = 정수 나눗셈으로 몫만 취함
// 음수 / 양수 -> 양수로 바꾼 뒤 몫 취하고, 몫을 음수로 바꿈

public class BOJ14888_dohee {
	static int N, a[], minVal, maxVal;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		a = new int[N]; // 숫자배열
		int[] operations = new int[4]; // 연산자배열
		int[] res = new int[N-1]; // 연산자 나열
		minVal = Integer.MAX_VALUE;
		maxVal = Integer.MIN_VALUE;
		
		String[] st = br.readLine().split(" ");
		String[] op = br.readLine().split(" ");
		
		for (int i=0; i<N; i++) {
			a[i] = Integer.parseInt(st[i]);
		}
		for (int i=0; i<4; i++) {
			operations[i] = Integer.parseInt(op[i]);				
		}
		
		perm(0, operations, res);
		// 만들 수 있는 식의 결과 최대, 최소
		System.out.println(maxVal);
		System.out.println(minVal);
	}
	
	public static void perm(int depth, int[] operations, int[] res) {
		if (depth == N-1) {
//			System.out.println(Arrays.toString(res));
			int sum = a[0];
			for (int i=1; i<N; i++) {
				sum = cal(sum, res[i-1], a[i]);
			}
			
			minVal = Math.min(minVal, sum);
			maxVal = Math.max(maxVal, sum);
		}
		
		
		for (int i=0; i<4; i++) {
			if (operations[i]==0) continue;
			res[depth] = i;
			operations[i] -=1;
			perm(depth+1,operations,res);
			operations[i] +=1;
		}
		
	}

	private static int cal(int sum, int opr, int num) {
		switch (opr) {
		case 0:
			return sum+num;
		case 1:
			return sum-num;
		case 2:
			return sum*num;
		case 3:
			return sum/num;
		}		
		return -1;
	}
}
