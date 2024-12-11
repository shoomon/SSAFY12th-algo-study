import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13458_mingyung {
	// 13458_시험 감독
	// 결과 : 맞았습니다!!
	// 메모리 : 92232 kb
	// 시간 : 436 ms
	
	// N개의 시험장
	// 총 감독관 감시 가능한 응시자 B, 부감독관 감시 가능한 응시자 C
	// 필요한 감독관 수의 최솟값 구하기
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		// 첫째 줄 시험장 개수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 둘째 줄 각 시험장 응시자 수
		int[] p = new int[N];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			p[i] = Integer.parseInt(st1.nextToken());
		}
		// 셋째 줄 B, C
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st2.nextToken());
		int C = Integer.parseInt(st2.nextToken());
		// 입력받기 완료
		
		// 답 구하기
		long ans = 0;
		for (int i=0; i<N; i++) {
			int pp = p[i] - B;
			ans++;
			if (pp > 0) {
				if (pp%C == 0)
					ans += pp/C;
				else ans += pp/C+1;
			}
		}
		
		// 출력하기
		System.out.println(ans);
	} // main
}