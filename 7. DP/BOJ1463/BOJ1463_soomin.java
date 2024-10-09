import java.util.*;
import java.io.*;
//날짜 24.09.25
//설계 시간: 12분
//구현 시간: 12분
//메모리: 18232 kb
//시간: 136 ms
public class Main {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N;
        int[] dp;
        //dp[i]는 i를 1로 만들기위한 최소 연산 횟수
        //(i%3 == 0 && i%2 == 0)-> dp[i] = Math.min(dp[i%3], dp[i%2])+1
        //((i-1)%3 == 0 && (i-1)%2 == 0)-> dp[i] = Math.min(dp[i%3], dp[i%2])+2
        //dp[1] = 0 dp[2] = 1

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
//dp[10] = dp[5]+1 = dp[2]+2+1 = 1+2+1
        // = dp[9]+1 = dp[3]+1+1 = 1+1+1
        //상향식 또는 하향식 -> dp[i*3] = Math.min(dp[i*3],dp[i]+1) or dp[i] = Math.min(dp[i],dp[i/3])
//        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = dp[1] = 0;

        for(int i = 2; i < N+1; i++){

            dp[i] = dp[i-1]+1;

           if(i%3 == 0){
                dp[i] = Math.min(dp[i],dp[i/3]+1);
            }
           if(i%2 == 0){
                dp[i] = Math.min(dp[i],dp[i/2]+1);
            }
//            dp[i] = dp[i-1]+1;를 하지 않고 아래와 같은 조건을 추가하면 i-1이 2와 3 모두 나누어떨어지지 않는 경우를 처리할 수 없음.
//            아래 if문 조건 자체가 필요 없는 로직임
//            if((i-1)%3 == 0 || (i-1)%2 == 0){
//                dp[i] = Math.min(dp[i],dp[i-1]+1);
//            }

        }

        bw.write(dp[N]+"\n");
        bw.close();
    }
}
