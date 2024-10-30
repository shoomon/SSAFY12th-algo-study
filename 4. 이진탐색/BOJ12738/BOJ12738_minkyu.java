import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
가장 긴 증가하는 부분 수열 3
수열이 주어졌을 때, 증가하는 부분이 가장 긴 부분 수열의 길이를 구하시오.

메모리 : 169496 KB
시간 : 472 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		// 각 수들의 연속된 값을 기록할 배열
		int[] seq = new int[N];
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		// 맨 첫배열은 무조건 유지
		seq[0] = arr[0];
		int length = 1;
		for (int i = 1; i < N; i++) {
			int tmp = arr[i];
			
			if (seq[length - 1] < tmp) {
				length++;
				seq[length - 1] = tmp;
			}else {
				int left = 0;
				int right = length - 1;
				while (left <= right) {
					int mid = (left + right) / 2;
					if (seq[mid] < tmp)
						left = mid + 1;
					else
						right = mid - 1;
				}
				seq[left] = tmp;
			}
		}
		
		System.out.println(length);
	}
}