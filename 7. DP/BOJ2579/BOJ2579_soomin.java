import java.util.*;
import java.io.*;
//날짜 24.10.02
//설계 시간: 분
//구현 시간: 분
//메모리: 14244 kb
//시간: 100 ms
public class Code2579 {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] stair = new int[N+1];
        int[] dp = new int[N+1]; //dp는 i번째 계단을 밟았을 때 최댓값

        for(int i = 1; i <= N; i++){
            stair[i] = Integer.parseInt(br.readLine());
        }

        //1번 계단을 올라오는 경우는 1칸을 올라오는 경우밖에 없음.
        dp[1] = stair[1];
        //2번 계단을 올라오는 경우는 1번 계단에서 한 칸 올라오거나 0번에서 2칸 올라오는 경우
        //이 중 최대는 1번계단을 오르고 한 번 더 오르는 경우
        if(N > 1) dp[2] = dp[1]+stair[2];

        for(int i = 3; i < N+1; i++){
            //i번째 계단을 올라오는 경우는 1칸을 올라오거나 2칸을 올라오는 경우
            //1칸을 올라오려면 이전에 2칸을 올라왔어야 한다.
            //i-2칸과 i-1칸까지의 최댓 값 중 선택
            dp[i] = Math.max(dp[i-2], dp[i-3]+stair[i-1]) + stair[i];
        }
        bw.write(dp[N]+"\n");
        bw.close();
    }
}
