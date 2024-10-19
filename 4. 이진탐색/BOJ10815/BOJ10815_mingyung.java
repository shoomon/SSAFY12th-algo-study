import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10815_mingyung {
	// S5_10815_숫자 카드
	// 결과 : 맞았습니다!!
	//           boolean  |    int
	// 메모리 : 109,140 KB  | 106,860 kb
	// 시간 : 1,172 ms     | 1,076 ms
	
	// 상근이가 가지고 있는 정수가 하나 적혀있는 카드 N개
	// 정수 M개가 주어졌을 때, 상근이가 이 수가 적혀있는 카드를 가지고 있는지 구하는 프로그램
	
	static int N, M;
	static int[] card;
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
		
		// 이분탐색 위해 정렬
		Arrays.sort(card);
		
		// 마지막 수는 입력받으면서 출력하기
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			sb.append(find(Integer.parseInt(st2.nextToken()))).append(' ');
		}
		System.out.println(sb);
	}
	
	// 이분탐색
	static int find(int m) {
		int left=0;
		int right=N-1;
		
		while(left<=right) {
			int mid = (left+right)/2;
			
			if (m==card[mid]) {
				return 1;
			} else if (m < card[mid]) {
				right = mid-1;
			} else {
				left = mid+1;
			}
		}
		
		return 0;
	}
}