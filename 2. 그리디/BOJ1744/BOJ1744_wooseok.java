package codingTest;
/*
*  핵심
1. 묶는 수는 두 개의 수를 곱한 값입니다.

2. 묶는 수는 한 번만 적용이 가능합니다.

3. -1000 ≤ N개의 수 ≤ 1000의 범위를 가집니다.

4. 묶는 수를 이용하여 N개의 합의 최대값을 결과로 출력합니다.
* */

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ1744_wooseok {
    static int N;
    // 양수 -> 내림차순 정렬
    static PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());
    // 음수 -> 오름차순 정렬
    static PriorityQueue<Integer> minus = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // N개의 입력을 양수와 음수로 나누어 우선순위 큐에 저장
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                plus.add(num);
            } else {
                minus.add(num);
            }
        }

        int answer = 0;

        // 음수 계산 (음수는 음수끼리 곱해야 양수가 나옴)
        while (minus.size() > 1) {
            int first = minus.poll();
            int second = minus.poll();
            answer += first * second;
        }
        // 음수가 하나 남아있다면 더하기
        if (!minus.isEmpty()) {
            answer += minus.poll();
        }

        // 양수 계산 (양수는 양수끼리 곱해서 더 큰 값을 얻을 수 있음)
        while (plus.size() > 1) {
            int first = plus.poll();
            int second = plus.poll();
            // 1은 곱하는 것보다 더하는 것이 이득이므로 예외 처리
            if (first == 1 || second == 1) {
                answer += first + second;
            } else {
                answer += first * second;
            }
        }
        // 양수가 하나 남아있다면 더하기
        if (!plus.isEmpty()) {
            answer += plus.poll();
        }

        // 결과 출력
        System.out.println(answer);
    }
}
