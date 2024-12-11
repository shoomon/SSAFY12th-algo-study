import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int arr [] = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken()); //총감독관 감시 가능 수
		int c = Integer.parseInt(st.nextToken()); //부감독관 감시 가능 수
		
		long ans = 0; //답

		for(int i = 0; i < n; i++) {
			ans++;
			int cnt = arr[i] - b;	
			
			//대표감독관이 한 명 들어가도 남으면
			//1과 1인경우, 나머지와 구분 
			if(cnt > 0) {
				//각 반 남는 감독 필요수가 0일때까지 
				ans += (cnt + c -1) /c;
			}
		}
		System.out.println(ans);
	}
}
