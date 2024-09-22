package recursion;

import java.io.*;
import java.util.*;

public class BOJ_14501_ver2 {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] schedule = new int[N][2];  // 상담 기간과 비용을 저장하는 배열
        dp = new int[N + 1];               // 해당 날짜까지의 최대 수익을 기록하는 배열

        // 상담 기간과 비용 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());  // 상담 기간
            schedule[i][1] = Integer.parseInt(st.nextToken());  // 상담 비용
        }

        // DP 배열 업데이트
        for (int i = 0; i < N; i++) {
            // 현재 날짜까지의 최대 수익을 다음 날짜로 이어감
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // 상담을 진행할 수 있는 경우
            if (i + schedule[i][0] <= N) {
                dp[i + schedule[i][0]] = Math.max(dp[i + schedule[i][0]], dp[i] + schedule[i][1]);
            }
        }

        // N일까지의 최대 수익 출력
        System.out.println(dp[N]);
    }
}
