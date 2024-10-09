import java.util.*;
import java.io.*;
//날짜 24.09.19
//설계 시간: 10분
//구현 시간: 15분
//메모리: 14308 kb
//시간: 104 ms
public class Code14501_2 {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N;
        int[] p, t, dp;
//풀이 1. dp
        N = Integer.parseInt(br.readLine());
        p = new int[N];
        t = new int[N];
        dp = new int[N+1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        //dp[i]는 i번째 날을 선택했을 때 최대 수익
        //i번째 날을 선택하거나 선택하지 않는 경우가 존재
        //i번째 날까지 얻을 수 있는 최대 이익은 max(t[i] 일 후 최대 수익 + i번째 날을 선택했을 때 수익, i+1번쩨 날까지의 최대 수익)
        for(int i = N-1; i > -1; i--){
            //i번째 날짜를 선택할 수 없는경우
            //1일은 당일에 끝낼 수 있으므로 조건 주의
            if(i+t[i] > N) {
                dp[i] = dp[i+1];
            }else{
                //i번째 날을 선택한 것과 선택하지 않은 것 중 최댓값
                dp[i] = Math.max(dp[i+1],dp[i+t[i]] + p[i]);
            }
        }

//        for(int i : dp) System.out.print(i+" ");
//        System.out.println();
        bw.write(dp[0]+"");
        bw.close();
    }
}
//풀이 2. 조합
//i+t[i], sum+p[i]
