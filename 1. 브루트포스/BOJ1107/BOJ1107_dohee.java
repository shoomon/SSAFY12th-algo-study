package bruteforce;

import java.io.*;
import java.util.*;

public class BOJ_1107_ver2 {
    static int N, M;
    static boolean[] broken;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        broken = new boolean[10];

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int brokenButton = Integer.parseInt(st.nextToken());
                broken[brokenButton] = true;
            }
        }

        // 현재 채널이 100번인 경우, +, -만으로 이동하는 횟수 계산
        int minPresses = Math.abs(N - 100);

        // 모든 채널을 탐색하여, 가장 가까운 채널을 찾음
        for (int i = 0; i <= 999999; i++) {
            int length = possible(i);
            if (length > 0) {
                // i 채널로부터 N 채널로 이동할 때 필요한 +, - 횟수 포함
                int presses = Math.abs(N - i) + length;
                minPresses = Math.min(minPresses, presses);
            }
        }

        System.out.println(minPresses);
    }

    // 숫자를 누를 수 있는지 확인하는 함수
    static int possible(int num) {
        if (num == 0) {
            return broken[0] ? 0 : 1;
        }

        int length = 0;
        while (num > 0) {
            if (broken[num % 10]) {
                return 0; // 고장난 버튼이면 불가능
            }
            length++;
            num /= 10;
        }
        return length;
    }
}
