import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920_mingyung {
	// S4_1920_수 찾기
	// 결과 : 맞았습니다!!
	// 메모리 : 43,356 kb
	// 시간 : 612 ms
	
	// N개의 정수 중 X 존재하는지 알아내는 프로그램
	
	static int N, M;
	static int[] nums, findN;
	
	public static void main(String[] args) throws IOException {
		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st1.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		
		// 이분탐색을 위한 정렬
		Arrays.sort(nums);
		
		// 찾아야 하는 숫자 입력 받으면서 결과 뽑아내 출력하기
		StringBuilder sb = new StringBuilder();
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			sb.append(find(Integer.parseInt(st2.nextToken())) ? 1 : 0).append('\n');
		}
		System.out.println(sb);
	} // main
	
	// 이분탐색
	static boolean find(int m) {
		int left = 0;
		int right = N-1;
		
		while (left<=right) {
			int mid = (left+right)/2;
			
			if (m == nums[mid]) {
				return true;
			} else if (m < nums[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return false;
	}
}