package DP;

import java.util.Scanner;

public class BOJ2579_mirim {
	public static void main(String[] args) {
		// 각 계단에 쓰여 있는 점수가 주어질 때 이 게임에서 얻을 수 있는 총 점수의 최댓값을 구해라
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 계단의 수
		
		// 계단 점수 저장 배열
		int[] stair = new int[N+1];
		for (int i = 1; i <= N; i++) {
			stair[i] = sc.nextInt(); // 계단 점수 저장
		}
		
		// 최댓값 저장 배열
		int[] sm = new int[N+1];
		
		// 초기값 저장
		sm[1] = stair[1];
		sm[2] = stair[1] + stair[2];
		
		for (int i = 3; i <= N; i++) {
			// 계단 오르는 방법 두가지
			int a = sm[i-2] + stair[i];
			int b = sm[i-3] + stair[i-1] + stair[i];
			sm[i] = Math.max(a, b); // 둘중 max값 구해서 값 저장하기
		}
		
		
	//  점수 최댓값 구하기
	System.out.println(sm[N]);
		
	} // end of main
	
	
} // end of class
