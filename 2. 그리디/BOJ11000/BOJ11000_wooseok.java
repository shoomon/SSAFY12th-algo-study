package codingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000_wooseok {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] lectures = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i][0] = Integer.parseInt(st.nextToken());  //시작 시간
            lectures[i][1] = Integer.parseInt(st.nextToken());  //종료 시간
        }

        // 시작 시간 기준으로 오름차순.. 만약 시작시간이 같을경우 종료시간 기준 오름차순
        Arrays.sort(lectures, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];

            return a[0] - b[0];
        });

        // 우선순위 큐를 종료 시간을 기준으로 오름차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 첫 번째 강의의 종료 시간을 큐에 넣음
        pq.add(lectures[0][1]);

        // 나머지 강의 처리
        for (int i = 1; i < N; i++) {
            // 큐에서 가장 먼저 끝나는 강의의 종료 시간과 비교
            if (pq.peek() <= lectures[i][0]) {
                // 강의실을 재사용 가능하므로 종료 시간을 업데이트
                pq.poll();
            }
            // 새로운 강의의 종료 시간을 추가
            pq.add(lectures[i][1]);
        }

        // 필요한 강의실의 최소 개수는 큐의 크기
        System.out.println(pq.size());
    }
}
