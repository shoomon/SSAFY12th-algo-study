import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2776_mingyung {
	// S4_2776_암기왕
	// 결과 : 맞았습니다!!
	// 메모리 : 230,744 KB
	// 시간 : 1,628 ms
	
	// 기억력 좋은 연종이가 본 숫자 수첩1에 적음
	// 질문 던져서 봤다고 주장한 수들 수첩2에 적음
	// 수첩2에 적혀있는 순서대로, 수첩1에 있으면 1, 없으면 0 출력
	
	static int N, M;
	static int[] memo1, memo2;
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		// 첫번째 줄 테스트케이스 갯수
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// 출력 : 시간초과 뜨니까 StringBuilder 써보자
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=T; tc++) {
			// 수첩1에 적힌 정수 N개
			N = Integer.parseInt(br.readLine());
			memo1 = new int[N];
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				memo1[i] = Integer.parseInt(st1.nextToken());
			}
			M = Integer.parseInt(br.readLine());
			memo2 = new int[M];
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				memo2[i] = Integer.parseInt(st2.nextToken());
			} // 입력받기 완료
			
			// 이분탐색 위한 정렬
			Arrays.sort(memo1);
			
			// 출력
			for (int i=0; i<M; i++) {
				sb.append(find(memo2[i]) ? 1 : 0).append('\n');
			}
		} // tc
		System.out.println(sb);
	} // main
	
	// 이분탐색
	static boolean find(int m) {
		int left = 0;
		int right = N-1;
		
		while(left<=right) {
			int mid = (left+right)/2;
			
			if (m == memo1[mid]) {
				return true;
			} else if (m > memo1[mid]) {
				left = mid+1;
			} else right = mid-1;
		}
		
		return false;
	}
}