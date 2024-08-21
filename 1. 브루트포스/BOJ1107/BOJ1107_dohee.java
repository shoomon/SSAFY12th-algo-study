package BOJ1107;

import java.util.Scanner;

public class BOJ1107_dohee {
	static int N, M, prst, minCnt;
	static boolean isBroken[] = new boolean[10];
	static boolean complete;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		minCnt = Integer.MAX_VALUE;
		for (int i = 0; i < M; i++) {
			isBroken[sc.nextInt()] = true; 
		}
		
		findMin();
		System.out.println(minCnt);
		
	}

	private static void findMin() {
		
		int idx = 0;
		while (true) {
			if(Math.abs(100-N) < Math.abs(idx)) {
				minCnt = Math.min(Math.abs(100-N), minCnt);
				break;
			}
			findMin(N+idx);
			findMin(N-idx);
			idx++;
		}
		
	}
	
	private static void findMin(int num) {
		char[] digits = Integer.toString(num).toCharArray();
		int cnt = 0;
		for (int i = 0; i < digits.length; i++) {
				return;
			}
		}
		complete = true;
		cnt = digits.length + Math.abs(N-num);
		minCnt = Math.min(cnt, minCnt);
	}
}
