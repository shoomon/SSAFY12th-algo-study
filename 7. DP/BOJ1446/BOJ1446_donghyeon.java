import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main6 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        int dp[] = new int[d+1];
        //일단 초기화
        for(int i = 1; i <= d; i++) {
        	dp[i] = i;
        }
        
        //더 나은 방법을 받아들이기 
        for(int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	int dis = Integer.parseInt(st.nextToken());
        	
        	for(int j = 1; j <= d; j++) {
        		if(end > n)continue;
        		dp[j] = Math.min(dp[j], Math.min(dp[j-(end-start)] + dis, dp[j-(end-start)]+(end-start)));
        	}
        	 
        }
        
        System.out.println(dp[d]);
	}
}
내가 푼 것 위
