import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int arr[];
	static int correct[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//범위가 매우 크므로 이분탐색 일듯하다
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			int idx = Integer.parseInt(st.nextToken());
			sb.append(binary(arr, n, idx)).append('\n');
		}
		System.out.println(sb);
		
	}
	
	static int binary(int arr[], int n, int idx) {
		int left = 0;
		int right = n-1;
		int mid = 0;
		
		while(left <= right) {
			mid = (left+right) /2;
			if(arr[mid] == idx) {
				return 1;
			}
			if(arr[mid] < idx) {
				left = mid+1;
			}else {
				//찾고자 하는게 mid보다 작으면
				right = mid -1;
			}
		}
		return 0;
	}
}
