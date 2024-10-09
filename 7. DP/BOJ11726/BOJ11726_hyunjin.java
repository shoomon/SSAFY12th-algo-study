package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BOJ11726_hyunjin {
    public static void main(String[] args) throws IOException {
        // 입력 최적화를 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        // N이 1일 경우 예외 처리
        if (N == 1) {
            System.out.println(1);
            return;
        }

        int[] dp = new int[N + 1];

        dp[1] = 1;
        dp[2] = 2;

        // 점화식을 이용하여 DP 테이블 채우기
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        // 결과 출력
        System.out.println(dp[N]);
    }
}