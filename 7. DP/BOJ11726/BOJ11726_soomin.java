import java.util.*;
import java.io.*;
//날짜 24.09.25
//설계 시간: 5분
//구현 시간: 5분
//메모리: kb
//시간: ms
public class Main {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N;
        int[] dp;
        //dp[i]는 길이일 때 채우는 방법 수
        //길이 2를 채우는 방법 -> 2*1블록 두 개 or 1*2블록 한 개
        //길이 1을 채우는 방법 -> 2*1블록 한 개
        //2*1블록 두 개를 쓰는 경우는 길이 1을 채우는 경우에 중복됨
        //dp[i] = dp[i-2]+dp[i-1]

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        dp[1] = 1;
        //n=1인 경우 고려해야함
        if(N > 1) dp[2] = 2;

        for(int i = 3; i < N+1; i++){
            dp[i] = (dp[i-2]%10007+dp[i-1]%10007)%10007;
        }

       bw.write(dp[N]+"\n");
        bw.close();
    }
}
