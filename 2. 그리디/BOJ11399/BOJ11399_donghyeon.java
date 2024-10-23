import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		int n = Integer.parseInt(br.readLine());
		int arr[] = new int[n];
		int sum[] = new int[n];//누적합 배열
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i =0; i< n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		//정렬 후 누적합이 답임
		sum[0] = arr[0];
		for(int i = 1; i < arr.length; i++) {
			
//			for(int j = 0; j <= i; j++) {
				sum[i] = sum[i-1] + arr[i];
						
//			}
		}
		int ans = 0;
		for(int i = 0; i < sum.length; i++) {
			ans += sum[i];
		}
		System.out.println(ans);
	}
}
