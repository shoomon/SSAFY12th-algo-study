package AlgoStudy_12;

import java.util.Scanner;

public class BOJ1929_khj {
// 소수 구하기
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
//		3 16 => 3 5 7 11 13
		// N과 M 사이에 소수 출력
		
		// N부터 M까지 돌면서 확인
		for (int i = N; i <= M; i++) {
			int cnt = 0;
			// j=1부터 i(=현재 확인하고 싶은 값)까지 돌면서 나누었을 때, 나머지가 0이 되는가?!
			for (int j = 1; j <= i; j++) {
				if (i % j == 0) {
					// 나머지가 0 == 약수 -> 약수가 나올 때마다 cnt++
					cnt++;
				}
			}
			// 소수라면 약수의 개수가 1과 자기자신뿐(==2) 이면 출력
			if(cnt == 2) {
				System.out.println(i); 
			}
			continue;
		}
	}
}
