import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10816_mingyung {
	// S4_10816_숫자 카드 2
	// 결과 : 맞았습니다!!
	// 메모리 : 125,088 KB
	// 시간 : 1,272 ms
	// StringBuilder 안썼을 때 시간초과 뜸.
	
	// 상근이가 가지고 있는 숫자 카드 N개  (1<=N, M<=500,000)
	// 상근이가 가지고 있는 카드와 비교할 M개의 숫자
	// 숫자는 -10,000,000 ~ 10,000,000 사이
	
	static int N, M;
	static int[] card, num;
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		card = new int[N];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			card[i] = Integer.parseInt(st1.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		num = new int[M];
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			num[i] = Integer.parseInt(st2.nextToken());
		} // 입력받기 완
		
		// 이분탐색 위해 card 배열 정렬
		Arrays.sort(card);
		
		// 값 찾으며 출력하기
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			sb.append(findR(num[i]) - findL(num[i])).append(" ");
		}
		System.out.println(sb);
	}
	
	// 이분탐색
	// 같은 숫자 중 왼쪽 끝 찾기
	static int findL(int m) {
		int left = 0;
		int right = N;
		
		while (left<right) {
			int mid = (left+right)/2;
			
			// 찾는 값이 중앙보다 작거나 같으면 오른쪽=중앙으로 범위 좁히기
			if (m <= card[mid]) {
				right = mid;
			} else {
				// 찾는 값이 왼쪽보다 크면 왼쪽을 중앙+1
				left = mid + 1;
			}
		}
		
		return left;
	}
	// 같은 숫자 중 오른쪽 끝 찾기
	static int findR(int m) {
		int left = 0;
		int right = N;
		
		while (left<right) {
			int mid = (left+right)/2;
			
			if (m < card[mid]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
}