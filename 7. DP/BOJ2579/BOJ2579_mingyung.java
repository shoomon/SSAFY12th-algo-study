import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579_mingyung {
	// 2579_계단오르기
	// 결과 : 맞았습니다!!
	// 메모리 : 14160 KB
	// 시간 : 104 ms
	
	// 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지
	// 연속된 세 개 계단 밟지 않고 최대 점수 얻기
	
	public static void main(String[] args) throws IOException {
		// 계단 갯수 300 이하 자연수
		// 점수는 10000 이하 자연수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫 줄 계단 갯수, 이후 한 줄에 하나씩 점수 입력
		int N = Integer.parseInt(br.readLine());
		int[] stair = new int[N];
		for (int i=0; i<N; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		} // 입력받기 완
		
		// 답 배열 만들기
		int[] score = new int[N];
		
		score[0] = stair[0];
		// 계단 갯수가 적을 때 처리
		if (N>1) {
			score[1] = stair[0]+stair[1];
			if (N>2)
				score[2] = Math.max(score[0]+stair[2], stair[1]+stair[2]);
		}
		
		// dp 채우기
		for (int i=3; i<N; i++) {
			score[i] = Math.max(score[i-2]+stair[i], score[i-3]+stair[i-1]+stair[i]);
		}
		
		// 출력하기
		System.out.println(score[N-1]);
	} // main
}