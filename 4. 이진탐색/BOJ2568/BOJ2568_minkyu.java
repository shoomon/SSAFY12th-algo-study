import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] lines = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// B를 기준으로 정렬
		Arrays.sort(lines,(o1,o2)->{
			return Integer.compare(o1[1], o2[1]);
		});
		
		// A는 무조건 단순하게 증가하는 배열이 되어야 한다.
		int[] seq = new int[N];
		int[] recordArr = new int[N];
		seq[0] = lines[0][0];
		recordArr[0] = 1;
		int length = 1;
		
		for (int i = 1; i < N; i++) {
			int tmp = lines[i][0];
			if (seq[length - 1] < tmp) {
				length++;
				seq[length-1] = tmp;
				recordArr[i] = length;
			}else {
				int left = 0;
				int right = length - 1;
				while(left<=right) {
					int mid = (left + right)/2;
					if (seq[mid] < tmp)
						left = mid + 1;
					else
						right = mid - 1;
				}
				seq[left] = tmp;
				recordArr[i] = left + 1;
			}
		}
		
		System.out.println(N - length);
		StringBuilder sb = new StringBuilder();
		int finder = length;
		int[] arr = new int[N - length];
		int idx = 0;
		for (int i = N - 1; i >=0; i-- ) {
			if (recordArr[i] == finder) {
				finder--;
			}else
				arr[idx++] = lines[i][0];
		}
		
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb);
	}
}
