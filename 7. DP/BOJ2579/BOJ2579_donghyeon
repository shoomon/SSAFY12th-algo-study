package solu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // 입력이 1개 이하일 때 예외 처리
        if (t == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
            return;
        }

        int point[] = new int[t + 1];
        for (int i = 1; i <= t; i++) {
            point[i] = Integer.parseInt(br.readLine());
        }

        int dp[] = new int[t + 1];

        dp[1] = point[1];
        if (t >= 2) {
            dp[2] = point[2] + point[1];
        }

        for (int i = 3; i <= t; i++) {
            dp[i] = Math.max(dp[i - 3] + point[i] + point[i - 1], dp[i - 2] + point[i]);
        }

        System.out.println(dp[t]);
    }
}
