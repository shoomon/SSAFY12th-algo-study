package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715_wooseok {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 카드 수를 담기 위한 최소 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 카드의 개수를 입력받아 우선순위 큐에 추가
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            pq.offer(num);
        }

        int totalCost = 0;

        // 큐가 비어있지 않을 때까지 반복
        while (pq.size() > 1) {
            // 가장 작은 두 개의 카드를 꺼내서 합산
            int a = pq.poll();
            int b = pq.poll();
            int cost = a + b;

            // 합산한 값을 총 비용에 추가
            totalCost += cost;

            // 다시 큐에 추가
            pq.offer(cost);
        }

        // 최종 비용 출력
        System.out.println(totalCost);
    }
}
