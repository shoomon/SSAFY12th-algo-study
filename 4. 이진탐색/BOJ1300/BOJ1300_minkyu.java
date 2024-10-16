import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
K번째 수
N * N 배열 내에서 들어있는 수는 A[i][j] = i * j일 때 일차원 배열에 넣고 정렬을 한다.
해당 정렬된 배열에서 K번째에 해당하는 수는 무슨 값인지 구해보자.

메모리 : 11688 KB
시간 : 100 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		// k번째가 될 수 있는 값을 이분탐색으로 찾는다.
		long left = 1;
		long right = 1000000000L;
		long ans = 0;
		while(left <= right) {
			long mid = (left + right) / 2;
			long cnt = 0;
			long limit = (N < mid) ? N : mid;
			// 해당 배수에 들어있을 수 있는 mid 보다 작거나 같은 값들의 개수
			for (int i = 1; i <= limit; i++)
				cnt += Math.min(limit, mid / i);
			if (k > cnt)
				left = mid + 1;
			else {
				right = mid - 1;
				ans = mid;
			}
		}
		
		System.out.println(ans);
	}
}